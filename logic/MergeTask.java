package logic;

import java.io.File;

public class MergeTask extends Task{
    private File[] files;

    private String keyword;

    public MergeTask(File[] files, String keyword) {
        super();

        this.files = files;
        this.keyword = keyword;
    }

    public void performTask() {

    }
}
