package Alfred.Exceptions;

public class InvalidDateTimeException extends AlfredException {
  static String ERROR_MESSAGE =
      "Invalid date time format, sir. Check that it follows ISO local format, of <YYYY>-<MM>-<DD>T<HH>:<MINMIN>:<SS>";

  public InvalidDateTimeException() {
    super(InvalidDateTimeException.ERROR_MESSAGE);
  }
}