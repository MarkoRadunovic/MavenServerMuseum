package rs.ac.bg.student.marko.MavenServerMuseum.so.user;

import java.util.List;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.User;
import rs.ac.bg.student.marko.MavenServerMuseum.so.AbstractSO;

public class SOGetAllUsers extends AbstractSO{

    private List<User> list; 
    
    @Override
    protected void validate(Object entity) throws Exception {
        if(!(entity instanceof User))
            throw new Exception("Prosledjeni objekat nije instanca klase User");
    }

    @Override
    protected void execute(Object entity) throws Exception {
        list = (List<User>)(List<?>)dBBroker.getAll((User)entity);
    }

    public List<User> getList() {
        return list;
    }
    
    
}