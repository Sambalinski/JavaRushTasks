package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* 
Что внутри папки?
Напиши программу, которая будет считать подробную информацию о папке и выводить ее на консоль.

Первым делом считай путь к папке с консоли.
Если введенный путь не является директорией - выведи "[полный путь] - не папка" и заверши работу.
Затем посчитай и выведи следующую информацию:

Всего папок - [количество папок в директории и поддиректориях]
Всего файлов - [количество файлов в директории и поддиректориях]
Общий размер - [общее количество байт, которое хранится в директории]

Используй только классы и методы из пакета java.nio.

Квадратные скобки [ ] выводить на экран не нужно.


Требования:
1. Метод main должен считывать путь к папке с консоли.
2. Если введенный путь не является директорией - нужно вывести "[полный путь] - не папка" и завершить работу.
3. На консоль должна быть выведена следующая информация: "Всего папок - [количество папок в директории и поддиректориях]".
4. На консоль должна быть выведена следующая информация: "Всего файлов - [количество файлов в директории и поддиректориях]".
5. На консоль должна быть выведена следующая информация: "Общий размер - [общее количество байт, которое хранится в директории]".
*/
public class Solution {
//    static int directories = -1;
//    static int files = 0;
//    static long size = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Path path = Paths.get(br.readLine());

        if (nonDirectory(path)) {
            System.out.println(path.toAbsolutePath().toString() + " - не папка");
            return;
        }
        br.close();

        AtomicInteger directories = new AtomicInteger(-1);
        AtomicInteger files = new AtomicInteger(0);
        AtomicLong size = new AtomicLong(0);

        Files.walk(path)
                .forEach(f -> {
                    if (Files.isDirectory(f)) directories.getAndIncrement();
                    else if (Files.isRegularFile(f)) {
                        files.getAndIncrement();
                        try {
                            size.addAndGet(Files.size(f));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                });

        System.out.println("Всего папок - " + directories.get());
        System.out.println("Всего файлов - " + files.get());
        System.out.println("Общий размер - " + size.get());

//        Files.walkFileTree(path, new MyVizitor());
//
//        System.out.println("Всего папок - " + directories);
//        System.out.println("Всего файлов - " + files);
//        System.out.println("Общий размер - " + size);

    }

    public static boolean nonDirectory(Path path) {
        return !Files.isDirectory(path);
    }


//    public static class MyVizitor extends SimpleFileVisitor<Path> {
//        @Override
//        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
//            if (attrs.isDirectory()) directories ++;
//            return FileVisitResult.CONTINUE;
//        }
//
//        @Override
//        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//            if (attrs.isRegularFile()) files++;
//            size += attrs.size();
//            return FileVisitResult.CONTINUE;
//        }
//    }
}
