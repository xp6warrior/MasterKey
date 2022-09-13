package UI_Pages;

import Core.Assets;

import javax.swing.*;

public class Frame extends JFrame {
    private final MenuPage menu = new MenuPage();
    private final PassPage pass = new PassPage();
    private final TermsPage terms = new TermsPage();
    private final ViewPage view = new ViewPage();

    public static final int MENU = 0;
    public static final int PASS = 1;
    public static final int TERM = 2;
    public static final int VIEW = 3;

    public Frame() {
        this.setSize(600, 600);
        this.setTitle("Password Generator");
        this.setIconImage(Assets.lock);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        setPage(MENU);
    }

    void setPage(int page) {
        this.getContentPane().removeAll();

        switch (page) {
            case MENU: menu.create(this); break;
            case PASS: pass.create(this); break;
            case TERM: terms.create(this); break;
            case VIEW: view.create(this); break;
        }

        this.revalidate();
        this.repaint();
    }
}