package Objects;

import Core.ModTerms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TermField extends JButton implements MouseListener, KeyListener {
    private boolean selected;
    private String name;
    private final JPanel panel;
    private final JTextField textField;

    public TermField(JPanel panel, String name) {
        this.panel = panel;

        if (name.equals("")) { // If it's not loaded from Terms.txt -> create textfield
            textField = new JTextField("Press ENTER to confirm");
            textField.setPreferredSize(new Dimension(500, 50));
            textField.setBorder(BorderFactory.createEtchedBorder());
            textField.setFont(new Font("Arial", Font.PLAIN, 30));
            textField.setOpaque(false);
            textField.addKeyListener(this);

            panel.add(textField);
            panel.revalidate();
            panel.repaint();
        } else {
            textField = null;
            finaliseTerm(name);
        }
    }

    private void finaliseTerm(String name) {
        boolean conditionsMet = true;

        if (name.equals("")) { // If it isn't loaded from Terms.txt -> check conditions
            if (textField.getText().length() <= 16) { // If less than 16 characters -> continue
                boolean hasSymbols = false;

                for (char c: textField.getText().toCharArray()) {
                    if (!Character.isLetter(c)) {
                        hasSymbols = true;
                        break;
                    }
                }

                if (!hasSymbols) { // If it doesn't have symbols -> register name/remove textfield
                    this.name = textField.getText().toLowerCase();
                    panel.remove(textField);
                } else {
                    textField.setText("Letters only!...");
                    conditionsMet = false;
                }

            } else {
                textField.setText("16 character max!...");
                conditionsMet = false;
            }

        } else {
            this.name = name; // If it is loaded from Terms.txt -> use old name
        }

        if (conditionsMet) { // If conditions met -> create button and create Term
            this.setText(this.name);
            this.setPreferredSize(new Dimension(500, 50));
            this.setBorder(BorderFactory.createLineBorder(Color.gray, 1, false));
            this.setFont(new Font("Arial", Font.PLAIN, 30));
            this.setHorizontalAlignment(JButton.LEFT);
            this.setContentAreaFilled(false);
            this.addMouseListener(this);

            panel.add(this);
            panel.revalidate();
            panel.repaint();

            ModTerms.addTerm(new Term(this.name));
        }
    }

    public boolean getSelected() {
        return selected;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        selected = !selected;
        if (selected) {
            this.setBorder(BorderFactory.createLineBorder(Color.black, 3, false));
        } else {
            this.setBorder(BorderFactory.createLineBorder(Color.gray, 2, false));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (!selected) {
            this.setBorder(BorderFactory.createLineBorder(Color.gray, 2, false));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!selected) {
            this.setBorder(BorderFactory.createLineBorder(Color.gray, 1, false));
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==10) {
            finaliseTerm("");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}