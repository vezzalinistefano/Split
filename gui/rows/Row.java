package gui.rows;

import javax.swing.*;
import java.awt.*;

/**
 * UN {@link JPanel} in cui è già settato Box Layout allineato a sinistra
 */
public abstract class Row extends JPanel{

    /**
     * Costruisce una nuova Row
     */
    public Row() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setAlignmentX(Component.LEFT_ALIGNMENT);
    }
}
