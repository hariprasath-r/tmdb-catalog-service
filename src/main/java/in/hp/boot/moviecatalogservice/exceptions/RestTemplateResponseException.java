package in.hp.boot.moviecatalogservice.exceptions;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RestTemplateResponseException extends RuntimeException {

    private HttpStatus statusCode;
    private String errorMessage;
    private String body;

    public RestTemplateResponseException(HttpStatus statusCode, String message, String body) {
        super(message);
        this.statusCode = statusCode;
        this.errorMessage = message;
        this.body = body;
    }
}
