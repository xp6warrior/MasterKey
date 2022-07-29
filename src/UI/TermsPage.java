package UI;

import Core.MUserData;
import Core.ToolBox;
import Fields.Field;
import Fields.TermField;
import Objects.Term;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TermsPage {
    private int scrollPanelHeight = 0;

    void create(Frame frame) {
        // Components
        JLabel title = new JLabel("Modify Terms");
        JButton add = new JButton("Add");
        JButton remove = new JButton("Remove");
        JButton back = new JButton("Back");
        JPanel viewPanel = new JPanel();
        JPanel scrollPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(scrollPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JPanel bottomPanel = new JPanel();

        // Removes blue highlight when clicking
        add.setFocusable(false);
        remove.setFocusable(false);
        back.setFocusable(false);

        // Buttons
        back.addActionListener(e -> backButtonFunction(frame));
        add.addActionListener(e -> this.add(scrollPanel));
        remove.addActionListener(e -> this.remove(scrollPanel));

        // Sizes
        title.setPreferredSize(ToolBox.titleSize);
        viewPanel.setPreferredSize(ToolBox.viewPanelSize);
        scrollPanel.setPreferredSize(ToolBox.scrollPanelSize);
        scrollPane.setPreferredSize(ToolBox.scrollPaneSize);
        add.setPreferredSize(ToolBox.termButtonSize);
        remove.setPreferredSize(ToolBox.termButtonSize);
        back.setPreferredSize(ToolBox.termButtonSize);

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
        add.setFont(ToolBox.font_35);
        remove.setFont(ToolBox.font_35);
        back.setFont(ToolBox.font_35);

        // BottomPanel components
        bottomPanel.setLayout(ToolBox.bottomPanelLayout);
        bottomPanel.add(add);
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

        // Load terms
        for (Term term: MUserData.loadFromTerms()) {
            scrollPanelHeight += 65;
            scrollPanel.setPreferredSize(new Dimension(550, scrollPanelHeight));
            scrollPanel.add(new Field(term.getName()));
            MUserData.addTerm(term);
        }
    }
    private void backButtonFunction(Frame frame) {
        frame.setPage(PageType.MENU);
        MUserData.saveToTerms();
        scrollPanelHeight = 0;
    }

    // Adds a TermField to the ScrollPanel
    private void add(JPanel scroll) {
        // Makes sure only one TextField at a time
        for (Component comp: scroll.getComponents()) {
            if (comp instanceof JTextField) {
                scroll.remove(comp);
                scrollPanelHeight -= 65;
            }
        }

        scrollPanelHeight += 65;
        scroll.setPreferredSize(new Dimension(550, scrollPanelHeight));

        new TermField(scroll);
    }

    // Removes TermField's from the ScrollPanel and removes the term from MUserData
    @SuppressWarnings("all")
    private void remove(JPanel scroll) {
        Object[] results = ToolBox.removeFieldFromList(scroll, scrollPanelHeight);
        ArrayList<String> terms = (ArrayList<String>) results[0];
        scrollPanelHeight = (int) results[1];

        for (String term: terms) {
            MUserData.removeTerm(term);
        }
    }
}