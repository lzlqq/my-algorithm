package com.leo.data.list;

/**
 * 双端链表
 */
public class FirstLastList {

    private Link first;
    private Link last;

    public FirstLastList() {
        this.first = null;
        this.last = null;
    }

    public FirstLastList(Link first, Link last) {
        this.first = first;
        this.last = last;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertFirst(double dData) {
        Link newLink = new Link(0, dData);
        if (isEmpty()) {
            last = newLink;
        }
        newLink.next = first;
        first = newLink;
    }

    public void insertLast(double dData) {
        Link newLink = new Link(0, dData);
        if (isEmpty()) {
            first = newLink;
        } else {
            last.next = newLink;
        }
        last = newLink;
    }

    public double deleteFirst() {
        double temp = first.dData;
        if (first.next == null) {
            last = null;
        }
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
        public int iData;
        public double dData;
        public Link next;

        public Link(int iData, double dData) {
            this.iData = iData;
            this.dData = dData;
        }

        public void displayLink() {
            System.out.print("{" + iData + ", " + dData + "} ");
        }

    }
}
