package UI;

import javax.swing.*;

public class Frame extends JFrame {
    private final MenuPage menu = new MenuPage();
    private final PassPage pass = new PassPage();
    private final TermsPage terms = new TermsPage();
    private final ViewPage view = new ViewPage();

    public Frame() {
        this.setSize(600, 600);
        this.setTitle("Password Generator");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        setPage(PageType.MENU);
    }

    public void setPage(PageType page) {
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
}