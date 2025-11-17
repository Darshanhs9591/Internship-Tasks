import java.util.ArrayList;
import java.util.Scanner;
import java.util.Optional;

public class StudentManagementSystem {

    // Simple Student class
    static class Student {
        private int id;
        private String name;
        private double marks;

        public Student(int id, String name, double marks) {
            this.id = id;
            this.name = name;
            this.marks = marks;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public double getMarks() { return marks; }

        public void setName(String name) { this.name = name; }
        public void setMarks(double marks) { this.marks = marks; }

        @Override
        public String toString() {
            return String.format("ID: %d | Name: %s | Marks: %.2f", id, name, marks);
        }
    }

    private static final ArrayList<Student> students = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewAllStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> viewStudentById();
                case 6 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice. Try again.");
            }
            System.out.println();
        } while (choice != 6);

        sc.close();
    }

    private static void showMenu() {
        System.out.println("=== STUDENT RECORD MANAGEMENT ===");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. View Student by ID");
        System.out.println("6. Exit");
    }

    private static void addStudent() {
        System.out.println("--- Add Student ---");
        int id = readInt("Enter Student ID (integer): ");
        if (findStudentIndexById(id).isPresent()) {
            System.out.println("A student with this ID already exists. Use a unique ID.");
            return;
        }
        String name = readNonEmptyString("Enter Name: ");
        double marks = readDouble("Enter Marks: ");
        students.add(new Student(id, name, marks));
        System.out.println("Student added successfully.");
    }

    private static void viewAllStudents() {
        System.out.println("--- All Students ---");
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private static void updateStudent() {
        System.out.println("--- Update Student ---");
        int id = readInt("Enter Student ID to update: ");
        Optional<Integer> idxOpt = findStudentIndexById(id);
        if (idxOpt.isEmpty()) {
            System.out.println("Student with ID " + id + " not found.");
            return;
        }
        Student s = students.get(idxOpt.get());
        System.out.println("Current record: " + s);

        System.out.println("Enter new values (leave blank to keep current):");
        String newName = readLineAllowEmpty("New name: ");
        if (!newName.isBlank()) s.setName(newName);

        String marksInput = readLineAllowEmpty("New marks: ");
        if (!marksInput.isBlank()) {
            try {
                double newMarks = Double.parseDouble(marksInput);
                s.setMarks(newMarks);
            } catch (NumberFormatException e) {
                System.out.println("Invalid marks input. Marks not updated.");
            }
        }

        System.out.println("Record updated: " + s);
    }

    private static void deleteStudent() {
        System.out.println("--- Delete Student ---");
        int id = readInt("Enter Student ID to delete: ");
        Optional<Integer> idxOpt = findStudentIndexById(id);
        if (idxOpt.isEmpty()) {
            System.out.println("Student with ID " + id + " not found.");
            return;
        }
        Student s = students.remove(idxOpt.get().intValue());
        System.out.println("Deleted: " + s);
    }

    private static void viewStudentById() {
        System.out.println("--- View Student By ID ---");
        int id = readInt("Enter Student ID: ");
        Optional<Integer> idxOpt = findStudentIndexById(id);
        if (idxOpt.isEmpty()) {
            System.out.println("Student with ID " + id + " not found.");
            return;
        }
        System.out.println(students.get(idxOpt.get()));
    }

    // Helper methods
    private static Optional<Integer> findStudentIndexById(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) return Optional.of(i);
        }
        return Optional.empty();
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Input cannot be empty.");
        }
    }

    private static String readLineAllowEmpty(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }
}
