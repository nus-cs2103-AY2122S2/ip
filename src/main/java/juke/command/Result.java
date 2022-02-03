package juke.command;

import juke.exception.JukeException;

/**
 * Abstraction for the result of the execution of a command.
 */
public abstract class Result {
    private String[] messages;
    
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
        private Exception exception;
        
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
        private static final Result EMPTY_INSTANCE = new Empty();
        
        private static final JukeException EMPTY_EXCEPTION = new JukeException("Result is empty.");
    
        private Empty() {
            super(null);
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
