package ru.mephi;

import java.util.Arrays;
public class MyList{
    private int size; // размер списка

    public Object[] list; // список

    private int cap = 10; // выделенная память

    public int getSize() {
        return this.size;
    }
    public MyList() { // пустой конструктор
        this.list = new Object[this.cap];
        this.size = 0;
    }
    public MyList(Object[] arr){ // конструктор по массиву
        this.list = new Object[arr.length];
        System.arraycopy(arr, 0, this.list, 0, arr.length);
        this.size = arr.length;
        this.cap = arr.length;
    }

    private void IncreaseCap(){ // метод увеличения памяти в списке
        this.cap *= 2;
         Object[] newList = new Object[this.cap];
         System.arraycopy(this.list, 0, newList, 0, this.size);
         this.list = newList;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; ++i){
            s.append(list[i]);
            s.append(" ");
        }
        return "size : " + size + "\nlist : " + s;
    }
    public void add(Object value) { // добавлению по значению
        if (this.size >= this.cap) {
            this.IncreaseCap();
        }
        this.list[this.size] = value;
        this.size += 1;
    }
    public void add (Object value, int index){ // добавлению по значению и индексу
        if (index < 0){
            System.out.println("Index can't be less than 0");
            return;
        }
        if (index > this.size){
            this.add(value);
            return;
        } else if (this.size == this.cap){
            this.IncreaseCap();
        }
        Object[] newList;
        newList = this.list;
        this.size += 1;
        System.arraycopy(this.list, index , newList, index + 1, this.size - index);
        this.list[index] = value;
    }
    public Object set(Object value, int index){ // замена элемента на value по индексу
        if (index < 0){
            System.out.println("Index can't be less than 0");
            return null;
        }
        if (index < this.size) {
            Object curItem = this.list[index];
            this.list[index] = value;
            return curItem;
        } else {
            System.out.println("There is no value by this index");
            return null;
        }
    }
    public Object remove(int index){ // удаление элемента из списка
        if (index < 0){
            System.out.println("Index can't be less than 0");
            return -1;
        }
        if (isEmpty()){
            System.out.println("List is empty");
            return null;
        }
        if (index < this.size) {
            Object curElem = this.list[index];
            if (size - 1 - index >= 0)
                System.arraycopy(this.list, index + 1, this.list, index, size - 1 - index);
            this.size -= 1;
            if (this.size * 2 < this.cap){
                this.cap = this.size + 1;
            }
            return curElem;
        } else {
            return null;
        }
    }
    public Object get(int index){
        if (index < 0){
            System.out.println("Index can't be less than 0");
            return null;
        }
        if (index < this.size)
            return this.list[index];
        else
            return -1;
    }
    public boolean isEmpty(){
        return this.size == 0;
    }

    public int indexOf(Object value){ // поиск в списке с помощью java.utils.Arrays
        int key =  Arrays.binarySearch(list, 0, this.size, value);
        if (key < 0){
            return -1;
        } else return key;
    }
    public int indexOF(Object value){ // поиск в списке без помощи Arrays
        for (int i = 0; i < this.size; ++i){
            if (this.list[i].equals(value)){
                return i;
            }
        }
        return -1;
    }
    public boolean contains(Object value){ // содержится ли элемент в списке
        return indexOF(value) >= 0;
    }

    public static void main(String[] args) {
        MyList list1 = new MyList();
        list1.add("abc", 1);
        list1.set("bca", -1);
        System.out.println(list1);
    }
}

