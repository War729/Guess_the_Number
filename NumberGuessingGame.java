import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class NumberGuessingGame extends JFrame {
    private JLabel roundLabel;
    private JLabel guessLabel;
    private JTextField guessTextField;
    private JButton guessButton;
    private JLabel resultLabel;
    private JLabel scoreLabel;
    private JLabel ruleLabel;
    private JButton playAgainButton;
    private JButton exitButton;

    private int score;
    private int rounds;
    private int targetNumber;
    private int attempts;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(600, 370);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        ruleLabel = new JLabel("<html>Welcome to the Number Guessing Game!<br/>"
                + "Rule of the Game ! <br/>"
                + "You need to guess a number between 1 and 100 within 5 attempts.<br/>"
                + "You will earn points based on the number of attempts taken to guess the correct number.<br/>"
                + "Good luck!</html>");
        ruleLabel.setBounds(20, 5, 300, 150);
        add(ruleLabel);

        roundLabel = new JLabel("Round 1");
        roundLabel.setBounds(20, 160, 100, 20);
        add(roundLabel);

        guessLabel = new JLabel("Enter your guess (1-100):");
        guessLabel.setBounds(20, 190, 200, 20);
        add(guessLabel);

        guessTextField = new JTextField();
        guessTextField.setBounds(220, 190, 50, 20);
        add(guessTextField);

        guessButton = new JButton("Guess");
        guessButton.setBounds(280, 190, 80, 20);
        add(guessButton);

        resultLabel = new JLabel();
        resultLabel.setBounds(20, 220, 300, 20);
        add(resultLabel);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setBounds(20, 250, 100, 20);
        add(scoreLabel);

        playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(150, 280, 100, 30);
        playAgainButton.setVisible(false);
        add(playAgainButton);

        exitButton = new JButton("Exit"); // Create and set up the Exit button
        exitButton.setBounds(250, 280, 100, 30);
        exitButton.setVisible(false);
        add(exitButton);

        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (attempts <= 5) {
                    int guess = Integer.parseInt(guessTextField.getText());
                    attempts++;

                    if (guess == targetNumber) {
                        ruleLabel.setVisible(false); // Hide the game rules after the player guesses the correct number
                        resultLabel.setText("Congratulations! You guessed the correct number!");
                        score += 10 - attempts;
                        guessButton.setEnabled(false);
                        playAgainButton.setVisible(true);
                        exitButton.setVisible(true);
                    } else if (guess < targetNumber) {
                        resultLabel.setText("Too low! Try again. Attempt: " + attempts);
                    } else {
                        resultLabel.setText("Too high! Try again. Attempt: " + attempts);
                    }
                    scoreLabel.setText("Score: " + score);
                }

                if (attempts == 5) {
                    ruleLabel.setVisible(false);
                    resultLabel.setText("Out of attempts.\nThe number was: " + targetNumber);
                    guessButton.setEnabled(false);
                    playAgainButton.setVisible(true);
                    exitButton.setVisible(true);
                }
            }
        });

        playAgainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startNewRound();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the application when the Exit button is clicked
            }
        });

        startNewRound();
    }

    private void startNewRound() {
        ruleLabel.setVisible(true);
        rounds++;
        roundLabel.setText("Round " + rounds);

        Random random = new Random();
        targetNumber = random.nextInt(100) + 1;
        attempts = 0;
        resultLabel.setText("");
        guessTextField.setText("");
        guessButton.setEnabled(true);
        playAgainButton.setVisible(false);
        exitButton.setVisible(false); 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NumberGuessingGame().setVisible(true);
            }
        });
    }
}
