package logic;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
                String filePartName = String.format("%03d-%s", partIdx++, fileName);

                File newFile = new File(file.getParent(), filePartName);
                try (FileOutputStream out = new FileOutputStream(newFile)) {
                    out.write(buffer, 0, bytesAmount);
                    out.close();
                    if(this.compress) {
                        compressFile(newFile.getPath());
                        newFile.delete();
                    }
                }
            }
        }
    }

    private void compressFile(String path) {
        File f = new File(path);
        String zipFileName = String.format(path + ".zip", f.getName());

        try {
            FileOutputStream fos = new FileOutputStream(zipFileName);
            ZipOutputStream zos = new ZipOutputStream(fos);

            zos.putNextEntry(new ZipEntry(f.getName()));

            byte[] zBuffer = new byte[(int)f.length()];
            FileInputStream fis = new FileInputStream(f);
            fis.read(zBuffer);
            fis.close();

            zos.write(zBuffer, 0, zBuffer.length);
            zos.closeEntry();
            zos.close();
        } catch (FileNotFoundException ex) {
            //TODO future log
        } catch (IOException ex) {
            //TODO future log
        }
    }
}
