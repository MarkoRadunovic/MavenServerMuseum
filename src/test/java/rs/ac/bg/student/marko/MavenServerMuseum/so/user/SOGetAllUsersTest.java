package rs.ac.bg.student.marko.MavenServerMuseum.so.user;

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
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.User;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;
import rs.ac.bg.student.marko.MavenServerMuseum.so.tipEksponata.SOGetAllTipoviEksponata;

class SOGetAllUsersTest {
	
	private AbstractSO getAllUsers;

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
        prop.setProperty("dbName", "muzej");
        prop.setProperty("serverPort", "9000");
        prop.store(fileOutputStream, "");
	}


	@BeforeEach
	void setUp() throws Exception {
		getAllUsers = new SOGetAllUsers();
	}

	@AfterEach
	void tearDown() throws Exception {
		getAllUsers = null;
	}

	@Test
	void constructorTest() {
		getAllUsers = new SOGetAllTipoviEksponata();
		assertNotNull(getAllUsers);
	}
	
	@Test
	void testValidate() {
		
		assertThrows(Exception.class, () -> ((SOGetAllUsers)getAllUsers).validate(null));
		assertThrows(Exception.class, () -> ((SOGetAllUsers)getAllUsers).validate(new Eksponat()));
	}

	@Test
	void testExecute() {
		try {
			getAllUsers.templateExecute(new User());
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<User> list = ((SOGetAllUsers)getAllUsers).getList();
		assertNotNull(list);
		assertEquals(4, list.size());
	}

}
