package rs.ac.bg.student.marko.MavenServerMuseum.so.ocena;

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
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

class SOGetAllOceneTest {

	private AbstractSO getAllOcene;

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
		getAllOcene = new SOGetAllOcene();
	}

	@AfterEach
	void tearDown() throws Exception {
		getAllOcene = null;
	}

	@Test
	void testValidateNull() {
		
		assertThrows(Exception.class, () -> ((SOGetAllOcene)getAllOcene).validate(null));
	}
	@Test
	void testValidateNotInstance() {

		assertThrows(Exception.class, () -> ((SOGetAllOcene)getAllOcene).validate(new Eksponat()));
	}

	@Test
	void constructorTest() {
		getAllOcene = new SOGetAllOcene();
		assertNotNull(getAllOcene);
	}
	
	
	@Test
	void testExecute() {
		
	}


}
