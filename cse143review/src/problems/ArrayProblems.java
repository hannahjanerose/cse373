package problems;

/**
 * See the spec on the website for example behavior.
 *
 * REMEMBER THE FOLLOWING RESTRICTIONS:
 * - Do not add any additional imports
 * - Do not create new `int[]` objects for `toString` or `rotateRight`
 */
public class ArrayProblems {

    /**
     * Returns a `String` representation of the input array.
     * Always starts with '[' and ends with ']'; elements are separated by ',' and a space.
     */
    public static String toString(int[] array) {
        String result = "[";
        if (array.length > 0) {
            for (int i = 0; i < array.length - 1; i++) {
                result += array[i] + ", ";
            }
            result += array[array.length - 1] + "]";
        } else {
            result += "]";
        }
        return result;
    }

    /**
     * Returns a new array containing the input array's elements in reversed order.
     * Does not modify the input array.
     */
    public static int[] reverse(int[] array) {
        // TODO replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Rotates the values in the array to the right.
     */
    public static void rotateRight(int[] array) {
        // TODO replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
