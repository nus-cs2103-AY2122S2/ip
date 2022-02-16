package walle;

/**
 * Exception to be thrown if descriptor is empty
 */
class EmptyDescriptorException extends Exception {
    public EmptyDescriptorException() {
        super(" OOPS!!! The description of a task cannot be empty.");
    }
}
