package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlussaOikein() {
        assertTrue(kortti.saldo() == 10);
    }
    
    @Test
    public void lataaminenToimii() {
        kortti.lataaRahaa(25);
        assertTrue(kortti.saldo() == 35);
    }
    
    @Test
    public void saldoVaheneeOikein() {
        kortti.otaRahaa(2);
        assertTrue(kortti.saldo() == 8);
    }
    
    @Test
    public void saldoEiMuutu() {
        kortti.otaRahaa(12);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahatRiittaaTrue() {
        assertEquals(true, kortti.otaRahaa(10));
    }
    
    @Test
    public void rahatEiRiitaFalse() {
        assertEquals(false, kortti.otaRahaa(11));
    }
    
    
}
