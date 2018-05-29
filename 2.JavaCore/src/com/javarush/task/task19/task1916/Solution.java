package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* 
Отслеживаем изменения
Считать с консоли 2 имени файла - file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines.
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME.
В оригинальном и редактируемом файлах пустых строк нет.

Пример:
оригинальный    редактированный    общий
file1:          file2:             результат:(lines)

строка1          строка1            SAME строка1
строка2                                 REMOVED строка2
строка3         строка3            SAME строка3
строка4                                REMOVED строка4
строка5         строка5            SAME строка5
                    строка0            ADDED строка0
строка1         строка1            SAME строка1
строка2                                REMOVED строка2
строка3         строка3            SAME строка3
                    строка5            ADDED строка5
строка4         строка4            SAME строка4
строка5                                 REMOVED строка5


Требования:
1. Класс Solution должен содержать класс LineItem.
2. Класс Solution должен содержать enum Type.
3. Класс Solution должен содержать публичное статическое поле lines типа List, которое сразу проинициализировано.
4. В методе main(String[] args) программа должна считывать имена файлов с консоли (используй BufferedReader).
5. В методе main(String[] args) BufferedReader для считывания данных с консоли должен быть закрыт.
6. Программа должна считывать содержимое первого и второго файла (используй FileReader).
7. Потоки чтения из файлов (FileReader) должны быть закрыты.
8. Список lines должен содержать объединенную версию строк из файлов, где для каждой строки указана одна из операций ADDED, REMOVED, SAME.
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
             Scanner scanner1 = new Scanner(new FileReader(bufferedReader.readLine()));
             Scanner scanner2 = new Scanner(new FileReader(bufferedReader.readLine()))) {

            ArrayList<String> listFile1 = new ArrayList<>();
            ArrayList<String> listFile2 = new ArrayList<>();

            //Заполняем списки строками из файлов
            while (scanner1.hasNext()) listFile1.add(scanner1.nextLine());
            while (scanner2.hasNext()) listFile2.add(scanner2.nextLine());

            //цикл работает до тех пор, пока в одном из списков не останется 2 элемента
            //чтобы не выходить за пределы массива

            while ((listFile1.size() > 2) && (listFile2.size() > 2)) {

                   //1-е условие
                if (listFile1.get(0).equals(listFile2.get(0))) {
                    lines.add(new LineItem(Type.SAME, listFile2.get(0)));
                    listFile1.remove(0);
                    listFile2.remove(0);

                    //2-е условие
                } else if (listFile1.get(0).equals(listFile2.get(1))) {
                    lines.add(new LineItem(Type.ADDED, listFile2.get(0)));
                    listFile1.remove(0);
                    listFile2.remove(0);

                    //3-е условие
                } else if (listFile1.get(1).equals(listFile2.get(0))) {
                    lines.add(new LineItem(Type.REMOVED, listFile1.get(0)));
                    listFile1.remove(0);
                    listFile2.remove(0);
                }
            }

            // работаем с оставшимися 3 - мя элементами
            // (2 в одном списке, 1 в другом)
            if (listFile1.size() > listFile2.size()) {
                if (listFile1.get(0).equals(listFile2.get(0))) {
                    lines.add(new LineItem(Type.SAME, listFile1.get(0)));
                    lines.add(new LineItem(Type.REMOVED, listFile1.get(1)));
                } else {
                    lines.add(new LineItem(Type.REMOVED, listFile1.get(0)));
                    lines.add(new LineItem(Type.SAME, listFile1.get(1)));
                }
            } else {
                if (listFile1.get(0).equals(listFile2.get(0))) {
                    lines.add(new LineItem(Type.SAME, listFile2.get(0)));
                    lines.add(new LineItem(Type.ADDED, listFile2.get(1)));
                } else {
                    lines.add(new LineItem(Type.ADDED, listFile2.get(0)));
                    lines.add(new LineItem(Type.SAME, listFile2.get(1)));
                }
//                for (LineItem l : lines) System.out.println(l.type + " " + l.line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
