package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    
    @Test
    public void eiSaaOttaaLiikaa() {
        varasto.lisaaVarastoon(10);
        varasto.otaVarastosta(11);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiSaaLaittaaLiikaa() {
        varasto.lisaaVarastoon(11);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiKaaduJosOtetaanNegatiivinen(){
        varasto.otaVarastosta(-1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiKaaduJosLaitetaanNegatiivinen(){
        varasto.lisaaVarastoon(-1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriEiSalliNegatiivistaTilavuutta(){
        Varasto toinenVarasto = new Varasto(-10);
        assertEquals(0, toinenVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void alkuSaldoKonstruktoriEiSalliNegatiivistaTilavuutta(){
        Varasto toinenVarasto = new Varasto(-10, -10);
        assertEquals(0, toinenVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void alkuSaldoKonstruktoriEiSalliNegatiivistaAlkuSaldoa(){
        Varasto toinenVarasto = new Varasto(10, -10);
        assertEquals(0, toinenVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void alkuSaldoKonstruktoriLisaaOikeinAlkuSaldoa(){
        Varasto toinenVarasto = new Varasto(10, 8);
        assertEquals(8, toinenVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void alkuSaldoKonstruktoriLisaaOikeinAlkuSaldoaKunAlkuSaldoaOnLiikaa(){
        Varasto toinenVarasto = new Varasto(10, 11);
        assertEquals(10, toinenVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonToStringToimii(){
        String varastoString = varasto.toString();
        assertTrue(varastoString.isEmpty());
    }

}