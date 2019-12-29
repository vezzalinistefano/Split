package gui.panels;

import gui.rows.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class DivideSettingPanel extends DivideAndMergePanel {
    private Border border;

    private ChooseFileRow chooseFileRow;
    private PartsSettingsRow partsSettingsRow;
    private CryptRow cryptRow;
    private CompressRow compressRow;
    private AddDivideJobRow addDivideJobRow;

    public DivideSettingPanel() {
        super("Dividi");

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

    public String getCurrentJob() {
        TitledBorder b = (TitledBorder) this.getBorder();
        return b.getTitle();
    }
}
