package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderSearch;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * xToOne (ManyToOne, OneToOne) : 성능 최적화
 * Order 조회
 * Order -> Member
 * Order -> Delivery
 */

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    /**
     * 주문 조회 V1 : entity 노출 케이스
     * - entity 리턴하는 경우 문제가 발생함
     * - 양방향 연관관계 무한 루프 빠짐 -> @JsonIgnore
     * - Hibernate5Module 모듈 등록, LAZY=null 처리
     * @return List<Order>
     */
    @GetMapping("/api/v1/simple-orders")
    public List<Order> orderV1() {
        List<Order> all = orderRepository.findAllByCriteria(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();  // Lazy 강제 초기화
            order.getDelivery().getAddress();  // Lazy 강제 초기화
        }
        return all;
    }

    /**
     * 주문 조회 V2 : N + 1 문제 발생 케이스
     *
     * - 엔티티를 조회해서 DTO로 변환
     * - fetch join 사용X
     * - 단점: 지연로딩으로 쿼리 N번 호출
     *   ㄴ order 1회 조회 + order -> member 지연로딩 N번 + order -> delivery 지연로딩 N번
     *   ㄴ order 의 조회 갯수 만큼 지연로딩 조회 쿼리 N*2 추가 됨
     */
    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAllByCriteria(new OrderSearch());
        return orders.stream()
                .map(SimpleOrderDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 주문 조회 V3 : fetch join (성능 최적화)
     * - fetch join 사용
     */
    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        return  orders.stream()
                .map(SimpleOrderDto::new )
                .collect(Collectors.toList());
    }

    /**
     * 주문 조회 V4 : JPA DTO 바로 조회
     * - fetch join 사용
     * - JPA 에서 DTO 바로 조회
     * - select 절에서 원하는 데이터만 선택해서 조회
     */
    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4() {
        return orderRepository.findOrderDtos();
    }

    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();  // LAZY  초기화
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();  // LAZY  초기화
        }
    }
}
