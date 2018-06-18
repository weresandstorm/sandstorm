package io.sandstorm.rest.common;

import io.sandstorm.common.ApplicationException;
import io.sandstorm.common.CaseCode;
import io.sandstorm.common.ResultCase;
import io.sandstorm.common.ViolateBizConstraintException;
import java.util.stream.Collectors;
import org.everit.json.schema.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler({ViolateBizConstraintException.class})
    public final ResponseEntity<Object> handleViolateBizConstraintException(ApplicationException ex,
                                                                            HttpServletRequest request,
                                                                            WebRequest webReq) {
        logThrowable(ex, request);
        HttpHeaders headers = new HttpHeaders();
        RestApiResult body = RestApiResult.failure(ex.resultCase());
        return handleExceptionInternal(ex, body, headers, HttpStatus.OK, webReq);
    }

    @ExceptionHandler({MultipartException.class})
    public final ResponseEntity<Object> handleFileUploadException(MultipartException ex,
                                                                  HttpServletRequest request,
                                                                  WebRequest webReq) {
        logThrowable(ex, request);
        HttpHeaders headers = new HttpHeaders();
        RestApiResult body = RestApiResult.failure(new ResultCase(CaseCode.illegal_api_input, ex.getMessage()));
        return handleExceptionInternal(ex, body, headers, HttpStatus.UNPROCESSABLE_ENTITY, webReq);
    }

    @ExceptionHandler({
            ApplicationException.class
    })
    public final ResponseEntity<Object> handleApplicationException(
            ApplicationException ex,
            HttpServletRequest request,
            WebRequest webReq) {

        logThrowable(ex, request);

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        RestApiResult body = RestApiResult.failure(ex.resultCase());
        return handleExceptionInternal(ex, body, headers, status, webReq);
    }

    //handle exception of json schema validation
    @ExceptionHandler({ValidationException.class})
    public final ResponseEntity<Object> handleValidationException(
        ValidationException ex,
        HttpServletRequest request,
        WebRequest webReq) {
        logThrowable(ex, request);

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StringBuilder messageBuilder = new StringBuilder("");
        ex.getCausingExceptions().stream()
            .map(ValidationException::getMessage)
            .forEach(msg -> messageBuilder.append(msg));
        RestApiResult body = RestApiResult.failure(new ResultCase(CaseCode.illegal_api_input, messageBuilder.toString()));

        return handleExceptionInternal(ex, body, headers, status, webReq);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleOthers(Throwable ex,
                                               HttpServletRequest request,
                                               WebRequest weReq) {
        logThrowable(ex, request);

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(null, null, headers, status, weReq);
    }

    private void logThrowable(Throwable ex, HttpServletRequest request) {
        logger.error(String.format(
                "Exception occurred while processing request: %s %s",
                request.getMethod(), request.getRequestURI()), ex);
    }

}
