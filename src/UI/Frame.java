package UI;

import Core.Cryptography;
import Core.MUserData;
import Core.ToolBox;

import javax.swing.*;
import java.nio.charset.StandardCharsets;

public class Frame extends JFrame {
    private final MenuPage menu = new MenuPage();
    private final PassPage pass = new PassPage();
    private final TermsPage terms = new TermsPage();
    private final ViewPage view = new ViewPage();

    public Frame() {
        this.setSize(600, 600);
        this.setTitle("Password Generator");
        this.setIconImage(ToolBox.lock);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        setPage(PageType.MENU);
    }

    void setPage(PageType page) {
        this.getContentPane().removeAll();

        switch (page) {
            case MENU: menu.create(this); break;
            case PASS: pass.create(this); break;
            case TERMS: terms.create(this); break;
            case VIEW: view.create(this); break;
        }

        this.revalidate();
        this.repaint();
    }


    public static void main(String[] args) {
        MUserData.createDirectory();
        String key;

        if (MUserData.hasData() == null) {
            key = JOptionPane.showInputDialog(null, "Create Password:", "Password Generator", JOptionPane.INFORMATION_MESSAGE);
            if (key != null) {
                if (!key.equals("") && key.length() <= 16 && StandardCharsets.US_ASCII.newEncoder().canEncode(key)) {
                    Cryptography.setKey(key);
                    new Frame();

                } else {
                    JOptionPane.showMessageDialog(null, "Password must be between 1-16 ASCII characters", "Password Generator", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else {
            key = JOptionPane.showInputDialog(null, "Enter Password:", "Password Generator", JOptionPane.QUESTION_MESSAGE);

            if (key != null) {
                if (Cryptography.keyTest(key)) {
                    new Frame();
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Password", "Password Generator", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}