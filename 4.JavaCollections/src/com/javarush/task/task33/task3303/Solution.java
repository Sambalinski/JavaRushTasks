package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

/* 
Десериализация JSON объекта
*/
public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        File file = new File(fileName);

        T t = mapper.readValue(file, clazz);

            //Альтернатива
//        FileReader reader = new FileReader(new File(fileName));
//        T t = mapper.readValue(reader, clazz);

        return t;
    }

    public static void main(String[] args) {

    }
}
