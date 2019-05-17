package com.leo.algorithm.sort.select;

/**
 * Created by LSH7120 on 2019/1/31.
 */
public class SelectSort {

    public static void main(String[] args) {
        int maxSize = 100;
        ArraySel arr = new ArraySel(maxSize);
        arr.insert(77);
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(33);
        arr.display();
        arr.selectSort();
        arr.display();
    }

    static class ArraySel {
        private long[] a;
        private int nElems;

        public ArraySel(int max) {
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
         * 每趟外层循环，只交换一次
         * <p>
         * 效率：
         * O(N2)
         */
        public void selectSort() {
            int out, in, min;
            for (out = 0; out < nElems - 1; out++) {
                min = out;
                for (in = out + 1; in < nElems; in++) {
                    if (a[in] < a[min]) {
                        min = in;
                    }
                }
                swap(out, min);
            }
        }

        private void swap(int one, int two) {
            long temp = a[one];
            a[one] = a[two];
            a[two] = temp;
        }

    }
}
