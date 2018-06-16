package io.sandstorm.rest;

import okhttp3.HttpUrl;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;

@RunWith(JUnit4.class)
public class DataSetRestTest extends OkHttpRestBase {

    @Test
    public void testUploadChunk() throws Exception {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost").port(8080)
                .addPathSegments("data-sets/data-chunks/actions/upload")
                .build();

        File chunk1 = fileFromResource("data-sets/instant-kill.gz");
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.MIXED)
                .addFormDataPart(
                        "dataChunk",
                        "instant-kill.gz",
                        RequestBody.create(MEDIA_TYPE_OCTET, chunk1)
                ).build();

        Request.Builder reqBuilder = new Request
                .Builder()
                .url(httpUrl)
                .post(requestBody);

        sendReqAndDisplayResp(reqBuilder.build());
    }

    @Test
    public void uploadSecKillChunk1() throws Exception {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost").port(8080)
                .addPathSegments("data-sets/data-chunks/actions/upload")
                .build();

        File chunk1 = fileFromResource("data-sets/seckill-1.json.gz");
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.MIXED)
                .addFormDataPart(
                        "dataChunk",
                        "seckill-1.json.gz",
                        RequestBody.create(MEDIA_TYPE_OCTET, chunk1)
                ).build();

        Request.Builder reqBuilder = new Request
                .Builder()
                .url(httpUrl)
                .post(requestBody);

        sendReqAndDisplayResp(reqBuilder.build());
    }

    @Test
    public void uploadSecKillChunk2() throws Exception {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost").port(8080)
                .addPathSegments("data-sets/data-chunks/actions/upload")
                .build();

        File chunk1 = fileFromResource("data-sets/seckill-2.json.gz");
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.MIXED)
                .addFormDataPart(
                        "dataChunk",
                        "seckill-2.json.gz",
                        RequestBody.create(MEDIA_TYPE_OCTET, chunk1)
                ).build();

        Request.Builder reqBuilder = new Request
                .Builder()
                .url(httpUrl)
                .post(requestBody);

        sendReqAndDisplayResp(reqBuilder.build());
    }

    @Test
    public void exceedMaxSizeLimit() throws Exception {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost").port(8080)
                .addPathSegments("data-sets/data-chunks/actions/upload")
                .build();

        File chunk1 = fileFromResource("data-sets/max-size-chunk.gz");
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.MIXED)
                .addFormDataPart(
                        "dataChunk",
                        "max-size-chunk.gz",
                        RequestBody.create(MEDIA_TYPE_OCTET, chunk1)
                ).build();

        Request.Builder reqBuilder = new Request
                .Builder()
                .url(httpUrl)
                .post(requestBody);

        sendReqAndDisplayResp(reqBuilder.build());
    }

}
