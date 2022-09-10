package Fields;

import Core.MUserData;
import Core.ToolBox;
import Objects.Term;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.charset.StandardCharsets;

public class TermField implements KeyListener, MouseListener {
    private final JPanel scrollPanel;
    private final JTextField textField;

    public TermField(JPanel scrollPanel) {
        this.scrollPanel = scrollPanel;

        textField = new JTextField("Press ENTER to confirm...");
        textField.setPreferredSize(ToolBox.fieldSize);
        textField.setFont(ToolBox.italic_30);
        textField.setForeground(Color.gray);
        textField.setOpaque(false);
        textField.addKeyListener(this);
        textField.addMouseListener(this);

        scrollPanel.add(textField);
        scrollPanel.revalidate();
        scrollPanel.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10) {
            boolean conditionsMet = true;
            String textFieldText = textField.getText();

            boolean hasSymbols = false;
            for (char c: textField.getText().toCharArray()) {
                if (!Character.isLetter(c)) {
                    hasSymbols = true;
                    break;
                }
            }

            if (hasSymbols || !StandardCharsets.US_ASCII.newEncoder().canEncode(textFieldText)) {
                textField.setText("Must contain only ASCII letters!...");
                conditionsMet = false;
            }

            if (textFieldText.length() > 12 || textFieldText.length() < 3) {
                textField.setText("3-12 character limit!...");
                conditionsMet = false;
            }


            if (conditionsMet) {
                scrollPanel.remove(textField);
                Field field = new Field(textFieldText.toLowerCase());
                ToolBox.updateUI(scrollPanel, field, true);

                MUserData.addTerm(new Term(textFieldText.toLowerCase()));
            } else {
                textField.setForeground(Color.gray);
                textField.setFont(ToolBox.italic_30);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (textField.getText().equals("Press ENTER to confirm...")
                || textField.getText().equals("3-12 character limit!...")
                || textField.getText().equals("Must contain only ASCII letters!...")) {
            textField.setText("");
            textField.setForeground(Color.darkGray);
            textField.setFont(ToolBox.font_30);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}