package io.sandstorm.controller.app;

import io.sandstorm.common.AppInternalException;
import io.sandstorm.common.IllegalInputException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

@Service
public class LocalTempStorage {

    private static final Logger logger = LoggerFactory.getLogger(LocalTempStorage.class);

    @Value("${temp.storage.dir}")
    private String tempStorageDir;

    private static final RandomStringGenerator generator;

    static {
        char[][] ranges = {{'a', 'z'}, {'A', 'Z'}, {'0', '9'}};
        generator = new RandomStringGenerator.Builder().withinRange(ranges).build();
    }

    public TempFile put(InputStream input, String fileName) {
        String destFullPath = new StringBuilder()
                .append(tempStorageDir)
                .append(File.separator)
                .append(generateUniqueDirname())
                .append(File.separator)
                .append(fileName)
                .toString();

        FileOutputStream output = null;
        try {
            File destFile = new File(destFullPath);
            destFile.getParentFile().mkdirs();
            output = new FileOutputStream(destFile);
            IOUtils.copy(input, output);
            return new TempFile(destFullPath);
        } catch (IOException ex) {
            throw new AppInternalException(String.format("Failed to write to a temp file: %s", destFullPath), ex);
        } finally {
            IOUtils.closeQuietly(output);
            IOUtils.closeQuietly(input);
        }
    }

    public InputStream get(TempFile tempFile) {
        try {
            BufferedInputStream stream = new BufferedInputStream(new FileInputStream(tempFile.path()));
            stream.mark(Integer.MAX_VALUE);
            return stream;
        } catch (FileNotFoundException ex) {
            throw new IllegalInputException(String.format("File %s not exist", tempFile.path()), ex);
        }
    }

    public void remove(Collection<TempFile> tempFiles) {
        tempFiles.forEach(tempFile -> remove(tempFile));
    }

    public void remove(TempFile tempFile) {
        File file = new File(tempFile.path());
        try {
            FileUtils.deleteDirectory(file.getParentFile());
        } catch (IOException ex) {
            logger.error("Failed to remove {}", file.getParentFile().getAbsolutePath());
        }
    }

    private String generateUniqueDirname() {
        return generator.generate(13);
    }

}
