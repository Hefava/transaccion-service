package com.bootcamp.transaccion_service.infrastructure.exceptionhandler;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import org.apache.commons.io.IOUtils;

import static com.bootcamp.transaccion_service.domain.utils.SuministroConstants.*;

public class FeignErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new ErrorDecoder.Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        String errorMessage = getErrorMessage(response);

        switch (response.status()) {
            case 400:
                return new ResponseStatusException(HttpStatus.BAD_REQUEST, ERROR_EN_SOLICITUD + errorMessage);
            case 404:
                return new ResponseStatusException(HttpStatus.NOT_FOUND, RECURSO_NO_ENCONTRADO + errorMessage);
            case 500:
                return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_INTERNO_SERVIDOR + errorMessage);
            default:
                return defaultErrorDecoder.decode(methodKey, response);
        }
    }

    private String getErrorMessage(Response response) {
        try {
            if (response.body() != null) {
                return IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            return ERROR_DESCONOCIDO;
        }
        return Optional.ofNullable(response.reason()).orElse(ERROR_DESCONOCIDO);
    }
}