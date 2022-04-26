package Objects.Fields;

import Core.ModTerms;
import Objects.Term;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TermField extends Field implements KeyListener {
    private final JTextField textField;
    private final JPanel panel;

    public TermField(JPanel panel) {
        this.panel = panel;
        textField = new JTextField("Press ENTER to confirm");
        textField.setPreferredSize(new Dimension(500, 50));
        textField.setBorder(BorderFactory.createEtchedBorder());
        textField.setFont(new Font("Arial", Font.PLAIN, 30));
        textField.setOpaque(false);
        textField.addKeyListener(this);
        panel.add(textField);
        panel.revalidate();
        panel.repaint();
    }

    private void finaliseTerm() {
        boolean conditionsMet = true;
        String name = "";

        if (textField.getText().length() <= 16) { // If less than 16 characters -> continue

            if (textField.getText().length() >= 3) { // If more than 3 characters -> continue

                boolean hasSymbols = false;
                for (char c: textField.getText().toCharArray()) {
                    if (!Character.isLetter(c)) {
                        hasSymbols = true;
                        break;
                    }
                }
                if (!hasSymbols) { // If it doesn't have symbols -> register name/remove textfield
                    name = textField.getText().toLowerCase();
                    panel.remove(textField);
                } else {
                    textField.setText("Letters only!...");
                    conditionsMet = false;
                }

            } else {
                textField.setText("Min 3 characters!...");
                conditionsMet = false;
            }

        } else {
            textField.setText("16 character max!...");
            conditionsMet = false;
        }

        if (conditionsMet) { // If conditions met -> create button and create Term
            this.setText(name);
            this.setPreferredSize(new Dimension(500, 50));
            this.setBorder(BorderFactory.createLineBorder(Color.gray, 1, false));
            this.setFont(new Font("Arial", Font.PLAIN, 30));
            this.setHorizontalAlignment(JButton.LEFT);
            this.setContentAreaFilled(false);

            panel.add(this);
            panel.revalidate();
            panel.repaint();

            ModTerms.addTerm(new Term(name));
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==10) {
            finaliseTerm();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}