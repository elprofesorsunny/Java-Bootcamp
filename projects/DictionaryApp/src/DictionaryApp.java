import java.io.*;
import java.util.*;

public class DictionaryApp {
    private static final String FILE_NAME = "dictionary.txt";
    private static Map<String, String> dictionary = new HashMap<>();
    
    public static void main(String[] args) {
        loadDictionary();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nDictionary App");
            System.out.println("1. Add a word");
            System.out.println("2. Remove a word");
            System.out.println("3. Search for a word");
            System.out.println("4. Save and Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    addWord(scanner);
                    break;
                case 2:
                    removeWord(scanner);
                    break;
                case 3:
                    searchWord(scanner);
                    break;
                case 4:
                    saveDictionary();
                    System.out.println("Dictionary saved. Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addWord(Scanner scanner) {
        System.out.print("Enter the word: ");
        String word = scanner.nextLine().toLowerCase();
        if (dictionary.containsKey(word)) {
            System.out.println("This word already exists.");
            return;
        }
        System.out.print("Enter the meaning: ");
        String meaning = scanner.nextLine();
        dictionary.put(word, meaning);
        System.out.println("Word added successfully!");
    }

    private static void removeWord(Scanner scanner) {
        System.out.print("Enter the word to remove: ");
        String word = scanner.nextLine().toLowerCase();
        if (dictionary.remove(word) != null) {
            System.out.println("Word removed successfully!");
        } else {
            System.out.println("Word not found!");
        }
    }

    private static void searchWord(Scanner scanner) {
        System.out.print("Enter the word to search: ");
        String word = scanner.nextLine().toLowerCase();
        if (dictionary.containsKey(word)) {
            System.out.println("Meaning: " + dictionary.get(word));
        } else {
            System.out.println("Word not found!");
        }
    }

    private static void saveDictionary() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving dictionary: " + e.getMessage());
        }
    }

    private static void loadDictionary() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    dictionary.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading dictionary: " + e.getMessage());
        }
    }
}
