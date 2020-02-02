package gui.rows;

import gui.customComponents.CustomJTextField;

import javax.swing.*;
import java.awt.*;

public class KeywordRow extends Row {
    private CustomJTextField txtKeyWord;

    public KeywordRow() {
        super();

        txtKeyWord = new CustomJTextField(128, 24);

        this.add(Box.createRigidArea(new Dimension(5,0)));

        this.add(new JLabel("Parola chiave  "));

        this.add(txtKeyWord);
    }

    public String getKeyword() {
        return txtKeyWord.getText();
    }
}
