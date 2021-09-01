package rs.ac.bg.student.marko.MavenServerMuseum.so.ocena;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.OcenaEksponata;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

public class SOGetAllOcene extends AbstractSO {

    private List<OcenaEksponata> list;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof OcenaEksponata)) {
            throw new Exception("Prosledjeni objekat nije klase OcenaEksponata");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        list = (List<OcenaEksponata>) (List<?>) dBBroker.getAll((OcenaEksponata) entity);
    }

    public List<OcenaEksponata> getList() {
        return list;
    }

}
