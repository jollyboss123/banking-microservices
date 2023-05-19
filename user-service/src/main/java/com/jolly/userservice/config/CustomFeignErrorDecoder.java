package com.jolly.userservice.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jolly.userservice.exception.SimpleBankingGlobalException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

/**
 * @author jolly
 */
@Slf4j
public class CustomFeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        SimpleBankingGlobalException exception = extractBankingCoreGlobalException(response);

        switch (response.status()) {
            case 400 -> {
                log.error("Error in request went through the feign client {}", exception.getMessage() +
                        " - " + exception.getCode());
                return exception;
            }
            case 401 -> {
                log.error("Unauthorised request throguh feign");
                return new Exception("Unauthorised request through feign");
            }
            case 404 -> {
                log.error("Unidentified request through feign");
                return new Exception("Unidentified request through feign");
            }
            default -> {
                log.error("Error in request went through feign client");
                return new Exception("Common feign exception");
            }
        }
    }

    private SimpleBankingGlobalException extractBankingCoreGlobalException(Response response) {
        SimpleBankingGlobalException exception = null;
        Reader reader = null;

        // capturing error message from response body
        try {
            reader = response.body().asReader(StandardCharsets.UTF_8);
            String result = IOUtils.toString(reader);
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            exception = mapper.readValue(result,
                    SimpleBankingGlobalException.class);
        } catch (IOException e) {
            log.error("IO exception on reading exception message from feign client" + e);
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                log.error("IO exception on reading exception message from feign client" + e);
            }
        }

        return exception;
    }
}
