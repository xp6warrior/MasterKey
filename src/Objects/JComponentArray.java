package Objects;

import java.awt.*;

public class JComponentArray {
    private final Component[] components;
    private final Component[] bottomPanelComponents;

    public JComponentArray(Component[] components, Component[] bottomPanel) {
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
