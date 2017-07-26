package com.Main.Dao.DerbyDAO;

import com.Main.Dao.NoteDao;
import com.Main.Entity.Note;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Repository
public class DerbyNoteDao implements NoteDao {

    private Statement statement;

    public DerbyNoteDao()
    {

        try
        {
            DataBaseConnector.connectToDataBase("dataBase");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            statement = DataBaseConnector.getStatement();
        }
    }

    public DerbyNoteDao(String dataBaseName) {
        try
        {
            DataBaseConnector.connectToDataBase(dataBaseName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            statement = DataBaseConnector.getStatement();
        }

    }

    @Override
    public List<Note> getNotes() {
        try
        {
            ResultSet re = statement.executeQuery("SELECT * FROM Notes");
            LinkedList<Note> result = new LinkedList<Note>();
            while(re.next())
            {
                result.add(new Note(new Date(re.getLong("created")), new Date(re.getLong("updated")), re.getString("note")));
            }
            return result;
        }
        catch(java.sql.SQLException e)
        {
            e.printStackTrace();
            return new LinkedList<Note>();
        }
    }

    @Override
    public Note getNote(int id) {
        try
        {
            ResultSet re = statement.executeQuery("SELECT * FROM Notes WHERE id = "+id);
            re.next();
            return new Note(new Date(re.getLong("created")), new Date(re.getLong("updated")), re.getString("note"));
        }
        catch(java.sql.SQLException e)
        {
            //e.printStackTrace();
            return null;
        }
    }

    @Override
    public int addNote(String note) {
        try
        {
            ResultSet re = statement.executeQuery("SELECT id FROM Notes");
            int counter = size(re)+1;
            Note n = new Note(note);
            String s = "insert into Notes values("+counter+",'"+n.getNote()+"','"+n.getCreated().getTime()+"','"+n.getUpdated().getTime()+"')";
            statement.executeUpdate("insert into Notes values("+counter+",'"+n.getNote()+"',"+n.getCreated().getTime()+","+n.getUpdated().getTime()+")");
            return counter;
        }
        catch(java.sql.SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    private int size(ResultSet rs)
    {
        try
        {
            rs.beforeFirst();
            rs.last();
            return  rs.getRow();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }

    }


    @Override
    public boolean removeNote(int id) {
        try
        {
            int result =statement.executeUpdate("Delete from Note Where id ="+id);
            if(result>0) return true;
            else return false;

        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateNote(int id, String noteText) {
        try
        {
            ResultSet re = statement.executeQuery("SELECT * FROM Notes WHERE id = "+id);
            re.first();
            Note note = new Note(new Date(re.getLong("created")), Date.from(Instant.now()), noteText);
            int result =statement.executeUpdate("UPDATE Notes SET note = '"+note.getNote()+"', updated ="+note.getUpdated().getTime()+" WHERE id = "+id);
            if (result>0) return true;
            else return false;
        }
        catch(java.sql.SQLException e)
        {
            System.out.println("Proba zaktualizowania nieistniejacej notatki!");
            return false;
        }
    }

    @Override
    public void deleteAll() {
        try
        {
            ResultSet re = statement.executeQuery("Drop Table Notes");

        }
        catch(java.sql.SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int size() {
        try
        {
            ResultSet re = statement.executeQuery("SELECT id FROM Notes");
            return size(re);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }

    }
}
