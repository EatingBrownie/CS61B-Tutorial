public class Palindrome {

    public Deque<Character> wordToDeque(String word){
        LinkedListDeque<Character> deque = new LinkedListDeque<>();

        for (int i = 0; i < word.length() ; i++) {
            deque.addLast(word.charAt(i));
        }

        return deque;
    }

    public boolean isPalindrome(String word){
        // should return true if the given word is a palindrome,
        // and false otherwise.
        boolean flag;


        return true;
    }


}
