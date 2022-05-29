package UI;

import Core.MUserData;
import Objects.Fields.Field;
import Objects.Fields.TermField;
import Objects.Term;

import javax.swing.*;
import java.awt.*;

class TermsPage extends MUserData {
    private int scrollHeight = 0;

    void create(Frame frame) {
        JPanel bottomPanel = new JPanel();

        // Components
        JLabel title = new JLabel("Modify Terms");
        JPanel viewPanel = new JPanel();
        JButton add = new JButton("Add");
        JButton remove = new JButton("Remove");
        JButton back = new JButton("Back");

        JPanel scroll = new JPanel();
        JScrollPane scrollPane = new JScrollPane(scroll, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Buttons
        back.addActionListener(e -> {
            frame.setPage(PageType.MENU);
            saveToTerms();
            scrollHeight = 0;
        });
        add.addActionListener(e -> this.add(scroll));
        remove.addActionListener(e -> this.remove(scroll));

        // Sizes
        title.setPreferredSize(new Dimension(600, 150));
        viewPanel.setPreferredSize(new Dimension(550, 270));
        add.setPreferredSize(new Dimension(170, 60));
        remove.setPreferredSize(new Dimension(170, 60));
        back.setPreferredSize(new Dimension(170, 60));
        scroll.setPreferredSize(new Dimension(550, 0));
        scrollPane.setPreferredSize(new Dimension(540, 260));

        // Text/fonts/borders/colors
        Font smallFont = new Font("Arial", Font.PLAIN, 35);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        viewPanel.setBorder(BorderFactory.createLineBorder(Color.black, 6, true));
        viewPanel.setBackground(Color.lightGray);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scroll.setOpaque(false);
        scroll.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 12));

        // Fonts
        title.setFont(new Font("Arial", Font.BOLD, 50));
        add.setFont(smallFont);
        remove.setFont(smallFont);
        back.setFont(smallFont);

        // BottomPanel components
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.add(add);
        bottomPanel.add(remove);
        bottomPanel.add(back);

        // ViewPanel components
        viewPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        viewPanel.add(scrollPane);

        // Frame components
        frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
        frame.getContentPane().add(title);
        frame.getContentPane().add(viewPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);

        // Load terms
        for (Term term: loadFromTerms()) {
            scrollHeight += 65;
            scroll.setPreferredSize(new Dimension(550, scrollHeight));
            scroll.add(new Field(term.getName()));
            addTerm(term);
        }
    }

    private void add(JPanel scroll) { // Adds a term
        for (Component comp: scroll.getComponents()) { // Makes sure only one TextField at a time
            if (comp instanceof JTextField) {
                scroll.remove(comp);
                scrollHeight -= 65;
            }
        }
        scrollHeight += 65;
        scroll.setPreferredSize(new Dimension(550, scrollHeight));

        new TermField(scroll); // Gets added by itself
    }

    private void remove(JPanel scroll) { // Removes term
        for (Component comp: scroll.getComponents()) {
            if (comp instanceof Field && ((Field) comp).getSelected()) {
                Field field = (Field)comp;

                if (field.getSelected()) {
                    scroll.remove(field);
                    scroll.revalidate();
                    scroll.repaint();
                    scrollHeight -= 64;
                    scroll.setPreferredSize(new Dimension(550, scrollHeight));

                    removeTerm(field.getText());
                }
            }
        }
    }
}