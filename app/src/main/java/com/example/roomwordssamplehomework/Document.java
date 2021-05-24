package com.example.roomwordssamplehomework;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "document_table")
public class Document {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "document")
    private String mDocument;
    public Document(@NonNull String document) {this.mDocument = document;}
    public String getDocument(){return this.mDocument;}

}
