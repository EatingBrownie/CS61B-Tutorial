package synthesizer;

import java.util.Iterator;


// Make sure to make this class and all of its methods public
// Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    private T returnItem;

    private int first(){
        if (first == capacity){
            first = 0;
        }
        return first;
    }

    private int last(){
        if (last == capacity){
            last = 0;
        }
        return last;
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        first = 0;
        last = 0;
        fillCount = 0;
        rb =  (T[]) new Object [capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // Enqueue the item.
        // Don't forget to increase fillCount and update last.
        if (fillCount() == capacity()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last()] = x;
        fillCount += 1;
        last += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // Dequeue the first item.
        // Don't forget to decrease fillCount and update first.
        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        returnItem = rb[first()];
        rb[first()] = null;
        fillCount -= 1;
        first += 1;
        return returnItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        return rb[first()];
    }

    @Override
    public Iterator<T> iterator() {
        return new BufferIterator();
    }

    private class BufferIterator implements Iterator<T> {
        private int wizPos;
        private int count;
        BufferIterator() {
            wizPos = first();
        }

        public boolean hasNext() {
            return count < fillCount;
        }

        public T next() {
            returnItem = rb[wizPos];
            wizPos = plusOne(wizPos);
            count += 1;
            return returnItem;
        }
    }

    private int plusOne(int num){
        return num == capacity ? 0 : (num + 1);
    }

}
