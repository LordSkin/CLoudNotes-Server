package com.Main.Dao;

import com.Main.Entity.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Getting access to database, basic operations on Notes
 */
public interface NoteDao {

    List<Note> getNotes();

    Note getNote(int id);

    int addNote(String note);

    boolean removeNote(int id);

    boolean updateNote(int id, String noteText);

    void deleteAll();

    int size();
}
