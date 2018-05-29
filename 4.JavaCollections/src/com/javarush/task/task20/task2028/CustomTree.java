package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/**
 * Построй дерево(1)
 * Амиго, похоже ты уже достаточно окреп. Самое время проверить свои навыки в большой задаче!
 * Сегодня реализуем свое дерево немного нестандартным способом(на базе AbstractList).
 * Вводную информацию можешь получить используя свой любимый поисковик и текст ниже.
 * Элементы дерева должны следовать так как показано на картинке:
 * Для начала сделаем наше дерево потомком класса AbstractList с параметром типа String, а также реализуем интерфейсы Cloneable и Serializable.
 * Реализацию методов get(int index) и size() пока оставь стандартной.
 * <p>
 * <p>
 * Требования:
 * 1. Класс CustomTree должен поддерживать интерфейс Cloneable.
 * 2. Класс CustomTree должен поддерживать интерфейс Serializable.
 * 3. Класс CustomTree должен быть потомком класса AbstractList.
 * <p>
 * Построй дерево(2)
 * Несмотря на то что наше дерево является потомком класса AbstractList, это не список в привычном понимании.
 * В частности нам недоступны принимающие в качестве параметра индекс элемента.
 * Такие методы необходимо переопределить и бросить новое исключение типа UnsupportedOperationException.
 * <p>
 * Вот их список:
 * public String get(int index)
 * public String set(int index, String element)
 * public void add(int index, String element)
 * public String remove(int index)
 * public List<String> subList(int fromIndex, int toIndex)
 * protected void removeRange(int fromIndex, int toIndex)
 * public boolean addAll(int index, Collection<? extends String> c)
 * <p>
 * <p>
 * Требования:
 * 1. При попытке вызвать метод get(int index) должно возникать исключение типа UnsupportedOperationException.
 * 2. При попытке вызвать метод set(int index, String element) должно возникать исключение типа UnsupportedOperationException.
 * 3. При попытке вызвать метод add(int index, String element) должно возникать исключение типа UnsupportedOperationException.
 * 4. При попытке вызвать метод String remove(int index) должно возникать исключение типа UnsupportedOperationException.
 * 5. При попытке вызвать метод subList(int fromIndex, int toIndex) должно возникать исключение типа UnsupportedOperationException.
 * 6. При попытке вызвать метод removeRange(int fromIndex, int toIndex) должно возникать исключение типа UnsupportedOperationException.
 * 7. При попытке вызвать метод addAll(int index, Collection c) должно возникать исключение типа UnsupportedOperationException.
 */

public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;

    protected CustomTree() {
        root = new Entry<>("root");
    }

    @Override
    public boolean add(String s) {
        return super.add(s);
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return super.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        return super.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return super.retainAll(c);
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        return super.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return super.lastIndexOf(o);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<String> iterator() {
        return super.iterator();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    @Override
    public Object[] toArray() {
        return super.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return super.toArray(a);
    }

    @Override
    public ListIterator<String> listIterator() {
        return super.listIterator();
    }

    @Override
    public ListIterator<String> listIterator(int index) {
        return super.listIterator(index);
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    static class Entry<T> implements Serializable {
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public void checkChildren() {
            if (leftChild != null) availableToAddLeftChildren = false;
            if (rightChild != null) availableToAddRightChildren = false;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }

    }
}
