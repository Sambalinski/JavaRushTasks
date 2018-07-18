package com.javarush.task.task33.task3303;

import java.io.FileReader;
import java.io.IOException;

/* 
Десериализация JSON объекта
*/
public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        try(FileReader fileReader = new FileReader(fileName)){

        }catch (IOException e){}
    }

    public static void main(String[] args) {

    }
}
