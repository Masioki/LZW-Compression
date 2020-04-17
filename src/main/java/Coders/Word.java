package Coders;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Boxed List. Just for code readability.
 */
public class Word {
    private final List<Byte> bytes;

    public Word() {
        bytes = new ArrayList<>(5);
    }

    public Word(List<Byte> bytes) {
        if (bytes != null) this.bytes = bytes;
        else this.bytes = new ArrayList<>(5);
    }

    /**
     * Copying constructor
     */
    public Word(Word word) {
        this.bytes = new ArrayList<>(word.getBytes());
    }

    public void add(Byte b) {
        bytes.add(b);
    }

    public void removeLast() {
        bytes.remove(bytes.size() - 1);
    }

    public Byte get(int index) {
        return bytes.get(index);
    }

    public List<Byte> getBytes() {
        return bytes;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;
        Word word = (Word) o;
        if (this.bytes.size() != word.bytes.size()) return false;
        for (int i = 0; i < this.bytes.size(); i++) {
            if (!this.bytes.get(i).equals(word.bytes.get(i))) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bytes);
    }
}
