package rs.ac.bg.student.marko.MavenServerMuseum.so.stalnaPostavka;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.StalnaPostavka;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;


public class SOEditStalnaPostavka extends AbstractSO {

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof StalnaPostavka)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StalnaPostavka");
        }

        StalnaPostavka stalnaPostavka = (StalnaPostavka) entity;
        List<StalnaPostavka> list = (List<StalnaPostavka>) (List<?>) dBBroker.getAll(stalnaPostavka);
        for (StalnaPostavka s : list) {
            if (s.getNazivPostavke() == (stalnaPostavka.getNazivPostavke()) && s.getPostavkaId() != stalnaPostavka.getPostavkaId()) {
                throw new Exception("Vec postoji postavka sa unetim nazivom");
            }
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        dBBroker.update((StalnaPostavka) entity);
    }

}