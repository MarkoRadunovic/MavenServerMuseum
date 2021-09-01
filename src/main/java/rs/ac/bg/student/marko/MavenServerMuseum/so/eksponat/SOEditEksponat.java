package rs.ac.bg.student.marko.MavenServerMuseum.so.eksponat;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Eksponat;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

public class SOEditEksponat extends AbstractSO {

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Eksponat)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Eksponat");
        }

        Eksponat eksponat = (Eksponat) entity;
        List<Eksponat> list = (List<Eksponat>) (List<?>) dBBroker.getAll(eksponat);
        for (Eksponat e : list) {
            if (e.getNazivEksponata() == (eksponat.getNazivEksponata()) && e.getEksponatId() != eksponat.getEksponatId()) {
                throw new Exception("Vec postoji eksponat sa unetim nazivom");
            }
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        Eksponat eksponat = (Eksponat) entity;
        
        List<Eksponat> list = (List<Eksponat>) (List<?>) dBBroker.getAll(eksponat);
        for(Eksponat e: list)
            if(e.getEksponatId() == eksponat.getEksponatId() && e.getStalnaPostavka().getPostavkaId() != eksponat.getStalnaPostavka().getPostavkaId()){
                e.getStalnaPostavka().setBrojEksponata(e.getStalnaPostavka().getBrojEksponata()-1);
                dBBroker.update(e.getStalnaPostavka());
                eksponat.getStalnaPostavka().setBrojEksponata(eksponat.getStalnaPostavka().getBrojEksponata()+1);
                dBBroker.update(eksponat.getStalnaPostavka());
            }
        dBBroker.update((Eksponat) entity);
    }

}
