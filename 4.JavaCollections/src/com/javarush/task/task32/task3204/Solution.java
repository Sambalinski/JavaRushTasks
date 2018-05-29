package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
Генератор паролей
Реализуй логику метода getPassword, который должен возвращать ByteArrayOutputStream, в котором будут байты пароля.
Требования к паролю:
1) 8 символов.
2) только цифры и латинские буквы разного регистра.
3) обязательно должны присутствовать цифры, и буквы разного регистра.
Все сгенерированные пароли должны быть уникальные.

Пример правильного пароля:
wMh7smNu


Требования:
1. Класс Solution должен содержать метод getPassword(), который возвращает ByteArrayOutputStream со сгенерированным паролем.
2. Длина пароля должна составлять 8 символов.
3. Пароль должен содержать хотя бы одну цифру.
4. Пароль должен содержать хотя бы одну латинскую букву нижнего регистра.
5. Пароль должен содержать хотя бы одну латинскую букву верхнего регистра.
6. Пароль не должен содержать других символов, кроме цифр и латинских букв разного регистра.
7. Сгенерированные пароли должны быть уникальными.
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ArrayList<Character> allChars = new ArrayList<>(62);
        StringBuilder sb = new StringBuilder();
        boolean numExists = false, upperCaseExists = false, lowerCaseExists = false;

        Random random = (((min, max) -> {   //функция возвращает случайное число от минимального значение до максимального
            max -= min;
            return (int) (Math.random() * ++max) + min;
        }));

        //заполняем список символами для пароля
        for (char c = '0'; c <= '9'; c++) //0-9 - диапазон 1
            allChars.add(c);

        for (char c = 'A'; c <= 'Z'; c++) //10-35 - диапазон 2
            allChars.add(c);

        for (char c = 'a'; c <= 'z'; c++) //36-62 - диапазон 3
            allChars.add(c);

        //заполняем первые 6 символов (не факт, что все 6 попадут во все диапазоны)
        for (int i = 0; i < 6; i++) {
            int rnd = random.randomFunc(1,3);

            if (rnd == 1) {
                numExists = true;
                sb.append(allChars.get(random.randomFunc(0,9)));
            } else if (rnd == 2) {
                upperCaseExists = true;
                sb.append(allChars.get(random.randomFunc(10,35)));
            } else if (rnd == 3) {
                lowerCaseExists = true;
                sb.append(allChars.get(random.randomFunc(36,61)));
            }
        }

        //проверяем, все ли диапазоны участвовали в генерации пароля
        if (!(numExists) || !(upperCaseExists) || !(lowerCaseExists)) {    //если нет, то добавляем недостающие
            for (int i = 0; i < 2; i++) {
                if (!(numExists)) {
                    sb.append(allChars.get(random.randomFunc(0, 9)));
                } else if (!(upperCaseExists)) {
                    sb.append(allChars.get(random.randomFunc(10, 35)));
                } else if (!(lowerCaseExists)) {
                    sb.append(allChars.get(random.randomFunc(36, 61)));
                }
            }
        } else {                                                                          //если да, то просто добавляем 2 радномных значения
            for (int i = 0; i < 2; i++) {
                sb.append(allChars.get(random.randomFunc(0, 61)));
            }
        }

        baos.write(sb.toString().getBytes());
            return baos;
    }

    interface Random {
        int randomFunc(int min, int max);
    }
}