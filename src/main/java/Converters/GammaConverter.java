package Converters;

import java.util.ArrayList;
import java.util.List;

/**
 * Elias gamma code.
 */
public class GammaConverter implements CodingConverter {
    @Override
    public String convert(int num) {
        if (num == 0) return "0";
        int n = 0;
        while (num >> n > 0) n++;
        return "0".repeat(n - 1) + Integer.toBinaryString(num);
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
            result.add(Integer.parseInt(s.substring(i, i + zeros + 1), 2));
            i += zeros + 1;
        }
        return result;
    }
}
