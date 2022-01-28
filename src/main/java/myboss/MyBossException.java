package myboss;

/**
 * Represents an Exception from the MyBoss program. A MyBossException Object corresponds
 * to an exception thrown by the MyBoss Object.
 */
public class MyBossException extends Exception {

    public MyBossException(String errorMessage) {
        super(errorMessage);
    }
}
