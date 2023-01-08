import static java.lang.Math.abs;

public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int diff = abs(x - y);
        if (diff == 1) {
            return true;
        }
        return false;
    }
}
