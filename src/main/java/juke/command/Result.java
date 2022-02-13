package juke.command;

import juke.exception.JukeException;

/**
 * Abstraction for the result of the execution of a command.
 */
public abstract class Result {
    /**
     * Messages to store.
     */
    private String[] messages;

    /**
     * Internal constructor to initialize a result.
     *
     * @param messages Messages to store.
     */
    private Result(String... messages) {
        this.messages = messages;
    }

    /**
     * Creates a new success result.
     *
     * @param messages Messages.
     * @return Success result.
     */
    public static Result success(String... messages) {
        return new Result.Success(messages);
    }

    /**
     * Creates a new error result.
     *
     * @param exception Exception to throw.
     * @return Error result.
     */
    public static Result error(Exception exception) {
        return new Result.Error(exception);
    }

    /**
     * Creates a new empty result.
     *
     * @return Empty result.
     */
    public static Result empty() {
        return Empty.EMPTY_INSTANCE;
    }

    /**
     * Returns the stored string.
     *
     * @return Stored string.
     */
    public String[] get() {
        return this.messages;
    }

    /**
     * Returns the stored string, or throws an exception.
     *
     * @return Stored string.
     * @throws Exception Exception.
     */
    public String[] getOrThrow() throws Exception {
        return this.messages;
    }

    /**
     * A successful execution of a command.
     */
    public static class Success extends Result {
        public Success(String... messages) {
            super(messages);
        }
    }

    /**
     * A command execution with an error.
     */
    public static class Error extends Result {
        /**
         * Exception in the error.
         */
        private Exception exception;

        /**
         * Constructor to initialize an error result with an exception.
         *
         * @param exception Exception.
         */
        private Error(Exception exception) {
            super(exception.getMessage());
            this.exception = exception;
        }

        /**
         * Throws the exception.
         *
         * @return Does not return.
         * @throws Exception Exception.
         */
        @Override
        public String[] getOrThrow() throws Exception {
            throw this.exception;
        }
    }

    /**
     * A result before command execution.
     */
    public static class Empty extends Result {
        /**
         * Singleton instance of empty.
         */
        private static final Result EMPTY_INSTANCE = new Empty();

        /**
         * Singleton exception of an empty result.
         */
        private static final JukeException EMPTY_EXCEPTION = new JukeException("Result is empty.");

        /**
         * Constructor to initialize an empty result.
         */
        private Empty() {
            super((String[]) null);
        }

        /**
         * Throws an exception for an empty result.
         *
         * @return Does not return.
         * @throws Exception Empty result exception.
         */
        @Override
        public String[] getOrThrow() throws Exception {
            throw EMPTY_EXCEPTION;
        }
    }
}
