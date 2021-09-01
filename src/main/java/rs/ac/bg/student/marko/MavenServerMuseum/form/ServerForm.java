package rs.ac.bg.student.marko.MavenServerMuseum.form;



import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import rs.ac.bg.student.marko.MavenServerMuseum.controller.ServerController;
import rs.ac.bg.student.marko.MavenServerMuseum.threads.ServerThread;


public class ServerForm extends javax.swing.JFrame {

    /**
     * Creates new form ServerForm
     */
    private ServerThread threadServer;

    public ServerForm() {
        initComponents();
        setLocationRelativeTo(null);
        lblServer.setText("Server nije pokrenut");
        btnZaustaviServer.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        btnPokreniServer = new javax.swing.JButton();
        btnZaustaviServer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUlogovani = new javax.swing.JTable();
        lblServer = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnPokreniServer.setText("Pokreni server");
        btnPokreniServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPokreniServerActionPerformed(evt);
            }
        });

        btnZaustaviServer.setText("Zaustavi server");
        btnZaustaviServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZaustaviServerActionPerformed(evt);
            }
        });

        tblUlogovani.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblUlogovani);

        lblServer.setText("jLabel1");

        jMenu1.setText("Konfiguracija");

        jMenuItem1.setText("Izmeni konfiguraciju");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPokreniServer, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnZaustaviServer, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblServer))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblServer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPokreniServer)
                    .addComponent(btnZaustaviServer))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void btnPokreniServerActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        try{
            FileInputStream fileInputStream = new FileInputStream(new File("konfiguracija.properties"));
            Properties prop = new Properties();
            prop.load(fileInputStream);
            String serverPortText = prop.getProperty("serverPort");
            int port = Integer.parseInt(serverPortText);
            threadServer = new ServerThread(port);
            threadServer.start();
            btnPokreniServer.setEnabled(false);
            btnZaustaviServer.setEnabled(true);
            TableModelUsers tableModel = new TableModelUsers(threadServer.getList(), ServerController.getInstance().getAllUsers());
            Thread th = new Thread(tableModel);
            th.start();
            tblUlogovani.setModel(tableModel);
            lblServer.setText("Server je pokrenut na portu: "+port);
            lblServer.setBackground(java.awt.Color.GREEN);
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Greška prilikom čitanja konfiguracije");
            ex.printStackTrace();
        }
    }                                                

    private void btnZaustaviServerActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        JOptionPane.showMessageDialog(this, "Gašenje servera...");
        System.exit(0);
    }                                                 

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        new EditConfigForm(this, rootPaneCheckingEnabled).setVisible(true);
    }                                          

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnPokreniServer;
    private javax.swing.JButton btnZaustaviServer;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblServer;
    private javax.swing.JTable tblUlogovani;
    // End of variables declaration                   
}