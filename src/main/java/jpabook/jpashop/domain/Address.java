package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

/**
 * Address
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */

@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

}
