package jpabook.jpashop.exception;

/**
 * NotEnoughStockException
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */
public class NotEnoughStockException extends RuntimeException {
    public NotEnoughStockException() {
        super();
    }

    public NotEnoughStockException(String message) {
        super(message);
    }

    public NotEnoughStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughStockException(Throwable cause) {
        super(cause);
    }
}
