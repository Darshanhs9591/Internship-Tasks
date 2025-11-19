import java.io.*;
import java.util.Scanner;

public class NotesApp {

    private static final String FILE_NAME = "notes.txt";
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n=== NOTES APP (File I/O) ===");
            System.out.println("1. Create/Overwrite Note");
            System.out.println("2. Append to Note");
            System.out.println("3. View Notes");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> createNote();
                case 2 -> appendNote();
                case 3 -> viewNotes();
                case 4 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 4);

    }

    // Create or overwrite a note
    private static void createNote() {
        System.out.println("\n--- Create/Overwrite Note ---");
        System.out.println("Enter your note content:");
        String content = sc.nextLine();

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write(content);
            System.out.println("Note saved successfully (overwritten).");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    // Append to existing note
    private static void appendNote() {
        System.out.println("\n--- Append to Note ---");
        System.out.println("Enter text to append:");
        String content = sc.nextLine();

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write("\n" + content);
            System.out.println("Note appended successfully.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    // Read the note file
    private static void viewNotes() {
        System.out.println("\n--- View Notes ---");

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n--- Saved Notes ---");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("--------------------\n");
        } catch (FileNotFoundException e) {
            System.out.println("No notes found. Create one first!");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
