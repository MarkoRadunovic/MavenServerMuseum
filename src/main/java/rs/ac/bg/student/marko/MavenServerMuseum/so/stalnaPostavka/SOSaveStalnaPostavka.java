package rs.ac.bg.student.marko.MavenServerMuseum.so.stalnaPostavka;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.StalnaPostavka;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

public class SOSaveStalnaPostavka extends AbstractSO {

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof StalnaPostavka)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StalnaPostavka");
        }

        StalnaPostavka stalnaPostavka = (StalnaPostavka) entity;
        List<StalnaPostavka> list = (List<StalnaPostavka>) (List<?>) dBBroker.getAll(stalnaPostavka);
        for (StalnaPostavka s : list) {
            if (s.getNazivPostavke().equals(stalnaPostavka.getNazivPostavke())) {
                throw new Exception("Vec postoji takva postavka");
            }
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        dBBroker.insert((StalnaPostavka) entity);
    }

}
