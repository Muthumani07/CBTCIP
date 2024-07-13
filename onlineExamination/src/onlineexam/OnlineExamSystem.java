package onlineexam;

import java.io.*;
import java.util.*;

public class OnlineExamSystem {
    private Map<String, User> users = new HashMap<>();
    private static final String USERS_FILE = "users.ser";
    private User loggedInUser;
    private Exam currentExam;

    public OnlineExamSystem() {
        loadUsers();
    }

    public boolean login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.checkPassword(password)) {
            loggedInUser = user;
            return true;
        }
        return false;
    }

    public void logout() {
        loggedInUser = null;
    }

    public void registerUser(String username, String password, String fullName) {
        User user = new User(username, password, fullName);
        users.put(username, user);
        saveUsers();
    }

    public void updateProfile(String newFullName) {
        if (loggedInUser != null) {
            loggedInUser.updateProfile(newFullName);
            saveUsers();
        }
    }

    public void updatePassword(String newPassword) {
        if (loggedInUser != null) {
            loggedInUser.updatePassword(newPassword);
            saveUsers();
        }
    }

    public void startExam(Exam exam) {
        currentExam = exam;
    }

    public void submitExam(List<Integer> answers) {
        if (currentExam != null) {
            int score = 0;
            List<Question> questions = currentExam.getQuestions();
            for (int i = 0; i < questions.size(); i++) {
                if (questions.get(i).checkAnswer(answers.get(i))) {
                    score++;
                }
            }
            System.out.println("Your score: " + score + "/" + questions.size());
        }
    }

    private void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }

    private void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            users = (Map<String, User>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File not found, start with empty users
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading users: " + e.getMessage());
        }
    }
}
