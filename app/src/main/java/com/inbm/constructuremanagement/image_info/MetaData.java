package com.inbm.constructuremanagement.image_info;

import java.util.Date;


public class MetaData {

    public MetaData() { super(); }

    private String filePath;
    private double fileLat;
    private double fileLng;
    private Date fileDate;

    public MetaData(String filePath, double fileLat, double fileLng, Date fileDate) {
        this.filePath = filePath;
        this.fileLat = fileLat;
        this.fileLng = fileLng;
        this.fileDate = fileDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public double getFileLat() {
        return fileLat;
    }

    public void setFileLat(double fileLat) {
        this.fileLat = fileLat;
    }

    public double getFileLng() {
        return fileLng;
    }

    public void setFileLng(double fileLng) {
        this.fileLng = fileLng;
    }

    public Date getFileDate() {
        return fileDate;
    }

    public void setFileDate(Date fileDate) {
        this.fileDate = fileDate;
    }

}
