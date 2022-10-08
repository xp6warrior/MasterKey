package Objects;

import javax.swing.*;
import java.awt.*;

public class JComponentArray {
    private final JComponent[] components;
    private final JComponent[] bottomPanelComponents;

    public JComponentArray(JComponent[] components, JComponent[] bottomPanel) {
        this.components = components;
        this.bottomPanelComponents = bottomPanel;
    }

    public Component[] getComponents() {
        return components;
    }

    public Component[] getBottomPanelComponents() {
        return bottomPanelComponents;
    }

}
