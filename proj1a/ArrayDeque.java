

import java.util.Iterator;

public class ArrayDeque<Item> {
    private int size;
    private Item[] items;
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
        items = (Item[])new Object [8];
        nextFirst = 4;
        nextLast = 5;
    }

    private class DequeIterator implements Iterator<Item> {
        private int wizPos;
        private int count;
        public DequeIterator() {
                wizPos = plusOne(nextFirst);
        }
        public boolean hasNext() { return count < size; }
        public Item next() {
            Item returnItem = items[wizPos];
            wizPos = plusOne(wizPos);
            count += 1;
            return returnItem;
        }
    }


    /** Inserts X into the back of the list. */
    public void addLast(Item x) {
        if (size == items.length){
            resize(size * 2);
        }
        items[nextLast] = x;
        size += 1;
        nextLast = plusOne(nextLast);
        usage = (double) (size) / items.length;

    }

    /** Insert X into the front of the list.*/
    public void addFirst(Item x){
        if (size == items.length){
            resize(size * 2);
        }
        items[nextFirst] = x;
        size += 1;
        nextFirst = minusOne(nextFirst);
        usage = (double) (size) / items.length;
    }

    /** Resize the underlying array to the target capacity. */
    private void resize(int capacity) {
        // whether longer or shorter, judge from outside
        // then renew nextFirst and nextLast
        Item[] a = (Item[]) new Object[capacity];
        int temp = 0;
        DequeIterator dequeIterator = new DequeIterator();
        while(dequeIterator.hasNext()){
            a[temp] = dequeIterator.next();
            temp += 1;
        }
        nextFirst = capacity - 1;
        nextLast = size;
        items = a;
    }

    public boolean needResize(){
        // here, size should be renew (i.e. size - 1)
        usage = (double) (size) / items.length;
        return (usage < USAGE_FACTOR && items.length >= 16);
    }


    /** Returns the item from the back of the list. */
    public Item getLast() {
        return items[ minusOne(nextLast) ];
    }

    /** Gets the ith item in the list (0 is the front). */
    public Item get(int i) {
        DequeIterator dequeIterator = new DequeIterator();
        Item x = null;
        for (int j = 0; j <= i; j++) {
            x = dequeIterator.next();
        }
        return x;
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public Item removeLast() {
        Item x;
        int index;

        if (needResize()){
            resize(items.length / 2);
        }
        index = minusOne(nextLast);
        nextLast = index;
        size -= 1;
        x = items[index];
        items[index] = null;
        return x;

    }

    /** Deletes item from front of the list and
     * returns deleted item. */
    public Item removeFirst() {
        // removeFirst, if we have already addFirst
        Item x;
        int index;

        if (needResize()){
            resize(items.length / 2);
        }
        index = plusOne(nextFirst);
        size -= 1;
        nextFirst = index;
        x = items[index];
        items[index] = null;
        return x;
    }



    public void printDeque(){
        DequeIterator dequeIterator = new DequeIterator();
        while(dequeIterator.hasNext()){
            System.out.print(dequeIterator.next() + " ");
        }
        System.out.println();
    }

    /** Use for nextFirst*/
    int minusOne(int index){
        if (index == 0){
            return items.length - 1;
        }
        return index - 1;
    }

    /** Use for nextLast*/
    int plusOne(int index){
        if (index == items.length - 1){
            return 0;
        }
        return index + 1;
    }

    public static void main(String[] args) {
        ArrayDeque<String> aList = new ArrayDeque();
        aList.addLast("a");
        aList.addLast("b");
        aList.addLast("c");
        aList.addFirst("d");
        aList.addLast("e");
        aList.addLast("f");
        aList.addLast("g");
        aList.addLast("h");
        aList.addLast("i");
        for (int i = 0; i < 20; i++) {
            aList.addLast("j" + i);
        }
        System.out.println("----------insert completely-----------");
        aList.printDeque();
        System.out.println("size = " + aList.size());



        for (int i = 0; i < 29; i++) {
            aList.removeLast();
        }
        System.out.println("----------remove  completely-----------");
        aList.printDeque();
        System.out.println("size = " + aList.size());


        System.out.println("----------continue to insert--------------");
        for (int i = 0; i < 9; i++) {
            aList.addLast("j" + i);
        }

        System.out.println("the 0th item: " + aList.get(0));
        System.out.println("the 1st item: " + aList.get(1));
        System.out.println("the last one: " + aList.getLast());
        aList.printDeque();

    }


}

