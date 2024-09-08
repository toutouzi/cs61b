import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        // TODO: Fill in this function.
        int res = 0;
        for (int i : L) {
            res += i;
        }
        return res;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Fill in this function.
        int sizeL = L.size();
        List<Integer> res = new ArrayList<>();
        if (sizeL < 2) {
            return res;
        }
        for (int i = 1; i < sizeL; i += 2) {
            res.addLast(L.get(i));
        }
        return res;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        List<Integer> res = new ArrayList<>();
        for (Integer i : L1) {
            for (Integer j : L2) {
                if (i.equals(j)) {
                    res.addLast(i);
                    break;
                }
            }
        }
        return res;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function.
        Integer occur = 0;
        for (String i : words) {
            for (Integer j = 0; j < i.length(); j++) {
                if(i.charAt(j) == c) {
                    occur++;
                    break;
                }
            }
        }
        return occur;
    }
}
