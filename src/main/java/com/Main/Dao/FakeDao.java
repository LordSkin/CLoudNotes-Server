package com.Main.Dao;

import com.Main.Entity.Note;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

public class FakeDao implements NoteDao {

    private ArrayList<Note> notes;

    public FakeDao() {
        notes = new ArrayList<Note>();
        notes.add(new Note("notatka 1"));
        notes.add(new Note("notatka 2"));
        notes.add(new Note("notatka 3"));
    }

    @Override
    public ArrayList<Note> getNotes()
    {
        return notes;
    }

    @Override
    public Note getNote(int id)
    {
        if(notes.size()>id&&id>=0)
        return notes.get(id);
        else return null;
    }

    @Override
    public int addNote(String note)
    {
         notes.add(new Note(note));
         return notes.size()-1;
    }

    @Override
    public boolean removeNote(int id)
    {
        if(notes.size()>id&&id>=0)
        {
            notes.remove(id);
            return true;
        }
        else
        {
            return false;
        }

    }

    @Override
    public boolean updateNote(int id, String noteText) {
        if(notes.size()>id&&id>=0)
        {
            notes.get(id).update(noteText);
            return true;
        }
        else
        {
            return false;
        }

    }

    @Override
    public void deleteAll() {
        notes.clear();
    }

    @Override
    public int size() {
       return notes.size();
    }
}
