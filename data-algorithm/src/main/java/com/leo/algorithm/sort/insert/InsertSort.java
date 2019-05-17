package com.leo.algorithm.sort.insert;

/**
 * Created by LSH7120 on 2019/1/31.
 */
public class InsertSort {

    public static void main(String[] args) {
        int maxSize = 100;
        ArrayIns arr = new ArrayIns(maxSize);
        arr.insert(77);
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(33);
        arr.display();
        arr.insertionSort();
        arr.display();
    }

    static class ArrayIns {
        private long[] a;
        private int nElems;

        public ArrayIns(int max) {
            this.a = new long[max];
            this.nElems = 0;
        }

        public void insert(long value) {
            a[nElems] = value;
            nElems++;
        }

        public void display() {
            for (int i = 0; i < nElems; i++) {
                System.out.print(a[i] + " ");
            }
            System.out.println();
        }

        /**
         * 规则：
         * 局部有序
         * <p>
         * 效率：
         * O(N2)
         */
        public void insertionSort() {
            int out, in;
            for (out = 1; out < nElems; out++) {
                long temp = a[out];
                in = out;
                while (in > 0 && a[in - 1] > temp) {
                    a[in] = a[in - 1];
                    --in;
                }
                a[in] = temp;
            }
        }


    }
}
