public class IncompleteInputException extends AlfredException {
    static String ERROR_MESSAGE = "Missing argument! /by or /at argument missing for deadline or event.\nProper formatting: deadline <task> /by <date>, analogous for event.";
    IncompleteInputException() {
        super(IncompleteInputException.ERROR_MESSAGE);
    }
}
