package gui.panels;

import gui.SplitFrame;
import gui.error.ErrorPopupMessage;
import gui.rows.*;
import logic.DivideTask;
import logic.Task;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;


/**
 * {@link DivideAndMergePanel} che da la possibilità di aggiungere un task di divisione
 */
public class DivideSettingPanel extends DivideAndMergePanel {

    /**
     * La {@link Row} che permette di scegliere i file da dividere
     */
    private ChooseFileRow chooseFileRow;

    /**
     * La {@link Row} che permette di impostare in quante parti dividere il file o le dimensioni delle singole parti
     */
    private PartsSettingsRow partsSettingsRow;

    /**
     * La {@link Row} che permette di scegliere se criptare tramite password i file generati dalla divisione
     */
    private CryptRow cryptRow;

    /**
     * La {@link Row} che permette di scegliere se comprimere i file generati dalla divisione
     */
    private CompressRow compressRow;

    /**
     * La {@link Row} che permette di scegliere la modalità di default della divisione
     */
    private DefaultRow defaultRow;

    /**
     * La {@link Row} che permette di aggiungere un nuovo {@link DivideTask} in coda
     */
    private AddDivideTaskRow addTaskRow;

    /**
     * Riferimento alla {@link QueueTablePanel} che permette di aggiornare la tabella
     * all'aggiunta di un {@link DivideTask}
     */
    private QueueTablePanel tablePanel;

    /**
     * Costruisce il DivideSettingPanel
     *
     * @param divideListener L'{@link ActionListener} collegato a {@link AddDivideTaskRow}
     * @param tablePanel     Riferimento alla {@link JTable} che contiene la tabella dei {@link Task}
     */
    public DivideSettingPanel(ActionListener divideListener, QueueTablePanel tablePanel) {
        super();

        this.chooseFileRow = new ChooseFileRow();
        this.cryptRow = new CryptRow();
        this.compressRow = new CompressRow();
        this.defaultRow = new DefaultRow();
        this.partsSettingsRow = new PartsSettingsRow(compressRow, cryptRow, defaultRow);
        this.addTaskRow = new AddDivideTaskRow(divideListener);
        this.tablePanel = tablePanel;


        this.add(Box.createVerticalStrut(8));
        this.add(chooseFileRow);

        this.add(Box.createVerticalStrut(8));
        this.add(partsSettingsRow);

        this.add(Box.createVerticalStrut(8));
        this.add(defaultRow);

        this.add(Box.createVerticalStrut(8));
        this.add(cryptRow);

        this.add(Box.createVerticalStrut(8));
        this.add(compressRow);

        this.add(Box.createVerticalStrut(8));
        this.add(addTaskRow);

        this.add(Box.createVerticalStrut(8));

        ButtonGroup group = new ButtonGroup();
        group.add(defaultRow.getDefaultChkBox());
        group.add(cryptRow.getCryptCheckBox());
        group.add(compressRow.getCompressCheckBox());
    }

    /**
     * Crea un nuovo {@link DivideTask} per ogni file selezionato e lo aggiunge in coda, dopodichè
     * aggiorna la tabella e pulisce la lista dei file selezionati in precedenza
     * <p>
     * Nel caso fosse già stato eseguita una serie di task pulisce la coda precedente
     *
     * @param tasksQueue la coda dei task da eseguire
     */
    public void AddDivideTask(ArrayList<Task> tasksQueue) {
        boolean allControl = true;
        if (partsSettingsRow.getParts() * partsSettingsRow.getFileSize() > 0) {
            ErrorPopupMessage.show("Inserire una dimensione valida", "Formato errato");
            allControl = false;
        }
        if (cryptRow.getCryptSelection() && cryptRow.getKeyword().equals("")) {
            ErrorPopupMessage.show("Inserire una password valida", "Password non inserita");
            allControl = false;
        }
        if (allControl) {
            if (SplitFrame.performed) {
                tasksQueue.clear();
                SplitFrame.performed = false;
            }
            for (File f : chooseFileRow.getFilesSelected()) {
                DivideTask divideTask = new DivideTask(f, partsSettingsRow.getParts(), partsSettingsRow.getFileSize(),
                        cryptRow.getCryptSelection(), compressRow.getCompressSelection(), cryptRow.getKeyword(),
                        tablePanel);

                tasksQueue.add(divideTask);
            }
            tablePanel.updateTableModel();
            chooseFileRow.cleanSelectedFiles();
        }

    }

    /**
     * Aggiorna la lista dei file selezionati all'interno della {@link JTextField} presente in
     * {@link ChooseFileRow}
     *
     * @param s Il nome del file che andrà nel campo di testo
     */
    public void setFileTextBox(String s) {
        chooseFileRow.updateFileSelected(s);
    }
}
