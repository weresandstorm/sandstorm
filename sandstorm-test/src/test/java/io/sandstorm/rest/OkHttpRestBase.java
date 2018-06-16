package io.sandstorm.rest;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public abstract class OkHttpRestBase {

    public static final String PLAIN_TEXT = "text/plain; charset=UTF-8";
    public static final String APPLICATION_JSON = "application/json; charset=UTF-8";
    public static final String MULTI_FORM_DATA = "multipart/form-data";
    public static final String APPLICATION_OCTET = "application/octet-stream";

    public static final MediaType MEDIA_TYPE_OCTET = MediaType.parse(APPLICATION_OCTET);

    protected final OkHttpClient httpClient;

    protected OkHttpRestBase() {
        this.httpClient = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build();
    }

    protected void sendReqAndDisplayResp(Request httpReq) throws Exception {
        CompletableFuture<Response> respFuture = new CompletableFuture<>();
        httpClient.newCall(httpReq).enqueue(new OkHttpCallback(respFuture));

        Response resp = respFuture.get();
        System.out.println(String.format("Status and message: %s %s", resp.code(), resp.message()));
        if (resp.body() != null) {
            System.out.println("Response body: " + resp.body().string());
        } else {
            System.out.println("Response body is empty!");
        }
    }

    protected File fileFromResource(String resourceName) {
        return new File(OkHttpRestBase.class.getClassLoader().getResource(resourceName).getFile());
    }

}
