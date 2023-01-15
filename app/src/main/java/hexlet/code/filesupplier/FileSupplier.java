package hexlet.code.filesupplier;

import java.io.File;
public interface FileSupplier {
    static File getFile(String path) {
        return new File(new File(path).getAbsolutePath());
    }
}
