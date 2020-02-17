package gui.rows;

import gui.customComponents.CustomJSeparator;
import gui.customComponents.CustomJTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


/**
 * La {@link Row} che permette all'utente di selezionare
 * la dimensione delle parti e il numero di parti
 */
public class PartsSettingsRow extends Row implements ItemListener {
    /**
     * Permette di inserire il numero di parti in cui dividere il
     * file
     */
    private JSpinner spnrPartsNumber;

    /**
     * Permette di inserire la dimensione di ogni file risultante dalla divisione
     */
    private CustomJTextField txtPartSize;

    /**
     * permette di effettuare una divisione in base al numero di parti
     */
    private JRadioButton bySizeCheckBox;

    /**
     * Permette di effettuare la divisione in base alla dimensione inserita
     */
    private JRadioButton byPartsCheckBox;

    private CompressRow compressRow;
    private CryptRow cryptRow;
    private DefaultRow defaultRow;

    /**
     * Costruisce una nuova PartsSettingsRow
     *
     * @param compressRow Riferimento alla {@link CompressRow } che permette di usare una modalità
     *                    di divisione alla volta
     * @param cryptRow    Riferimento alla {@link CryptRow} che permette di usare una modalità di
     *                    divisione alla volta
     * @param defaultRow  Riferimento alla {@link DefaultRow } che permette di usare una modalità
     *                    di divisione alla volta
     */
    public PartsSettingsRow(CompressRow compressRow, CryptRow cryptRow, DefaultRow defaultRow) {
        super();

        this.cryptRow = cryptRow;
        this.compressRow = compressRow;
        this.defaultRow = defaultRow;

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

    /**
     * Prende il valore inserito in {@link #spnrPartsNumber}
     *
     * @return Il numero di parti in cui si vuole dividere il file
     */
    public int getParts() {
        if (byPartsCheckBox.isSelected())
            return (Integer) spnrPartsNumber.getValue();
        else
            return -1;
    }

    /**
     * Prende il valore inserito in {@link #txtPartSize}
     *
     * @return La dimensione massima scelta di ogni file nato dalla divisione
     */
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

    /**
     * Rende operativa solo una delle tre modalità alla volta
     *
     * @param itemEvent Cattura la selezione di una nuova checkbox
     */
    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        if (byPartsCheckBox.isSelected()) {
            txtPartSize.setEnabled(false);
            spnrPartsNumber.setEnabled(true);
            cryptRow.setStatus(false);
            compressRow.getCompressCheckBox().setEnabled(false);
            defaultRow.getDefaultChkBox().setEnabled(false);
        } else if (bySizeCheckBox.isSelected()) {
            txtPartSize.setEnabled(true);
            spnrPartsNumber.setEnabled(false);
            cryptRow.setStatus(true);
            compressRow.getCompressCheckBox().setEnabled(true);
            defaultRow.getDefaultChkBox().setEnabled(true);
        }
    }
}
