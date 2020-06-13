package org.elypia.retropia.core.exceptions;

/**
 * <p>
 *   Thrown when the unknown, default enum or otherwise enum constant
 *   that denotes the value returned from the API isn't known to the
 *   wrapper is passed to a request method.
 * </p>
 * <p>
 *   These may be returned when an API returns a enum constant that the
 *   wrapper isn't aware of. It should never be used in API requests.
 * </p>
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 1.3.0
 */
public class InvalidEnumException extends RuntimeException {

    private final Enum<?> enumValue;

    /**
     * @param enumValue The enum constant which is causing the problem.
     */
    public InvalidEnumException(Enum<?> enumValue) {
        this(enumValue, "Invalid use of " + enumValue + " constant.");
    }

    /**
     * @param enumValue The enum constant which is causing the problem.
     * @param message An explanation of the exception.
     */
    public InvalidEnumException(Enum<?> enumValue, String message) {
        super(message);
        this.enumValue = enumValue;
    }

    public Enum<?> getEnumValue() {
        return enumValue;
    }
}
