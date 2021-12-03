package com.ss.xmltest;


public class DemoB {
        public String note;

    public String subModel;

    public String options;

    public String cc;


    public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getSubModel() {
            return subModel;
        }

    public void setSubModel(String subModel) {
            this.subModel = subModel;
        }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    @Override
    public String toString() {
        return "DemoB{" +
                "note='" + note + '\'' +
                ", subModel='" + subModel + '\'' +
                ", options='" + options + '\'' +
                ", cc='" + cc + '\'' +
                '}';
    }
}
