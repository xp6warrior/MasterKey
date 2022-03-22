package UIv2;

import UIv2.Pages.*;
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

        setPage(PagesEnum.MENU);
    }

    public void setPage(PagesEnum page) {
        this.getContentPane().removeAll();

        switch (page) {
            case MENU: menu.create(this);
            case PASS: pass.create(this);
            case TERMS: terms.create(this);
            case VIEW: view.create(this);
        }

        this.revalidate();
        this.repaint();
    }
}