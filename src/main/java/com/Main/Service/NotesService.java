package com.Main.Service;

import com.Main.Dao.NoteDao;
import com.Main.Entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service geting acces to Notes dao
 */
@Service
public class NotesService {
    @Autowired
    private NoteDao noteDao;

    public List<Note> getNotes()
    {
        return noteDao.getNotes();
    }

    public Note getNote(int id)
    {
        return noteDao.getNote(id);
    }

    public int addNote(String note)
    {
        return noteDao.addNote(note);
    }

    public boolean deleteNote(int id)
    {
        return noteDao.removeNote(id);
    }

    public boolean updateNote(int id, String text)
    {
        return noteDao.updateNote(id, text);
    }
}
