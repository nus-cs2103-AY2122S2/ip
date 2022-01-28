package juke.command;

import juke.exception.JukeException;

public abstract class Result<T> {
    public abstract T get();
    
    public abstract String getOrThrow() throws Exception;
    
    public static class Success extends Result<String> {
        private String message;
        
        public Success(String message) {
            this.message = message;
        }
    
        @Override
        public String get() {
            return this.message;
        }
    
        @Override
        public String getOrThrow() {
            return this.message;
        }
    }
    
    public static class Error extends Result<Exception> {
        private Exception exception;
        
        public Error(Exception exception) {
            this.exception = exception;
        }
    
        @Override
        public Exception get() {
            return this.exception;
        }
    
        @Override
        public String getOrThrow() throws Exception {
            throw this.exception;
        }
    }
    
    public static class Empty extends Result<Object> {
        private static final Object NULL_OBJECT = null;
        
        @Override
        public Object get() {
            return NULL_OBJECT;
        }
    
        @Override
        public String getOrThrow() throws Exception {
            throw new JukeException("Result is empty");
        }
    }
}
