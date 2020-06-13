package com.apollo.statussaver.Utils;

import java.io.File;

/**
 * Created by Apollo on 4/8/2020.
 */

public class Method1 {
    public static void load_Directory_Files(File directory) {
        File[] fileList = directory.listFiles();
        if (fileList != null && fileList.length > 0) {
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    load_Directory_Files(aFileList);
                } else {
                    String name = aFileList.getName().toLowerCase();
                    for (String extension : Constant1.musicExtensions) {
                        //check file extension
                        if (name.endsWith(extension)) {
                            Constant1.mediaList.add(aFileList);
                            //when a found file
                            break;
                        }
                    }
                }
            }
        }
    }
}
