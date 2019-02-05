package com.leo.algorithm.linklist;

public class InterIteratorLinkList {

    private Link first;

    public InterIteratorLinkList() {
        first = null;
    }

    public Link getFirst() {
        return first;
    }

    public void setFirst(Link first) {
        this.first = first;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public ListIterator getIterator() {
        return new ListIterator(this);
    }

    public void displayForward() {
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

    class ListIterator {
        private Link current;
        private Link previous;
        private InterIteratorLinkList outList;

        public ListIterator(InterIteratorLinkList interIterator) {
            outList = interIterator;
            reset();
        }

        private void reset() {
            current = outList.getFirst();
            previous = null;
        }

        public boolean atEnd() {
            return current.next == null;
        }

        public void nextLink() {
            previous = current;
            current = current.next;
        }

        public Link getCurrent() {
            return current;
        }

        public void insertAfter(long dData) {
            Link newLink = new Link(dData);
            if (outList.isEmpty()) {
                outList.setFirst(newLink);
                current = newLink;
            } else {
                newLink.next = current.next;
                current.next = newLink;
                nextLink();
            }
        }

        public void insertBefore(long dData) {
            Link newLink = new Link(dData);
            if (previous == null) {
                newLink.next = outList.getFirst();
                outList.setFirst(newLink);
                reset();
            } else {
                newLink.next = previous.next;
                previous.next = newLink;
                current = newLink;
            }
        }

        public long deleteCurrent() {
            long value = current.dData;
            if (previous == null) {
                outList.setFirst(current.next);
                reset();
            } else {
                previous.next = current.next;
                if (atEnd()) {
                    reset();
                } else {
                    current = current.next;
                }
            }
            return value;
        }
    }
}
