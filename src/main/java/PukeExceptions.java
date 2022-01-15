public class PukeExceptions extends Exception {

    //Pass on the error message
    public PukeExceptions(String e) {
        super(e);
    }

    public PukeExceptions(String e, Throwable throwE) {
        super(e, throwE);
    }

}
