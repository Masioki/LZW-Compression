package Coders;

import Converters.CodingConverter;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains full, fast dictionary management for LZW using Bidirectional Hash Map
 */
public abstract class AbstractCoder {
    private final CodingConverter converter;
    private BiMap<Word, Integer> dictionary;
    private final int dictionarySize;
    private int counter;
    private final Map<Word, Integer> base;

    public AbstractCoder(CodingConverter converter, int dictionarySize) {
        this.dictionarySize = dictionarySize;//Math.max(dictionarySize, 256);
        this.converter = converter;
        counter = 256;
        base = new HashMap<>();
        for (int i = 0; i < 256; i++) base.put(new Word(Collections.singletonList((byte) i)), i + 1);
        dictionary = HashBiMap.create(base);
    }

    private void checkSize() {
        if (counter >= dictionarySize) {
            dictionary = HashBiMap.create(base);
            counter = 256;
        }
    }

    protected synchronized void addToDict(Word word) {
        checkSize();
        dictionary.put(word, ++counter);
    }

    protected boolean contains(Word word, Byte s) {
        boolean result = false;
        word.add(s);
        result = dictionary.containsKey(word);
        word.removeLast();
        return result;
    }

    protected boolean contains(Integer val) {
        return counter >= val; //
    }

    protected int getCode(Word word) {
        return dictionary.get(word);
    }

    protected synchronized Word getWord(Integer in) {
        return dictionary.inverse().get(in);
    }

    protected String convert(int num) {
        return converter.convert(num);
    }

    protected List<Integer> decodeString(String s) {
        return converter.decode(s);
    }

}
