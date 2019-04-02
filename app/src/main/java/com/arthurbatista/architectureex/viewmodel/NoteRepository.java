package com.arthurbatista.architectureex.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.arthurbatista.architectureex.model.Note;
import com.arthurbatista.architectureex.model.NoteDAO;
import com.arthurbatista.architectureex.model.NoteDataBase;

import java.util.List;

public class NoteRepository {

    private NoteDAO noteDAO;

    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDataBase noteDataBase = NoteDataBase.getInstance(application);
        noteDAO = noteDataBase.noteDAO();
        allNotes = noteDAO.getAllNotes();
    }

    public void insert(Note note){
        new InsertNoteAsyncTaks(noteDAO).execute(note);
    }

    public void update(Note note){
        new UpdateNoteAsyncTaks(noteDAO).execute(note);
    }

    public void delete(Note note){
        new DeleteNoteAsyncTaks(noteDAO).execute(note);
    }

    public void deleteAllNotes(){
        new DeleteAllNotesAsyncTaks(noteDAO).execute();
    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }

    public static class InsertNoteAsyncTaks extends AsyncTask<Note, Void, Void>{

        private NoteDAO noteDAO;

        public InsertNoteAsyncTaks(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.insert(notes[0]);
            return null;
        }
    }

    public static class DeleteNoteAsyncTaks extends AsyncTask<Note, Void, Void>{

        private NoteDAO noteDAO;

        public DeleteNoteAsyncTaks(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.delete(notes[0]);
            return null;
        }
    }

    public static class UpdateNoteAsyncTaks extends AsyncTask<Note, Void, Void>{

        private NoteDAO noteDAO;

        public UpdateNoteAsyncTaks(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.update(notes[0]);
            return null;
        }
    }

    public static class DeleteAllNotesAsyncTaks extends AsyncTask<Void, Void, Void>{

        private NoteDAO noteDAO;

        public DeleteAllNotesAsyncTaks(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDAO.deleteAllNote();
            return null;
        }
    }
}
