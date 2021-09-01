package rs.ac.bg.student.marko.MavenServerMuseum.database;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.DomainObject;

public class DBBroker {

    private Connection connection;

    public void connect() throws Exception {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("konfiguracija.properties"));
            Properties prop = new Properties();
            prop.load(fileInputStream);
            String dbPort = prop.getProperty("dbPort");
            String dbUsername = prop.getProperty("dbUsername");
            String dbPassword = prop.getProperty("dbPassword");
            String dbName = prop.getProperty("dbName");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:" + dbPort + "/" + dbName, dbUsername, dbPassword);
            connection.setAutoCommit(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void disconnect() throws Exception {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Greska prilikom raskidanja konekcije sa bazom!\n" + ex.getMessage());
            }
        }
    }

    public void commit() throws Exception {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Greska prilikom potvrdjivanja transakcije!\n" + ex.getMessage());
            }
        }
    }

    public void rollback() throws Exception {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new Exception("Greska prilikom ponistavanja transakcije!\n" + ex.getMessage());
            }
        }
    }

    public void insert(DomainObject ado) throws SQLException {
        String query = ado.getQueryForInsert();
        boolean isAutoIncrement = ado.isAutoIncrement();
        PreparedStatement ps;
        if (isAutoIncrement) {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        } else {
            ps = connection.prepareStatement(query);
        }
        ps.execute();
        if (ado.isAutoIncrement()) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                ado.setId(rs.getInt(1));
            }
        }
    }

    public void update(DomainObject ado) throws SQLException {
        String query = ado.getQueryForUpdate();
        connection.createStatement().execute(query);
    }

    public void delete(DomainObject ado) throws SQLException {
        String query = ado.getQueryForDelete();
        connection.createStatement().execute(query);
    }

    public List<DomainObject> getAll(DomainObject ado) throws SQLException {
        String query = ado.getQueryForAll();
        ResultSet rs = connection.createStatement().executeQuery(query);
        return ado.getList(rs);
    }

    public List<DomainObject> search(DomainObject ado, String param) throws SQLException {
        String query = ado.getQueryForSearch(param);
        ResultSet rs = connection.createStatement().executeQuery(query);
        return ado.getList(rs);
    }
}