package rs.ac.bg.student.marko.MavenServerMuseum.so.eksponat;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Eksponat;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.StalnaPostavka;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

public class SOSaveEksponat extends AbstractSO {

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Eksponat)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Eksponat");
        }

        Eksponat eksponat = (Eksponat) entity;
        List<Eksponat> list = (List<Eksponat>) (List<?>) dBBroker.getAll(eksponat);
        for (Eksponat e : list) {
            if (e.getNazivEksponata().equals(eksponat.getNazivEksponata())) {
                throw new Exception("Vec postoji takav eksponat");
            }
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        Eksponat eksponat = (Eksponat) entity;
        dBBroker.insert(eksponat);
        StalnaPostavka stalnaPostavka = eksponat.getStalnaPostavka();
        stalnaPostavka.setBrojEksponata(stalnaPostavka.getBrojEksponata()+1);
        dBBroker.update(stalnaPostavka);
        
    }

}

