import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class QuizQuestion {
    private String question;
    private List<String> options;
    private int correctOptionIndex; // 0-based index

    public QuizQuestion(String question, List<String> options, int correctOptionIndex) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}

public class QuizApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<QuizQuestion> questions = createQuestions();

        int score = 0;

        System.out.println("=== ONLINE QUIZ APP (Console) ===");
        System.out.println("Answer by entering option number (1, 2, 3, 4)");
        System.out.println("----------------------------------------");

        for (int i = 0; i < questions.size(); i++) {
            QuizQuestion q = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + q.getQuestion());

            List<String> options = q.getOptions();
            for (int j = 0; j < options.size(); j++) {
                System.out.println((j + 1) + ". " + options.get(j));
            }

            int userAnswer = readAnswer(sc, 1, options.size());

            // Convert to 0-based index
            if (userAnswer - 1 == q.getCorrectOptionIndex()) {
                System.out.println("✅ Correct!");
                score++;
            } else {
                System.out.println("❌ Wrong!");
                System.out.println("Correct answer: " + (q.getCorrectOptionIndex() + 1) + ". "
                        + options.get(q.getCorrectOptionIndex()));
            }
        }

        System.out.println("\n=== QUIZ COMPLETED ===");
        System.out.println("Your score: " + score + " out of " + questions.size());

        double percentage = (score * 100.0) / questions.size();
        System.out.printf("Percentage: %.2f%%\n", percentage);

        if (percentage == 100.0) {
            System.out.println("Excellent! 🎉");
        } else if (percentage >= 60.0) {
            System.out.println("Good job! 👍");
        } else {
            System.out.println("Keep practicing! 💪");
        }

        sc.close();
    }

    // Create sample questions
    private static List<QuizQuestion> createQuestions() {
        List<QuizQuestion> list = new ArrayList<>();

        // Q1
        List<String> q1Options = new ArrayList<>();
        q1Options.add("Java Virtual Machine");
        q1Options.add("Java Variable Machine");
        q1Options.add("Joint Virtual Module");
        q1Options.add("None of the above");
        list.add(new QuizQuestion(
                "What does JVM stand for?",
                q1Options,
                0 // correct = first option
        ));

        // Q2
        List<String> q2Options = new ArrayList<>();
        q2Options.add("class");
        q2Options.add("public");
        q2Options.add("static");
        q2Options.add("void");
        list.add(new QuizQuestion(
                "Which keyword is used to define a class in Java?",
                q2Options,
                0
        ));

        // Q3
        List<String> q3Options = new ArrayList<>();
        q3Options.add("int[] arr = new int(5);");
        q3Options.add("int arr[] = new int[5];");
        q3Options.add("int arr = int[5];");
        q3Options.add("array int arr = new int[5];");
        list.add(new QuizQuestion(
                "Which is the correct way to declare an integer array of size 5?",
                q3Options,
                1
        ));

        // Q4
        List<String> q4Options = new ArrayList<>();
        q4Options.add("Common Language Runtime");
        q4Options.add("Just-In-Time Compiler");
        q4Options.add("Java Development Kit");
        q4Options.add("Java Runtime Environment");
        list.add(new QuizQuestion(
                "What does JRE stand for?",
                q4Options,
                3
        ));

        // Q5
        List<String> q5Options = new ArrayList<>();
        q5Options.add("Object Oriented Programming");
        q5Options.add("Open Office Project");
        q5Options.add("Operating System Program");
        q5Options.add("None of the above");
        list.add(new QuizQuestion(
                "OOP in Java stands for?",
                q5Options,
                0
        ));

        return list;
    }

    private static int readAnswer(Scanner sc, int min, int max) {
        while (true) {
            System.out.print("Your answer (" + min + "-" + max + "): ");
            String input = sc.nextLine().trim();
            try {
                int val = Integer.parseInt(input);
                if (val >= min && val <= max) {
                    return val;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
            }
        }
    }
}
