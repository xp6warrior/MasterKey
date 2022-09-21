package Objects;

import java.awt.*;

public class ComponentArray {
    private final Component[] components;
    private final Component[] bottomPanelComponents;

    public ComponentArray(Component[] components, Component[] bottomPanel) {
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
