package gui.panels;

import gui.SplitFrame;
import gui.rows.AddDivideTaskRow;
import gui.rows.AddMergeTaskRow;
import gui.rows.ChooseFileRow;
import gui.rows.KeywordRow;
import logic.MergeTask;
import logic.Task;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * {@link DivideSettingPanel} che permette di aggiungere un task di unione
 */
public class MergeSettingPanel extends DivideAndMergePanel {

    /**
     * La {@link gui.rows.Row} che permette di scegliere il primo file della serie di file da unire
     */
    private ChooseFileRow chooseFileRow;

    /**
     * La {@link gui.rows.Row} che permette di inserire la password per decriptare i file da unire
     */
    private KeywordRow keywordRow;

    /**
     * La {@link gui.rows.Row} che permette di aggiungere il task di unione
     */
    private AddMergeTaskRow addMergeTaskRow;

    /**
     * Riferimento alla {@link QueueTablePanel} che permette di aggiornare la tabella
     * all'aggiunta di un {@link MergeTask}
     */
    private QueueTablePanel tablePanel;

    /**
     * Costruisce il MergeSettingPanel.
     *
     * @param mergeListener L'{@link ActionListener} collegato a {@link AddDivideTaskRow}
     * @param tablePanel    Riferimento alla {@link JTable} che contiene la tabella dei {@link Task}
     */
    public MergeSettingPanel(ActionListener mergeListener, QueueTablePanel tablePanel) {
        super(SplitFrame.MERGE_PANEL);

        this.chooseFileRow = new ChooseFileRow();
        this.addMergeTaskRow = new AddMergeTaskRow(mergeListener);
        this.keywordRow = new KeywordRow();
        this.tablePanel = tablePanel;

        this.add(Box.createVerticalStrut(8));
        this.add(chooseFileRow);

        this.add(Box.createVerticalStrut(8));
        this.add(keywordRow);

        this.add(Box.createVerticalStrut(8));
        this.add(addMergeTaskRow);


    }

    /**
     * Crea un nuovo {@link MergeTask} per ogni file selezionato e lo aggiunge in coda, dopodichè
     * aggiorna la tabella e pulisce la lista dei file selezionati in precedenza
     * <p>
     * Nel caso fosse già stato eseguita una serie di task pulisce la coda precedente
     *
     * @param tasksQueue la coda dei task da eseguire
     */
    public void AddMergeTask(ArrayList<Task> tasksQueue) {
        if (SplitFrame.performed == true) {
            tasksQueue.clear();
            SplitFrame.performed = false;
        }
        for (File f : chooseFileRow.getFilesSelected()) {
            MergeTask mergeTask = new MergeTask(f, this.keywordRow.getKeyword(), tablePanel);

            tasksQueue.add(mergeTask);
        }
        tablePanel.updateTableModel();
        chooseFileRow.cleanSelectedFiles();
    }
}
