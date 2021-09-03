package rs.ac.bg.student.marko.MavenServerMuseum.so.eksponat;

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
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.TipEksponata;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;


class SOGetAllEksponatiTest {

	private AbstractSO getAllEksponati;

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
		getAllEksponati = new SOGetAllEksponati();
	}

	@AfterEach
	void tearDown() throws Exception {
		getAllEksponati = null;
	}

	@Test
	void testValidate() {
		
		assertThrows(Exception.class, () -> ((SOGetAllEksponati)getAllEksponati).validate(null));
		assertThrows(Exception.class, () -> ((SOGetAllEksponati)getAllEksponati).validate(new TipEksponata()));
	}

	@Test
	void constructorTest() {
		getAllEksponati = new SOGetAllEksponati();
		assertNotNull(getAllEksponati);
	}
	
	
	@Test
	void testExecute() {
		try {
			getAllEksponati.templateExecute(new Eksponat());
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Eksponat> list = ((SOGetAllEksponati)getAllEksponati).getList();
		assertNotNull(list);
		assertEquals(11, list.size());
	}
}
