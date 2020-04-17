package Converters;

import java.util.ArrayList;
import java.util.List;

/**
 * Elias omega code
 */
public class OmegaConverter implements CodingConverter {
    @Override
    public String convert(int num) {
        StringBuilder result = new StringBuilder("0");
        int k = num;
        while (k > 1) {
            String s = Integer.toBinaryString(k);
            k = s.length() - 1;
            result.insert(0, s);
        }
        return result.toString();
    }

    @Override
    public List<Integer> decode(String s) {
        List<Integer> result = new ArrayList<>();
        int i = 0;
        int len = s.length();
        while (len - i > 0) { //1
            int n = 1;
            while (s.charAt(i) != '0') {
                int temp = n + 1;
                n = Integer.parseInt(s.substring(i, i + n + 1), 2);
                i += temp;
            }
            result.add(n);
            i++;
        }
        return result;
    }
}
