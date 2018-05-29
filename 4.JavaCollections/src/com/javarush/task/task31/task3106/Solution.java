package com.javarush.task.task31.task3106;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
Разархивируем файл
В метод main приходит список аргументов.
Первый аргумент - имя результирующего файла resultFileName, остальные аргументы - имена файлов fileNamePart.
Каждый файл (fileNamePart) - это кусочек zip архива. Нужно разархивировать целый файл, собрав его из кусочков.
Записать разархивированный файл в resultFileName.
Архив внутри может содержать файл большой длины, например, 50Mb.
Внутри архива может содержаться файл с любым именем.

Пример входных данных. Внутри архива находится один файл с именем abc.mp3:
C:/result.mp3
C:/pathToTest/test.zip.003
C:/pathToTest/test.zip.001
C:/pathToTest/test.zip.004
C:/pathToTest/test.zip.002


Требования:
1. В методе main нужно создать ZipInputStream для архива, собранного из кусочков файлов. Файлы приходят аргументами в main, начиная со второго.
2. Создай поток для записи в файл, который приходит первым аргументом в main. Запиши туда содержимое файла из архива.
3. Поток для чтения из архива должен быть закрыт.
4. Поток для записи в файл должен быть закрыт.
*/
public class Solution {
    public static void main(String[] args) {
//        args = new String[]{"d:/testDir/result.mp3", "d:/testDir/Ace_Of_Base_Happy_Nation_Wiliam.7z.005", "d:/testDir/Ace_Of_Base_Happy_Nation_Wiliam.7z.003",
//                "d:/testDir/Ace_Of_Base_Happy_Nation_Wiliam.7z.001", "d:/testDir/Ace_Of_Base_Happy_Nation_Wiliam.7z.002",
//                "d:/testDir/Ace_Of_Base_Happy_Nation_Wiliam.7z.004"};


        List list = Arrays.stream(args)
                .sequential()
                .skip(1)
                .filter(f -> Files.exists(Paths.get(f)))
                .sorted()
                .map(m -> convertToInputStream(m).get())
                .collect(Collectors.toList());

        Enumeration<FileInputStream> enumeration = Collections.enumeration(list);

        try(ZipInputStream zis = new ZipInputStream(new SequenceInputStream(enumeration));
            FileOutputStream fos = new FileOutputStream(args[0])) {

            final int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length;
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                while ((length = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }
                fos.flush();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Optional <FileInputStream> convertToInputStream (String s) {
        Optional<FileInputStream> oFIS = Optional.empty();
        try {
            return oFIS = Optional.ofNullable(new FileInputStream(s));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return oFIS;
    }

}
