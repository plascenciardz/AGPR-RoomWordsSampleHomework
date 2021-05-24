package com.example.roomwordssamplehomework;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DocumentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Document document);

    @Query("UPDATE document_table SET document = :document WHERE document = document")
    void updateDocument(String document);

    @Query("DELETE FROM document_table")
    void deleteAll();

    @Query("SELECT * from document_table")
    LiveData<List<Document>> getAllDocuments();

}
