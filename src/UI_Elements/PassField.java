package UI_Elements;

import Objects.Password;

import java.awt.*;

public class PassField extends Field {
    private static final Font font_20 = font_30.deriveFont(20f);
    private static final Font font_25 = font_30.deriveFont(25f);

    public PassField(int width, int height, Password password) {
        super(width, height, true);
        this.setHorizontalAlignment(RIGHT);
        this.setText(password.getPass());
        this.setFont(font_20);

        Field titleField = new Field(width / 2, height - 10, false);
        titleField.setText(password.getTitle());
        titleField.setFont(font_25);
        this.add(titleField);
    }
}