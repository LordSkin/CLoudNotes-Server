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
    private Note weatherNote;

    public void setWeatherNote(String note)
    {
        this.weatherNote = Note.addWeather(note);
    }

    public List<Note> getNotes()
    {
        List<Note> result = noteDao.getNotes();
        result.add(0, weatherNote);
        return result;
    }

    public Note getNote(int id)
    {
        if(id==Note.WEATHER_NOTE_ID)
        {
            return weatherNote;
        }
        else
        {
            return noteDao.getNote(id);
        }

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

    public void deelteAll()
    {
        noteDao.deleteAll();
    }
}
