package UI_Elements;

import Objects.Password;

import java.awt.*;

public class PassField extends Field {
    private static final Font font_s = new Font("Arial", Font.PLAIN, 20);
    private static final Font font_l = new Font("Arial", Font.PLAIN, 25);
    private static final Dimension size = new Dimension(500, 50);

    public PassField(Password password) {
        super(size.width, size.height, true);
        this.setHorizontalAlignment(RIGHT);
        this.setText(password.getPass());
        this.setFont(font_s);

        Field titleField = new Field(size.width / 2, size.height - 10, false);
        titleField.setText(password.getTitle());
        titleField.setFont(font_l);
        this.add(titleField);
    }
}