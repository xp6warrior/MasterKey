package UI_Pages;

import Objects.ComponentArray;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Frame extends JFrame {
    public static final LayoutManager bottomPanelLayout = new FlowLayout(FlowLayout.CENTER, 20, 10);

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
        this.setTitle("KeyWizard");
        this.setName("KeyWizard");
        this.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/padlock.png"))).getImage());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));

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

    public void add(ComponentArray components) {
        for (Component comp: components.getComponents()) {
            this.getContentPane().add(comp);
        }

        if (components.getBottomPanelComponents() != null) {
            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(bottomPanelLayout);

            for (Component comp: components.getBottomPanelComponents()) {
                bottomPanel.add(comp);
            }

            this.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
        }
    }
}