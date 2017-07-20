package Tests;

import com.Main.Main;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class NoteControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getNotesTest()
    {
        try
        {
            mvc.perform(MockMvcRequestBuilders.get("/notes").accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }
        catch (Exception e)
        {
            Assert.fail();
        }
    }

    @Test
    public void getNoteTest()
    {
        try
        {
            mvc.perform(MockMvcRequestBuilders.get("/notes/1").accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }
        catch (Exception e)
        {
            Assert.fail();
        }
    }

    @Test
    public void getNoteTest2()
    {
        try
        {
            mvc.perform(MockMvcRequestBuilders.get("/notes/-1").accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
        }
        catch (Exception e)
        {
            Assert.fail();
        }
    }

    @Test
    public void getNoteTest3()
    {
        try
        {
            mvc.perform(MockMvcRequestBuilders.get("/notes/").accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }
        catch (Exception e)
        {
            Assert.fail();
        }
    }

    @Test
    public void removeTest()
    {
        try
        {
            mvc.perform(MockMvcRequestBuilders.delete("/notes/").accept(MediaType.APPLICATION_JSON));
        }
        catch (Exception e)
        {
            Assert.fail();
        }
    }

    @Test
    public void updateTest()
    {
        try
        {
            mvc.perform(MockMvcRequestBuilders.put("/notes/1").accept(MediaType.APPLICATION_JSON).content("aaaaaaaaaaaaaaaaaaaaaaa"));
        }
        catch (Exception e)
        {
            Assert.fail();
        }
    }

    @Test
    public void addTest()
    {
        try
        {
            mvc.perform(MockMvcRequestBuilders.post("/notes/").accept(MediaType.APPLICATION_JSON).content("asdasdasd"));
        }
        catch (Exception e)
        {
            Assert.fail();
        }
    }

}
