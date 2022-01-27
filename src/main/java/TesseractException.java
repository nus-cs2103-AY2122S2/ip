package tesseract.main;

import java.security.cert.Extension;

public class TesseractException extends Exception {
    protected String errMsg;

    public TesseractException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
