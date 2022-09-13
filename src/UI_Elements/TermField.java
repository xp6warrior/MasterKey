package UI_Elements;

import Core.MUserData;
import Objects.Term;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TermField extends Field implements KeyListener {
    private final JTextField textField;

    public TermField(int width, int height) {
        super(width, height, false);

        textField = new JTextField("Enter Term");
        textField.setPreferredSize(new Dimension(width - 10, height - 10)); // -10 to account for gaps
        textField.setBackground(new Color(0,0,0,0));
        textField.setForeground(Color.gray);
        textField.setFont(font_30);
        textField.setBorder(null);
        textField.addKeyListener(this);

        this.add(textField);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.setText(textField.getText());
            this.remove(textField);
            this.addMouseListener(this);

            MUserData.addTerm(new Term(textField.getText().trim()));
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
