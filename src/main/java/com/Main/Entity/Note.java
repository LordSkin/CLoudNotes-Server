package com.Main.Entity;

import java.time.Instant;
import java.util.Date;

/**
 * Basic entity representing note
 */
public class Note {

    private Date created;
    private Date updated;
    private String note;

    public Note(Date created, Date updated, String note) {
        this.created = created;
        this.updated = updated;
        this.note = note;
    }

    public Note() {
        created = Date.from(Instant.now());
        updated = Date.from(Instant.now());
    }

    public Note(String note) {
        this.note = note;
        created = Date.from(Instant.now());
        updated = Date.from(Instant.now());
    }

    public String getNote() {
        return note;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void update(String newNote) {
        this.note = note;
        updated = Date.from(Instant.now());
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Note))
        {
            return false;
        }
        else
        {
            Note n = (Note)obj;
            return(this.created.equals(n.created)&&this.updated.equals(n.updated)&&this.note.equals(n.note));
        }
    }

    @Override
    public String toString() {
        return "Utworzona:"+this.created+", edytowana:"+updated+", tresc:"+note;
    }
}
