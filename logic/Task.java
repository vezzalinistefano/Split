package logic;

import java.io.File;

public class Task {

    private File file;
    private String keyword;
    private Boolean crypt;
    private Boolean compress;

    public Task(File file, Boolean crypt, Boolean compress) {
        this.file = file;
        this.crypt = crypt;
        this.compress = compress;
    }
}
