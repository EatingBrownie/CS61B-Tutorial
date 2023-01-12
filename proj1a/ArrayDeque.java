

import java.util.Iterator;

public class ArrayDeque<T> {
    private int size;
    private T[] items;
    // items contains the whole address
    private int nextFirst;
    // if use "addFirst", this is the index
    private int nextLast;
    // if use "addLast", this is the index
    private double usage;

    private static final double USAGE_FACTOR = 0.25;


    /** Creates an empty list. */
    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object [8];
        nextFirst = 4;
        nextLast = 5;
    }

    private class DequeIterator implements Iterator<T> {
        private int wizPos;
        private int count;
        DequeIterator() {
            wizPos = plusOne(nextFirst);
        }
        public boolean hasNext() {
            return count < size;
        }
        public T next() {
            T returnItem = items[wizPos];
            wizPos = plusOne(wizPos);
            count += 1;
            return returnItem;
        }
    }

    /** Insert X into the front of the list.*/
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = x;
        size += 1;
        nextFirst = minusOne(nextFirst);
        usage = (double) (size) / items.length;
    }

    /** Inserts X into the back of the list. */
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = x;
        size += 1;
        nextLast = plusOne(nextLast);
        usage = (double) (size) / items.length;

    }

    /** use to check if it is empty*/
    public boolean isEmpty() {
        return (size == 0);
    }

    /** Returns the number of items in the list. */
    public int size() {
        if (size < 0) {
            size = 0;
            return size;
        }
        return size;
    }

    public void printDeque() {
        DequeIterator dequeIterator = new DequeIterator();
        while (dequeIterator.hasNext()) {
            System.out.print(dequeIterator.next() + " ");
        }
        System.out.println();
    }

    /** Deletes item from front of the list and
     * returns deleted item. */
    public T removeFirst() {
        // removeFirst, if we have already addFirst
        T x;
        int index;

        if (needResize()) {
            resize(items.length / 2);
        }
        index = plusOne(nextFirst);
        size -= 1;
        nextFirst = index;
        x = items[index];
        items[index] = null;
        return x;
    }


    /** Deletes item from back of the list and
     * returns deleted item. */
    public T removeLast() {
        T x;
        int index;

        if (needResize()) {
            resize(items.length / 2);
        }
        index = minusOne(nextLast);
        nextLast = index;
        size -= 1;
        x = items[index];
        items[index] = null;
        return x;

    }

    /** Gets the ith item in the list (0 is the front). */
    public T get(int i) {
        DequeIterator dequeIterator = new DequeIterator();
        T x = null;
        for (int j = 0; j <= i; j++) {
            x = dequeIterator.next();
        }
        return x;
    }


    /** Resize the underlying array to the target capacity. */
    private void resize(int capacity) {
        // whether longer or shorter, judge from outside
        // then renew nextFirst and nextLast
        T[] a = (T[]) new Object[capacity];
        int temp = 0;
        DequeIterator dequeIterator = new DequeIterator();
        while (dequeIterator.hasNext()) {
            a[temp] = dequeIterator.next();
            temp += 1;
        }
        nextFirst = capacity - 1;
        nextLast = size;
        items = a;
    }

    private boolean needResize() {
        // here, size should be renew (i.e. size - 1)
        usage = (double) (size) / items.length;
        return (usage < USAGE_FACTOR && items.length >= 16);
    }


    /** Use for nextFirst*/
    private int minusOne(int index) {
        if (index == 0) {
            return items.length - 1;
        }
        return index - 1;
    }

    /** Use for nextLast*/
    private int plusOne(int index) {
        if (index == items.length - 1) {
            return 0;
        }
        return index + 1;
    }


}

