package UIv2.Pages;

import Objects.SelectButton;
import Objects.Term;
import UIv2.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TermsPage implements KeyListener {
    static int scrollYSize; // for incrementing scrollbar

    public static void create(Frame frame) {
        JPanel bottomPanel = new JPanel();

        // Components
        JLabel title = new JLabel("Modify Key Terms");
        JPanel viewPanel = new JPanel();
        JButton add = new JButton("Add");
        JButton remove = new JButton("Remove");
        JButton back = new JButton("Back");

        JPanel scroll = new JPanel();
        JScrollPane scrollPane = new JScrollPane(scroll, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Buttons
        back.addActionListener(e -> frame.setPage(PagesEnum.MENU));
        add.addActionListener(e -> TermsPage.addTerm(scroll));
        remove.addActionListener(e -> TermsPage.removeTerm(scroll));

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
        scroll.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 16));

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
    }

    static void addTerm(JPanel scroll) {
        // Removes incomplete JTextFields
        for (Component comp:scroll.getComponents()) {
            if (comp instanceof JTextField) {
                scroll.remove(comp);
            }
        }

        JTextField textField = new JTextField("Press ENTER to confirm");
        textField.setPreferredSize(new Dimension(500, 50));
        textField.setOpaque(false);
        textField.setFont(new Font("Arial", Font.PLAIN, 30));
        textField.setBorder(BorderFactory.createEtchedBorder());
        textField.addKeyListener(new TermsPage());

        // Increments scrollbar
        scrollYSize+=66;
        scroll.setPreferredSize(new Dimension(500, scrollYSize));

        scroll.add(textField);
        finaliseScroll(scroll);
    }

    static void validateTerm(JTextField textField) {
        // Replaces JTextField with JButton/checks for 18-character limit/only letters

        JPanel scroll = (JPanel) textField.getParent();
        boolean conditionsMet = true;

        if (textField.getText().length() <= 16) {
            for (char c:textField.getText().toCharArray()) {
                if(!Character.isLetter(c)) {
                    textField.setText("Must only include letters!...");
                    conditionsMet = false;
                    break;
                }
            }

        } else {
            textField.setText("Must not exceed 16 characters!...");
            conditionsMet = false;
        }

        if (conditionsMet) {
            scroll.remove(textField);
            SelectButton button = new SelectButton(textField.getText());
            scroll.add(button);
        }
        finaliseScroll(scroll);
    }

    static void removeTerm(JPanel scroll) {
        // Removes selected terms
        for (Component comp:scroll.getComponents()) {
            if (comp instanceof SelectButton && ((SelectButton) comp).selected) {
                scroll.remove(comp);
            }
        }

        // Decrements scrollbar
        scrollYSize-=66;
        scroll.setPreferredSize(new Dimension(500, scrollYSize));

        finaliseScroll(scroll);
    }

    static void finaliseScroll(JPanel scroll) {
        scroll.revalidate();
        scroll.repaint();
    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10) {
            validateTerm((JTextField) e.getComponent());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}