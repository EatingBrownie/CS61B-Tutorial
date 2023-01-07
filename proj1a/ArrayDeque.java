

import java.util.Iterator;

public class ArrayDeque<Item> {
    private int size;
    private Item[] items;
    // items contains the whole address
    private int nextFirst;
    // if use "addFirst", this is the index
    private int nextLast;
    // if use "addLast", this is the index
    private boolean MoveFirst = false;
    // use to find if addFirst() is used
    private boolean MoveLast = false;
    // use t find if addLast() is used

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
            // todo change the wizPos
            if ( !MoveFirst ){
                // which means there is first thing
                wizPos = 5;
            }else{
                wizPos = nextFirst + 1;
            }
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
        // we use addLast to insert into the specific position
        // and renew the nextLast
        if (size == items.length){
            resize(size + 1);
        }
        items[nextLast] = x;
        size += 1;
        nextLast = plusOne(nextLast);
    }

    /** Insert X into the front of the list.*/
    public void addFirst(Item x){
        if (size == items.length){
            resize(size + 1);
        }
        items[nextFirst] = x;
        size += 1;
        nextFirst = minusOne(nextFirst);
        MoveFirst = true;
    }

    /** Resize the underlying array to the target capacity. */
    private void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
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
        if ( !MoveLast ){
            index = 5;
            nextLast = minusOne(nextLast);
            nextFirst = index;
        }else{
            index = minusOne(nextLast);
            nextLast = index;
        }
        x = items[index];
        items[index] = null;
        size -= 1;
        return x;

    }

    /** Deletes item from front of the list and
     * returns deleted item. */
    public Item removeFirst() {
        // removeFirst, if we have already addFirst
        //
        Item x;
        int index;
        if ( !MoveFirst ){
            index = 4;
            nextFirst = plusOne(nextFirst);
            nextLast = index;
        }else{
            index = plusOne(nextFirst);
            nextFirst = index;
        }
        x = items[index];
        items[index] = null;
        size -= 1;
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
        //aList.addLast("c");
        aList.addFirst("d");
        aList.addLast("e");
        aList.addLast("f");
        aList.addLast("g");


        aList.printDeque();

        System.out.println("remove the first one: " + aList.removeFirst());
        System.out.println("remove the first one: " + aList.removeFirst());
        System.out.println("remove the first one: " + aList.removeFirst());

        System.out.println("the 0th item: " + aList.get(0));
        System.out.println("the last one: " + aList.getLast());
        aList.printDeque();

    }


}

