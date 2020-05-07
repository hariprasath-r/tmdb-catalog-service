package in.hp.boot.moviecatalogservice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RestTemplateResponseException extends RuntimeException {

    private final HttpStatus statusCode;
    private final String errorMessage;
    private final String body;

    public RestTemplateResponseException(HttpStatus statusCode, String message, String body) {
        super(message);
        this.statusCode = statusCode;
        this.errorMessage = message;
        this.body = body;
    }
}
