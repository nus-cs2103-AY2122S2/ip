import java.security.cert.Extension;

public class TesseractException extends Exception {
    protected String errMsg;

    TesseractException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }

    String getErrMsg() {
        return errMsg;
    }
}
