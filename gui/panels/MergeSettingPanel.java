package gui.panels;

import gui.SplitFrame;
import gui.rows.AddMergeTaskRow;
import gui.rows.ChooseFileRow;
import gui.rows.KeywordRow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MergeSettingPanel extends DivideAndMergePanel implements ActionListener {

    private ChooseFileRow chooseFileRow;
    private KeywordRow keywordRow;
    private AddMergeTaskRow addMergeTaskRow;

    public MergeSettingPanel() {
        super(SplitFrame.MERGE_PANEL);

        chooseFileRow = new ChooseFileRow();
        addMergeTaskRow = new AddMergeTaskRow(this);
        keywordRow = new KeywordRow();

        this.add(Box.createVerticalStrut(8));
        this.add(chooseFileRow);

        this.add(Box.createVerticalStrut(8));
        this.add(keywordRow);

        this.add(Box.createVerticalStrut(8));
        this.add(addMergeTaskRow);


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
