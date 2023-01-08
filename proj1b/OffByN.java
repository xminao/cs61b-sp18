import static java.lang.Math.abs;

public class OffByN implements CharacterComparator {
    private int offBy;

    /**
     * Constructors
     * @param N
     */
    public OffByN(int N) {
        offBy = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = abs(x - y);
        if (diff == offBy) {
            return true;
        }
        return false;
    }
}
