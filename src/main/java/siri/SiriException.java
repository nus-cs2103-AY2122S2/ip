package siri;

class SiriException extends RuntimeException {
    public SiriException(String errMsg) {
        super(errMsg);
    }
}