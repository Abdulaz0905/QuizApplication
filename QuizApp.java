

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApp {
    private JFrame frame;
    private JPanel panel;
    private JLabel questionLabel;
    private JRadioButton[] options;
    private JButton nextButton, submitButton;
    private ButtonGroup optionGroup;

    private String[] questions = {
        "What is the capital of France?",
        "What is the largest planet in our solar system?",
        "What is the chemical symbol for water?"
    };

    private String[][] optionsList = {
        {"Berlin", "Madrid", "Paris", "Lisbon"},
        {"Earth", "Jupiter", "Mars", "Saturn"},
        {"H2O", "O2", "CO2", "HO"}
    };

    private String[] answers = {"Paris", "Jupiter", "H2O"};
    private int currentQuestionIndex = 0;
    private int score = 0;

    public QuizApp() {
        frame = new JFrame("Quiz Application");
        panel = new JPanel();
        questionLabel = new JLabel();
        options = new JRadioButton[4];
        optionGroup = new ButtonGroup();
        nextButton = new JButton("Next");
        submitButton = new JButton("Submit");

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(questionLabel);
        
        for (int i = 0; i < options.length; i++) {
            options[i] = new JRadioButton();
            options[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            optionGroup.add(options[i]);
            panel.add(options[i]);
        }

        nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.length) {
                    loadQuestion();
                } else {
                    showResult();
                }
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                showResult();
            }
        });

        panel.add(nextButton);
        panel.add(submitButton);
        
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);

        loadQuestion();
    }

    private void loadQuestion() {
        questionLabel.setText(questions[currentQuestionIndex]);
        
        String[] currentOptions = optionsList[currentQuestionIndex];
        
        for (int i = 0; i < currentOptions.length; i++) {
            options[i].setText(currentOptions[i]);
            options[i].setSelected(false);
        }
        
        optionGroup.clearSelection();
    }

    private void checkAnswer() {
        String selectedOption = null;
        
        for (JRadioButton option : options) {
            if (option.isSelected()) {
                selectedOption = option.getText();
                break;
            }
        }
        
        if (selectedOption != null && selectedOption.equals(answers[currentQuestionIndex])) {
            score++;
        }
    }

    private void showResult() {
        JOptionPane.showMessageDialog(frame, "Your score: " + score + "/" + questions.length);
        System.exit(0);
    }

    public static void main(String[] args) {
        new QuizApp();
    }
}