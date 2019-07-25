package lucky.sky.db.mongo.io;

import lucky.sky.db.mongo.lang.StrKit;

import java.io.File;

/**
 * Utilities for file operation.
 */
public class Files {

    /**
     * 检测指定的路径是否存在。
     */
    public static boolean exists(String path) {
        if (StrKit.isBlank(path)) {
            return false;
        }
        File file = new File(path);
        return file.exists();
    }

    public static void mkDir(File file) {
        if (file.getParentFile().exists()) {
            file.mkdir();
        } else {
            mkDir(file.getParentFile());
            file.mkdir();
        }
    }


}
