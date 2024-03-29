package UI_Pages;

import Core.CommonAssets;
import Core.MasterKey;
import Objects.JComponentArray;
import UI_Elements.Button;
import UI_Elements.Title;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MenuPage {
    private static final Dimension buttonSize = new Dimension(550, 110);
    private static final Dimension titleSize = new Dimension(350, 150);

    public void create(Frame frame) {
        // Components
        Title title = new Title(MasterKey.name, titleSize);
        Button newButton = new Button("Create New Password", buttonSize);
        Button termsButton = new Button("Add / Remove Terms", buttonSize);
        Button viewButton = new Button("View Passwords", buttonSize);
        JLabel lock = new JLabel(CommonAssets.padlock);
        JLabel key = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/key.png"))));

        JComponentArray components = new JComponentArray(new JComponent[]{lock, title, key, newButton, termsButton, viewButton}, null);

        // Buttons
        newButton.addActionListener(e -> frame.setPage(Frame.PASS));
        termsButton.addActionListener(e -> frame.setPage(Frame.TERM));
        viewButton.addActionListener(e -> frame.setPage(Frame.VIEW));

        // Adding
        frame.add(components);
    }
}