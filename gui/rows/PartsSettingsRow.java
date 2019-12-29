package gui.rows;

import gui.customcomponents.CustomJSeparator;
import gui.customcomponents.CustomJTextField;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che implementa il pannello riga che permette all'utente di selezionare
 * la dimensione delle parti e il numero di parti
 */
public class PartsSettingsRow extends Row {
    private JSpinner   spnrPartsNumber;
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

        this.add(txtPartSize);

        this.add(new JLabel("  B"));

        this.add(Box.createRigidArea(new Dimension(10,0)));

        CustomJSeparator separator1;
        separator1 = new CustomJSeparator();
        this.add(separator1);

        this.add(Box.createRigidArea(new Dimension(10,0)));

        this.add(new JLabel("Numero parti  "));

        this.add(spnrPartsNumber);

    }
}
