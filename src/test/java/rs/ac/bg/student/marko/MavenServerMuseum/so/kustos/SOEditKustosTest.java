package rs.ac.bg.student.marko.MavenServerMuseum.so.kustos;

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
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Kustos;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

class SOEditKustosTest {
	
	AbstractSO editKustos;
	String izmena;

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
		editKustos = new SOEditKustos();
	}

	@AfterEach
	void tearDown() throws Exception {
		editKustos = null;
	}

	@Test
	void testValidateNull() {
		assertThrows(Exception.class, () -> ((SOEditKustos)editKustos).validate(null));
	}
	
	@Test
	void testValidateNotInstance() {
		assertThrows(Exception.class, () -> ((SOEditKustos)editKustos).validate(new Eksponat()));
	}

	@Test
	void testExecute() throws Exception {
		AbstractSO searchKustosi = new SOSearchKustosi();
		String pretraga;
		izmena = "Nova adresa";
		
		pretraga = "Nikola Milosavljevic";

		searchKustosi.templateExecute(pretraga);

		Kustos k = ((SOSearchKustosi)searchKustosi).getList().get(0);
		
		k.setAdresa(izmena);
		
		editKustos.templateExecute(k);
		
		searchKustosi.templateExecute(pretraga);

		Kustos k1 = ((SOSearchKustosi)searchKustosi).getList().get(0);
		
		assertEquals(k.getAdresa(), k1.getAdresa());
	}

}
