package com.javarush.task.task31.task3105;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Добавление файла в архив
 * В метод main приходит список аргументов.
 * Первый аргумент - полный путь к файлу fileName.
 * Второй аргумент - путь к zip-архиву.
 * Добавить файл (fileName) внутрь архива в директорию 'new'.
 * Если в архиве есть файл с таким именем, то заменить его.
 * <p>
 * Пример входных данных:
 * D:/testDirAce_Of_Base_Happy_Nation_Wiliam.mp3
 * D:/testDir/archive.zip
 * <p>
 * Файлы внутри test.zip:
 * a.txt
 * b.txt
 * <p>
 * После запуска Solution.main архив test.zip должен иметь такое содержимое:
 * new/Ace_Of_Base_Happy_Nation_Wiliam.mp3
 * a.txt
 * b.txt
 * <p>
 * Подсказка: нужно сначала куда-то сохранить содержимое всех энтри, а потом записать в архив все энтри вместе с добавленным файлом.
 * Пользоваться файловой системой нельзя.
 * <p>
 * Требования:
 * 1. В методе main создай ZipInputStream для архивного файла (второй аргумент main). Нужно вычитать из него все содержимое.
 * 2. В методе main создай ZipOutputStream для архивного файла (второй аргумент main).
 * 3. В ZipOutputStream нужно записать содержимое файла, который приходит первым аргументом в main.
 * 4. В ZipOutputStream нужно записать все остальное содержимое, которое было вычитано из ZipInputStream.
 * 5. Потоки для работы с архивом должны быть закрыты.
 */
public class Solution {
    public Map<String, ByteArrayOutputStream> map;

    public void deCompression(ZipInputStream zis) throws IOException {
        map = new HashMap<>();
        ZipEntry zipEntry;
        byte[] buffer = new byte[1024];
        int length;
        ByteArrayOutputStream baos;

        while ((zipEntry = zis.getNextEntry()) != null) {
            baos = new ByteArrayOutputStream();
            while ((length = zis.read(buffer)) > 0) {
                baos.write(buffer, 0, length);
            }
            map.put(zipEntry.getName(), baos);
        }
    }

    public void compression(ZipOutputStream zos, String file) throws IOException {
        ZipEntry zipEntry;
        Path path = Paths.get(file);

        for (Map.Entry<String, ByteArrayOutputStream> m : map.entrySet()) {
            if (!(m.getKey().equals("new/" + path.getFileName()))) {
                zipEntry = new ZipEntry(m.getKey());
                zos.putNextEntry(zipEntry);
                zos.write(m.getValue().toByteArray());
            } else {}
        }

        zipEntry = new ZipEntry("new/" + path.getFileName());
        zos.putNextEntry(zipEntry);
        Files.copy(Paths.get(file), zos);
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(args[1]))) {
            s.deCompression(zis);
        } catch (Exception e) {e.printStackTrace();}

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(args[1]))) {
            s.compression(zos, args[0]);
        } catch (Exception e) {e.printStackTrace();}
    }
}
