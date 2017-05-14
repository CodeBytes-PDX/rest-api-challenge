package codebytes;


/**
 * Payload wrapper for decorating responses with a status and message.
 */
public class ResponsePayload {
    public enum Status {
        SUCCESS,
        ERROR
    }

    private Status status;
    private String message;
    private Object data;

    public ResponsePayload(Status status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponsePayload() {
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
