package duke.testutil;

@FunctionalInterface
public interface ThrowableConsumer<T, E extends Exception> {
    void accept(T t) throws E;
}
