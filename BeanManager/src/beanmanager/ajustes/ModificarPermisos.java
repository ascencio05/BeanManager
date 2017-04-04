/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanmanager.ajustes;

import beanmanager.controles.*;
import java.awt.Color;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author jacky
 */
public class ModificarPermisos extends javax.swing.JFrame {
    String idPermiso;
    /**
     * Creates new form ModificarPermisos
     */
    public ModificarPermisos(String id) {
        idPermiso = id;
        initComponents();
        jlbRol.setText("prueba");
        jlbModulo.setText("prueba");
//        JOptionPane.showMessageDialog(null, "Error: "+idPermiso);
        CargarDatos();
        setLocationRelativeTo(null);
        setLayout(null);
        this.getContentPane().setBackground(Color.decode("#FFFFFF"));
    }
    public void CargarDatos()
    {
        try{
            Boolean permiso;
            Bdd conexion = new Bdd();
            String consulta="SELECT `PermisosRoles`.`idModulo`,`Modulos`.`modulo` as 'modulo', `rolesProyecto`.`rol` as 'rol',"
                    + "`modificar`,`agregar`, `borrar`, `ingresar` FROM `PermisosRoles` inner join `Modulos` on "
                    + "`Modulos`.`idModulo` = `PermisosRoles`.`idModulo` inner join `rolesProyecto` on `rolesProyecto`.`idRol`=`PermisosRoles`.`idRol`"
                    + " WHERE `idPermiso`="+idPermiso;
            Statement stmt = conexion.con.createStatement();
            ResultSet resultado = stmt.executeQuery(consulta);
            if(resultado.next())
            {
                jlbRol.setText(resultado.getString("rol"));
                jlbModulo.setText("Módulo: "+resultado.getString("modulo"));
                permiso = (resultado.getBoolean("ingresar"));
                jcbIngresar.setSelected(permiso);
                permiso = (resultado.getBoolean("agregar"));
                jcbAgregar.setSelected(permiso);
                permiso = (resultado.getBoolean("modificar"));
                jcbModificar.setSelected(permiso);
                permiso = (resultado.getBoolean("borrar"));
                jcbBorrar.setSelected(permiso);
              }
            conexion.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: "+e);
        }
    }
    public void Modificar()
    {
        try
        {
                Bdd conexion = new Bdd();
                String consulta="UPDATE `Permisos` SET `modificar`="+jcbModificar.isSelected()+","
                        + "`agregar`="+jcbAgregar.isSelected()+",`borrar`="+jcbBorrar.isSelected()+","
                        + "`ingresar`="+jcbIngresar.isSelected()+" WHERE `idPermiso`= "+idPermiso;
                Statement stmt = conexion.con.createStatement();
                int agregado = stmt.executeUpdate(consulta);
                if(agregado>0)
                {
                    JOptionPane.showMessageDialog(null, "Se han modificado los permisos con éxito.");
                    conexion.close();
                    permisos jfmPermisos = new permisos();
                    jfmPermisos.setVisible(true);
                    this.setVisible(false);
                }
                else
                {
                    conexion.close();
                    JOptionPane.showMessageDialog(null, "No se pudo modificar los permisos.");
                }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error: "+e);
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

        jLabel1 = new javax.swing.JLabel();
        jlbRol = new javax.swing.JLabel();
        jcbAgregar = new javax.swing.JCheckBox();
        jcbBorrar = new javax.swing.JCheckBox();
        jcbModificar = new javax.swing.JCheckBox();
        jcbIngresar = new javax.swing.JCheckBox();
        jbnModificar = new javax.swing.JButton();
        jbnCancelar = new javax.swing.JButton();
        jlbModulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Modificar Permisos del Rol:");

        jcbAgregar.setText("Agregar");

        jcbBorrar.setText("Borrar");

        jcbModificar.setText("Modificar");

        jcbIngresar.setText("Ingresar");

        jbnModificar.setText("Modificar");
        jbnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnModificarActionPerformed(evt);
            }
        });

        jbnCancelar.setText("Cancelar");
        jbnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnCancelarActionPerformed(evt);
            }
        });

        jLabel2.setText("Permisos:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlbRol)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcbIngresar)
                                    .addComponent(jcbModificar)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcbBorrar)
                                    .addComponent(jcbAgregar)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addComponent(jbnModificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbnCancelar)))
                        .addGap(30, 30, 30))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbModulo)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jlbRol))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlbModulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbIngresar)
                    .addComponent(jcbAgregar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbBorrar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jcbModificar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbnCancelar)
                    .addComponent(jbnModificar))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnCancelarActionPerformed
        // TODO add your handling code here:
        permisos jfmPermisos = new permisos();
        jfmPermisos.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jbnCancelarActionPerformed

    private void jbnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnModificarActionPerformed
        // TODO add your handling code here:
        Modificar();
    }//GEN-LAST:event_jbnModificarActionPerformed

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
            java.util.logging.Logger.getLogger(ModificarPermisos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificarPermisos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificarPermisos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificarPermisos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModificarPermisos("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jbnCancelar;
    private javax.swing.JButton jbnModificar;
    private javax.swing.JCheckBox jcbAgregar;
    private javax.swing.JCheckBox jcbBorrar;
    private javax.swing.JCheckBox jcbIngresar;
    private javax.swing.JCheckBox jcbModificar;
    private javax.swing.JLabel jlbModulo;
    private javax.swing.JLabel jlbRol;
    // End of variables declaration//GEN-END:variables
}
