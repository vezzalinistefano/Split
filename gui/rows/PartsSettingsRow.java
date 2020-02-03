package gui.rows;

import gui.customComponents.CustomJSeparator;
import gui.customComponents.CustomJTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

/**
 * Classe che implementa il pannello riga che permette all'utente di selezionare
 * la dimensione delle parti e il numero di parti
 */
public class PartsSettingsRow extends Row implements ItemListener {
    private JSpinner spnrPartsNumber;
    private CustomJTextField txtPartSize;

    private JRadioButton bySizeCheckBox;
    private JRadioButton byPartsCheckBox;

    public PartsSettingsRow() {
        super();

        txtPartSize = new CustomJTextField(80, 24);

        SpinnerModel model =
                new SpinnerNumberModel(1, 1, 50, 1);

        spnrPartsNumber = new JSpinner(model);
        spnrPartsNumber.setPreferredSize(new Dimension(50, 24));
        spnrPartsNumber.setMaximumSize(new Dimension(50, 24));
        bySizeCheckBox = new JRadioButton();
        bySizeCheckBox.setSelected(true);

        this.add(Box.createRigidArea(new Dimension(5, 0)));

        this.add(bySizeCheckBox);

        this.add(Box.createRigidArea(new Dimension(5, 0)));

        this.add(new JLabel("Dimensioni parti  "));

        txtPartSize.setText("1");
        this.add(txtPartSize);

        this.add(new JLabel("  MB"));

        this.add(Box.createRigidArea(new Dimension(10, 0)));

        byPartsCheckBox = new JRadioButton();

        CustomJSeparator separator1;
        separator1 = new CustomJSeparator();
        this.add(separator1);

        this.add(Box.createRigidArea(new Dimension(10, 0)));

        this.add(byPartsCheckBox);

        this.add(Box.createRigidArea(new Dimension(10, 0)));

        this.add(new JLabel("Numero parti  "));

        this.add(spnrPartsNumber);

        ButtonGroup group = new ButtonGroup();
        group.add(byPartsCheckBox);
        group.add(bySizeCheckBox);

        bySizeCheckBox.addItemListener(this);
        byPartsCheckBox.addItemListener(this);

        spnrPartsNumber.setEnabled(false);

    }

    public int getParts() {
        if (byPartsCheckBox.isSelected())
            return (Integer) spnrPartsNumber.getValue();
        else
            return -1;
    }

    public int getFileSize() {
        if (bySizeCheckBox.isSelected()) {
            try {
                int parts = Integer.parseInt(txtPartSize.getText());
                if (parts > 0) return parts;
                else return -1;
            } catch (NumberFormatException e) {
                return -1;
            }
        } else
            return -1;
    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        if (byPartsCheckBox.isSelected()) {
            txtPartSize.setEnabled(false);
            spnrPartsNumber.setEnabled(true);
        } else if (bySizeCheckBox.isSelected()) {
            txtPartSize.setEnabled(true);
            spnrPartsNumber.setEnabled(false);
        }
    }
}
