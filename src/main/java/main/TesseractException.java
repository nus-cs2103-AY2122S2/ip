package main;

import java.security.cert.Extension;

/**
 * Represent exceptions specific to Tesseract.
 * @author Fan Jue
 * @version 0.1.0
 * @since 0.1.0
 */
public class TesseractException extends Exception {
    protected String errMsg;

    /**
     * Create a new exception.
     *
     * @param errMsg A string of specific error incurred.
     */
    public TesseractException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }

    /**
     * Return a string representing the error.
     *
     * @return A string representation of the error.
     */
    public String getErrMsg() {
        return errMsg;
    }
}
