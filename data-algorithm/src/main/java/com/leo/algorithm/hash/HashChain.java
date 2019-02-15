package com.leo.algorithm.hash;

/**
 * 链地址法
 */
public class HashChain {

    private SortedList[] hashArray;
    private int arraySize;

    public HashChain(int arraySize) {
        this.arraySize = arraySize;
        this.hashArray = new SortedList[this.arraySize];
        for (int j = 0; j < arraySize; j++) {
            hashArray[j] = new SortedList();
        }
    }

    public void displayTable() {
        for (int i = 0; i < arraySize; i++) {
            System.out.print(i + ". ");
            hashArray[i].displayList();
        }
    }

    public int hashFunc(int key) {
        return key % arraySize;
    }

    public void insert(Link theLink) {
        int hashVal = hashFunc(theLink.getKey());
        hashArray[hashVal].insert(theLink);
    }

    public void delete(int key) {
        int hasVal = hashFunc(key);
        hashArray[hasVal].delete(key);
    }

    public Link find(int key) {
        int hashVal = hashFunc(key);
        return hashArray[hashVal].find(key);
    }

    class SortedList {
        private Link first;

        public SortedList() {
            first = null;
        }

        public void insert(Link theLink) {
            int key = theLink.getKey();
            Link previous = null;
            Link current = first;

            while (current != null && key > current.getKey()) {
                previous = current;
                current = current.next;
            }
            if (previous == null) {
                first = theLink;
            } else {
                previous.next = theLink;
            }
            theLink.next = current;
        }

        public void delete(int key) {
            Link previous = null;
            Link current = first;

            while (current != null && key != current.getKey()) {
                previous = current;
                current = current.next;
            }
            if (previous == null) {
                first = first.next;//assumes non-empty list
            } else {
                previous.next = current.next;
            }
        }

        public Link find(int key) {
            Link current = first;
            //until end of list or key too small
            while (current != null && current.getKey() <= key) {
                if (current.getKey() == key) {
                    return current;
                }
                current = current.next;
            }
            return null;
        }

        public void displayList() {
            System.out.print("List(first-->last): ");
            Link current = first;
            while (current != null) {
                current.displayLink();
                current = current.next;
            }
            System.out.println();
        }
    }

    class Link {
        private int iData;
        public Link next;

        public Link(int iData) {
            this.iData = iData;
        }

        public int getKey() {
            return iData;
        }

        public void displayLink() {
            System.out.print(iData + " ");
        }
    }
}
