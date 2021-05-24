package com.example.roomwordssamplehomework;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DocumentViewModel extends AndroidViewModel {

    private DocumentRepository mRepository;

    private LiveData<List<Document>> mAllDocuments;

    public DocumentViewModel (Application application) {
        super(application);
        mRepository = new DocumentRepository(application);
        mAllDocuments = mRepository.getAllDocuments();
    }

    LiveData<List<Document>> getAllDocuments() { return mAllDocuments; }

    public void updateDocument (Document document) { mRepository.updateDocument(document); }

    public void insert(Document document) { mRepository.insert(document); }


}
