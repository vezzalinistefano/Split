package gui.customcomponents;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che implementa una versione personalizzata in dimensioni
 * e orientamento di un JSeparator
 */
public class CustomJSeparator extends JSeparator {
    public CustomJSeparator () {
        super(JSeparator.VERTICAL);
        this.setPreferredSize(new Dimension(1, 30));
        this.setMaximumSize(new Dimension(1, 30));
    }
}
