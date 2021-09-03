package rs.ac.bg.student.marko.MavenServerMuseum.so.stalnaPostavka;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Eksponat;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.StalnaPostavka;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;


class SOEditStalnaPostavkaTest {

	AbstractSO editPostavka;

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
		editPostavka = new SOEditStalnaPostavka();
	}

	@AfterEach
	void tearDown() throws Exception {
		editPostavka= null;
	}

	@Test
	void testValidateNull() {
		assertThrows(Exception.class, () -> ((SOEditStalnaPostavka)editPostavka).validate(null));
	}
	
	@Test
	void testValidateNotInstance() {
		assertThrows(Exception.class, () -> ((SOEditStalnaPostavka)editPostavka).validate(new Eksponat()));
	}

	@Test
	void testExecute() throws Exception {
		AbstractSO searchPostavke = new SOSearchStalnePostavke();
		String pretraga;
		
		pretraga = "Umetnost Srbije";

		searchPostavke.templateExecute(pretraga);

		StalnaPostavka sp = ((SOSearchStalnePostavke)searchPostavke).getList().get(0);
		
		sp.setBrojEksponata(99);
		
		editPostavka.templateExecute(sp);
		
		searchPostavke.templateExecute(pretraga);

		StalnaPostavka sp1 = ((SOSearchStalnePostavke)searchPostavke).getList().get(0);
		
		assertEquals(sp.getBrojEksponata(), sp1.getBrojEksponata());
	}

}
