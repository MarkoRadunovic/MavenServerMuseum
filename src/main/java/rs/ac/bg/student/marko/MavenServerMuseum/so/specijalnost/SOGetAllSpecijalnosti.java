package rs.ac.bg.student.marko.MavenServerMuseum.so.specijalnost;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Specijalnost;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

public class SOGetAllSpecijalnosti extends AbstractSO {

    private List<Specijalnost> list;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Specijalnost)) {
            throw new Exception("Prosledjeni objekat nije klase Specijalnost");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        list = (List<Specijalnost>) (List<?>) dBBroker.getAll((Specijalnost) entity);
    }

    public List<Specijalnost> getList() {
        return list;
    }

}
