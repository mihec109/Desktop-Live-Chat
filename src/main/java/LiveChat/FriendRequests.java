/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LiveChat;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author lukap
 */
public class FriendRequests extends javax.swing.JFrame {

    /**
     * Creates new form FriendRequests
     */
    User _user;
    LoggedInForm lf;
    public DatabaseConnection db;
    
    public FriendRequests(User u, LoggedInForm _lf) {
        initComponents();
        _user = u;
        lf = _lf;
        db = new DatabaseConnection();
        fillFields();
        
    }
    public FriendRequests(){
        initComponents();
        db = new DatabaseConnection();
    }
    public void fillFields(){
        jPanel1.removeAll();
        jPanel1.revalidate();
        jPanel1.repaint();
        jPanel3.removeAll();
        jPanel3.revalidate();
        jPanel3.repaint();
        db.Open();
        List<User> userSent = db.getSentFriendRequests(_user.GetId());
        db.closeDB();
        for(User usr : userSent){
            FlowLayout fl = new FlowLayout();
            JPanel jpnl = new JPanel();
            jpnl.setLayout(fl);
            Label lbl = new Label(usr.GetFullName());
            lbl.setAlignment(Label.LEFT);
            lbl.setPreferredSize(new Dimension(80,20));
            Button add = new Button("Sent");
            add.setEnabled(false);
            Button remov = new Button("Remove");
            add.setActionCommand(Integer.toString(usr.GetId()));
            remov.setActionCommand(Integer.toString(usr.GetId()));
            remov.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    db.Open();
                    db.RemoveFriendRequest(_user.GetId(), Integer.parseInt(e.getActionCommand()));
                    db.closeDB();
                    fillFields();
                    lf.loadAllContacts();
                }
            });
            jpnl.add(lbl);
            jpnl.add(add);
            jpnl.add(remov);
            jPanel1.add(jpnl);
        }
        
        
        db.Open();
        List<User> userLists = db.getFriendRequests(_user.GetId());
        db.closeDB();
        for(User usr : userLists){
            FlowLayout fl = new FlowLayout();
            JPanel jpnl = new JPanel();
            jpnl.setLayout(fl);
            Label lbl = new Label(usr.GetFullName());
            lbl.setAlignment(Label.LEFT);
            Button add = new Button("Add Friend");
            Button remov = new Button("Remove");
            add.setActionCommand(Integer.toString(usr.GetId()));
            remov.setActionCommand(Integer.toString(usr.GetId()));
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    db.Open();
                    db.AddFriendRequest(Integer.parseInt(e.getActionCommand()), _user.GetId());
                    db.closeDB();
                    fillFields();
                    lf.loadAllContacts();
                }
            });
            remov.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    db.Open();
                    db.RemoveFriendRequest(Integer.parseInt(e.getActionCommand()), _user.GetId());
                    db.closeDB();
                    fillFields();
                    lf.loadAllContacts();
                }
            });
            jpnl.add(lbl);
            jpnl.add(add);
            jpnl.add(remov);
            jPanel3.add(jpnl);
        }
        
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 400));
        setResizable(false);

        jLabel2.setText("Sent");

        jLabel3.setText("Received");

        jLabel1.setText("Friend Requests");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(jLabel2)
                                .addGap(218, 218, 218)
                                .addComponent(jLabel3)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(FriendRequests.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FriendRequests.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FriendRequests.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FriendRequests.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FriendRequests().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
