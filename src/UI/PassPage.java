package UI;

import Core.MUserData;
import Core.RandomPassword;
import Objects.Password;
import Objects.Term;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

class PassPage extends MUserData {
    private ArrayList<Term> terms;
    private static final Random random = new Random();

    void create(Frame frame) {
        JPanel bottomPanel = new JPanel();

        // Components
        JLabel title = new JLabel("Create Password");
        JTextField inputTitle = new JTextField("Input title");
        JLabel outputPass = new JLabel(".....");
        JButton refresh = new JButton("R");
        JButton confirm = new JButton("Confirm");
        JButton back = new JButton("Back");

        // Buttons
        back.addActionListener(e -> {
            frame.setPage(PageType.MENU);
            saveToPasswords();
        });
        refresh.addActionListener(e -> generate(outputPass));
        confirm.addActionListener(e -> confirm(inputTitle, outputPass));

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
        inputTitle.setFont(new Font("Arial", Font.ITALIC, 45));
        outputPass.setFont(new Font("Arial", Font.ITALIC, 35));
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

        // Loads all the terms / passwords
        terms = loadFromTerms();
        for (Password password: loadFromPasswords()) {
            addPassword(password);
        }
    }

    private void generate(JLabel outputPass) { // Creates a password, makes sure that there are terms -- WIP ADD RANDOMIZATION
        if (!terms.isEmpty()) {
            Term randomTerm = terms.get(random.nextInt(terms.size()));
            String randomPassword = RandomPassword.createRandomPassword(randomTerm);

            outputPass.setText(randomPassword);
        }
    }

    private void confirm(JTextField inputTitle, JLabel outputPass) { // Confirms the password
        boolean conditionsMet = true;
        String in = inputTitle.getText();
        String out = outputPass.getText();

        if (out.equals("") || out.equals("Requires password!...") || out.equals(".....")) {
            outputPass.setText("Requires password!...");
            conditionsMet = false;
        }

        if (in.length() > 16) { // No need for String check, since "Must be..." is more that 16 characters
            inputTitle.setText("Must be < 16 characters");
            conditionsMet = false;
        }

        if (in.equals("") || in.equals("Requires title!...")) {
            inputTitle.setText("Requires title!...");
            conditionsMet = false;
        }

        if (conditionsMet) {
            addPassword(new Password(in, out));
        }
    }
}