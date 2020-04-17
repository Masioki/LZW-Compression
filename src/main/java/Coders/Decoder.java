package Coders;

import Converters.CodingConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * LZW decoding algorithm
 */
public class Decoder extends AbstractCoder {

    public Decoder(CodingConverter codingConverter, int dictionarySize) {
        super(codingConverter, dictionarySize);
    }


    public List<Byte> decode(String s) {
        List<Integer> list = decodeString(s);
        List<Word> result = new ArrayList<>(list.size());
        Integer pk = list.get(0); //old
        result.add(getWord(pk));
        Word pc; //new
        for (int k : list.subList(1, list.size())) {
            pc = getWord(pk);
            if (contains(k)) {
                Word temp = new Word(pc);
                Word w = getWord(k);
                temp.add(w.get(0));
                addToDict(temp);
                result.add(w);
            } else {
                Word temp = new Word(pc);
                temp.add(pc.get(0));
                addToDict(temp);
                result.add(temp);
            }
            pk = k;
        }
        List<Byte> out = new ArrayList<>();
        result.forEach(w -> out.addAll(w.getBytes()));
        return out;
    }

}
