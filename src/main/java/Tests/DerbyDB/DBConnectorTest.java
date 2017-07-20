package Tests.DerbyDB;

import com.Main.Dao.DerbyDAO.DataBaseConnector;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnectorTest {

    @Test
    public void creatingDB()
    {
        try
        {
            DataBaseConnector.connectToDataBase("testDB1");
            Assert.assertNotNull(DataBaseConnector.getConnection());
            Assert.assertNotNull(DataBaseConnector.getStatement());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void connectingtoDExistingDB()
    {
        creatingDB();
    }

    @Test
    public void testingEmnptyDB()
    {
        try
        {
            DataBaseConnector.connectToDataBase("testDB2");
            Statement s = DataBaseConnector.getStatement();
        }
        catch(java.sql.SQLSyntaxErrorException e)
        {
            Assert.assertTrue(true);
        }
        catch (java.sql.SQLException e)
        {
            e.printStackTrace();
            Assert.fail();
        }


    }

    @Test
    public void testingAccesTorecords()
    {
        try
        {
            DataBaseConnector.connectToDataBase("testDB2");
            Statement s = DataBaseConnector.getStatement();
            s.executeUpdate("Drop table testTable");
            s.executeUpdate("Create table testTable (id int primary key, name varchar(30))");
            s.executeUpdate("insert into testTable values (113,'tom')");
            s.executeUpdate("insert into testTable values (15,'to234m')");
            ResultSet rs = s.executeQuery("SELECT * FROM testTable");
            Assert.assertTrue(rs.next()&&rs.next());
        }
        catch (java.sql.SQLException e)
        {
            e.printStackTrace();
            Assert.fail();
        }


    }

    @Test
    public void deleteRecordstest()
    {
        try
        {
            DataBaseConnector.connectToDataBase("testDB2");
            Statement s = DataBaseConnector.getStatement();
            s.executeUpdate("Delete from testTable");
            ResultSet rs = s.executeQuery("SELECT * FROM testTable");
            Assert.assertFalse(rs.next());
        }
        catch (java.sql.SQLException e)
        {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
