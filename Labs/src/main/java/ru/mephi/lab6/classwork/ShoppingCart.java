package ru.mephi.lab6.classwork;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ShoppingCart implements Serializable {
    private static final long serialVersionUID = 1L;
    private int cartID;
    private ArrayList<Item> items;
    private int itemCount;
    private transient double cartTotal;
    private LocalDateTime curTime;

    public ShoppingCart() {
    }

    public ShoppingCart(int cartID, int itemCount, double cartTotal, ArrayList<Item> items) {
        this.cartID = cartID;
        this.itemCount = itemCount;
        this.cartTotal = cartTotal;
        this.items = items;
    }

    public LocalDateTime getCurTime() {
        return curTime;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cartID=" + cartID +
                ", items=" + items +
                ", itemCount=" + itemCount +
                ", cartTotal=" + cartTotal +
                '}';
    }

    public void writeObject() throws IOException {
        LocalDateTime curentDataTime = LocalDateTime.now();
        FileOutputStream outputStream = new FileOutputStream("Labs/src/main/java/ru/mephi/lab6/serialize");
        this.curTime = LocalDateTime.now();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.close();
    }

    public void readObject() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("Labs/src/main/java/ru/mephi/lab6/serialize");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ShoppingCart scSer = (ShoppingCart) objectInputStream.readObject();
        this.cartID = scSer.cartID;
        this.itemCount = scSer.itemCount;
        this.items = scSer.items;
        this.curTime = scSer.curTime;
        this.cartTotal = 30;
    }
}
