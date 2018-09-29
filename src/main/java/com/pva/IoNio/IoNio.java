package com.pva.IoNio;

import java.io.File;

public class IoNio {

    public static void workWithFile() {
        File file = new File("E:/testFile.txt");
        System.out.println(file.exists());
    }
}
