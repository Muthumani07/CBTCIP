package onlineexam;

import java.util.*;

public class Main {
    private static OnlineExamSystem examSystem = new OnlineExamSystem();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to the Online Examination System!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    if (login()) {
                        loggedInMenu();
                    }
                    break;
                case 3:
                    System.out.println("Thank you for using the Online Examination System!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void register() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        System.out.println("Enter full name:");
        String fullName = scanner.nextLine();

        examSystem.registerUser(username, password, fullName);
        System.out.println("Registration successful!");
    }

    private static boolean login() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        if (examSystem.login(username, password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }

    private static void loggedInMenu() {
        while (true) {
            System.out.println("1. Update Profile");
            System.out.println("2. Update Password");
            System.out.println("3. Start Exam");
            System.out.println("4. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    updateProfile();
                    break;
                case 2:
                    updatePassword();
                    break;
                case 3:
                    startExam();
                    break;
                case 4:
                    examSystem.logout();
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void updateProfile() {
        System.out.println("Enter new full name:");
        String newFullName = scanner.nextLine();
        examSystem.updateProfile(newFullName);
        System.out.println("Profile updated successfully!");
    }

    private static void updatePassword() {
        System.out.println("Enter new password:");
        String newPassword = scanner.nextLine();
        examSystem.updatePassword(newPassword);
        System.out.println("Password updated successfully!");
    }

    private static void startExam() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is 2 + 2?", Arrays.asList("1", "2", "3", "4"), 3));
        questions.add(new Question("What is the capital of France?", Arrays.asList("Berlin", "London", "Paris", "Rome"), 2));
        Exam exam = new Exam(questions);

        examSystem.startExam(exam);
        List<Integer> answers = new ArrayList<>();
        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            List<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }
            int answer = scanner.nextInt() - 1;
            answers.add(answer);
        }
        examSystem.submitExam(answers);
    }
}
