package Tests.DerbyDB;

import com.Main.Dao.DerbyDAO.DataBaseConnector;
import com.Main.Dao.DerbyDAO.DerbyNoteDao;
import com.Main.Entity.Note;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;

public class DerbyNoteDaoTests {

    DerbyNoteDao dnd;
    public DerbyNoteDaoTests()
    {

        dnd = new DerbyNoteDao("test1");

    }



    @Test
    public void addNoteTest()
    {

        String text = "testNote1";
        int id = dnd.addNote(text);
        String result = dnd.getNote(id).getNote();
        Note d = dnd.getNote(id);
        Assert.assertEquals(text,result);
    }

    @Test
    public void showNotes()
    {
        addNoteTest();
        addNoteTest();
        addNoteTest();
        addNoteTest();
        addNoteTest();
        LinkedList<Note> notes = (LinkedList)dnd.getNotes();
        for(Note n : notes)
        {
            System.out.println(n);
        }
        Assert.assertTrue(true);

    }

    @Test
    public void showNotes2()
    {
        System.out.println("test2:");
        dnd = new DerbyNoteDao("test2");
        LinkedList<Note> notes = (LinkedList)dnd.getNotes();
        for(Note n : notes)
        {
            System.out.println(n);
        }
        Assert.assertTrue(true);

    }


    @Test
    public void showNote()
    {
        Assert.assertNull(dnd.getNote(0));
    }

    @Test
    public void updateTest()
    {
        try
        {
            dnd = new DerbyNoteDao("test3");
            int id =dnd.addNote("aaa");
            dnd.updateNote(id, "bbb");
            Assert.assertEquals(dnd.getNote(id).getNote(), "bbb");
        }
        catch(Exception e)
        {
            Assert.fail();
        }
    }

    @Test
    public void updateTest2()
    {
        try
        {
            dnd = new DerbyNoteDao("test4");
            int id =dnd.addNote("aaa");
            Date updated = dnd.getNote(id).getUpdated();
            dnd.updateNote(id, "bbb");
            Date d2 = dnd.getNote(id).getUpdated();
            Assert.assertTrue(updated.before(dnd.getNote(id).getUpdated()));
        }
        catch(Exception e)
        {
            Assert.fail();
        }
    }
}
