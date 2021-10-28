package com.semihbkgr.ecommerce.imageserver.util;

public class FilenameUtils {

    public static String extractExtension(String filename){
        if(filename==null || !filename.contains("."))
            return null;
        return filename.substring(filename.lastIndexOf('.') +1,filename.length());
    }

}
