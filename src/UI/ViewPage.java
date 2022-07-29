package UI;

import Core.MUserData;
import Core.ToolBox;
import Fields.PassField;
import Objects.Password;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewPage {
    private int scrollPanelHeight = 0;

    void create(Frame frame) {
        // Components
        JLabel title = new JLabel("View Passwords");
        JPanel viewPanel = new JPanel();
        JButton remove = new JButton("Remove");
        JButton back = new JButton("Back");
        JPanel scrollPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(scrollPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JPanel bottomPanel = new JPanel();

        // Removes blue highlight when clicking
        remove.setFocusable(false);
        back.setFocusable(false);

        // Buttons
        back.addActionListener(e -> backButtonFunction(frame));
        remove.addActionListener(e -> remove(scrollPanel));

        // Sizes
        title.setPreferredSize(ToolBox.titleSize);
        viewPanel.setPreferredSize(ToolBox.viewPanelSize);
        scrollPanel.setPreferredSize(ToolBox.scrollPanelSize);
        scrollPane.setPreferredSize(ToolBox.scrollPaneSize);
        back.setPreferredSize(ToolBox.termButtonSize);
        remove.setPreferredSize(ToolBox.removeSize);

        // Alignment / Color / Border
        title.setHorizontalAlignment(SwingConstants.CENTER);
        viewPanel.setBorder(ToolBox.blackBorder6);
        viewPanel.setBackground(Color.lightGray);
        scrollPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 12));
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPanel.setOpaque(false);

        // Fonts
        title.setFont(ToolBox.font_50);
        remove.setFont(ToolBox.font_35);
        back.setFont(ToolBox.font_35);

        // BottomPanel components
        bottomPanel.setLayout(ToolBox.bottomPanelLayout);
        bottomPanel.add(remove);
        bottomPanel.add(back);

        // ViewPanel components
        viewPanel.setLayout(ToolBox.viewPanelLayout);
        viewPanel.add(scrollPane);

        // Frame components
        frame.getContentPane().setLayout(ToolBox.frameLayout);
        frame.getContentPane().add(title);
        frame.getContentPane().add(viewPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);

        // Load passwords
        for (Password password: MUserData.loadFromPasswords()) {
            scrollPanelHeight += 65;
            scrollPanel.setPreferredSize(new Dimension(550, scrollPanelHeight));
            scrollPanel.add(new PassField(password));
            MUserData.addPassword(password);
        }
    }
    private void backButtonFunction(Frame frame) {
        frame.setPage(PageType.MENU);
        MUserData.saveToPasswords();
        scrollPanelHeight = 0;
    }

    // Removes PassField's from the ScrollPanel and removes the password from MUserData
    @SuppressWarnings("all")
    private void remove(JPanel scroll) { // Removing password
        Object[] results = ToolBox.removeFieldFromList(scroll, scrollPanelHeight);
        ArrayList<String> passwords = (ArrayList<String>) results[0];
        scrollPanelHeight = (int) results[1];

        for (String password: passwords) {
            MUserData.removePassword(password);
        }
    }
}