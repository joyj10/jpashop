package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

/**
 * BookForm
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */

@Getter @Setter
public class BookForm {
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;
}
