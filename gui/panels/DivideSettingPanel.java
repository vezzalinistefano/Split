package gui.panels;

import gui.SplitFrame;
import gui.queueTable.QueueTableModel;
import gui.queueTable.QueueTablePanel;
import gui.rows.*;
import logic.DivideTask;
import logic.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Classe che implementa il pannello contenente i componenti per impostare
 * la divisione di uno o più file
 */
public class DivideSettingPanel extends DivideAndMergePanel {

    /**
     * Pannello contenente i componenti per la scelta dei/del file
     * */
    private ChooseFileRow chooseFileRow;
    /**
     * Pannello contenente i componenti per la scelta delle dimensioni
     * delle parti in cui verrà diviso il file
     */
    private PartsSettingsRow partsSettingsRow;
    /** Pannello contenente i componenti che permettono all'utente di scegliere se
     *  criptare o meno i/il file e di impostare una parola chiave
     */
    private CryptRow cryptRow;
    /**
     * Pannello contenente i componenti che permettono all'utente di scegliere
     * se comprimere o meno i/il file
     */
    private CompressRow compressRow;

    private DefaultRow defaultRow;
    /**
     * Pannello contenente il bottone che aggiunge il bottone alla coda dei job da eseguire
     */
    private AddDivideTaskRow addTaskRow;

    private QueueTablePanel tablePanel;

    private ButtonGroup group;
    /**
     * Aggiunge i vari componenti al pannello
     */
    public DivideSettingPanel(ActionListener divideListener, QueueTablePanel tablePanel) {
        super(SplitFrame.DIVIDE_PANEL);

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

        group = new ButtonGroup();
        group.add(defaultRow.getDefaultChkBox());
        group.add(cryptRow.getCryptCheckBox());
        group.add(compressRow.getCompressCheckBox());
    }

    public void AddDivideTask(ArrayList<Task> tasksQueue) {
        for(File f : chooseFileRow.getFilesSelected()) {
            DivideTask divideTask = null;

            divideTask = new DivideTask(f, partsSettingsRow.getParts(), partsSettingsRow.getFileSize(),
                    cryptRow.getCryptSelection(), compressRow.getCompressSelection(), cryptRow.getKeyword());
            tasksQueue.add(divideTask);
        }
        tablePanel.updateTableModel();
        chooseFileRow.cleanSelectedFiles();
    }

}
