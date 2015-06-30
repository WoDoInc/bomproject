package com.java.error;

/**
 * This exception is thrown when the requested entry is not found.
 */
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2991739233809030272L;

	public NotFoundException(String id) {
        super(String.format("No entry found with id: <%s>", id));
    }
}
