package duke.testutil;

/**
 * Extends the Consumer functional interface to allow exceptions to be passed through.
 * @param <T> Type accepted by the consumer as an argument.
 * @param <E> Exception that may be thrown by the consumer.
 */
@FunctionalInterface
public interface ThrowableConsumer<T, E extends Exception> {
    void accept(T t) throws E;
}
