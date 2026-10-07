public class TextAnalyzer {
    public static void main(String[] args) {
        String input = "Java Full Stack 2026 - Capstone Project!";

        int vowels = 0, consonants = 0, digits = 0, spaces = 0;
        String lowerInput = input.toLowerCase();

        for (int i = 0; i < lowerInput.length(); i++) {
            char ch = lowerInput.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }
            } else if (ch >= '0' && ch <= '9') {
                digits++;
            } else if (Character.isWhitespace(ch)) {
                spaces++;
            }
        }

        System.out.println("=== Text Analysis Summary ===");
        System.out.println("Original String: \"" + input + "\"");
        System.out.println("Vowels Count    : " + vowels);
        System.out.println("Consonants Count: " + consonants);
        System.out.println("Digits Count    : " + digits);
        System.out.println("Whitespace Count: " + spaces);

        String[] words = input.split(" ");
        StringBuilder reversedSentence = new StringBuilder();

        for (String word : words) {
            StringBuilder reversedWord = new StringBuilder(word).reverse();
            reversedSentence.append(reversedWord).append(" ");
        }

        System.out.println("\n=== Word Reversal Output ===");
        System.out.println("Reversed Words  : " + reversedSentence.toString().trim());

        String searchKeyword = "Capstone";
        boolean containsKeyword = input.contains(searchKeyword);
        System.out.println("\n=== Keyword Search ===");
        System.out.println("Contains '" + searchKeyword + "'?: " + containsKeyword);
    }
}
