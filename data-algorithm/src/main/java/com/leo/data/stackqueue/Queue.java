package com.leo.data.stackqueue;

/**
 * Created by LSH7120 on 2019/2/1.
 */
public class Queue {
    private int maxSize;
    private long[] queArray;
    private int front;
    private int rear;
    private int nItems;

    public Queue(int maxSize) {
        this.maxSize = maxSize;
        queArray = new long[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    public void insert(long j) {
        if (rear == maxSize - 1) {
            rear = -1;
        }
        queArray[++rear] = j;
        nItems++;
    }

    public long remove() {
        long temp = queArray[front++];
        if (front == maxSize) {
            front = 0;
        }
        nItems--;
        return temp;
    }

    public long peekFront() {
        return queArray[front];
    }

    public boolean isEmpty() {
        return nItems == 0;
    }

    public boolean isFull() {
        return nItems == maxSize;
    }

    public int size() {
        return nItems;
    }

    public static void main(String[] args) {
        Queue theQueue = new Queue(5);
        theQueue.insert(5);
        theQueue.insert(10);
        theQueue.remove();
        theQueue.insert(10);
        theQueue.insert(11);
        theQueue.insert(12);
        theQueue.insert(13);
        while (!theQueue.isEmpty()){
            long n = theQueue.remove();
            System.out.print(n+" ");
        }
    }
}
