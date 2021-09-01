package rs.ac.bg.student.marko.MavenServerMuseum.so.kustos;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Kustos;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

public class SOEditKustos extends AbstractSO {

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Kustos)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Kustos");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        dBBroker.update((Kustos) entity);
    }

}
