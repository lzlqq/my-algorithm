package com.leo.algorithm.linklist;

/**
 * 有序列表
 */
public class SortedList {

    private Link first;

    public SortedList() {
        this.first = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insert(long key) {
        Link newLink = new Link(key);
        Link previous = null;
        Link current = first;

        while (current != null && key > current.dData) {
            previous = current;
            current = current.next;
        }
        if (previous == null) {
            first = newLink;
        } else {
            previous.next = newLink;
        }
        newLink.next = current;
    }

    public Link remove() {
        Link temp = first;
        first = first.next;
        return temp;
    }

    public void displayList() {
        System.out.print("List (first-->last): ");
        Link current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println("");
    }

    class Link {
        public long dData;
        public Link next;

        public Link(long dData) {
            this.dData = dData;
        }

        public void displayLink() {
            System.out.print(+dData + " ");
        }

    }
}
