package UI_Pages;

import UI_Elements.Assets;
import Core.UserData;
import Core.RandomPassword;
import Objects.Password;
import UI_Elements.Button;
import UI_Elements.Label;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.charset.StandardCharsets;

public class PassPage implements MouseListener {
    private JTextField inputTitle;
    private JTextField outputPass;

    void create(Frame frame) {
        // Components
        Label title = new Label("Create New Password");
        inputTitle = new JTextField("Input title...");
        outputPass = new JTextField(".....");
        Button refresh = new Button(Assets.refresh, new Dimension(110, 110));
        Button confirm = new Button("Confirm", Assets.mediumButtonSize);
        Button back = new Button("Back", Assets.mediumButtonSize);
        JPanel bottomPanel = new JPanel();

        inputTitle.setName("in");
        outputPass.setName("out");

        // Buttons
        back.addActionListener(e -> backButtonFunction(frame));
        refresh.addActionListener(e -> generate(outputPass));
        confirm.addActionListener(e -> confirm(inputTitle, outputPass));
        inputTitle.addMouseListener(this);
        outputPass.addMouseListener(this);

        // Sizes
        inputTitle.setPreferredSize(Assets.menuButtonSize);
        outputPass.setPreferredSize(Assets.outputPassSize);
        inputTitle.setHorizontalAlignment(SwingConstants.CENTER);
        outputPass.setHorizontalAlignment(SwingConstants.CENTER);
        inputTitle.setBorder(Assets.blackBorder6);
        outputPass.setBorder(BorderFactory.createLineBorder(Color.black, 5, true));
        inputTitle.setBackground(Color.lightGray);
        outputPass.setBackground(Color.lightGray);
        inputTitle.setForeground(Color.gray);
        outputPass.setOpaque(true);
        inputTitle.setFont(Assets.italic_45);
        outputPass.setFont(Assets.italic_35);

        // BottomPanel components
        bottomPanel.setLayout(Assets.passPageBottomPanelLayout);
        bottomPanel.add(confirm);
        bottomPanel.add(back);

        // Frame components
        frame.getContentPane().setLayout(Assets.frameLayout);
        frame.getContentPane().add(title);
        frame.getContentPane().add(inputTitle);
        frame.getContentPane().add(outputPass);
        frame.getContentPane().add(refresh);
        frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);

        // Load passwords
        for (Password password: UserData.loadFromPasswords()) {
            UserData.addPassword(password);
        }
    }
    private void backButtonFunction(Frame frame) {
        frame.setPage(Frame.MENU);
        UserData.saveToPasswords();
    }


    // Creates a random password
    private void generate(JTextField outputPass) {
        String randomPassword = RandomPassword.createRandomPassword();
        outputPass.setText(randomPassword);
        outputPass.setForeground(Color.darkGray);
    }

    // Confirms the password
    private void confirm(JTextField inputTitle, JTextField outputPass) {
        boolean conditionsMet = true;
        String input = inputTitle.getText();
        String output = outputPass.getText();

        if (output.equals("Requires password!...") || output.equals(".....") || output.equals("")) {
            outputPass.setText("Requires password!...");
            outputPass.setForeground(Color.gray);
            conditionsMet = false;
        }

        if (input.equals("") || input.equals("Requires title!...") || input.equals("Input title...")) {
            inputTitle.setText("Requires title!...");
            inputTitle.setForeground(Color.gray);
            inputTitle.setFont(Assets.italic_45);
            conditionsMet = false;
        }

        if (!StandardCharsets.US_ASCII.newEncoder().canEncode(output)) {
            outputPass.setText("ASCII keys only!...");
            outputPass.setForeground(Color.gray);
            conditionsMet = false;
        }

        if (conditionsMet) {
            UserData.addPassword(new Password(input, output));
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getComponent().getName().equals("in")) {
            if (inputTitle.getText().equals("Input title...") || inputTitle.getText().equals("Requires title!...")) {
                inputTitle.setText("");
                inputTitle.setForeground(Color.darkGray);
                inputTitle.setFont(Assets.font_45);
            }
        }
        else {
            if (outputPass.getText().equals("ASCII keys only!...") || outputPass.getText().equals("Requires password!...")
                    || outputPass.getText().equals(".....")) {
                outputPass.setText("");
                outputPass.setForeground(Color.darkGray);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}