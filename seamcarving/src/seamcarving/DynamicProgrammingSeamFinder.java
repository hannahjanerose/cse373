package seamcarving;

import java.util.List;
import java.util.ArrayList;

/**
 * Dynamic programming implementation of the {@link SeamFinder} interface.
 *
 * @see SeamFinder
 * @see SeamCarver
 */
public class DynamicProgrammingSeamFinder implements SeamFinder {
    @Override
    public List<Integer> findHorizontalSeam(double[][] energies) {
        List<Integer> first = new ArrayList<>();
        return first;
    }

    @Override
    public List<Integer> findVerticalSeam(double[][] energies) {
        List<Integer> second = new ArrayList<>();
        return second;
    }
}
