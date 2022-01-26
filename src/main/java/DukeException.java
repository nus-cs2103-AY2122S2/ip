public class DukeException extends Exception {
    /**
     * The class {@code DukeException} and its subclasses are a form
     * of {@code Throwable} subclassed from {@code Exception}
     * that indicates conditions that the Duke application might
     * want to catch.
     *
     * <p>The class {@code DukeException} and any subclasses that are not
     * also subclasses of {@link RuntimeException} are <em>checked
     * exceptions</em>.  Checked exceptions need to be declared in a
     * method or constructor's {@code throws} clause if they can be thrown
     * by the execution of the method or constructor and propagate outside
     * the method or constructor boundary.
     *
     * @author  Ryan Aidan
     */
    public DukeException(String err) {
        super("\n############################################################\n ☹ OOPS!!! "
                + err
                + "\n############################################################");
    }
}
