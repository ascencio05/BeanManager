/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanmanager;

import beanmanager.clases.BgPanel;
import beanmanager.clases.Usuario;
import beanmanager.controles.Bdd;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Rodrigo
 */
public class LogIn extends javax.swing.JFrame {
    BgPanel bgUser;
    Bdd db;
    Usuario actual;
    boolean loading;
    private final String imgPath="recursos/imagenes/";
    
    /**
     * Creates new form LogIn
     */
    public LogIn() {
        initComponents();
        setResizable(false); //Quitar Resize
        
        setSize(450,150);
        jPanel2.setBackground(Color.white);
        jPanel4.setBackground(Color.white);
        jPanel5.setBackground(Color.white); 
        jPanel6.setBackground(Color.white);
        jPanel7.setBackground(Color.white);
        imposible.setBackground(Color.white);
        jLabel1.setBackground(Color.white);
        jLabel2.setBackground(Color.white);
        jLabel3.setBackground(Color.white);
        jLabel3.setBackground(Color.white);
        setBackground(Color.white);
        initImg();
        setLocationRelativeTo(null);//Centra pantalla
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        jPanel6 = new javax.swing.JPanel();
        btnAceptar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        imposible = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel7.setLayout(new java.awt.GridLayout(3, 1));

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Correo:");
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jLabel1);
        jPanel4.add(txtUsuario);

        jPanel7.add(jPanel4);

        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Contraseña:");
        jPanel5.add(jLabel2);
        jPanel5.add(txtPass);

        jPanel7.add(jPanel5);

        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/beanmanager/recursos/imagenes/entrar.png"))); // NOI18N
        btnAceptar.setText("Entrar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        jPanel6.add(btnAceptar);

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/beanmanager/recursos/imagenes/exit.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel6.add(btnSalir);

        jPanel7.add(jPanel6);

        jPanel2.add(jPanel7, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("BeanManager");
        imposible.add(jLabel3);

        getContentPane().add(imposible, java.awt.BorderLayout.NORTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        try {
            this.setVisible(false);
            loading = true;
            Load l = new Load();
            l.setVisible(true);
            hilo(l);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al ingresar.");
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    
    public void hilo(Load l) 
    {
        final Load load = l;
        final LogIn act= this;
         Thread loading = new Thread(){
                @Override
                public void run()
                {
                    try {
                        Usuario actual = validar();
                        load.dispose();
                        if(actual != null)
                        {
                            //JOptionPane.showMessageDialog(null,"Exito.");
                            menuInicio home = new menuInicio();
                            home.session = actual;
                            home.setVisible(true);
                        }
                        else
                        {
                           JOptionPane.showMessageDialog(null,"Datos erroneos.");
                           act.setVisible(true);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error validar");
                    }
                }
            };
         loading.start();
    }
    
    public Usuario validar() throws Exception
    {
            if(db==null)
                db= new Bdd();
            String user = txtUsuario.getText();
            char[] passArray = txtPass.getPassword();
            String pass = new String(passArray);
            
            Usuario aux = new Usuario(user);
            
            if(aux.authenticate(pass, db))
            {
                aux.getPermisos(db);
                aux.validado = true;
                db.close();
                return aux;
            }
            else
            {
                db.close();
                return null;
            }
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
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogIn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel imposible;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

    private void initImg() 
    {
        String path = System.getProperty("user.dir") + "/src/beanmanager/recursos/imagenes/bean1.png";
        try {
           Image img = ImageIO.read(new File(path));
           bgUser = new BgPanel(img);
           bgUser.setPreferredSize(new Dimension(100, 100));
           bgUser.setBackground(Color.white);
           getContentPane().add(bgUser,BorderLayout.EAST);
        } catch (Exception e) {
        }
    }
}