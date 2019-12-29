package gui.customcomponents;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che implementa una versione personalizzata di JTextField che
 * permetta di settare la dimensione direttamente da costruttore
 */
public class CustomJTextField extends JTextField {

    public CustomJTextField(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
    }
}
