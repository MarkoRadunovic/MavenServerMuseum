package rs.ac.bg.student.marko.MavenServerMuseum.so.izlozba;

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
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Izlozba;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;
import rs.ac.bg.student.marko.MavenServerMuseum.so.kustos.SOEditKustos;

class SOEditIzlozbaTest {

	AbstractSO editIzlozba;
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
		editIzlozba = new SOEditKustos();
	}

	@AfterEach
	void tearDown() throws Exception {
		editIzlozba = null;
	}

	@Test
	void testValidateNull() {
		assertThrows(Exception.class, () -> ((SOEditIzlozba)editIzlozba).validate(null));
	}
	
	@Test
	void testValidateNotInstance() {
		assertThrows(Exception.class, () -> ((SOEditIzlozba)editIzlozba).validate(new Eksponat()));
	}

	@Test
	void testExecute() throws Exception {
		AbstractSO searchIzlozbe = new SOSearchIzlozbe();
		String pretraga;
		izmena = "8.88";
		
		pretraga = "Slikarstvo Srbije";

		searchIzlozbe.templateExecute(pretraga);

		Izlozba i = ((SOSearchIzlozbe)searchIzlozbe).getList().get(0);
		
		i.setOcenaIzlozbe(Double.parseDouble(pretraga));
		
		editIzlozba.templateExecute(i);
		
		searchIzlozbe.templateExecute(pretraga);

		Izlozba i1  = ((SOSearchIzlozbe)searchIzlozbe).getList().get(0);
		
		assertEquals(i.getOcenaIzlozbe(), i1.getOcenaIzlozbe());
	}

}
