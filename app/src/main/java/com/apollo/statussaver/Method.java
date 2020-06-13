package com.apollo.statussaver;

import java.io.File;

/**
 * Created by Apollo on 4/8/2020.
 */

public class Method {
    public static void load_Directory_Files(File directory) {
        File[] fileList = directory.listFiles();
        if (fileList != null && fileList.length > 0) {
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    load_Directory_Files(aFileList);
                } else {
                    String name = aFileList.getName().toLowerCase();
                    for (String extension : Constant.musicExtensions) {
                        //check file extension
                        if (name.endsWith(extension)) {
                            Constant.mediaList.add(aFileList);
                            //when a found file
                            break;
                        }
                    }
                }
            }
        }
    }
}
