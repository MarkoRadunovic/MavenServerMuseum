package rs.ac.bg.student.marko.MavenServerMuseum.so.kustos;

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
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Kustos;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Specijalnost;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;


class SOSaveKustosTest {

	private AbstractSO saveKustos;
	private Kustos k;

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
	static void setUpAfterClass() throws Exception {
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
		saveKustos = new SOSaveKustos();
		k = new Kustos();
	}

	@AfterEach
	void tearDown() throws Exception {
		saveKustos = null;
		k = null;
	}

	@Test
	void testValidate() {
		k.setIme("Nikola");
		k.setPrezime("Milosavljevic");
		assertThrows(Exception.class, () -> ((SOSaveKustos)saveKustos).validate(null));
		assertThrows(Exception.class, () -> ((SOSaveKustos)saveKustos).validate(new Eksponat()));
		assertThrows(Exception.class, () -> ((SOSaveKustos)saveKustos).validate(k));
	}

	@Test
	void constructorTest() {
		saveKustos = new SOSaveKustos();
		assertNotNull(saveKustos);
	}
	
	
	@Test
	void testExecute() {
		
		
		Specijalnost spec = new Specijalnost();
		spec.setSpecijalnostId(1);
		spec.setOblast("Zanimljivosti");
		
		k.setAdresa("Batut");
		k.setGodine(16);
		k.setKustosId(1);
		k.setIme("N");
		k.setPrezime("N");
		k.setSpecijalnost(spec);
		
		
		try {
			saveKustos.templateExecute(k);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		AbstractSO getAllKustosi = new SOGetAllKustosi();
		try {
			getAllKustosi.templateExecute(new Kustos());
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Kustos> list = ((SOGetAllKustosi)getAllKustosi).getList();
		
		assertNotNull(list);
		assertEquals(6, list.size());
	}

}
