package gui.rows;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che estende JPanel settando già il Box Layout allineato a sinistra
 */
public class Row extends JPanel{

    public Row() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setAlignmentX(Component.LEFT_ALIGNMENT);
    }
}
