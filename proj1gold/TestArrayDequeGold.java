import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    /*
    * Your test should randomly call StudentArrayDeque and ArrayDequeSolution methods,
    * until they disagree on an output.
    *
    * */
    @Test
    public void testAddAndRemove(){
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();

        String operation = "\n";

        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addLast(i);
                ads1.addLast(i);
                operation += "addLast(" + i + ") \n";
            } else {
                sad1.addFirst(i);
                ads1.addFirst(i);
                operation += "addFirst(" + i + ") \n";
            }
        }

        for (int i = 0; i < 10; i++) {
            Integer expected = ads1.removeLast();
            Integer actual = sad1.removeLast();
            operation += "removeLast() \n";
            assertEquals(operation,
                    expected, actual);
        }

    }


    @Test
    public void testGet(){

        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();

        String operation = "\n";

        for (int i = 0; i < 20; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addLast(i);
                ads1.addLast(i);
                operation += "addLast(" + i + ") \n";
            } else {
                sad1.addFirst(i);
                ads1.addFirst(i);
                operation += "addFirst(" + i + ") \n";
            }
        }

        for (int i = 0; i < 20; i++) {
            Integer numberBetweenZeroAndNine = StdRandom.uniform(10);
            Integer expected = ads1.get(numberBetweenZeroAndNine);
            Integer actual = sad1.get(numberBetweenZeroAndNine);
            operation += "get(" + numberBetweenZeroAndNine + ") \n";
            assertEquals(operation,
                    expected, actual);
        }
    }



}
