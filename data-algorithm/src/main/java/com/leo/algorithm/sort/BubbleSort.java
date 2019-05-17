package com.leo.algorithm.sort;

/**
 * Created by LSH7120 on 2019/1/31.
 */
public class BubbleSort {
    public static void main(String[] args) {
        int maxSize = 100;
        ArrayBub arr = new ArrayBub(maxSize);
        arr.insert(77);
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(33);
        arr.display();
        arr.bubbleSort();
        arr.display();
    }

    static class ArrayBub {
        private long[] a;
        private int nElems;

        public ArrayBub(int max) {
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
         * 1.比较两个队员
         * 2.如果左边的队员高，则两队员交换位置
         * 3.向右移动一个位置，比较下面两个队员
         * 4.当碰到第一个排定的队员后，就返回到队列的左端重新开始下一趟排序
         * <p>
         * 效率：
         * O(N2)
         */
        public void bubbleSort() {
            int out, in;
            for (out = nElems - 1; out > 1; out--) {
                for (in = 0; in < out; in++) {
                    if (a[in] > a[in + 1]) {
                        swap(in, in + 1);
                    }
                }
            }
        }

        private void swap(int one, int two) {
            long temp = a[one];
            a[one] = a[two];
            a[two] = temp;
        }

    }
}

