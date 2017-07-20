package com.Main.Controller;

import com.Main.Entity.Note;
import com.Main.Service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller of notes, gets acces to service
 */
@RestController
@RequestMapping("/notes")
public class NotesController {

    @Autowired
    private NotesService notesService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Note> getAllNotes()
    {
        return notesService.getNotes();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Note> getNote(@PathVariable("id") int id)
    {
        Note n = notesService.getNote(id);

        if(n!=null)
        {
            return new ResponseEntity<Note>(n,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<Note>(n,HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Integer> addNote(@RequestBody String note)
    {
        ResponseEntity<Integer> result = new ResponseEntity<Integer>(notesService.addNote(note),HttpStatus.OK);
        return result;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteNote(@PathVariable("id") int id)
    {
       if(notesService.deleteNote(id))
       {
            return new ResponseEntity<Void>(HttpStatus.OK);
       }
       else
       {
           return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
       }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Void> updateNote(@PathVariable("id") int id, @RequestBody String text)
    {
        if(notesService.updateNote(id, text))
        {
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }
}
