package com.javarush.task.task20.task2022;

import java.io.*;

/**
Переопределение сериализации в потоке
Сериализация/десериализация Solution не работает.
Исправь ошибки не меняя сигнатуры методов и класса.
Метод main не участвует в тестировании.

Написать код проверки самостоятельно в методе main:
1) создать экземпляр класса Solution
2) записать в него данные - writeObject
3) сериализовать класс Solution - writeObject(ObjectOutputStream out)
4) десериализовать, получаем новый объект
5) записать в новый объект данные - writeObject
6) проверить, что в файле есть данные из п.2 и п.5


Требования:
1. Поле stream должно быть объявлено с модификатором transient.
2. В методе writeObject(ObjectOutputStream out) не должен быть вызван метод close
      у потока полученного в качестве параметра.
3. В методе readObject(ObjectInputStream in) не должен быть вызван метод close у потока
      полученного в качестве параметра.
4. В методе readObject(ObjectInputStream in) поле stream должно быть инициализировано
      новым объектом типа FileOutputStream с параметрами(fileName, true).
5. В конструкторе класса Solution поле stream должно быть инициализировано
      новым объектом типа FileOutputStream с параметром(fileName).
*/
public class Solution implements Serializable, AutoCloseable {
    transient private FileOutputStream stream;
    private String fileName;

    public Solution(String fileName) throws FileNotFoundException { //конструктор
        this.fileName = fileName;
        this.stream = new FileOutputStream(fileName);
    }

    public void writeObject(String string) throws IOException { //запись данных объекта в файл
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException { //сохранение объекта
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException { //восстановление объекта
        in.defaultReadObject();
        stream = new FileOutputStream(fileName,true);
    }

    @Override
    public void close() throws Exception { //закрытие потока
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws Exception {

        Solution solution = new Solution("test");
        solution.writeObject("This is new Data"); //пишем в файл данные

        ObjectOutputStream oos = new ObjectOutputStream(solution.stream);
        oos.writeObject(solution);                                                                        //сохраняем объект

        FileInputStream fis = new FileInputStream("test");
        ObjectInputStream ois = new ObjectInputStream(fis);

        Object object = ois.readObject();   //восстанавливаем обект
        Solution newSolution = (Solution) object;

        newSolution.writeObject("Second data"); //записываем новые данные (дописываем)

        ois.close();
        ois.close();
    }
}
//      эксперименты
//public class Solution implements Serializable, AutoCloseable {
//    transient private FileOutputStream stream;
//    transient private FileDescriptor fd;
//
//    public Solution(String fileName) throws IOException { //конструктор
//        this.stream = new FileOutputStream(fileName);
//        fd = stream.getFD();
//    }
//
//    public void writeObject(String string) throws IOException { //запись данных объекта в файл
//        stream.write(string.getBytes());
//        stream.write("\n".getBytes());
//        stream.flush();
//    }
//
//    private void writeObject(ObjectOutputStream out) throws IOException { //сохранение объекта
//        out.defaultWriteObject();
//    }
//
//    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException { //восстановление объекта
//        in.defaultReadObject();
//        stream = new FileOutputStream(fd);
//    }
//
//    @Override
//    public void close() throws Exception { //закрытие потока
//        System.out.println("Closing everything!");
//        stream.close();
//    }
//
//    public static void main(String[] args) throws Exception {
//
//        Solution solution = new Solution("test");
//        solution.writeObject("This is new Data"); //пишем в файл данные
//
//        ObjectOutputStream oos = new ObjectOutputStream(solution.stream);
//        oos.writeObject(solution);                                                                        //сохраняем объект
//
//        FileInputStream fis = new FileInputStream("test");
//        ObjectInputStream ois = new ObjectInputStream(fis);
//
//        Object object = ois.readObject();   //восстанавливаем обект
//        Solution newSolution = (Solution) object;
//
//        newSolution.writeObject("Second data"); //записываем новые данные (дописываем)
//
//        ois.close();
//        ois.close();
//    }
//}