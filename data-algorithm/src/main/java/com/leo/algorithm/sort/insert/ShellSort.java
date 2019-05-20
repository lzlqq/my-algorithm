package com.leo.algorithm.sort.insert;

/**
 * 希尔排序
 */
public class ShellSort{

    public static void main(String[] args){
        ArraySh arr = new ArraySh(10);
        for (int i = 0; i < 10; i++){
            arr.insert((long) (Math.random() * 100));
        }
        arr.display();
        arr.shellSort();
        arr.display();
    }

    static class ArraySh{

        private long[] theArray;

        private int nElems;

        public ArraySh(int max){
            theArray = new long[max];
            nElems = 0;
        }

        public void insert(long value){
            theArray[nElems] = value;
            nElems++;
        }

        public void display(){
            System.out.print("A=");
            for (int i = 0; i < nElems; i++){
                System.out.print(theArray[i] + " ");
            }
            System.out.println();
        }

        /**
         * 和插入排序的不同是：
         * 1.步长从1变成h
         * 2.步长h有特殊的计算方式，步长从大到小，每缩减一次完成一次步长为h的插入排序，步长最终变成1，完成一次普通插入排序
         */
        public void shellSort(){
            int inner, outer;
            long temp;
            int h = 1;
            while (h < nElems / 3){
                h = h * 3 + 1;
            }
            while (h > 0){
                for (outer = h; outer < nElems; outer++){
                    temp = theArray[outer];
                    inner = outer;
                    while (inner > h - 1 && theArray[inner - h] >= temp){
                        theArray[inner] = theArray[inner - h];
                        inner -= h;
                    }
                    theArray[inner] = temp;
                }
                h = (h - 1) / 3;
            }
        }
    }
}
