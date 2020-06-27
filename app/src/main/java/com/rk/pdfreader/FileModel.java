package com.rk.pdfreader;

import java.io.File;

public class FileModel {

    String fileName;
    File file;

    public FileModel() {
    }

    public FileModel(String fileName, File file) {
        this.fileName = fileName;
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
