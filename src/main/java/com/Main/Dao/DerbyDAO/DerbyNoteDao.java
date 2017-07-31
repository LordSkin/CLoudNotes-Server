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
        ResultSet re=null;
        try
        {
            re = statement.executeQuery("SELECT * FROM Notes");
            LinkedList<Note> result = new LinkedList<Note>();
            re.beforeFirst();
            while(re.next())
            {
                result.add(new Note(new Date(re.getLong("created")), new Date(re.getLong("updated")), re.getString("note"), re.getInt("id")));
            }
            return result;
        }
        catch(java.sql.SQLException e)
        {
            e.printStackTrace();
            return new LinkedList<Note>();
        }
        finally
        {
            try
            {
                if(re!=null)re.close();
            }
            catch (java.sql.SQLException e)
            {
            }

        }
    }

    @Override
    public Note getNote(int id) {
        ResultSet re = null;
        try
        {
            re = statement.executeQuery("SELECT * FROM Notes WHERE id = "+id);
            re.first();
            return new Note(new Date(re.getLong("created")), new Date(re.getLong("updated")), re.getString("note"), re.getInt("id"));
        }
        catch(java.sql.SQLException e)
        {
            //e.printStackTrace();
            return null;
        }
        finally
        {
            try
            {
                if(re!=null)re.close();
            }
            catch (java.sql.SQLException e)
            {
            }

        }
    }

    @Override
    public int addNote(String note) {
        ResultSet re = null;
        try
        {
            re = statement.executeQuery("SELECT * FROM Notes");
            int counter;
            if(re.last())
            {
                counter = re.getInt("id")+1;
            }
            else
            {
                counter = 0;
            }
            re.close();
            Note n = new Note(note, counter);
            String s = "insert into Notes values("+counter+",'"+n.getNote()+"','"+n.getCreated().getTime()+"','"+n.getUpdated().getTime()+"')";
            statement.executeUpdate("insert into Notes values("+counter+",'"+n.getNote()+"',"+n.getCreated().getTime()+","+n.getUpdated().getTime()+")");
            return counter;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return -1;
        }
        finally
        {
            try
            {
                if(re!=null)re.close();
            }
            catch (java.sql.SQLException e)
            {
            }

        }
    }

    private int size(ResultSet rs) throws Exception
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
            throw new Exception("cant get size of notes list");
        }

    }


    @Override
    public boolean removeNote(int id) {
        try
        {
            int result =statement.executeUpdate("Delete from Notes Where id ="+id);
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
        ResultSet re = null;
        try
        {
            re = statement.executeQuery("SELECT * FROM Notes WHERE id = "+id);
            re.first();
            Note note = new Note(new Date(re.getLong("created")), Date.from(Instant.now()), noteText, id);
            int result =statement.executeUpdate("UPDATE Notes SET note = '"+note.getNote()+"', updated ="+note.getUpdated().getTime()+" WHERE id = "+id);
            if (result>0) return true;
            else return false;
        }
        catch(java.sql.SQLException e)
        {
            System.out.println("Proba zaktualizowania nieistniejacej notatki!");
            return false;
        }
        finally
        {
            try
            {
                if(re!=null)re.close();
            }
            catch (java.sql.SQLException e)
            {
            }

        }
    }

    @Override
    public void deleteAll() {
        try
        {
            statement.executeUpdate("Drop Table Notes");
            statement.executeUpdate("Create table Notes (id int primary key, note varchar(500), created bigint, updated bigint)");
        }
        catch(java.sql.SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int size() {
        ResultSet re = null;
        try
        {
            re = statement.executeQuery("SELECT id FROM Notes");
            return size(re);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
        finally
        {
            try
            {
                if(re!=null)re.close();
            }
            catch (java.sql.SQLException e)
            {
            }

        }

    }
}
