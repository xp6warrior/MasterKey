package UI_Elements;

import Objects.Password;

import java.awt.*;

public class PassField extends Field {
    private static final Font font_s = new Font("Arial", Font.PLAIN, 20);
    private static final Font font_l = new Font("Arial", Font.PLAIN, 25);

    public PassField(int width, int height, Password password) {
        super(width, height, true);
        this.setHorizontalAlignment(RIGHT);
        this.setText(password.getPass());
        this.setFont(font_s);

        Field titleField = new Field(width / 2, height - 10, false);
        titleField.setText(password.getTitle());
        titleField.setFont(font_l);
        this.add(titleField);
    }
}