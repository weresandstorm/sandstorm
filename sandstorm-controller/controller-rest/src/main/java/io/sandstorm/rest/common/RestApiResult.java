package io.sandstorm.rest.common;

import com.fasterxml.jackson.annotation.JsonView;
import io.sandstorm.common.presentation.Views;
import io.sandstorm.common.ResultCase;

public class RestApiResult<T> {

    @JsonView(Views.Rest.class)
    private String message;

    @JsonView(Views.Rest.class)
    private String code;

    @JsonView(Views.Rest.class)
    private T data;

    private RestApiResult(ResultCase resultCase) {
        this(resultCase, null);
    }

    private RestApiResult(ResultCase resultCase, T data) {
        this.code = resultCase.code().get();
        this.message = resultCase.message();
        this.data = data;
    }

    private static final RestApiResult OK = new RestApiResult(ResultCase.ok());

    public static final <T> RestApiResult<T> success() {
        return OK;
    }

    public static final <T> RestApiResult<T> success(T data) {
        return new RestApiResult<>(ResultCase.ok(), data);
    }

    public static final <T> RestApiResult<T> failure(ResultCase resultCase) {
        return new RestApiResult<>(resultCase);
    }

}
