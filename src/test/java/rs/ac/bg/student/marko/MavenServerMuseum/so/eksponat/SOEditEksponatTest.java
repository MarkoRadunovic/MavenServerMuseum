package rs.ac.bg.student.marko.MavenServerMuseum.so.eksponat;

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


class SOEditEksponatTest {

	AbstractSO editEksponat;
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
		editEksponat = new SOEditEksponat();
	}

	@AfterEach
	void tearDown() throws Exception {
		editEksponat = null;
	}

	@Test
	void testValidateNull() {
		assertThrows(Exception.class, () -> ((SOEditEksponat)editEksponat).validate(null));
	}
	
	@Test
	void testValidateNotInstance() {
		assertThrows(Exception.class, () -> ((SOEditEksponat)editEksponat).validate(new Kustos()));
	}

	@Test
	void testExecute() throws Exception {
		AbstractSO searchEksponati = new SOSearchEksponati();
		String pretraga;
		izmena = "Najstariji eksponat";
		
		pretraga = "Kubura";

		searchEksponati.templateExecute(pretraga);

		Eksponat e = ((SOSearchEksponati)searchEksponati).getList().get(0);
		
		e.setStarost(izmena);
		
		editEksponat.templateExecute(e);
		
		searchEksponati.templateExecute(pretraga);

		Eksponat e1 = ((SOSearchEksponati)searchEksponati).getList().get(0);
		
		assertEquals(e.getStarost(), e1.getStarost());
	}

}
