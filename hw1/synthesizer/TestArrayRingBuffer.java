package synthesizer;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(4);
        arb.enqueue("a");
        arb.enqueue("b");
        arb.enqueue("c");
        arb.enqueue("d");

        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();

        arb.enqueue("x");
        arb.enqueue("y");


        Iterator iterator = arb.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
