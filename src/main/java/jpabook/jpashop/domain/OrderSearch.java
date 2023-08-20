package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * OrderSearch
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */

@Getter
@Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;
}
