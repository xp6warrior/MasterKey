package UI;

import Core.ModPass;
import Core.ModTerms;
import Objects.Password;
import Objects.Term;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

class PassPage {
    private ArrayList<Term> terms;
    private final Random random = new Random();

    void create(Frame frame) {
        JPanel bottomPanel = new JPanel();

        // Components
        JLabel title = new JLabel("Create Password");
        JTextField inputTitle = new JTextField("Input title");
        JLabel outputPass = new JLabel();
        JButton refresh = new JButton("R");
        JButton confirm = new JButton("Confirm");
        JButton back = new JButton("Back");

        // Buttons
        back.addActionListener(e -> {frame.setPage(PagesEnum.MENU);ModPass.saveToPasswords();});
        refresh.addActionListener(e -> generate(outputPass));
        confirm.addActionListener(e -> confirm(outputPass, inputTitle));

        // Sizes
        title.setPreferredSize(new Dimension(600, 150));
        inputTitle.setPreferredSize(new Dimension(550, 110));
        outputPass.setPreferredSize(new Dimension(420, 110));
        refresh.setPreferredSize(new Dimension(110, 110));
        confirm.setPreferredSize(new Dimension(350, 70));
        back.setPreferredSize(new Dimension(180, 70));

        // Text/fonts/borders/colors
        Font smallFont = new Font("Arial", Font.PLAIN, 35);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        inputTitle.setHorizontalAlignment(SwingConstants.CENTER);
        outputPass.setHorizontalAlignment(SwingConstants.CENTER);
        inputTitle.setBorder(BorderFactory.createLineBorder(Color.black, 6, true));
        outputPass.setBorder(BorderFactory.createLineBorder(Color.black, 5, true));
        inputTitle.setBackground(Color.lightGray);
        outputPass.setBackground(Color.lightGray);
        inputTitle.setOpaque(true);
        outputPass.setOpaque(true);

        // Fonts
        title.setFont(new Font("Arial", Font.BOLD, 60));
        inputTitle.setFont(new Font("Arial", Font.PLAIN, 40));
        outputPass.setFont(smallFont);
        refresh.setFont(smallFont);
        confirm.setFont(smallFont);
        back.setFont(smallFont);

        // BottomPanel components
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 25));
        bottomPanel.add(confirm);
        bottomPanel.add(back);

        // Frame components
        frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
        frame.getContentPane().add(title);
        frame.getContentPane().add(inputTitle);
        frame.getContentPane().add(outputPass);
        frame.getContentPane().add(refresh);
        frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);

        // Loads all the terms
        terms = ModTerms.loadFromTerms();
    }

    private void generate(JLabel label) {
        // Creates a password, makes sure that there are terms
        if (!terms.isEmpty()) {
            String term = terms.get(random.nextInt(terms.size())).getName();
            label.setText(term);
        } else {
            label.setText("Create terms first!...");
        }
    }

    private void confirm(JLabel output, JTextField title) {
        String outputString = output.getText();

        if (title.getText().equals("")) { // If title is blank -> requires title
            output.setText("Requires title!...");
        } else if (outputString.equals("") || outputString.equals("Create terms first!...") ||
                outputString.equals("Requires title!...") ||
                outputString.equals("Generate a password!...")) // If output is blank/message displayed in output -> generate a password
        {
            output.setText("Generate a password!...");
        } else {
            ModPass.addPass(new Password(outputString, title.getText()));
        }
    }
}