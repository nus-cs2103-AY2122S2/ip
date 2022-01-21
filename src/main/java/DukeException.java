public class DukeException extends Exception{
    public DukeException(String message) {
        super(message);
    }

    public void exceptionResponse() {
        String divString = "    ---------------------------------------------";
        String strPadding = "      ";

        System.out.println(divString);
        System.out.println(strPadding + this);
        System.out.println(divString);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
