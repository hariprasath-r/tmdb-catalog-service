package in.hp.boot.moviecatalogservice.exceptions;

import lombok.ToString;

@ToString
public class GenericException {
    private String timestamp;
    private String message;
    private String details;

    public GenericException() {
    }

    public GenericException(String timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
