public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> deque = new LinkedListDeque<>();

        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }

        return deque;
    }

    public boolean isPalindrome(String word) {
        /*// should return true if the given word is a palindrome,
        // and false otherwise.
        Deque<Character> deque = wordToDeque(word);
        String actual = "";
        for (int i = 0; i < word.length(); i++) {
            actual += deque.removeLast();
        }

        return actual.equals(word);*/

        if (word.length() <= 1) {
            return true;
        }
        Deque<Character> deque = wordToDeque(word);
        Character front = deque.removeFirst();
        Character last = deque.removeLast();
        boolean flag = front.equals(last);
        word = dequeToWord(deque);
        return flag && isPalindrome(word);
    }


    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        boolean odd = false;
        if (word.length() % 2 != 0) {
            odd = true;
        }

        String actual = "";
        for (int i = 0; i < word.length(); i++) {
            actual += deque.removeLast();
        }
        for (int i = 0; i < word.length(); i++) {
            if (!cc.equalChars(actual.charAt(i), word.charAt(i))) {
                if (!(odd && i == word.length() / 2)) {
                    return false;
                }
            }
        }
        return true;
    }


    private String dequeToWord(Deque<Character> deque) {

        String actual = "";

        for (int i = 0; i < deque.size(); i++) {
            actual += deque.get(i);
        }

        return actual;
    }

}
