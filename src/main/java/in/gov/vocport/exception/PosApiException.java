package in.gov.vocport.exception;

public class PosApiException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String errorCode;

    public PosApiException(String message) {
        super(message);
        this.errorCode = null;
    }

    public PosApiException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}

