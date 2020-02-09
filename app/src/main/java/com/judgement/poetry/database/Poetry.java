package com.judgement.poetry.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity(tableName = "poetry")
public class Poetry {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "poetryName")
    private String poetryName;
    @ColumnInfo(name = "poetName")
    private String poetName;
    @ColumnInfo(name = "dynasty")
    private String dynasty;
    @ColumnInfo(name = "content")
    private String content;

    public Poetry(String poetryName, String poetName, String dynasty, String content) {
        this.poetryName = poetryName;
        this.poetName = poetName;
        this.dynasty = dynasty;
        this.content = content;
    }

    public ArrayList<Poetry> getKeySentence(String key) {
        ArrayList<Poetry> poetryArrayList = new ArrayList<>();
        String rule = "[^。？！；!?]*?"+key+".*?[。？！；!?]";
        Pattern pattern = Pattern.compile(rule);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            poetryArrayList.add(new Poetry(poetryName, poetName, dynasty, matcher.group()));
        }
        return poetryArrayList;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(poetryName).append("[").append(dynasty).append("]").append(poetName);
        return stringBuilder.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoetryName() {
        return poetryName;
    }

    public void setPoetryName(String poetryName) {
        this.poetryName = poetryName;
    }

    public String getPoetName() {
        return poetName;
    }

    public void setPoetName(String poetName) {
        this.poetName = poetName;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
