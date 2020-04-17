package Converters;

import java.util.ArrayList;
import java.util.List;

/**
 * Elias delta code
 */
public class DeltaConverter implements CodingConverter {
    @Override
    public String convert(int num) {
        String s = Integer.toBinaryString(num);
        int len = s.length();
        s = s.substring(1, len);
        String sw = Integer.toBinaryString(len);
        return "0".repeat(sw.length() - 1) + sw + s;
    }

    @Override
    public List<Integer> decode(String s) {
        List<Integer> result = new ArrayList<>();
        int i = 0;
        int len = s.length();
        while (i < len) {
            int zeros = 0;
            while (s.charAt(i) == '0') {
                zeros++;
                i++;
            }
            int k = Integer.parseInt(s.substring(i, i + zeros + 1), 2);
            i += zeros + 1;
            int n = k - 1 > 0 ? Integer.parseInt(s.substring(i, i + k - 1), 2) : 0;
            i += k - 1;
            n += 1 << k - 1;
            result.add(n);
        }
        return result;
    }
}
