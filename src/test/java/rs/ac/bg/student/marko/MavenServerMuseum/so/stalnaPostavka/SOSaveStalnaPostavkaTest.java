package rs.ac.bg.student.marko.MavenServerMuseum.so.stalnaPostavka;

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

import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;


class SOSaveStalnaPostavkaTest {

	private AbstractSO saveStalnaPostavka;
	private StalnaPostavka postavka;

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
		saveStalnaPostavka = new SOSaveStalnaPostavka();
		postavka = new StalnaPostavka();
	}

	@AfterEach
	void tearDown() throws Exception {
		saveStalnaPostavka = null;
		postavka = null;
	}

	@Test
	void testValidate() {
		postavka.setNazivPostavke("Predmeti od vrednosti");
		assertThrows(Exception.class, () -> ((SOSaveStalnaPostavka)saveStalnaPostavka).validate(null));
		assertThrows(Exception.class, () -> ((SOSaveStalnaPostavka)saveStalnaPostavka).validate(new Eksponat()));
		assertThrows(Exception.class, () -> ((SOSaveStalnaPostavka)saveStalnaPostavka).validate(postavka));
	}

	@Test
	void constructorTest() {
		saveStalnaPostavka = new SOSaveStalnaPostavka();
		assertNotNull(saveStalnaPostavka);
	}
	
	
	@Test
	void testExecute() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Date date = new Date();
		try {
			date = sdf.parse("5.10.2022");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		Specijalnost spec = new Specijalnost();
		spec.setSpecijalnostId(999);
		spec.setOblast("Zanimljivosti");
		
		Kustos k = new Kustos();
		k.setAdresa("Batut");
		k.setGodine(16);
		k.setKustosId(1);
		k.setIme("N");
		k.setPrezime("N");
		k.setSpecijalnost(spec);
		
		postavka.setPostavkaId(999);
		postavka.setBrojEksponata(0);
		postavka.setDatumFormiranja(date);
		postavka.setNazivPostavke("Nova postavka");
		postavka.setKustos(k);
		
		
		
		try {
			saveStalnaPostavka.templateExecute(postavka);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		AbstractSO getAllPostavke = new SOGetAllStalnePostavke();
		
		try {
			getAllPostavke.templateExecute(new StalnaPostavka());
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<StalnaPostavka> list = ((SOGetAllStalnePostavke)getAllPostavke).getList();
		
		assertNotNull(list);
		assertEquals(5, list.size());
	}

}
