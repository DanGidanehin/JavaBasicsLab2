import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Input text and character for sorting
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the text:");
        String inputText = scanner.nextLine();

        System.out.println("Enter the character for sorting:");
        char targetChar = scanner.next().toLowerCase().charAt(0);

        try {
            // Call the method to process the text
            String result = processText(inputText, targetChar);
            System.out.println("Result:");
            System.out.println(result);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        scanner.close();
    }

    /**
     * Processes the text: sorts words by the number of occurrences of a given character.
     *
     * @param text Input text.
     * @param targetChar Character for sorting.
     * @return Result formatted as grouped words.
     */
    private static String processText(String text, char targetChar) {
        // Remove unnecessary characters, leaving only letters and spaces
        text = text.replaceAll("[^\\p{L}\\s]", "");

        // Split the text into words
        String[] words = text.split("\\s+");

        // Map for grouping words by the number of occurrences of the character
        Map<Integer, List<String>> groupedWords = new TreeMap<>(
            Collections.reverseOrder()
        );

        for (String word : words) {
            int count = countOccurrences(word.toLowerCase(), targetChar);
            groupedWords.computeIfAbsent(count, ArrayList::new).add(word);
        }

        // Build the result
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer, List<String>> entry : groupedWords.entrySet()) {
            int count = entry.getKey();
            List<String> wordList = entry.getValue();
            result
                .append(count)
                .append(": ")
                .append(String.join(", ", wordList))
                .append(";\n");
        }

        return result.toString().trim();
    }

    /**
     * Counts the number of occurrences of a character in a word.
     *
     * @param word       Word for analysis.
     * @param targetChar Character to count.
     * @return Number of occurrences of the character.
     */
    private static int countOccurrences(String word, char targetChar) {
        int count = 0;
        for (char c : word.toCharArray()) {
            if (c == targetChar) {
                count++;
            }
        }
        return count;
    }
}
