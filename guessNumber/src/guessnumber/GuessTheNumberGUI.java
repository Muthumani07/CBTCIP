package guessnumber;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessTheNumberGUI {

    private static int numberToGuess;
    private static int attemptsLeft;
    private static int score;
    private static int round;
    private static final int maxAttempts = 5;
    private static final int rounds = 3;
    
    public static void main(String[] args) {
        // Initialize game variables
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;
        attemptsLeft = maxAttempts;
        score = 0;
        round = 1;
        
        // Create the frame
        JFrame frame = new JFrame("Guess the Number");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        
        // Create UI components
        JLabel label = new JLabel("Guess a number between 1 and 100:");
        JTextField textField = new JTextField(10);
        JButton guessButton = new JButton("Guess");
        JLabel resultLabel = new JLabel("");
        JLabel scoreLabel = new JLabel("Score: 0");
        JLabel roundLabel = new JLabel("Round: 1/" + rounds);
        JLabel attemptsLabel = new JLabel("Attempts left: " + maxAttempts);
        
        // Add action listener to the button
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int userGuess = Integer.parseInt(textField.getText());
                    if (userGuess < 1 || userGuess > 100) {
                        resultLabel.setText("Please enter a number between 1 and 100.");
                        return;
                    }
                    
                    if (userGuess == numberToGuess) {
                        score += attemptsLeft * 10;
                        resultLabel.setText("Congratulations! You've guessed the number.");
                        scoreLabel.setText("Score: " + score);
                        nextRound(random, roundLabel, attemptsLabel, resultLabel);
                    } else if (userGuess < numberToGuess) {
                        resultLabel.setText("The number is higher than " + userGuess);
                    } else {
                        resultLabel.setText("The number is lower than " + userGuess);
                    }
                    
                    attemptsLeft--;
                    attemptsLabel.setText("Attempts left: " + attemptsLeft);
                    
                    if (attemptsLeft == 0) {
                        resultLabel.setText("Sorry, you've used all attempts. The number was: " + numberToGuess);
                        nextRound(random, roundLabel, attemptsLabel, resultLabel);
                    }
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Please enter a valid number.");
                }
            }
        });
        
        // Create a panel and add components to it
        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(textField);
        panel.add(guessButton);
        panel.add(resultLabel);
        panel.add(scoreLabel);
        panel.add(roundLabel);
        panel.add(attemptsLabel);
        
        // Add panel to frame
        frame.getContentPane().add(panel);
        
        // Display the frame
        frame.setVisible(true);
    }
    
    private static void nextRound(Random random, JLabel roundLabel, JLabel attemptsLabel, JLabel resultLabel) {
        round++;
        if (round > rounds) {
            resultLabel.setText("Game over! Your total score is: " + score);
            JOptionPane.showMessageDialog(null, "Game over! Your total score is: " + score);
            System.exit(0);
        } else {
            numberToGuess = random.nextInt(100) + 1;
            attemptsLeft = maxAttempts;
            roundLabel.setText("Round: " + round + "/" + rounds);
            attemptsLabel.setText("Attempts left: " + attemptsLeft);
            resultLabel.setText("Guess a number between 1 and 100:");
        }
    }
}

