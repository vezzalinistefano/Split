package logic;

import java.io.File;

public class DivideTask extends Task {
    private File file;

    private int parts;

    private int dimension;

    private boolean crypt;

    private boolean compress;

    private String keyword;

    public DivideTask(File file, int parts, int dimension, boolean crypt, boolean compress, String keyword) {
        this.file = file;
        this.parts = parts;
        this.dimension = dimension;
        this.crypt = crypt;
        this.compress = compress;
        this.keyword = keyword;
    }

    public String getFileName() {
        return file.getName();
    }

    public int getParts() {
        return parts;
    }

    public int getDimension() {
        return dimension;
    }

    public boolean isCrypt() {
        return crypt;
    }

    public boolean isCompress() {
        return compress;
    }

    public String getKeyword() {
        return keyword;
    }
}
