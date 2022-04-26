package Objects.Fields;

import Core.MUserData;
import Objects.Term;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TermField extends MUserData implements KeyListener {
    private final JPanel scroll;
    private final JTextField textField;

    public TermField(JPanel scroll) {
        this.scroll = scroll;

        textField = new JTextField("Press ENTER to confirm");
        textField.setPreferredSize(new Dimension(500, 50));
        textField.setFont(new Font("Arial", Font.ITALIC, 30));
        textField.setOpaque(false);
        textField.addKeyListener(this);

        scroll.add(textField);
        scroll.revalidate();
        scroll.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==10) {

            boolean conditionsMet = true;
            String textFieldText = textField.getText();

            boolean hasSymbols = false; // Checking for symbols
            for (char c: textField.getText().toCharArray()) {
                if (!Character.isLetter(c)) {
                    hasSymbols = true;
                    break;
                }
            }

            if (hasSymbols) { // Conditioning
                textField.setText("Must contain only letters!!");
                conditionsMet = false;
            } else if (textFieldText.length() > 16 || textFieldText.length() < 3) {
                textField.setText("3-16 character limit!!");
                conditionsMet = false;
            }

            if (conditionsMet) { // If conditions met -> create button and create Term
                String termName = textFieldText.toLowerCase();
                scroll.remove(textField);

                Field field = new Field(termName);
                scroll.add(field);
                scroll.revalidate();
                scroll.repaint();

                addTerm(new Term(termName));
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}