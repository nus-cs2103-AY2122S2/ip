package juke.command;

import juke.exception.JukeException;

/**
 * Abstraction for the result of the execution of a command.
 *
 * @param <T> Only used for inner classes.
 */
public abstract class Result<T> {
    /**
     * Returns the stored value.
     *
     * @return Stored value.
     */
    public abstract T get();
    
    /**
     * Returns the stored value, or throws an exception.
     *
     * @return Stored value.
     * @throws Exception Exception.
     */
    public abstract String getOrThrow() throws Exception;
    
    /**
     * A successful execution of a command.
     */
    public static class Success extends Result<String> {
        private String message;
        
        public Success(String message) {
            this.message = message;
        }
    
        /**
         * Returns the stored value.
         *
         * @return Stored value.
         */
        @Override
        public String get() {
            return this.message;
        }
    
        /**
         * Returns the stored value.
         *
         * @return Stored value.
         */
        @Override
        public String getOrThrow() {
            return this.message;
        }
    }
    
    /**
     * A command execution with an error.
     */
    public static class Error extends Result<Exception> {
        private Exception exception;
        
        public Error(Exception exception) {
            this.exception = exception;
        }
    
        /**
         * Returns the exeception.
         *
         * @return Exception.
         */
        @Override
        public Exception get() {
            return this.exception;
        }
    
        /**
         * Throws the exception.
         *
         * @return Does not return.
         * @throws Exception Exception.
         */
        @Override
        public String getOrThrow() throws Exception {
            throw this.exception;
        }
    }
    
    /**
     * A result before command execution.
     */
    public static class Empty extends Result<Object> {
        private static final Object NULL_OBJECT = null;
    
        /**
         * Returns a static instance of a null object.
         *
         * @return Null.
         */
        @Override
        public Object get() {
            return NULL_OBJECT;
        }
    
        /**
         * Throws an exception for an empty result.
         *
         * @return Does not return.
         * @throws Exception Empty result exception.
         */
        @Override
        public String getOrThrow() throws Exception {
            throw new JukeException("Result is empty");
        }
    }
}
