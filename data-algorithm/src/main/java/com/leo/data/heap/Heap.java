package com.leo.data.heap;

/**
 * 堆
 */
public class Heap{

    private Node[] heapArray;

    private int maxSize;

    private int currentSize;

    public Heap(int maxSize){
        this.maxSize = maxSize;
        currentSize = 0;
        heapArray = new Node[maxSize];
    }

    public boolean isEmpty(){
        return currentSize == 0;
    }

    /**
     * 若数组中节点的索引为x，则
     * 1.它的父节点的下标为(x-1)/2
     * 2.它的左子节点的下标为2*x+1
     * 3.它的右子节点的下标为2*x+2
     * 注意：记住"/"这个符号，应用于整数的算式时，它执行整数，且得到的结果时下取整数的值
     */
    public boolean insert(int key){
        if (currentSize == maxSize){
            return false;
        }
        Node newNode = new Node(key);
        heapArray[currentSize] = newNode;
        trickleUp(currentSize++);
        return true;
    }

    private void trickleUp(int index){
        int parent = (index - 1) / 2;
        Node bottom = heapArray[index];
        while (index > 0 && heapArray[parent].getKey() < bottom.getKey()){
            heapArray[index] = heapArray[parent];
            index = parent;
            parent = (parent - 1) / 2;
        }
        heapArray[index] = bottom;
    }

    public Node remove(){// delete item with max key,assumes non-empty list
        Node root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }

    private void trickleDown(int index){
        int largerChild;
        Node top = heapArray[index];
        while (index < currentSize / 2){
            // 前提 2*index < currentSize => index < currentSize/2
            // 因为 index 最大等于 currentSize/2-1 => 2*index+2=2*(currentSize/2-1)+2=currentSize=>下标刚好是currentSize，但此时右下标为空对应的值刚好越界了
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;
            if (rightChild < currentSize && heapArray[leftChild].getKey() < heapArray[rightChild].getKey()){
                largerChild = rightChild;//rightChild exists?
            }else{
                largerChild = leftChild;
            }
            if (top.getKey() >= heapArray[largerChild].getKey()){
                break;
            }
            heapArray[index] = heapArray[largerChild];
            index = largerChild;
        }
        heapArray[index] = top;
    }

    public boolean change(int index,int newValue){
        if (index < 0 || index >= currentSize){
            return false;
        }
        int oldValue = heapArray[index].getKey();//remember old
        heapArray[index].setKey(newValue);
        if (oldValue < newValue){
            trickleUp(index);
        }else{
            trickleDown(index);
        }
        return true;
    }

    public void displayHeap(){
        System.out.print("HeapArray: ");
        for (int i = 0; i < currentSize; i++){
            if (heapArray[i] != null){
                System.out.print(heapArray[i].getKey() + " ");
            }else{
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
        while (currentSize > 0){
            if (column == 0){
                for (int k = 0; k < nBlanks; k++){
                    System.out.print(" ");
                }
                System.out.print(heapArray[j].getKey());
                if (++j == currentSize){
                    break;
                }
                if (++column == itemsPerRow){
                    nBlanks /= 2;
                    itemsPerRow *= 2;
                    column = 0;
                    System.out.println();
                }else{
                    for (int k = 0; k < nBlanks * 2 - 2; k++){
                        System.out.println(" ");
                    }
                }
                System.out.println(dots + dots);
            }
        }
    }

    class Node{

        private int iData;

        public Node(int iData){
            this.iData = iData;
        }

        public int getKey(){
            return iData;
        }

        public void setKey(int iData){
            this.iData = iData;
        }
    }
}
