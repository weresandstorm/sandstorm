package io.sandstorm.rest;

import io.sandstorm.common.CaseCode;
import io.sandstorm.common.IllegalInputException;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

public final class FilePartHelper {

    private FilePartHelper() {
    }

    public static InputStream getInputStream(Part filePart) {
        try {
            return filePart.getInputStream();
        } catch (IOException ex) {
            String message = String.format(
                    "Can't get the InputStream of uploaded file %s, please try uploading it again",
                    getFileName(filePart));
            throw new IllegalInputException(CaseCode.upload_io_error, message, ex);
        }
    }

    public static String getFileName(Part filePart) {
        return filePart.getSubmittedFileName();
    }

}
