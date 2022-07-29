package UI;

import Core.MUserData;
import Core.ToolBox;

import javax.swing.*;
import java.awt.*;

public class MenuPage extends MUserData {
    void create(Frame frame) {
        // Components
        JLabel title = new JLabel("Password Generator");
        JButton newButton = new JButton("Create new password");
        JButton termsButton = new JButton("Modify key terms");
        JButton viewButton = new JButton("View passwords");

        // Removes blue highlight when clicking
        newButton.setFocusable(false);
        termsButton.setFocusable(false);
        viewButton.setFocusable(false);

        // Buttons
        newButton.addActionListener(e -> newButtonFunction(frame, newButton));
        termsButton.addActionListener(e -> frame.setPage(PageType.TERMS));
        viewButton.addActionListener(e -> frame.setPage(PageType.VIEW));

        // Sizes
        title.setPreferredSize(ToolBox.titleSize);
        newButton.setPreferredSize(ToolBox.menuButtonSize);
        termsButton.setPreferredSize(ToolBox.menuButtonSize);
        viewButton.setPreferredSize(ToolBox.menuButtonSize);

        // Alignment
        title.setHorizontalAlignment(SwingConstants.CENTER);

        // Fonts
        title.setFont(ToolBox.font_50);
        newButton.setFont(ToolBox.font_35);
        termsButton.setFont(ToolBox.font_35);
        viewButton.setFont(ToolBox.font_35);

        // Frame components
        frame.getContentPane().setLayout(ToolBox.menuPageLayout);
        frame.getContentPane().add(title);
        frame.getContentPane().add(newButton);
        frame.getContentPane().add(termsButton);
        frame.getContentPane().add(viewButton);
    }
    private void newButtonFunction(Frame frame, JButton newButton) {
        if (!loadFromTerms().isEmpty()) {
            frame.setPage(PageType.PASS);
        } else {
            newButton.setFont(new Font("Arial", Font.ITALIC, 35));
            newButton.setForeground(Color.gray);
            newButton.setText("Create terms first!...");
        }
    }
}