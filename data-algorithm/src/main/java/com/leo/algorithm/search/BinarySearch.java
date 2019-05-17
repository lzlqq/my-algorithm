package com.leo.algorithm.search;

/**
 * 递归二分查找
 */
public class BinarySearch {

    public static void main(String[] args) {
        OrdArray o = new OrdArray(10);
        o.insert(72);
        o.insert(90);
        o.insert(45);
        o.insert(126);
        int searchKey = 126;
        System.out.println(o.find(searchKey));
    }

    static class OrdArray {
        private long[] a;
        private int nElems;

        public OrdArray(int max) {
            a = new long[max];
            nElems = 0;
        }

        public int size() {
            return nElems;
        }

        public int find(long searchKey) {
            return recFind(searchKey, 0, nElems - 1);
        }

        private int recFind(long searchKey, int lowerBound, int upperBound) {
            int curIn;
            curIn = (lowerBound + upperBound) / 2;
            if (a[curIn] == searchKey) {
                return curIn;
            } else if (lowerBound > upperBound) {
                return nElems;
            } else {
                if (a[curIn] < searchKey) {
                    return recFind(searchKey, curIn + 1, upperBound);
                } else {
                    return recFind(searchKey, lowerBound, curIn - 1);
                }
            }
        }

        public void insert(long value) {
            int j;
            for (j = 0; j < nElems; j++) {
                if (a[j] > value) {
                    break;
                }
            }
            for (int k = nElems; k > j; k--) {
                a[k] = a[k - 1];
            }
            a[j] = value;
            nElems++;
        }

        public void display() {
            for (int i = 0; i < nElems; i++) {
                System.out.print(a[i] + " ");
            }

        }

    }
}
