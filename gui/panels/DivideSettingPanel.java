package gui.panels;

import gui.SplitFrame;
import gui.rows.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class DivideSettingPanel extends DivideAndMergePanel {

    private ChooseFileRow chooseFileRow;
    private PartsSettingsRow partsSettingsRow;
    private CryptRow cryptRow;
    private CompressRow compressRow;
    private AddDivideJobRow addDivideJobRow;

    public DivideSettingPanel() {
        super(SplitFrame.DIVIDE_PANEL);

        chooseFileRow = new ChooseFileRow();
        partsSettingsRow = new PartsSettingsRow();
        cryptRow = new CryptRow();
        compressRow = new CompressRow();
        addDivideJobRow = new AddDivideJobRow();

        this.add(Box.createVerticalStrut(8));
        this.add(chooseFileRow);

        this.add(Box.createVerticalStrut(8));
        this.add(partsSettingsRow);

        this.add(Box.createVerticalStrut(8));
        this.add(cryptRow);

        this.add(Box.createVerticalStrut(8));
        this.add(compressRow);

        this.add(Box.createVerticalStrut(8));
        this.add(addDivideJobRow);
    }
}
