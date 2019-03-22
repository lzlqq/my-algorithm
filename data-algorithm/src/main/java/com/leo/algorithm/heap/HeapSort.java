package com.leo.algorithm.heap;

/**
 * 堆排序
 * 1.先放入数组中
 * 2.遍历数组调用trickleDown（）方法
 * 3.调用remove方法
 */
public class HeapSort {


    private Node[] heapArray;
    @SuppressWarnings("unused")
    private int maxSize;
    private int currentSize;
    
    
    public static void main(String[] args){
        //sort
        HeapSort heap =new HeapSort(100);
        for (int i = heap.currentSize/2-1; i >=0; i--){
            heap.trickleDown(i);
        }
        for(int i=heap.currentSize-1;i>=0;i--) {
            heap.remove();
            // insert(i,node)
        }
    }

    public HeapSort(int maxSize) {
        this.maxSize = maxSize;
        currentSize = 0;
        heapArray = new Node[maxSize];
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }


    public Node remove() {// delete item with max key,assumes non-empty list
        Node root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }

    public void trickleDown(int index) {
        int largerChild;
        Node top = heapArray[index];
        while (index < currentSize / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;
            if (rightChild < currentSize && heapArray[leftChild].getKey() < heapArray[rightChild].getKey()) {
                largerChild = rightChild;//rightChild exists?
            } else {
                largerChild = leftChild;
            }
            if (top.getKey() >= heapArray[largerChild].getKey()) {
                break;
            }
            heapArray[index] = heapArray[largerChild];
            index = largerChild;
        }
        heapArray[index] = top;
    }


    public void displayHeap() {
        System.out.print("HeapArray: ");
        for (int i = 0; i < currentSize; i++) {
            if (heapArray[i] != null) {
                System.out.print(heapArray[i].getKey() + " ");
            } else {
                System.out.print("-- ");
            }
        }
        System.out.println();
        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0;
        String dots = "------------------------------";
        System.out.println(dots + dots);
        while (currentSize > 0) {
            if (column == 0) {
                for (int k = 0; k < nBlanks; k++) {
                    System.out.print(" ");
                }
                System.out.print(heapArray[j].getKey());
                if (++j == currentSize) {
                    break;
                }
                if (++column == itemsPerRow) {
                    nBlanks /= 2;
                    itemsPerRow *= 2;
                    column = 0;
                    System.out.println();
                } else {
                    for (int k = 0; k < nBlanks * 2 - 2; k++) {
                        System.out.println(" ");
                    }
                }
                System.out.println(dots + dots);
            }
        }
    }

    public void insertAt(int index, Node newNode) {
        heapArray[index] = newNode;
    }

    public void incrementSize() {
        currentSize++;
    }

    class Node {
        private int iData;

        public Node(int iData) {
            this.iData = iData;
        }

        public int getKey() {
            return iData;
        }

        public void setKey(int iData) {
            this.iData = iData;
        }
    }
}
