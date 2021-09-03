package rs.ac.bg.student.marko.MavenServerMuseum.so.stalnaPostavka;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Eksponat;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.StalnaPostavka;

import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;


class SOGetAllStalnePostavkeTest {

	private AbstractSO getAllStalnePostavke;

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
		getAllStalnePostavke = new SOGetAllStalnePostavke();
	}

	@AfterEach
	void tearDown() throws Exception {
		getAllStalnePostavke = null;
	}

	@Test
	void testValidate() {
		
		assertThrows(Exception.class, () -> ((SOGetAllStalnePostavke)getAllStalnePostavke).validate(null));
		assertThrows(Exception.class, () -> ((SOGetAllStalnePostavke)getAllStalnePostavke).validate(new Eksponat()));
	}

	@Test
	void constructorTest() {
		getAllStalnePostavke = new SOGetAllStalnePostavke();
		assertNotNull(getAllStalnePostavke);
	}
	
	
	@Test
	void testExecute() {
		try {
			getAllStalnePostavke.templateExecute(new StalnaPostavka());
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<StalnaPostavka> list = ((SOGetAllStalnePostavke)getAllStalnePostavke).getList();
		assertNotNull(list);
		assertEquals(4, list.size());
	}

}
