public class DukeException extends Exception {
    protected ExceptionType eType;
    protected String lineBreak = "____________________________________________________________\n";

    public DukeException(ExceptionType eType) {
        this.eType = eType;
    }

    public void EmptyInputException() {
        if (this.eType == ExceptionType.EMPTYINPUT) {
            System.out.print(lineBreak + "Meow! Enter a valid command!\n" + lineBreak);
        }
    }

    public void EmptyDescriptionException() {
        if (this.eType == ExceptionType.EMPTYDESC) {
            System.out.print(lineBreak + "Meow! A task needs a description!\n" + lineBreak);
        }
    }

    public void InvalidDateException() {
        if (this.eType == ExceptionType.INVALIDDATE) {
            System.out.print(lineBreak + "Meow! A date is required!\n" + lineBreak);
        }
    }
}