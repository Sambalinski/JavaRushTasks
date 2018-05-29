package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String.
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами.

Заполнить список PEOPLE используя данные из файла.
Закрыть потоки.

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013


Требования:
1. Класс Solution должен содержать публичную константу PEOPLE типа List, которая должна быть сразу проинициализирована.
2. Программа НЕ должна считывать данные с консоли.
3. Программа должна считывать содержимое файла (используй FileReader).
4. Поток чтения из файла (FileReader) должен быть закрыт.
5. Программа должна заполнить список PEOPLE данными из файла.
6. Программа должна правильно работать с двойными именами, например Анна-Надежда.
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String s;
            String name;
            String dateStr;

            while (br.ready()) {
                s = br.readLine();
                name = s.replaceAll("\\w", "").trim();

                dateStr = s.replaceAll("[А-яA-z\\-]", "").trim();

                PEOPLE.add(new Person(name, new Date(Integer.parseInt(dateStr.split(" ")[2])-1900, Integer.parseInt(dateStr.split(" ")[1])-1, Integer.parseInt(dateStr.split(" ")[0]))));
            }
        } catch (Exception e) {e.printStackTrace();}
        for (Person person : PEOPLE) System.out.println(person.getName() + person.getBirthday());
    }
}