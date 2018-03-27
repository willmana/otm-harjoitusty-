
package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author aleksiwillman
 */
public class KassapaateTest {
    
    Kassapaate kassapaate;
    Maksukortti maksukortti;
    
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        maksukortti = new Maksukortti(1000);
    }
    


    @Test
    public void oikeaRahaAlussa() {
        assertTrue(kassapaate.kassassaRahaa() == 100000);
    }
    
    @Test
    public void lounaatAlussa() {
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 0);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == 0);
    }
    
    @Test
    public void syoEdullisestiToimii() {        
        int apu = kassapaate.syoEdullisesti(240);
        assertEquals(0, apu);
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 1);
        apu = kassapaate.syoEdullisesti(230);
        assertEquals(230, apu);
        
    }
    
    @Test
    public void syoMaukkaastiToimii() {
        int apu = kassapaate.syoMaukkaasti(400);
        assertEquals(0, apu);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == 1);
        apu = kassapaate.syoMaukkaasti(230);
        assertEquals(230, apu);

    }
    
    
    
    
}
