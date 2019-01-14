package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
Сформируй часть запроса WHERE используя StringBuilder.
Если значение null, то параметр не должен попадать в запрос.

Пример:
{name=Ivanov, country=Ukraine, city=Kiev, age=null}

Результат:
name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'


Требования:
1. Метод getQuery должен принимать один параметр типа Map.
2. Метод getQuery должен иметь тип возвращаемого значения String.
3. Метод getQuery должен быть статическим.
4. Метод getQuery должен возвращать строку сформированную по правилам описанным в условии задачи.
*/
public class Solution {
    public static void main(String[] args) {
        Map<String, String> params = new HashMap<>();
        params.put("name", "Ivanov");
        params.put("country", "Ukraine");
        params.put("city", "Kiev");
        params.put("age", null);

        System.out.println(getQuery(params));
    }

    public static String getQuery(Map<String, String> params) {
        final String AND = "' and ";
        final String EQUALLY = " = '";
        StringBuilder sb = new StringBuilder();

        /*
        final String AND = " and ";
        final String NAME = "name";
        final String COUNTRY = "country";
        final String CITY= "city";
        final String AGE = "age";

        if (params.get(NAME) != null && !params.get(NAME).isEmpty())
            sb.append(NAME).append(EQUALLY).append(params.get(NAME)).append("'");

        if (params.get(COUNTRY) != null && !params.get(NAME).isEmpty())
            sb.append(AND).append(COUNTRY).append(EQUALLY).append(params.get(COUNTRY)).append("'");

        if (params.get(CITY) != null && !params.get(NAME).isEmpty())
            sb.append(AND).append(CITY).append(EQUALLY).append(params.get(CITY)).append("'");

        if (params.get(AGE) != null && !params.get(NAME).isEmpty())
            sb.append(AND).append(AGE).append(EQUALLY).append(params.get(AGE)).append("'");
*/

        params.entrySet().stream()
                .filter(pair ->
                                pair.getKey() != null
                                        && pair.getValue() != null
                                        && !pair.getKey().isEmpty()
                                        && !pair.getValue().isEmpty())
                .forEach(pair ->
                        sb.append(pair.getKey())
                                .append(EQUALLY)
                                .append(pair.getValue())
                                .append(AND));

        int length = sb.length();
        sb.setLength(length > 4 ? length-5 : 0);


        return sb.toString();
    }
}
