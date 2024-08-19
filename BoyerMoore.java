import java.util.HashMap;
import java.util.Map;

public class BoyerMoore {
    public static void findPattern(char[] text, char[] pattern) {
        int textLength = text.length;
        int patternLength = pattern.length;

        // Check if the pattern is empty
        if (patternLength == 0) {
            System.out.println("Pattern is empty");
            return;
        }

        // For this assignment, we are implementing the Boyer-Moore algorithm using the bad character heuristic.
        // Create a map to store the last occurrence position of each character in the pattern
        Map<Character, Integer> lastOccurrence = new HashMap<>();

        // Initialize the last occurrence position of all characters in the pattern
        for (int i = 0; i < patternLength; i++) {
            lastOccurrence.put(pattern[i], i);
        }

        // Print the algorithm name and pattern length
        System.out.println("------------------");
        System.out.println(" BOYER MOORE");
        System.out.println("------------------");

        // Print the bad character table
        System.out.println("Bad Character Table:\n");
        System.out.print(" |");

        for (char c : pattern) {
            System.out.print(" " + c + " |");
        }
        System.out.println(" * |");

        System.out.print(" |");
        for (int i = 0; i < patternLength; i++) {
            System.out.print("---|");
        }
        System.out.println("---|");

        // Print shift values for characters in the pattern
        System.out.print(" |");
        for (char c : pattern) {
            int lastIndex = lastOccurrence.getOrDefault(c, -1);

            // Determines shift as max of 1 or patternIndex - 1 - lastIndex,
            // We need only positive shift, not negative, so we are using max function
            // This prevents algorithm stalls and aligning pattern efficiently.
            int shift = Math.max(1, patternLength - 1 - lastIndex);
            System.out.print(" " + shift + " |");
        }
        System.out.println(" " + patternLength + " |");
        System.out.println();

        int textIndex = 0; // Index in the text where the pattern is currently being compared
        boolean patternFound = false; // Flag to indicate if the pattern has been found in the text
        int patternCount = 0; // Count of occurrences of the pattern in the text (sometimes the same pattern can occur more than once in the text)

        // Slide the pattern over the text one position at a time
        while (textIndex <= (textLength - patternLength)) {
            int patternIndex = patternLength - 1; // Start comparing from the end of the pattern

            // Compare characters of the pattern and text from end to start
            while (patternIndex >= 0 && pattern[patternIndex] == text[textIndex + patternIndex]) {
                patternIndex--;
            }

            // If all characters in the pattern are matched (successfully matched every character in the pattern array against the corresponding characters in the text array)
            if (patternIndex < 0) {
                // Case 1: Pattern found
                System.out.println("Comparison:");
                System.out.println("------------------");
                System.out.println(new String(text));
                System.out.println(" ".repeat(textIndex) + new String(pattern));
                System.out.println();
                System.out.println("All characters match, so the pattern \"" + new String(pattern) + "\" is found at index " + textIndex + " in the text.");

                patternFound = true; // Set the flag to true to indicate that the pattern is found
                patternCount++; // Increment the pattern count

                // After finding a mismatch between the pattern and the text,
                // we move the pattern to the right based on where the mismatched
                // character last appeared in the pattern. This helps align the next
                // character in the text with its last position in the pattern,
                // making the search more efficient by potentially skipping over
                // unmatched characters in the text.
                textIndex += (textIndex + patternLength < textLength) ? patternLength - lastOccurrence.getOrDefault(text[textIndex + patternLength], -1) : 1;
            } else {

                // Mismatch found, calculate the shift amount
                int lastIndex = lastOccurrence.getOrDefault(text[textIndex + patternIndex], -1);

                // Shift is declared twice due to clearly distinguish their roles:
                // One for visualizing potential shifts, and the other for actual pattern adjustments.
                int shift = Math.max(1, patternIndex - lastIndex);
                System.out.println("Comparison:");
                System.out.println("------------------");
                System.out.println(new String(text));
                System.out.println(" ".repeat(textIndex) + new String(pattern));
                System.out.println();
                System.out.println(pattern[patternIndex] + " (pattern) vs. " + (text[textIndex + patternIndex] == ' ' ? "space" : text[textIndex + patternIndex]) + " (text) → mismatch. So shift " + shift + " position" + (shift > 1 ? "s" : "") + ".");
                System.out.println();

                textIndex += shift;
            }
        }

        // Case 2: No pattern is found in the text
        if (!patternFound) {
            System.out.println("No such pattern is found in the text.");
        } else {
            System.out.println("\nTotal occurrences of the pattern in the text: " + patternCount);
        }
    }

    public static void main(String[] args) {
        
        // Initialize the text and pattern arrays
        char[] text = "BOYER MOORE ALGO".toCharArray();
        char[] pattern = "ALGO".toCharArray();
        findPattern(text, pattern);
    }
}
