import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame implements ActionListener {
    private int level = 1;
    private int maxNumber;
    private int randomNumber;
    private JTextField guessInput;
    private JButton guessButton;
    private JLabel feedbackLabel;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        guessInput = new JTextField(10);
        guessButton = new JButton("Guess");
        feedbackLabel = new JLabel("Level 1: Guess a number between 1 and 10");

        guessButton.addActionListener(this);

        add(feedbackLabel);
        add(guessInput);
        add(guessButton);

        startNewLevel();
    }

    private void startNewLevel() {
        maxNumber = level * 10;
        randomNumber = new Random().nextInt(maxNumber) + 1;
        feedbackLabel.setText("Level " + level + ": Guess a number between 1 and " + maxNumber);
        guessInput.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int userGuess = Integer.parseInt(guessInput.getText());
            if (userGuess == randomNumber) {
                level++;
                if (level > 4) {
                    feedbackLabel.setText("Congratulations! You've completed all levels!");
                    guessButton.setEnabled(false);
                } else {
                    feedbackLabel.setText("Correct! Moving to Level " + level);
                    startNewLevel();
                }
            } else {
                feedbackLabel.setText("Wrong! Try again.");
            }
        } catch (NumberFormatException ex) {
            feedbackLabel.setText("Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NumberGuessingGame game = new NumberGuessingGame();
            game.setVisible(true);
        });
    }
}