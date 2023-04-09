package problems;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * See the spec on the website for example behavior.
 */
public class MapProblems {

    /**
     * Returns true if any string appears at least 3 times in the given list; false otherwise.
     */
    public static boolean contains3(List<String> list) {
        Map<String, Integer> storage = new HashMap<>();
        for (String word : list) {
            if (storage.containsKey(word)) {
                storage.put(word, storage.get(word) + 1);
            } else {
                storage.put(word, 1);
            }
        }
        return storage.containsValue(3);
    }

    /**
     * Returns a map containing the intersection of the two input maps.
     * A key-value pair exists in the output iff the same key-value pair exists in both input maps.
     */
    public static Map<String, Integer> intersect(Map<String, Integer> m1, Map<String, Integer> m2) {
        Map<String, Integer> result = new HashMap<>();
        for (String key : m1.keySet()) {
            if (m2.containsKey(key) && m2.get(key) == m1.get(key)) {
                result.put(key, m1.get(key));
            }
        }
        return result;
    }
}
