package logic;

import java.io.*;

public class DivideTask extends Task {
    private File file;

    private int parts;

    private int sizeOfFiles;

    private boolean crypt;

    private boolean compress;

    private String keyword;

    public DivideTask(File file, int parts, int sizeOfFiles, boolean crypt, boolean compress, String keyword) {
        this.file = file;
        this.parts = parts;
        this.sizeOfFiles = sizeOfFiles;
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

    public int getSizeOfFiles() {
        return sizeOfFiles;
    }

    public String isCrypt() {
        return (this.crypt ? "Si" : "No");
    }

    public String isCompress() {
        return (this.compress ? "Si" : "No");
    }

    public String getKeyword() {
        return keyword;
    }

    public void fileSplit() throws IOException {
        String fileName = file.getName();

        byte[] buffer = new byte[sizeOfFiles * 1024 * 1024];

        int partIdx = 1;

        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            int bytesAmount = 0;
            while ((bytesAmount = bis.read(buffer)) > 0) {

                String filePartName = String.format("%s(%03d)", fileName, partIdx++);
                File newFile = new File(file.getParent(), filePartName);
                try (FileOutputStream out = new FileOutputStream(newFile)) {
                    out.write(buffer, 0, bytesAmount);
                }
            }
        }
    }
}
