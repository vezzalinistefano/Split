package gui.panels;

import gui.SplitFrame;
import gui.queueTable.QueueTablePanel;
import gui.rows.AddMergeTaskRow;
import gui.rows.ChooseFileRow;
import gui.rows.KeywordRow;
import logic.DivideTask;
import logic.MergeTask;
import logic.Task;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class MergeSettingPanel extends DivideAndMergePanel {

    private ChooseFileRow chooseFileRow;
    private KeywordRow keywordRow;
    private AddMergeTaskRow addMergeTaskRow;
    private QueueTablePanel tablePanel;

    public MergeSettingPanel(ActionListener mergeListener, QueueTablePanel tablePanel) {
        super(SplitFrame.MERGE_PANEL);

        chooseFileRow = new ChooseFileRow();
        addMergeTaskRow = new AddMergeTaskRow(mergeListener);
        keywordRow = new KeywordRow();
        this.tablePanel = tablePanel;

        this.add(Box.createVerticalStrut(8));
        this.add(chooseFileRow);

        this.add(Box.createVerticalStrut(8));
        this.add(keywordRow);

        this.add(Box.createVerticalStrut(8));
        this.add(addMergeTaskRow);


    }

    public void AddMergeTask(ArrayList<Task> tasksQueue) {
        if(SplitFrame.performed == true) {
            tasksQueue.clear();
            SplitFrame.performed = false;
        }
        for(File f:chooseFileRow.getFilesSelected()) {
            MergeTask mergeTask = new MergeTask(f, this.keywordRow.getKeyword(), tablePanel);

            tasksQueue.add(mergeTask);
        }
        tablePanel.updateTableModel();
        chooseFileRow.cleanSelectedFiles();
    }
}
