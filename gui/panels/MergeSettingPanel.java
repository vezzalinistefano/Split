package gui.panels;

import gui.SplitFrame;
import gui.rows.AddTaskRow;
import gui.rows.ChooseFileRow;

import javax.swing.*;

public class MergeSettingPanel extends DivideAndMergePanel {

    private ChooseFileRow chooseFileRow;
    private AddTaskRow addTaskRow;

    public MergeSettingPanel() {
        super(SplitFrame.MERGE_PANEL);

        chooseFileRow = new ChooseFileRow();
        addTaskRow = new AddTaskRow();

        this.add(Box.createVerticalStrut(8));
        this.add(chooseFileRow);

        this.add(Box.createVerticalStrut(8));
        this.add(addTaskRow);


    }
}
