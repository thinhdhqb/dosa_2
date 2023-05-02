package com.example.dosa.ui.Activity;

public class TraTu {
    private String id;
    private String tudien;
    private String UK;
    private String US;
    private String sampleEN;
    private  String sampleVN;

    public TraTu(String id, String tudien, String UK, String US, String sampleEN, String sampleVN) {
        this.id = id;
        this.tudien = tudien;
        this.UK = UK;
        this.US = US;
        this.sampleEN = sampleEN;
        this.sampleVN = sampleVN;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTudien() {
        return tudien;
    }

    public void setTudien(String tudien) {
        this.tudien = tudien;
    }

    public String getUK() {
        return UK;
    }

    public void setUK(String UK) {
        this.UK = UK;
    }

    public String getUS() {
        return US;
    }

    public void setUS(String US) {
        this.US = US;
    }

    public String getSampleEN() {
        return sampleEN;
    }

    public void setSampleEN(String sampleEN) {
        this.sampleEN = sampleEN;
    }

    public String getSampleVN() {
        return sampleVN;
    }

    public void setSampleVN(String sampleVN) {
        this.sampleVN = sampleVN;
    }
}
