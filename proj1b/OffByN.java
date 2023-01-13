public class OffByN implements CharacterComparator {
    private int size;

    public OffByN(int size) {
        this.size = size;
    }


    @Override
    public boolean equalChars(char x, char y) {
        return ((x + size) == y) || ((x - size) == y);
    }
}
