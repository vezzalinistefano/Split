package gui.customComponents;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che implementa una versione personalizzata di {@link JTextField} che
 * permetta di settare la dimensione direttamente da costruttore
 */
public class CustomJTextField extends JTextField {

    /**
     * Imposta il text field alle dimensioni desiderate in modo tale
     * che non vada ad occupare tutto lo spazio disponibile quando si usano
     * certi layout
     *
     * @param width larghezza scelta
     * @param height altezza scelta
     */
    public CustomJTextField(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
    }
}
