package rs.ac.bg.student.marko.MavenServerMuseum.threads;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Eksponat;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Izlozba;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.Kustos;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.StalnaPostavka;
import rs.ac.bg.student.marko.MavenCommonMuseum.domain.User;
import rs.ac.bg.student.marko.MavenCommonMuseum.transfer.Operation;
import rs.ac.bg.student.marko.MavenCommonMuseum.transfer.Request;
import rs.ac.bg.student.marko.MavenCommonMuseum.transfer.Response;
import rs.ac.bg.student.marko.MavenServerMuseum.controller.ServerController;


public class ClientThread extends Thread {

    private Socket socket;
    private User user;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) ois.readObject();
                Response response = createResponse(request);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Response createResponse(Request zahtev) {
        Response odgovor = new Response();
        try {
            switch (zahtev.getOperation()) {
                case Operation.GET_ALL_EKSPONATI:
                    odgovor.setPodaci(ServerController.getInstance().getAllEksponati());
                    break;
                case Operation.GET_ALL_IZLOZBE:
                    odgovor.setPodaci(ServerController.getInstance().getAllIzlozbe());
                    break;
                case Operation.GET_ALL_KUSTOSI:
                    odgovor.setPodaci(ServerController.getInstance().getAllKustosi());
                    break;
                case Operation.GET_ALL_OCENE:
                    odgovor.setPodaci(ServerController.getInstance().getAllOcene());
                    break;
                case Operation.GET_ALL_SPECIJALNOSTI:
                    odgovor.setPodaci(ServerController.getInstance().getAllSpecijalnosti());
                    break;
                case Operation.GET_ALL_STALNE_POSTAVKE:
                    odgovor.setPodaci(ServerController.getInstance().getAllStalnePostavke());
                    break;
                case Operation.GET_ALL_TIPOVI_EKSPONATA:
                    odgovor.setPodaci(ServerController.getInstance().getAllTipoviEksponata());
                    break;
                case Operation.LOGIN:
                    user = ServerController.getInstance().login((User) zahtev.getPodaci());
                    odgovor.setPodaci(user);
                    break;
                case Operation.SAVE_EKSPONAT:
                    ServerController.getInstance().saveEksponat((Eksponat) zahtev.getPodaci());
                    break;
                case Operation.SAVE_IZLOZBA:
                    ServerController.getInstance().saveIzlozba((Izlozba) zahtev.getPodaci());
                    break;
                case Operation.SAVE_STALNA_POSTAVKA:
                    ServerController.getInstance().saveStalnaPostavka((StalnaPostavka) zahtev.getPodaci());
                    break;
                case Operation.SAVE_KUSTOS:
                    ServerController.getInstance().saveKustos((Kustos) zahtev.getPodaci());
                    break;
                case Operation.SEARCH_EKSPONATI:
                    odgovor.setPodaci(ServerController.getInstance().searchEksponati((String) zahtev.getPodaci()));
                    break;
                case Operation.SEARCH_IZLOZBE:
                    odgovor.setPodaci(ServerController.getInstance().searchIzlozbe((String) zahtev.getPodaci()));
                    break;
                case Operation.SEARCH_STALNE_POSTAVKE:
                    odgovor.setPodaci(ServerController.getInstance().searchStalnePostavke((String) zahtev.getPodaci()));
                    break;
                case Operation.SEARCH_KUSTOSI:
                    odgovor.setPodaci(ServerController.getInstance().searchKustosi((String) zahtev.getPodaci()));
                    break;
                case Operation.EDIT_EKSPONAT:
                    ServerController.getInstance().editEksponat((Eksponat) zahtev.getPodaci());
                    break;
                case Operation.EDIT_IZLOZBA:
                    ServerController.getInstance().editIzlozba((Izlozba) zahtev.getPodaci());
                    break;
                case Operation.EDIT_KUSTOS:
                    ServerController.getInstance().editKustos((Kustos) zahtev.getPodaci());
                    break;
                case Operation.EDIT_STALNA_POSTAVKA:
                    ServerController.getInstance().editStalnaPostavka((StalnaPostavka) zahtev.getPodaci());
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setGreska(ex);
        }
        return odgovor;
    }

    public User getUser() {
        return user;
    }

    
}
