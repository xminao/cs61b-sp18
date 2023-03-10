public class Palindrome {
    /**
     * return a Deque where the characters appear in the same order as in the String.
     * @return a deque
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    private boolean isPalindromeHelper(Deque<Character> d) {
        // base case
        if (d.size() == 0 || d.size() == 1) {
            return true;
        }
        if (d.removeFirst() == d.removeLast()) {
            return isPalindromeHelper(d);
        }
        return false;
    }

    /**
     * return true if the given word is a palindrome, otherwise false
     * @param word
     * @return
     */
    public boolean isPalindrome(String word) {
        if (word == null || word.length() <= 1) {
            return true;
        }

        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            deque.addLast(word.charAt(i));
        }
        return isPalindromeHelper(deque);
    }

    private boolean isPalindromeHelper(Deque<Character> d, CharacterComparator cc) {
        // base case
        if (d.size() == 0 || d.size() == 1) {
            return true;
        }
        if (cc.equalChars(d.removeFirst(), d.removeLast())) {
            return isPalindromeHelper(d, cc);
        }
        return false;
    }

    /**
     * return true if the given word is a palindrome, otherwise false
     * @param word
     * @param cc
     * @return
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null || word.length() <= 1) {
            return true;
        }

        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            deque.addLast(word.charAt(i));
        }
        return isPalindromeHelper(deque, cc);
    }


}
