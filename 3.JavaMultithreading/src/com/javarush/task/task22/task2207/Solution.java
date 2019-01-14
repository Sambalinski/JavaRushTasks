package com.javarush.task.task22.task2207;

import java.io.*;
import java.util.*;

/*
Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Использовать StringBuilder.
Кодировка файла - UTF-8.

Пример содержимого файла
рот тор торт о
о тот тот тот

Вывод:
рот тор
о о
тот тот


Требования:
1. Метод main должен считывать имя файла с клавиатуры.
2. В методе main должен быть использован StringBuilder.
3. В классе Solution должен содержаться вложенный класс Pair.
4. В классе Pair должен быть объявлен конструктор без параметров (или конструктор по умолчанию).
5. Список result должен быть заполнен корректными парами согласно условию задачи.
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        File file = new File(scanner.nextLine());
        scanner = new Scanner(file);
        while (scanner.hasNext()) sb.append(scanner.nextLine()).append(" ");

//        ArrayList<String> words = new ArrayList<>();
//        words.addAll(Arrays.asList(sb.toString().split(" ")));
        List<String> words = new ArrayList<>(Arrays.asList(sb.toString().split(" ")));

        String s;
        Pair pair;

        while (words.size() != 0) {
            s = words.get(0);
            words.remove(0);
            for (int i = 0; i < words.size(); i++) {
                if (s.equals(new StringBuilder(words.get(i)).reverse().toString())) {
                    pair = new Pair();
                    pair.first = s;
                    pair.second = words.get(i);
                    result.add(pair);
                    words.remove(i);
                    break;
                }
            }
        }
        System.out.println(result.size());
    }

    public static class Pair {
        String first;
        String second;

        public Pair() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null && second != null ? second :
                            second == null && first != null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
