import java.util.Scanner;

public class TextAnalyzer {
    public static void main(String[] args) {
        System.out.println("=== Day 2: Text Analyzer ===");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence or text: ");
        String text = scanner.nextLine();

        int charCount = text.length();
        int wordCount = text.trim().isEmpty() ? 0 : text.trim().split("\\s+").length;
        String upperCase = text.toUpperCase();
        String reversed = new StringBuilder(text).reverse().toString();

        System.out.println("Character Count: " + charCount);
        System.out.println("Word Count: " + wordCount);
        System.out.println("Uppercase: " + upperCase);
        System.out.println("Reversed: " + reversed);
        scanner.close();
    }
}