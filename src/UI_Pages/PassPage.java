package UI_Pages;

import Objects.JComponentArray;
import Core.UserData;
import Core.RandomPassword;
import Objects.Password;
import UI_Elements.Button;
import Core.CommonAssets;
import UI_Elements.InputField;
import UI_Elements.Title;

import javax.swing.*;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class PassPage {
    private static final ImageIcon refreshIcon = new ImageIcon(Objects.requireNonNull(PassPage.class.getResource("/refresh.png")));
    private static final Dimension buttonSize = new Dimension(265, 60);
    private static final Dimension refreshSize = new Dimension(125, 125);

    private InputField inputTitle;
    private InputField outputPass;

    public void create(Frame frame) {
        // Components
        Title title = new Title("Create New Password", CommonAssets.titleSize);
        inputTitle = new InputField("in");
        outputPass = new InputField("out");
        Button refresh = new Button(refreshIcon, refreshSize);
        Button confirm = new Button("Confirm", buttonSize);
        Button back = new Button("Back", buttonSize);

        JComponentArray components = new JComponentArray(new JComponent[]{title, inputTitle, outputPass, refresh}, new JComponent[]{confirm, back});

        // Buttons
        refresh.addActionListener(e -> generate(outputPass));
        confirm.addActionListener(e -> confirm(inputTitle, outputPass));
        back.addActionListener(e -> {
            frame.setPage(Frame.MENU);
            UserData.saveToPasswords();
        });

        // Adding
        frame.add(components);

        // Load passwords
        for (Password password: UserData.loadFromPasswords()) {
            UserData.addPassword(password);
        }
    }

    // Creates a random password
    private void generate(InputField outputPass) {
        String randomPassword = RandomPassword.createRandomPassword();
        if (randomPassword != null) {
            outputPass.setText(randomPassword);
            outputPass.setForeground(Color.darkGray);
        }
    }

    // Confirms the password
    private void confirm(InputField inputTitle, InputField outputPass) {
        boolean conditionsMet = true;
        String input = inputTitle.getText();
        String output = outputPass.getText();

        if (output.length() > 16) {
            outputPass.setText("16 character limit!...");
            outputPass.setForeground(Color.gray);
            conditionsMet = false;
        }

        if (input.length() > 16) {
            inputTitle.setText("16 character limit!...");
            inputTitle.setForeground(Color.gray);
            inputTitle.setFont(CommonAssets.inputItalic);
            conditionsMet = false;
        }

        if (output.equals("Requires password!...") || output.equals(".....") || output.equals("") || output.equals("16 character limit!...")) {
            outputPass.setText("Requires password!...");
            outputPass.setForeground(Color.gray);
            conditionsMet = false;
        }

        if (input.equals("") || input.equals("Requires title!...") || input.equals("Input title...") || input.equals("Success!...") || input.equals("16 character limit!...")) {
            inputTitle.setText("Requires title!...");
            inputTitle.setForeground(Color.gray);
            inputTitle.setFont(CommonAssets.inputItalic);
            conditionsMet = false;
        }

        if (!StandardCharsets.US_ASCII.newEncoder().canEncode(output)) {
            outputPass.setText("ASCII keys only!...");
            outputPass.setForeground(Color.gray);
            conditionsMet = false;
        }

        if (conditionsMet) {
            UserData.addPassword(new Password(input, output));

            inputTitle.setText("Success!...");
            inputTitle.setFont(CommonAssets.inputItalic);
            inputTitle.setForeground(Color.gray);
        }
    }
}