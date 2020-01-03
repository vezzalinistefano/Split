package gui.panels;

import gui.SplitFrame;
import gui.rows.AddJobRow;
import gui.rows.ChooseFileRow;

import javax.swing.*;

public class MergeSettingPanel extends DivideAndMergePanel {

    private ChooseFileRow chooseFileRow;
    private AddJobRow addJobRow;

    public MergeSettingPanel() {
        super(SplitFrame.MERGE_PANEL);

        chooseFileRow = new ChooseFileRow();
        addJobRow = new AddJobRow();

        this.add(Box.createVerticalStrut(8));
        this.add(chooseFileRow);

        this.add(Box.createVerticalStrut(8));
        this.add(addJobRow);


    }
}
