package Coders;

import Converters.CodingConverter;

import java.util.List;

/**
 * LZW encoding alghoritm
 */
public class Encoder extends AbstractCoder {

    public Encoder(CodingConverter codingConverter, int dictionarySize) {
        super(codingConverter, dictionarySize);
    }


    public synchronized String encode(List<Byte> bytes) {
        StringBuilder builder = new StringBuilder(bytes.size() / 2);
        Word c = new Word();
        c.add(bytes.get(0));
        for (Byte s : bytes.subList(1, bytes.size())) {
            if (!contains(c, s)) {
                builder.append(convert(getCode(c)));
                c.add(s);
                addToDict(c);
                c = new Word();
            }
            c.add(s);
        }
        builder.append(convert(getCode(c)));
        return builder.toString();
    }

}
