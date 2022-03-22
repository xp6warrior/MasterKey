package Objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TermField extends JButton implements MouseListener, KeyListener {
    public boolean selected;
    private final JPanel panel;
    private final JTextField textField;

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

    private void FinaliseTerm() {
        if (textField.getText().length() <= 16) {
            boolean hasSymbols = false;

            for (char c: textField.getText().toCharArray()) {
                if (!Character.isLetter(c)) {
                    hasSymbols = true;
                    break;
                }
            }

            if (!hasSymbols) {
                this.setText(textField.getText().toLowerCase());
                this.setPreferredSize(new Dimension(500, 50));
                this.setBorder(BorderFactory.createLineBorder(Color.gray, 1, false));
                this.setFont(new Font("Arial", Font.PLAIN, 30));
                this.setHorizontalAlignment(JButton.LEFT);
                this.setContentAreaFilled(false);
                this.addMouseListener(this);

                panel.remove(textField);
                panel.add(this);
                panel.revalidate();
                panel.repaint();
            } else {
                textField.setText("Letters only!...");
            }

        } else {
            textField.setText("16 character max!...");
        }
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
            FinaliseTerm();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}