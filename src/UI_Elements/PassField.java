package UI_Elements;

import Core.CommonAssets;
import Objects.Password;

import java.awt.*;

public class PassField extends Field {
    private static final Dimension smallSize = new Dimension(CommonAssets.fieldSize.width / 2, CommonAssets.fieldSize.height - 10);
    private static final Font font_s = new Font("Arial", Font.PLAIN, 20);
    private static final Font font_l = new Font("Arial", Font.PLAIN, 25);

    public PassField(Password password) {
        super(CommonAssets.fieldSize, true);
        this.setHorizontalAlignment(RIGHT);
        this.setText(password.getPass());
        this.setFont(font_s);

        Field titleField = new Field(smallSize, false);
        titleField.setText(password.getTitle());
        titleField.setFont(font_l);
        this.add(titleField);
    }
}