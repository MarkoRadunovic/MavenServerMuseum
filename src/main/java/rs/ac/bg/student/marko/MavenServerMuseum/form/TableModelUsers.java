package rs.ac.bg.student.marko.MavenServerMuseum.form;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.User;
import rs.ac.bg.student.marko.MavenServerMuseum.threads.ClientThread;


public class TableModelUsers extends AbstractTableModel implements Runnable{
    
    private List<ClientThread> listThreads;
    private List<User> listUsers;
    private String []index = new String[]{"Ime","Prezime","Username","Ulogovan"};

    public TableModelUsers(List<ClientThread> listThreads, List<User> listUsers) {
        this.listThreads = listThreads;
        this.listUsers = listUsers;
    }
    
    

    @Override
    public int getRowCount() {
        return listUsers.size();
    }

    @Override
    public String getColumnName(int column) {
        return index[column];
    }

    
    @Override
    public int getColumnCount() {
        return index.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = listUsers.get(rowIndex);
        switch(columnIndex){
            case 0:
                return user.getIme();
            case 1:
                return user.getPrezime();
            case 2:
                return user.getUsername();
            case 3:
                for(ClientThread ct: listThreads)
                    if(ct.getUser()!=null && user.equals(ct.getUser()))
                        return "Da";
                return "Ne";
            default:
                return null;
        }
    }
    


    @Override
    public void run() {
        try{
            while(true){
                Thread.sleep(500);
                fireTableDataChanged();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
}