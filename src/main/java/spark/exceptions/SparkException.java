package spark.exceptions;

public class SparkException extends Exception {
    public SparkException(String errorMessage) {
        super(String.format("OOPS!!!\n%s",errorMessage));
    }
}
