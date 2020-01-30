package gui.rows;

import gui.customComponents.CustomJSeparator;
import gui.customComponents.CustomJTextField;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che implementa il pannello riga che permette all'utente di selezionare
 * la dimensione delle parti e il numero di parti
 */
public class PartsSettingsRow extends Row {
    private JSpinner spnrPartsNumber;
    private CustomJTextField txtPartSize;

    public PartsSettingsRow() {
        super();

        txtPartSize = new CustomJTextField(80, 24);

        SpinnerModel model =
                new SpinnerNumberModel(1, 1, 50, 1);

        spnrPartsNumber = new JSpinner(model);
        spnrPartsNumber.setPreferredSize(new Dimension(50,24));
        spnrPartsNumber.setMaximumSize(new Dimension(50,24));

        this.add(Box.createRigidArea(new Dimension(5,0)));

        this.add(new JLabel("Dimensioni parti  "));

        txtPartSize.setText("1");
        this.add(txtPartSize);

        this.add(new JLabel("  MB"));

        this.add(Box.createRigidArea(new Dimension(10,0)));

        CustomJSeparator separator1;
        separator1 = new CustomJSeparator();
        this.add(separator1);

        this.add(Box.createRigidArea(new Dimension(10,0)));

        this.add(new JLabel("Numero parti  "));

        this.add(spnrPartsNumber);
    }

    public int getParts() {
        return (Integer) spnrPartsNumber.getValue();
    }

    public int getFileSize() {
        try{
            int parts = Integer.parseInt(txtPartSize.getText());
            if(parts > 0) return parts;
            else return -1;
        } catch (NumberFormatException e) { return -1; }
    }
}
