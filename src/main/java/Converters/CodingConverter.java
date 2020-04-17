package Converters;

import java.util.List;

/**
 * Convert code(Integer) to binary String 
 */
public interface CodingConverter {
    String convert(int num);
    List<Integer> decode(String s);
}
