package com.example.roomwordssamplehomework;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Document.class}, version = 1, exportSchema = false)
public abstract class DocumentRoomDatabase extends RoomDatabase {

    public abstract DocumentDao documentDao();
    private static DocumentRoomDatabase INSTANCE;

    static DocumentRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DocumentRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DocumentRoomDatabase.class, "document_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final DocumentDao mDao;
        String documents = "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate deleniti doloremque eius enim fugit ipsam, libero molestiae natus nihil, officia rerum similique velit voluptatibus. Ab dignissimos error nulla quod sed!";

        PopulateDbAsync(DocumentRoomDatabase db) {
            mDao = db.documentDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mDao.deleteAll();

            Document document = new Document(documents);
            mDao.insert(document);

            return null;
        }
    }

}
