package rs.ac.bg.student.marko.MavenServerMuseum.so.eksponat;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Eksponat;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Kustos;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Specijalnost;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.StalnaPostavka;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.TipEksponata;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;
import rs.ac.bg.student.marko.MavenServerMuseum.so.kustos.SOSaveKustos;

class SOSaveEksponatTest {

	private AbstractSO saveEksponat;
	private Eksponat e;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		File file = new File("konfiguracija.properties");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        
        Properties prop = new Properties();
        prop.load(fileInputStream);
        
        prop.setProperty("dbUsername", "root");
        prop.setProperty("dbPassword", "");
        prop.setProperty("dbPort", "3306");
        prop.setProperty("dbName", "muzej_test");
        prop.setProperty("serverPort", "9000");
        prop.store(fileOutputStream, "");
        fileOutputStream.close();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		File file = new File("konfiguracija.properties");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        
        Properties prop = new Properties();
        prop.load(fileInputStream);
        
        prop.setProperty("dbUsername", "root");
        prop.setProperty("dbPassword", "");
        prop.setProperty("dbPort", "3306");
        prop.setProperty("dbName", "muzej");
        prop.setProperty("serverPort", "9000");
        prop.store(fileOutputStream, "");
        fileOutputStream.close();
	}


	@BeforeEach
	void setUp() throws Exception {
		saveEksponat = new SOSaveEksponat();
		e = new Eksponat();
	}

	@AfterEach
	void tearDown() throws Exception {
		saveEksponat = null;
		e = null;
	}

	@Test
	void testValidate() {
		e.setNazivEksponata("Kubura");
		assertThrows(Exception.class, () -> ((SOSaveEksponat)saveEksponat).validate(null));
		assertThrows(Exception.class, () -> ((SOSaveEksponat)saveEksponat).validate(new Eksponat()));
		assertThrows(Exception.class, () -> ((SOSaveEksponat)saveEksponat).validate(e));
	}

	@Test
	void constructorTest() {
		saveEksponat = new SOSaveKustos();
		assertNotNull(saveEksponat);
	}
	
	
	@Test
	void testExecute() {
		
		StalnaPostavka postavka = new StalnaPostavka();
		TipEksponata tip = new TipEksponata();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Date date = new Date();
		try {
			date = sdf.parse("1.10.2021");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		Specijalnost spec = new Specijalnost();
		spec.setSpecijalnostId(999);
		spec.setOblast("Zanimljivosti");
		
		Kustos k = new Kustos();
		k.setAdresa("Batut");
		k.setGodine(16);
		k.setKustosId(3);
		k.setIme("N");
		k.setPrezime("N");
		k.setSpecijalnost(spec);
		
		postavka.setPostavkaId(1);
		postavka.setBrojEksponata(0);
		postavka.setDatumFormiranja(date);
		postavka.setNazivPostavke("Predmeti od vrednosti");
		postavka.setKustos(k);
		
		tip.setNazivTipaEksponata("tip1");
		tip.setTipId(1);
		
		e.setEksponatId(999);
		e.setNazivEksponata("Novi eksponat");
		e.setStalnaPostavka(postavka);
		e.setStarost("111");
		e.setVisina(155);
		e.setTezina(155);
		e.setTipEksponata(tip);
		
		
		try {
			saveEksponat.templateExecute(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		AbstractSO getAllEksponati = new SOGetAllEksponati();
		try {
			getAllEksponati.templateExecute(new Eksponat());
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Eksponat> list = ((SOGetAllEksponati)getAllEksponati).getList();
		
		assertNotNull(list);
		assertEquals(12, list.size());
	}

}
