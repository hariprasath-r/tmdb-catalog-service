package in.hp.boot.moviecatalogservice.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        if (isResponseStatus4xxOr5xx(response))
            return true;
        else
            return false;
    }

    private boolean isResponseStatus4xxOr5xx(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (isResponseStatus4xxOr5xx(response)) {
            String responseBodyString = response.getBody().toString();
            ObjectMapper mapper = new ObjectMapper();
//            GenericException genericException = mapper.readValue(responseBodyString, GenericException.class);
            throw new RestTemplateResponseException(response.getStatusCode(), responseBodyString,
                    responseBodyString);
        }
    }
}
