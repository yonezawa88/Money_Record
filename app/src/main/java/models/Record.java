package com.example.moneyrecord.models;

import java.io.Serializable;


public class Record {
    private String date;
    private int amount;
    private String type;
    private String genre;
    private String note;

    public Record(String date, int amount, String type, String genre, String note) {
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.genre = genre;
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getGenre() {
        return genre;
    }

    public String getNote() {
        return note;
    }

    @Override
    public String toString() {
        return "Record{" +
                "date='" + date + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", genre='" + genre + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}




