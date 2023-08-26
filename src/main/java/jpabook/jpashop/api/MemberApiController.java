package jpabook.jpashop.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * MemberApiController
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    // (좋지 않은 예시) 엔티티 파라미터로 받으면 X, entity 변경 시 api 스펙이 변경되기 때문에 아래와 같이 사용하면 안됨
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    // (좋은 예시) request 별도 구현 시 필요한 API 필요 필드만 작성 가능, 엔티티 변경 되어도 API 스펙 변경되지 않음. Valid 세팅하면 정확하게 써서 유지보수에 좋음
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }



    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id,
                                               @RequestBody @Valid UpdateMemberRequest request) {
        memberService.update(id, request.getName());

        // command, 조회 쿼리 분리 정책에 의해 재 조회 (개발 스타일에 따라 선택 가능하나, 변경과 조회 쿼리를 명확하게 분리하는 경우 아래와 같이 작성
        // (단점) 조회를 한번 더 하기는 하지만, 트래픽이 너무 많은 경우가 아니면 성능상 문제 되지 않음
        // (장점) 유지보수성이 높아짐
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }

    @Data
    static class CreateMemberRequest {
        @NotEmpty
        private String name;
    }

    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    static class UpdateMemberRequest {
        @NotEmpty
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;
    }
}
