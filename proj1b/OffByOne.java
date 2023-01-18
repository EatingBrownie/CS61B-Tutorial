public class OffByOne implements CharacterComparator {


    @Override
    public boolean equalChars(char x, char y) {
//        if ((x >= 66 && x <= 89)) {
//            return ((x + 1) == y
//                    || (x - 1) == y
//                    || (x + 31) == y);
//        } else if (x == 65 || x == 97) {
//            return (y == 66 ||  y == 98 );
//        } else if (x == 90 || x == 122) {
//            return (y == 121 || y == 89);
//        } else if ((x >= 98 && x <= 121)) {
//            return ((x + 1) == y
//                    || (x - 1) == y
//                    || (x - 31) == y);
//        }

        return (((x - 1) == y) || ((x + 1) == y));

    }


}
