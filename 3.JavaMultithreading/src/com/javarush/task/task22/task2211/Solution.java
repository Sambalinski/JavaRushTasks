package com.javarush.task.task22.task2211;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        byte[] bytes =Files.readAllBytes(Paths.get(args[0]));
        String src = new String(bytes, "Windows-1251");

        bytes = src.getBytes("UTF-8");

        try (OutputStream outputStream = new FileOutputStream(args[1])) {
            outputStream.write(bytes);
        }
    }
}
