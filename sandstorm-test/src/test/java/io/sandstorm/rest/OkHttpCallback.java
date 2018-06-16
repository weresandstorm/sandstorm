package io.sandstorm.rest;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class OkHttpCallback implements Callback {

    private CompletableFuture<Response> respFuture;

    public OkHttpCallback(CompletableFuture<Response> respFuture) {
        this.respFuture = respFuture;
    }

    @Override
    public final void onResponse(Call call, Response response) throws IOException {
        respFuture.complete(response);
    }

    @Override
    public final void onFailure(Call call, IOException e) {
        respFuture.completeExceptionally(e);
    }

}
