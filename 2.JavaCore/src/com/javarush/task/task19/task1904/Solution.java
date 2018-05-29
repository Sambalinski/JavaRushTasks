package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/* 
И еще один адаптер
Адаптировать Scanner к PersonScanner.
Классом-адаптером является PersonScannerAdapter.
В классе адаптере создать приватное финальное поле Scanner fileScanner.
Поле инициализировать в конструкторе с одним аргументом типа Scanner.

Данные в файле хранятся в следующем виде:
Иванов Иван Иванович 31 12 1950
Петров Петр Петрович 31 12 1957

В файле хранится большое количество людей, данные одного человека находятся в одной строке.
Метод read() должен читать данные только одного человека.

Требования:
1. PersonScanner должен быть интерфейсом.
2. Класс PersonScannerAdapter должен реализовывать интерфейс PersonScanner.
3. Класс PersonScannerAdapter должен содержать приватное поле fileScanner типа Scanner.
4. Класс PersonScannerAdapter должен содержать конструктор с параметром Scanner.
5. Метод close() класса PersonScannerAdapter должен делегировать полномочие такому же методу fileScanner.
6. Метод read() класса PersonScannerAdapter должен читать строку с файла, парсить её, и возвращать данные
       только одного человека, в виде объекта класса Person.
*/

public class Solution {

    public static void main(String[] args) {

    }

    public static class PersonScannerAdapter implements PersonScanner{
        private Scanner fileScanner;

        PersonScannerAdapter(Scanner scanner) {
            this.fileScanner = scanner;
        }

        @Override
        public Person read() throws IOException {
            String s [] = this.fileScanner.nextLine().split(" ");
            return new Person(s[1], s[2], s[0], new Date(Integer.parseInt(s[5])-1900, Integer.parseInt(s[4])-1, Integer.parseInt(s[3])));
        }
//        @Override
//        public Person read() throws IOException, ParseException {
//            String s [] = this.fileScanner.nextLine().split(" ");
//            String date = s[3].concat("/").concat(s[4]).concat("/").concat(s[5]);
//            return new Person(s[1], s[2], s[0], new SimpleDateFormat("dd/MM/yyyy").parse(date));
//        }

        @Override
        public void close() throws IOException {
            this.fileScanner.close();
        }
    }
}
