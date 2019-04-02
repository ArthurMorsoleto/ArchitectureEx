package com.arthurbatista.architectureex.model;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDataBase extends RoomDatabase {

    private static NoteDataBase instance;

    public abstract NoteDAO noteDAO();

    public static synchronized NoteDataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    NoteDataBase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            //new PopulateDBAsyncTask(instance).execute();
        }
    };

    public static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void>{

        private NoteDAO noteDAO;

        public PopulateDBAsyncTask(NoteDataBase db) {
            noteDAO = db.noteDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDAO.insert( new Note("Title 1", "Description 1",1));
            noteDAO.insert( new Note("Title 2", "Description 2",2));
            noteDAO.insert( new Note("Title 3", "Description 3",3));
            noteDAO.insert( new Note("Title 4", "Description 4",4));
            return null;
        }
    }



}
