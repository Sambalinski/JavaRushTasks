package com.javarush.task.task18.task1823;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/* 
Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit".
Передайте имя файла в нить ReadThread.
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Закрыть потоки.

Требования:
1. Программа должна считывать имена файлов с консоли, пока не будет введено слово "exit".
2. Для каждого файла создай нить ReadThread и запусти ее.
3. После запуска каждая нить ReadThread должна создать свой поток для чтения из файла.
4. Затем, нити должны найти максимально встречающийся байт в своем файле и добавить его в словарь resultMap.
5. Поток для чтения из файла в каждой нити должен быть закрыт.
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) {
        ReadThread readThread;
        Scanner scanner = new Scanner(System.in);
        String s;
        while (true) {
            if ((s = scanner.nextLine()).equals("exit")) break;
            readThread = new ReadThread(s);
            readThread.start();
        }

//        for (Map.Entry<String, Integer> pair : resultMap.entrySet()) System.out.println(pair.getKey() + " " + pair.getValue());
    }

    public static class ReadThread extends Thread {
        private byte [] bytes = new byte[256];
        private String fileName;
        public ReadThread(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void run() {
            try(InputStream in = new FileInputStream(fileName)) {
                while (in.available() > 0) bytes[in.read()]++;
                int maxByteCount = 0;
                int thisMaxByte = 0;
                for (int i = 0; i < bytes.length; i++) if (maxByteCount < bytes[i]) maxByteCount = bytes[i];
                for (int i = 0; i < bytes.length; i++) if (bytes[i] == maxByteCount) thisMaxByte = i;
                resultMap.put(fileName, thisMaxByte);
            }catch (Exception e) {e.printStackTrace();}
        }
    }
}
