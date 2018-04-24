

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

/**
 *
 * @author aleksiwillman
 */
public class tetrisTest {
    
    public tetrisTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {

    }
    
    @After
    public void tearDown() {
    }
    
    

    @Test
    public void perusJututToimii() {
        Tetris tetris = new Tetris();
        
        assertTrue(tetris.getStatusBar() != null);
    }
    
//    @Test
//    public void painaus() {
//        
//        
//        
//    }

    // @Test
    // public void hello() {}
}
