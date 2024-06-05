package primitives;

/**
 * A counter.
 */
public class Counter {

    private int counter = 0;

    /**
     * add number to current count.
     *
     * @param number a number.
     */
    public void increase(int number) {
        counter = counter + number;
    }

    /**
     * subtract number from current count.
     *
     * @param number a number.
     */
    public void decrease(int number) { counter = counter - number; }

    /**
     * @return current count.
     */
    public int getValue() { return this.counter; }
}
