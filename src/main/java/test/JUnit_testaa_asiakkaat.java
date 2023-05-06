package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import model.Asiakas;
import model.dao.Dao;

@TestMethodOrder(OrderAnnotation.class)
class JUnit_testaa_asiakkaat {

	@Test
	@Order(1) 
	public void testPoistaKaikkiAsiakkaat() {
		Dao dao = new Dao();
		dao.removeAllItems("Nimda");
		ArrayList<Asiakas> asiakkaat = dao.getAllItems();
		assertEquals(0, asiakkaat.size());		
	}
	
	@Test
	@Order(2) 
	public void testLisaaAsiakkaat() {
		Dao dao = new Dao();
		Asiakas asiakas_1 = new Asiakas(0,"Sami", "Koskinen", "040-5173344", "s@koskinen.com");
		Asiakas asiakas_2 = new Asiakas(0, "Jaana", "Nieminen", "040-2233554", "jaana@nieminen.com");
		Asiakas asiakas_3 = new Asiakas(0, "Markku", "Manninen", "040-1234896", "markku@manninen.com");
		Asiakas asiakas_4 = new Asiakas(0, "Sampo", "Niemi", "050-7894563", "sampo.niemi@gmail.com");
		assertEquals(true, dao.addItem(asiakas_1)); //tai assertTrue(dao.addItem(asiakas_1));	
		assertEquals(true, dao.addItem(asiakas_2));
		assertEquals(true, dao.addItem(asiakas_3));
		assertEquals(true, dao.addItem(asiakas_4)); 	
		assertEquals(4, dao.getAllItems().size());		
	}
	
	@Test
	@Order(3) 
	public void testMuutaAsiakas() {
		//Muutetaan asiakkaan Sami etunimi Sanni
		Dao dao = new Dao();		
		ArrayList<Asiakas> asiakkaat = dao.getAllItems("Sami");		
		asiakkaat.get(0).setEtunimi("Sanni");		
		dao.changeItem(asiakkaat.get(0));
		asiakkaat = dao.getAllItems("Sanni");
		assertEquals("Sanni", asiakkaat.get(0).getEtunimi());
		assertEquals("Koskinen", asiakkaat.get(0).getSukunimi());
		assertEquals("040-5173344", asiakkaat.get(0).getPuhelin());
		assertEquals("s@koskinen.com", asiakkaat.get(0).getSposti());		
	}
	
	@Test
	@Order(4) 
	public void testPoistaAsiakas() {
		//Poistetaan se asiakas, jonka etunimi on Sanni
		Dao dao = new Dao();
		ArrayList<Asiakas> asiakkaat = dao.getAllItems("Sanni");
		dao.removeItem(asiakkaat.get(0).getAsiakas_id());
		assertEquals(0, dao.getAllItems("Sanni").size());					
	}
	
	@Test
	@Order(5) 
	public void testHaeOlematonAsiakas() {
		//Haetaan asiakas,jonka asiakas_id on -1
		Dao dao = new Dao();
		assertNull(dao.getItem(-1));
	}
}