package gui.customComponents;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che implementa una versione personalizzata in dimensioni
 * e orientamento di un {@link JSeparator}
 */
public class CustomJSeparator extends JSeparator {

    /**
     * Inizializza un nuovo {@link JSeparator} di dimensioni 1x30
     */
    public CustomJSeparator () {
        super(JSeparator.VERTICAL);
        this.setPreferredSize(new Dimension(1, 30));
        this.setMaximumSize(new Dimension(1, 30));
    }
}
