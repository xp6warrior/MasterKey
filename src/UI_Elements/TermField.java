package UI_Elements;

import Core.CommonAssets;
import Core.UserData;
import Objects.Term;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TermField extends Field implements KeyListener {
    private static final Dimension smallSize = new Dimension(CommonAssets.fieldSize.width - 10, CommonAssets.fieldSize.height - 10);
    private static final Font fItalic = new Font("Arial", Font.ITALIC, 30);
    private static final Pattern symbols = Pattern.compile("[0-9!@#$%&*()_+=|<>?{}\\[\\]~-]");
    private static final Color bColor = new Color(0,0,0,0);

    private final JTextField textField;

    public TermField() {
        super(CommonAssets.fieldSize, false);

        textField = new JTextField("Press ENTER to confirm...");
        textField.setPreferredSize(smallSize); // -10 to account for gaps
        textField.setBackground(bColor);
        textField.setForeground(Color.gray);
        textField.setFont(fItalic);
        textField.setOpaque(false);
        textField.setBorder(null);
        textField.addKeyListener(this);
        textField.addMouseListener(this);

        this.add(textField);
    }

    // "Click-clearing"
    @Override
    public void mouseClicked(MouseEvent e) {
        if (!(e.getComponent() instanceof JTextField)) {
            super.mouseClicked(e);
        } else {
            if (clickRequirements()) {
                textField.setText("");
                textField.setForeground(Color.darkGray);
                textField.setFont(CommonAssets.fieldFont);
            }
        }
    }
    private boolean clickRequirements() {
        return textField.getText().equals("Press ENTER to confirm...") || textField.getText().equals("3-12 character limit!...")
                || textField.getText().equals("ASCII Letters only!...") || textField.getText().equals("Requires name!...");
    }

    // Disable borders for TextField
    @Override
    public void mouseEntered(MouseEvent e) {
        if (!(e.getComponent() instanceof JTextField)) {
            super.mouseEntered(e);
        }
    }
    @Override
    public void mouseExited(MouseEvent e) {
        if (!(e.getComponent() instanceof JTextField)) {
            super.mouseExited(e);
        }
    }

    // Enter functionality
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String resultMsg = enterRequirements();

            if (resultMsg == null) {
                String text = textField.getText().toLowerCase();
                this.setText(text);
                this.remove(textField);
                this.addMouseListener(this);

                UserData.addTerm(new Term(text.trim()));
            }
            else {
                textField.setText(resultMsg);
                textField.setForeground(Color.gray);
                textField.setFont(fItalic);
            }
        }
    }
    private String enterRequirements() {
        String resultMsg = null;
        Matcher mSymbols = symbols.matcher(textField.getText());
        String text = textField.getText();

        if (text.length() < 3 || text.length() > 12) {
            resultMsg = "3-12 character limit!...";
        }
        if (!StandardCharsets.US_ASCII.newEncoder().canEncode(text) || mSymbols.find()) {
            resultMsg = "ASCII Letters only!...";
        }
        if (text.equals("") || text.equals("3-12 character limit!...") || text.equals("ASCII Letters only!...") || text.equals("Requires name!...")) {
            resultMsg = "Requires name!...";
        }

        return resultMsg;
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
