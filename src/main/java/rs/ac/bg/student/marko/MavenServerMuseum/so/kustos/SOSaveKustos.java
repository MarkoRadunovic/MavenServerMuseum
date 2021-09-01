package rs.ac.bg.student.marko.MavenServerMuseum.so.kustos;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Kustos;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

public class SOSaveKustos extends AbstractSO {

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Kustos)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Kustos");
        }
        List<Kustos> list = (List<Kustos>)(List<?>)dBBroker.getAll((Kustos) entity);
        Kustos kustos = (Kustos) entity;
        for (Kustos k : list) {
            if(k.getIme().equals(kustos.getIme())&& k.getPrezime().equals(kustos.getPrezime()))
                throw new Exception("Vec postoji kustos sa tim imenom i prezimenom");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        dBBroker.insert((Kustos) entity);
    }

}

