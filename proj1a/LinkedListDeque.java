

import java.util.Iterator;

public class LinkedListDeque<Item> {

    private int size;
    private IntNode sentinel;
    private IntNode p;
    private Item returnItem = (Item) new Object();

    public class IntNode {
        private IntNode prev;
        private Item item;
        private IntNode next;
        public IntNode(IntNode p, Item i, IntNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private class DequeIterator implements Iterator<Item> {

        private int wizPos;
        private LinkedListDeque linkedListDeque;
        private IntNode p;

        DequeIterator(LinkedListDeque linkedListDeque) {
            wizPos = 0;
            this.linkedListDeque = linkedListDeque;
            p = linkedListDeque.sentinel;
        }

        @Override
        public boolean hasNext() {
            return wizPos < size();
        }

        @Override
        public Item next() {
            wizPos += 1;
            p = p.next;
            return p.item;
        }
    }

    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        //T a = (T)  new Object ();
        sentinel = new IntNode(null, null, null);

        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        // the number in sentinel node can be any
        // but we should consider different types
        // It is a constructor, we should make a circle here
        p = this.sentinel;
        size = 0;
    }

    /** Creates a deep copy of other
     Creating a deep copy means that you create an entirely new LinkedListDeque,
     with the exact same items as other */
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new IntNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        p = other.sentinel;
        size = 0;

        for (int i = 0; i < other.size(); i++) {
            addLast((Item) other.get(i));
            size += 1;
        }
    }

    public Item getRecursive(int index) {
        if (size() == 0) {
            return null;
        }
        if (index == 0) {
            returnItem = p.next.item;
            p = sentinel;
            return returnItem;
        } else {
            p = p.next;
            index -= 1;
            return getRecursive(index);
        }
    }


    public void addFirst(Item item) {
        size += 1;
        sentinel.next = new IntNode(sentinel, item, sentinel.next);
        // first add the new node in,
        // the prev points to sentinel node
        sentinel.next.next.prev = sentinel.next;
        // the node should reset the prev
        // the second one's prev should point to the new node
    }

    public void addLast(Item item) {
        size += 1;
        sentinel.prev = new IntNode(sentinel.prev, item, sentinel);
        // we only need to set the node point
        // sentinel node, next points to the same reference
        // then, the prev needs to be changed
        // in the new node constructor, it needs to  figure out the prev and next point
        sentinel.prev.prev.next = sentinel.prev;
        // this line is to switch the last two point (change the "next" to the new node)
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return (size() == 0);
        // cannot use the "sentinel.next == null" to judge
        // because I now use the circular type
    }

    /** Returns the number of items in the deque. */
    public int size() {
        if (size < 0) {
            size = 0;
            return size;
        }
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     Once all the items have been printed, print out a new line.*/
    public void printDeque() {
        DequeIterator dequeIterator = new DequeIterator(this);
        while (dequeIterator.hasNext()) {
            System.out.print(dequeIterator.next() + " ");
        }
        System.out.println();

    }

    /** Removes and returns the item at the front of the deque.
     If no such item exists, returns null.*/
    public Item removeFirst() {
        if (size() == 0) {
            return null;
        }
        returnItem = sentinel.next.item;
        size -= 1;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;

        // should avoid one case that we remove more than it has

        return returnItem;
    }


    /** Removes and returns the item at the back of the deque.
     If no such item exists, returns null. */
    public Item removeLast() {
        if (size() == 0) {
            return null;
        }
        returnItem = sentinel.prev.item;
        size -= 1;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return returnItem;
    }


    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     If no such item exists, returns null.
     Must not alter the deque! */
    public Item get(int index) {
        if (index <= size()) {
            for (int i = 0; i <= index; i++) {
                p = p.next;
            }
            returnItem = p.item;
            p = sentinel;
            return returnItem;
        }
        return null;
    }


}
