package com.example.roomwordssamplehomework;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DocumentRepository {

    private DocumentDao mDocumentDao;
    private LiveData<List<Document>> mAllDocuments;

    DocumentRepository(Application application) {
        DocumentRoomDatabase db = DocumentRoomDatabase.getDatabase(application);
        mDocumentDao = db.documentDao();
        mAllDocuments = mDocumentDao.getAllDocuments();
    }

    LiveData<List<Document>> getAllDocuments() {
        return mAllDocuments;
    }

    public void insert (Document document) {
        new insertAsyncTask(mDocumentDao).execute(document);
    }

    public void updateDocument (Document document) {
        new updateDocumentAsyncTask(mDocumentDao).execute(document);
    }

    private static class updateDocumentAsyncTask extends AsyncTask<Document, Void, Void> {

        private DocumentDao mAsyncTaskDao;

        updateDocumentAsyncTask(DocumentDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Document... params) {
            mAsyncTaskDao.updateDocument(params[0].getDocument());
            return null;
        }
    }

    private static class insertAsyncTask extends AsyncTask<Document, Void, Void> {

        private DocumentDao mAsyncTaskDao;

        insertAsyncTask(DocumentDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Document... params) {
            mAsyncTaskDao.updateDocument(params[0].getDocument());
            return null;
        }
    }

}
