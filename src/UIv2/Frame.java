package UIv2;

import UIv2.Pages.*;
import javax.swing.*;

public class Frame extends JFrame {
    public Frame() {
        this.setSize(600, 600);
        this.setTitle("Password Generator");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        setPage(PagesEnum.MENU);
    }

    void clearPage() {
        this.getContentPane().removeAll();
    }

    void finalisePage() {
        this.revalidate();
        this.repaint();
    }

    public void setPage(PagesEnum page) {
        clearPage();

        switch (page) {
            case MENU:MenuPage.create(this);
            case PASS:PassPage.create(this);
            case TERMS:TermsPage.create(this);
            case VIEW:ViewPage.create(this);
        }

        finalisePage();
    }
}