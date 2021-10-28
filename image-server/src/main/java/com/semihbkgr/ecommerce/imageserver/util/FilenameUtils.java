package com.semihbkgr.ecommerce.imageserver.util;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class FilenameUtils {

    @Nullable
    public static String detachExtension(@Nullable String filename) {
        if (filename == null || !filename.contains("."))
            return null;
        return filename.substring(filename.lastIndexOf('.') + 1, filename.length());
    }

    @NonNull
    public static String attachExtension(@NonNull String name, @Nullable String extension) {
        if(extension==null)
            return name;
        return name.concat(".").concat(extension);
    }

}
