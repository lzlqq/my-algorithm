package com.leo.algorithm.sort;

/**
 * 归并排序 N*logN
 */
public class MergeSort {

    public static void main(String[] args) {
        Darray arr = new Darray(10);
        arr.insert(64);
      arr.insert(21);
//        arr.insert(33);
//        arr.insert(70);
//        arr.insert(12);
//        arr.insert(85);
        arr.display();
        arr.mergeSort();
        arr.display();
    }

    static class Darray {
        private long[] theArray;
        private int nElems;

        public Darray(int max) {
            theArray = new long[max];
            nElems = 0;
        }

        public void insert(long value) {
            theArray[nElems] = value;
            nElems++;
        }

        public void display() {
            for (int i = 0; i < nElems; i++) {
                System.out.print(theArray[i] + " ");
            }
            System.out.println();
        }

        public void mergeSort() {
            long[] workSpace = new long[nElems];
            recMergeSort(workSpace, 0, nElems-1);
        }

        public void recMergeSort(long[] workSpace, int lowerBound, int upperBound) {
            if (lowerBound == upperBound) {
                return;
            } else {
                int mid = (lowerBound + upperBound) / 2;
                recMergeSort(workSpace, lowerBound, mid);
                recMergeSort(workSpace, mid + 1, upperBound);
                merge(workSpace, lowerBound, mid + 1, upperBound);
            }
        }

        private void merge(long[] workSpace, int lowerPtr, int highPtr, int upperBound) {
            int j = 0;
            int lowerBound = lowerPtr;
            int mid = highPtr - 1;
            int n = upperBound - lowerBound + 1;
            while (lowerPtr <= mid && highPtr <= upperBound) {
                if (theArray[lowerPtr] < theArray[highPtr]) {
                    workSpace[j++] = theArray[lowerPtr++];
                } else {
                    workSpace[j++] = theArray[highPtr++];
                }
            }
            while (lowerPtr <= mid) {
                workSpace[j++] = theArray[lowerPtr++];
            }
            while (highPtr <= upperBound) {
                workSpace[j++] = theArray[highPtr++];
            }
            for (j = 0; j < n; j++) {
                theArray[lowerBound + j] = workSpace[j];
            }
        }
    }
}
