package codebytes;


public class AppResponse {
    public enum Code {
        SUCCESS,
        ERROR
    }

    private Code code;
    private String message;
    private Object data;

    public AppResponse(Code code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public AppResponse() {
    }

    public Code getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
