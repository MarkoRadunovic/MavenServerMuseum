package rs.ac.bg.student.marko.MavenServerMuseum.so.kustos;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Kustos;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

public class SOGetAllKustosi extends AbstractSO {

    private List<Kustos> list;

    public List<Kustos> getList() {
        return list;
    }

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Kustos)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Kustos");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        list = (List<Kustos>) (List<?>) dBBroker.getAll((Kustos) entity);
    }

}