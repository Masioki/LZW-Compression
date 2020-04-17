package Converters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Fibonacci code
 */
public class FibonacciConverter implements CodingConverter {

    //TODO: numbers should be stored, not calculated every time its called
    private List<Integer> fibNumbersLessThan(int n) {
        if (n <= 1) return Collections.singletonList(1);
        int first = 1;
        int second = 2;
        List<Integer> result = new ArrayList<>();
        result.add(1);
        result.add(2);
        while (first + second <= n) {
            result.add(first + second);
            int temp = first;
            first = second;
            second += temp;
        }
        return result;
    }

    private int fibToInt(String s) {
        int first = 0;
        int second = 1;
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') result += first + second;
            int temp = first;
            first = second;
            second += temp;
        }
        return result;
    }

    private String compress(String s) {
        return s.replace("01", "1");
    }

    @Override
    public String convert(int num) {
        if (num == 0) return "01";
        StringBuilder result = new StringBuilder();
        List<Integer> fib = fibNumbersLessThan(num);
        for (int i = fib.size() - 1; i >= 0; i--) {
            if (fib.get(i) <= num) {
                num -= fib.get(i);
                result.append("1");
            } else result.append("0");
        }
        return result.reverse().append("1").toString();
    }

    @Override
    public List<Integer> decode(String s) {
        String[] list = s.replace("11", "1;").split(";");
        List<Integer> result = new ArrayList<>(list.length);
        for (String n : list) result.add(fibToInt(n));
        return result;
    }
}
