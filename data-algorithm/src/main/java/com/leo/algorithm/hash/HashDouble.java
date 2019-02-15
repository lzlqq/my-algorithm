package com.leo.algorithm.hash;

/**
 * 开放地址法-再哈希法
 */
public class HashDouble {
    private DataItem[] hashArray;
    private int arraySize;
    private DataItem nonItem;

    public HashDouble(int size) {
        arraySize = size;
        hashArray = new DataItem[arraySize];
        nonItem = new DataItem(-1);
    }

    public void displayTable() {
        System.out.print("Table: ");
        for (int j = 0; j < arraySize; j++) {
            if (hashArray[j] != null) {
                System.out.print(hashArray[j].getKey() + " ");
            } else {
                System.out.print("** ");
            }
        }
        System.out.println();
    }

    public int hashFunc1(int key) {
        return key % arraySize;
    }

    public int hashFunc2(int key) {
        //non-zero,less than array size,different from hF1
        //array size must be relatively prime to 5,4,3,and 2
        return 5 - key % 5;
    }

    public void insert(int key, DataItem item) {
        //assumes table not full
        int hashVal = hashFunc1(key);
        int stepSize = hashFunc2(key);
        while (hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {
            hashVal += stepSize;//add the step
            hashVal %= arraySize;//for wraparound
        }
        hashArray[hashVal] = item;
    }

    public DataItem delete(int key) {
        int hasVal = hashFunc1(key);
        int stepSize = hashFunc2(key);
        while (hashArray[hasVal] != null) {
            if (hashArray[hasVal].getKey() == key) {
                DataItem temp = hashArray[hasVal];
                hashArray[hasVal] = nonItem;
                return temp;
            }
            hasVal += stepSize;
            hasVal %= arraySize;
        }
        return null;
    }

    public DataItem find(int key) {
        //assumes table not full
        int hashVal = hashFunc1(key);
        int stepSize = hashFunc2(key);
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) {
                return hashArray[hashVal];
            }
            hashVal += stepSize;
            hashVal %= arraySize;
        }
        return null;
    }

    class DataItem {
        private int iData;

        public DataItem(int iData) {
            this.iData = iData;
        }

        public int getKey() {
            return iData;
        }
    }
}
