package rs.ac.bg.student.marko.MavenServerMuseum.controller;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Eksponat;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Izlozba;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Kustos;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.OcenaEksponata;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Specijalnost;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.StalnaPostavka;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.TipEksponata;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.User;
import rs.ac.bg.student.marko.MavenServerMuseum.so.eksponat.SOEditEksponat;
import rs.ac.bg.student.marko.MavenServerMuseum.so.eksponat.SOGetAllEksponati;
import rs.ac.bg.student.marko.MavenServerMuseum.so.eksponat.SOSaveEksponat;
import rs.ac.bg.student.marko.MavenServerMuseum.so.eksponat.SOSearchEksponati;
import rs.ac.bg.student.marko.MavenServerMuseum.so.izlozba.SOEditIzlozba;
import rs.ac.bg.student.marko.MavenServerMuseum.so.izlozba.SOGetAllIzlozbe;
import rs.ac.bg.student.marko.MavenServerMuseum.so.izlozba.SOSaveIzlozba;
import rs.ac.bg.student.marko.MavenServerMuseum.so.izlozba.SOSearchIzlozbe;
import rs.ac.bg.student.marko.MavenServerMuseum.so.kustos.SOEditKustos;
import rs.ac.bg.student.marko.MavenServerMuseum.so.kustos.SOGetAllKustosi;
import rs.ac.bg.student.marko.MavenServerMuseum.so.kustos.SOSaveKustos;
import rs.ac.bg.student.marko.MavenServerMuseum.so.kustos.SOSearchKustosi;
import rs.ac.bg.student.marko.MavenServerMuseum.so.ocena.SOGetAllOcene;
import rs.ac.bg.student.marko.MavenServerMuseum.so.specijalnost.SOGetAllSpecijalnosti;
import rs.ac.bg.student.marko.MavenServerMuseum.so.stalnaPostavka.SOEditStalnaPostavka;
import rs.ac.bg.student.marko.MavenServerMuseum.so.stalnaPostavka.SOGetAllStalnePostavke;
import rs.ac.bg.student.marko.MavenServerMuseum.so.stalnaPostavka.SOSaveStalnaPostavka;
import rs.ac.bg.student.marko.MavenServerMuseum.so.stalnaPostavka.SOSearchStalnePostavke;
import rs.ac.bg.student.marko.MavenServerMuseum.so.tipEksponata.SOGetAllTipoviEksponata;
import rs.ac.bg.student.marko.MavenServerMuseum.so.user.SOGetAllUsers;

public class ServerController {

    private static ServerController instance;

    public ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public synchronized User login(User user) throws Exception {
        SOGetAllUsers so = new SOGetAllUsers();
        so.templateExecute(new User());
        List<User> list = so.getList();
        for(User u : list)
            if(u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword()))
                return u;
        throw new Exception("Pogresno uneti kredencijali");
    }
    
    public synchronized List<User> getAllUsers() throws Exception{
        SOGetAllUsers so = new SOGetAllUsers();
        so.templateExecute(new User());
        return so.getList();
    }

    public synchronized List<Eksponat> getAllEksponati() throws Exception {
        SOGetAllEksponati so = new SOGetAllEksponati();
        so.templateExecute(new Eksponat());
        return so.getList();
    }

    public synchronized void saveEksponat(Eksponat eksponat) throws Exception {
        SOSaveEksponat so = new SOSaveEksponat();
        so.templateExecute(eksponat);
    }

    public synchronized List<Eksponat> searchEksponati(String param) throws Exception {
        SOSearchEksponati so = new SOSearchEksponati();
        so.templateExecute(param);
        return so.getList();
    }

    public synchronized void editEksponat(Eksponat eksponat) throws Exception {
        SOEditEksponat so = new SOEditEksponat();
        so.templateExecute(eksponat);
    }

    public synchronized List<Izlozba> getAllIzlozbe() throws Exception {
        SOGetAllIzlozbe so = new SOGetAllIzlozbe();
        so.templateExecute(new Izlozba());
        return so.getList();
    }

    public synchronized List<Izlozba> searchIzlozbe(String param) throws Exception {
        SOSearchIzlozbe so = new SOSearchIzlozbe();
        so.templateExecute(param);
        return so.getList();
    }

    public synchronized void saveIzlozba(Izlozba izlozba) throws Exception {
        SOSaveIzlozba so = new SOSaveIzlozba();
        so.templateExecute(izlozba);
    }

    public synchronized void editIzlozba(Izlozba izlozba) throws Exception {
        SOEditIzlozba so = new SOEditIzlozba();
        so.templateExecute(izlozba);
    }

    public synchronized List<OcenaEksponata> getAllOcene() throws Exception {
        SOGetAllOcene so = new SOGetAllOcene();
        so.templateExecute(new OcenaEksponata());
        return so.getList();
    }

    public synchronized List<Kustos> getAllKustosi() throws Exception {
        SOGetAllKustosi so = new SOGetAllKustosi();
        so.templateExecute(new Kustos());
        return so.getList();
    }

    public synchronized void saveKustos(Kustos kustos) throws Exception {
        SOSaveKustos so = new SOSaveKustos();
        so.templateExecute(kustos);
    }

    public synchronized List<Kustos> searchKustosi(String param) throws Exception {
        SOSearchKustosi so = new SOSearchKustosi();
        so.templateExecute(param);
        return so.getList();
    }

    public synchronized void editKustos(Kustos kustos) throws Exception {
        SOEditKustos so = new SOEditKustos();
        so.templateExecute(kustos);
    }

    public synchronized List<Specijalnost> getAllSpecijalnosti() throws Exception {
        SOGetAllSpecijalnosti so = new SOGetAllSpecijalnosti();
        so.templateExecute(new Specijalnost());
        return so.getList();
    }

    public synchronized List<StalnaPostavka> getAllStalnePostavke() throws Exception {
        SOGetAllStalnePostavke so = new SOGetAllStalnePostavke();
        so.templateExecute(new StalnaPostavka());
        return so.getList();
    }

    public synchronized void saveStalnaPostavka(StalnaPostavka stalnaPostavka) throws Exception {
        SOSaveStalnaPostavka so = new SOSaveStalnaPostavka();
        so.templateExecute(stalnaPostavka);
    }

    public synchronized List<StalnaPostavka> searchStalnePostavke(String param) throws Exception {
        SOSearchStalnePostavke so = new SOSearchStalnePostavke();
        so.templateExecute(param);
        return so.getList();
    }

    public synchronized void editStalnaPostavka(StalnaPostavka stalnaPostavka) throws Exception {
        SOEditStalnaPostavka so = new SOEditStalnaPostavka();
        so.templateExecute(stalnaPostavka);
    }

    public synchronized List<TipEksponata> getAllTipoviEksponata() throws Exception {
        SOGetAllTipoviEksponata so = new SOGetAllTipoviEksponata();
        so.templateExecute(new TipEksponata());
        return so.getList();
    }
}