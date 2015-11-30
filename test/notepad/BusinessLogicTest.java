package notepad;

import notepad.BusinessLogic;
import notepad.Frame;
import java.io.File;
import java.net.URL;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juha
 */
public class BusinessLogicTest {
    
    Frame frame;
    
    public BusinessLogicTest() {      
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        frame = new Frame();
    }
    
    @After
    public void tearDown() {     
    }

    /**
     * Test of Save method, of class BusinessLogic.
     * NOTES: Is initializing and deleting file like this a good practice?
     */
    @Test
    public void testSave() {
        System.out.println("Save");
        BusinessLogic instance = new BusinessLogic(frame);
        File file = new File("writetestfile.txt");
        instance.Save(file);
        assertTrue(file.exists());
        file.delete();
    }

    /**
     * Test of New method, of class BusinessLogic.
     * NOTES: This is a really stupid test isn't it...?
     */
    @Test
    public void testNew() {
        System.out.println("New");
        BusinessLogic instance = new BusinessLogic(frame);
        frame.getInputJTextArea().setText("teststring");
        instance.New();
        assertEquals(frame.getInputJTextArea().getText(),"");
    }

    /**
     * Test of Read method, of class BusinessLogic.
     * NOTES: Is it a good practice to create files beforehand manually...?
     */
    @Test
    public void testRead() {
        System.out.println("Read");
        BusinessLogic instance = new BusinessLogic(frame);
        File file = new File("readtestfile.txt");
        String string = "this is a testfile for reading.";
        assertEquals(instance.Read(file), string);
    }
    
}
