package com.javarush.task.task31.task3101;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Проход по дереву файлов
 * 1. На вход метода main подаются два параметра.
 * Первый - path - путь к директории
 * Второй - resultFileAbsolutePath -  имя существующего файла, который будет содержать результат.
 * 2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
 * Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
 * 2.1. Отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке.
 * 2.2. Переименовать resultFileAbsolutePath в 'allFilesContent.txt' (используй метод FileUtils.renameFile, и, если понадобится, FileUtils.isExist).
 * 2.3. В allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. После каждого тела файла записать "\n".
 * Все файлы имеют расширение txt.
 * В качестве разделителя пути используй "/".
 * <p>
 * Требования:
 * 1. Файл, который приходит вторым параметром в main, должен быть переименован в allFilesContent.txt.
 * 2. Нужно создать поток для записи в переименованный файл.
 * 3. Содержимое всех файлов, размер которых не превышает 50 байт, должно быть записано в файл allFilesContent.txt
 * в отсортированном по имени файла порядке.
 * 4. Поток для записи в файл нужно закрыть.
 */

//Как хочешь валидатор

public class Solution {
    public static void main(String[] args) throws IOException {
        Path parentDir = Paths.get(args[0]);
        File fileToWrite = new File(args[1]);

        //переименовываем файл, если не занято имя
        File allFilesContent = new File(fileToWrite.getParent() + File.separator + "allFilesContent.txt");
        if (!FileUtils.isExist(allFilesContent)) FileUtils.renameFile(fileToWrite, allFilesContent);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

//      Вариант, который я сдал
        Files.walk(parentDir)
                .map(path -> path.toFile())
                .filter(file -> !file.isDirectory())
                .filter(file -> file.length() <= 50)
                .filter(file -> !file.getName().equals("allFilesContent.txt"))
                .sorted(Comparator.comparing(File::getName))
                .forEachOrdered(file -> {
                    try (InputStream is = new FileInputStream(file)) {

                        byte[] fileImage = new byte[is.available()];
                        is.read(fileImage);

                        baos.write(fileImage);
                        baos.write(System.lineSeparator().getBytes());
                        baos.flush();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        // Ещё один вариант решения
//                Files.walk(parentDir)
//                        .map(path -> path.toFile())
//                        .filter(file -> !file.isDirectory())
//                        .filter(file -> file.length() <= 50)
//                        .filter(file -> !file.getName().equals("allFilesContent.txt"))
//                        .sorted(Comparator.comparing(File::getName))
//                        .
        try(OutputStream os = new FileOutputStream(allFilesContent, true)) {
            os.write(baos.toByteArray());
        } catch (Exception e) {e.printStackTrace();}
    }
}

//  Как должно быть
    /*
public class Solution {
    public static void main(String[] args) throws IOException {
        Path parentDir = Paths.get(args[0]);
        File fileToWrite = new File(args[1]);

        //переименовываем файл, если не занято имя
        File allFilesContent = new File(fileToWrite.getParent() + File.separator + "allFilesContent.txt");
        if (!FileUtils.isExist(allFilesContent)) FileUtils.renameFile(fileToWrite, allFilesContent);

        Files.walk(parentDir)
                .map(path -> path.toFile())
                .filter(file -> !file.isDirectory())
                .filter(file -> file.length() <= 50)
                .filter(file -> !file.getName().equals("allFilesContent.txt"))
                .sorted(Comparator.comparing(File::getName))
                .forEachOrdered(file -> copy(file, allFilesContent));
    }

    public static void copy(File fileInput, File fileOutput) {
        try (InputStream is = new FileInputStream(fileInput);
             OutputStream os = new FileOutputStream(fileOutput, true)) {

//            byte[] fileImage = new byte[is.available()];
//            is.read(fileImage);
//            os.write(fileImage);
//Альтернатива по копированию файлов
            os.write(is.readAllBytes());
            os.write(System.lineSeparator().getBytes());
            os.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/

//Вариант Ильяса
/*
public class Solution {
    public static void main(String[] args) throws IOException {
        File file = new File(args[1]);
        FileUtils.renameFile(file, file = new File(file.getParent() + "/allFilesContent.txt"));

        FileOutputStream fos = new FileOutputStream(file);

        Files.walk(Paths.get(args[0]))
                .map(Path::toFile)
                .filter(f -> f.isFile() && f.length() < 51 && !"allFilesContent.txt".equals(f.getName()))
                .sorted(Comparator.comparing(File::getName))
                .forEach(f -> {
                    try {
                        fos.write(Files.readAllBytes(f.toPath()));
                        fos.write(System.lineSeparator().getBytes());
                    } catch (IOException e) {}
                });
        fos.close();
    }
}
*/