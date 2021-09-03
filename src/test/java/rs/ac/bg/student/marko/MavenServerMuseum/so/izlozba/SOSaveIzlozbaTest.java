package rs.ac.bg.student.marko.MavenServerMuseum.so.izlozba;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Eksponat;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Izlozba;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Kustos;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.OcenaEksponata;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Specijalnost;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.StalnaPostavka;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.TipEksponata;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

class SOSaveIzlozbaTest {

	private AbstractSO saveIzlozba;
	private Izlozba i;

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
		saveIzlozba = new SOSaveIzlozba();
		i = new Izlozba();
	}

	@AfterEach
	void tearDown() throws Exception {
		saveIzlozba = null;
		i = null;
	}

	@Test
	void testValidate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Date datP = new Date();
		Date datK = new Date();
		try {
			datP = sdf.parse("2.10.2020");
			datK = sdf.parse("4.10.2020");
		} catch (ParseException ex1) {
			ex1.printStackTrace();
		}
		
		
		
		i.setNazivIzlozbe("Slikarstvo Srbije");
		assertThrows(Exception.class, () -> ((SOSaveIzlozba)saveIzlozba).validate(null));
		assertThrows(Exception.class, () -> ((SOSaveIzlozba)saveIzlozba).validate(new Eksponat()));
		assertThrows(Exception.class, () -> ((SOSaveIzlozba)saveIzlozba).validate(i));
		i.setDatumPocetka(datP);
		i.setDatumZavrsetka(datK);
		i.setNazivIzlozbe("Nova Izlozba");
		assertThrows(Exception.class, () -> ((SOSaveIzlozba)saveIzlozba).validate(i));
		
	}

	@Test
	void constructorTest() {
		saveIzlozba = new SOSaveIzlozba();
		assertNotNull(saveIzlozba);
	}
	
	
	@Test
	void testExecute() {
		
		StalnaPostavka postavka = new StalnaPostavka();
		TipEksponata tip = new TipEksponata();
		Eksponat e = new Eksponat();
		OcenaEksponata oc = new OcenaEksponata();
		
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
		
		postavka.setPostavkaId(1);
		postavka.setBrojEksponata(0);
		postavka.setDatumFormiranja(date);
		postavka.setNazivPostavke("Nova postavka");
		postavka.setKustos(k);
		
		tip.setNazivTipaEksponata("tip1");
		tip.setTipId(1);
		
		e.setEksponatId(4);
		e.setNazivEksponata("Novi eksponat");
		e.setStalnaPostavka(postavka);
		e.setStarost("111");
		e.setVisina(155);
		e.setTezina(155);
		e.setTipEksponata(tip);
		
		oc.setEksponat(e);
		oc.setIzlozba(i);
		oc.setOcena(6);
		
		List<OcenaEksponata> lista = new ArrayList<OcenaEksponata>();
		lista.add(oc);
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd.MM.yyyy");
		Date datP = new Date();
		Date datK = new Date();
		try {
			datP = sdf1.parse("2.11.2020");
			datK = sdf1.parse("4.11.2020");
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		
		i.setBrojEksponata(12);
		i.setDatumPocetka(datP);
		i.setDatumZavrsetka(datK);
		i.setNazivIzlozbe("Nova izlozba");
		i.setIzlozbaId(55);
		i.setOcenaIzlozbe(6);
		i.setKustos(k);
		i.setList(lista);
		
		try {
			saveIzlozba.templateExecute(i);
		} catch (Exception ex2) {
			ex2.printStackTrace();
		}
		
		AbstractSO getAllIzlozbe = new SOGetAllIzlozbe();
		try {
			getAllIzlozbe.templateExecute(new Izlozba());
		} catch (Exception ex3) {
			ex3.printStackTrace();
		}
		List<Izlozba> list = ((SOGetAllIzlozbe)getAllIzlozbe).getList();
		
		assertNotNull(list);
		assertEquals(3, list.size());
	}

}
