/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanmanager.proyectos;
import beanmanager.clases.SeguridadProyectos;
import beanmanager.clases.Usuario;
import beanmanager.controles.Bdd;
import java.awt.Color;
import java.awt.Component;
import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;

/**
 *
 * @author ascencio
 */
public class Programadores extends javax.swing.JFrame {
    int idActividad =0,idProyecto;
    String[] nombreProgramador,apellidoProgramador,rol;
    int[] idProgramador,idRol;
    boolean[] estadoProgramador,estadoProgramadorOriginal,movimiento;
    Statement st;
    ResultSet rs;
    DefaultTableModel modelTblDisponibles,modelTblActividad;
    public Usuario session;
    /**
     * Creates new form Actividades
     */
    public Programadores(Usuario session) {
        this.session=session;
        initComponents();
        iniciarVentana();
    }
    public Programadores(Usuario session,int idProyecto) {
        this.session=session;
        this.idProyecto=idProyecto;
        initComponents();
        iniciarVentana();
    }
    
    private void iniciarVentana()
    {
        setResizable(false); //Quitar Resize
        setLocationRelativeTo(null);//Centra pantalla
            setLayout(null); // Libre seleccion de tamaño
        getContentPane().setBackground(Color.decode("#FFFFFF"));//Colocamos fondo blanco
        modelTblDisponibles=(DefaultTableModel)tblDisponibles.getModel();
        modelTblActividad=(DefaultTableModel)tblActividad.getModel();
        try{
            Bdd baseDatos= new Bdd();
            st = baseDatos.con.createStatement();
            rs=st.executeQuery("SELECT idRol,rol FROM rolesProyecto WHERE eliminado=0");
            while(rs.next())
                cbRol.addItem(new Item(rs.getInt("idRol"),rs.getString("rol")));
            rs=st.executeQuery("SELECT count(*) FROM Usuarios");
            rs.next();
            idProgramador = new int[rs.getInt("count(*)")];
            idRol = new int[rs.getInt("count(*)")];
            estadoProgramador = new boolean[rs.getInt("count(*)")];
            estadoProgramadorOriginal=new boolean[rs.getInt("count(*)")];
            movimiento=new boolean[rs.getInt("count(*)")];
            nombreProgramador=new String[rs.getInt("count(*)")];
            apellidoProgramador=new String[rs.getInt("count(*)")];
            rol = new String[rs.getInt("count(*)")];
            
            rs=st.executeQuery("SELECT idUsuario,nombre,apellido FROM Usuarios");
            int i=0;
            while(rs.next())
            {
                idProgramador[i]=rs.getInt("idUsuario");
                nombreProgramador[i]=rs.getString("nombre");
                apellidoProgramador[i]=rs.getString("apellido");
                estadoProgramador[i]=false;
                estadoProgramadorOriginal[i]=false;
                movimiento[i]=false;
                i++;
            } 
              //PERMISOS (SeguridadProyectos(IDMODULO,IDUSUARIO,CONNECTION,AGREGAR[],MODIFICAR[],ELIMINAR[])
               Component[] agregar={btnAgregar};
               Component[] eliminar={btnSacar};
               Component[] modificar=null;
               SeguridadProyectos Seg=new SeguridadProyectos(2,session,agregar,modificar,eliminar);
               // Fin PERMISOS
            mostrarProgramadores();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error en BDD: "+e.toString());
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDisponibles = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblActividad = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnSacar = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        cbRol = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblDisponibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDisponibles);

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel6.setText("Usuarios en el Proyecto:");

        tblActividad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido", "Rol"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblActividad);

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel7.setText("Usuarios Disponibles:");

        btnAgregar.setText("Agregar>");
        btnAgregar.setToolTipText(" Agregar usuario a la actividad.");
        btnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarMouseClicked(evt);
            }
        });
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnSacar.setText("<Sacar");
        btnSacar.setToolTipText("Sacar usuario de la Actividad.");
        btnSacar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSacarMouseClicked(evt);
            }
        });

        btnAtras.setBackground(new java.awt.Color(255, 255, 255));
        btnAtras.setForeground(new java.awt.Color(255, 255, 255));
        btnAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/beanmanager/recursos/imagenes/backtousers.png"))); // NOI18N
        btnAtras.setToolTipText("Menu Proyectos");
        btnAtras.setBorder(null);
        btnAtras.setBorderPainted(false);
        btnAtras.setFocusable(false);
        btnAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAtrasMouseClicked(evt);
            }
        });
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/beanmanager/recursos/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setToolTipText("Guardar");
        btnGuardar.setBorderPainted(false);
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGuardarMouseClicked(evt);
            }
        });
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        cbRol.setToolTipText("Elegir un tipo");
        cbRol.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbRolItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setText("Rol:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnSacar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAgregar)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbRol, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAtras))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 324, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(198, 198, 198)))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSacar))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseClicked

       int cont=0;
       for(int i =0;i<idProgramador.length;i++)
           if(!estadoProgramador[i])
           {
                if(tblDisponibles.getSelectedRow()==cont)
                 {
                    estadoProgramador[i]=true;
                    Item pivote = (Item)cbRol.getSelectedItem();
                    rol[i]=pivote.getDescription();
                    idRol[i]=pivote.getId();
                }
                cont++;
           }
       
      ponerProgramadores();
    }//GEN-LAST:event_btnAgregarMouseClicked

    private void btnSacarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSacarMouseClicked
          int cont=0;
       for(int i =0;i<idProgramador.length;i++)
           if(estadoProgramador[i])
           {
                if(tblActividad.getSelectedRow()==cont)
                {
                    estadoProgramador[i]=false;
                    movimiento[i]=true;
                    rol[i]="";
                    idRol[i]=0;
                }
                cont++;
           }
       
       ponerProgramadores();
    }//GEN-LAST:event_btnSacarMouseClicked

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed

    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnAtrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasMouseClicked
  
        IndexProyecto Index = new IndexProyecto(session);
        Index.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnAtrasMouseClicked

    private void btnGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseClicked
   
        String nombre,fechaInicio,fechaFinal,descripcion,query= new String(),query2= new String();
        int tipoActividad;
        boolean pruebas=true;
                for(int i = 0 ; i<idProgramador.length;i++)
                {
                    if(estadoProgramador[i]!=estadoProgramadorOriginal[i]||movimiento[i])
                    {
                        if(!estadoProgramadorOriginal[i])
                             query2="INSERT INTO Integrantes(idRol,idUsuario,idProyecto,eliminado) VALUES("+idRol[i]+","+idProgramador[i]+","+idProyecto+",0);";
                        else
                             query2="UPDATE Integrantes SET eliminado=1 WHERE idUsuario="+idProgramador[i];
                        
                            
                        try{st.executeUpdate(query2);}catch(Exception e){ JOptionPane.showMessageDialog(null, "Error en BDD: "+e.toString());}
                        if(movimiento[i]&&idRol[i]>0)
                        {
                            query2="INSERT INTO Integrantes(idRol,idUsuario,idProyecto,eliminado) VALUES("+idRol[i]+","+idProgramador[i]+","+idProyecto+",0);";
                            try{st.executeUpdate(query2);}catch(Exception e){ JOptionPane.showMessageDialog(null, "Error en BDD: "+e.toString());}
                        }
                    }
                }        
                IndexProyecto Index = new IndexProyecto(session);
                Index.setVisible(true);
                this.setVisible(false);       
    }//GEN-LAST:event_btnGuardarMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cbRolItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbRolItemStateChanged

    }//GEN-LAST:event_cbRolItemStateChanged

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
            java.util.logging.Logger.getLogger(Programadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Programadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Programadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Programadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Programadores(null).setVisible(true);
            }
        });
    }

    
    public void mostrarProgramadores()
    {
        
            try{
            rs=st.executeQuery("SELECT A.idUsuario,C.idRol,C.rol FROM Usuarios A INNER JOIN Integrantes B on (A.idUsuario=B.idUsuario) INNER JOIN rolesProyecto C on (B.idRol=C.idRol) WHERE B.eliminado=0 AND B.idProyecto="+idProyecto);
  
            while(rs.next())
                for(int i =0;i<idProgramador.length;i++)
                    if(idProgramador[i]==rs.getInt("A.idUsuario"))
                    {
                       estadoProgramador[i]=true;
                       estadoProgramadorOriginal[i]=true;
                       idRol[i]=rs.getInt("C.idRol");
                       rol[i]=rs.getString("C.rol");
                    }
            }
            catch(Exception e)
            { 
                JOptionPane.showMessageDialog(null, "Error en BDD: "+e.toString());
            }
        ponerProgramadores();
           
    }
    public void ponerProgramadores()
    {
        while(modelTblDisponibles.getRowCount()>0)
            modelTblDisponibles.removeRow(modelTblDisponibles.getRowCount()-1);
        while(modelTblActividad.getRowCount()>0)
            modelTblActividad.removeRow(modelTblActividad.getRowCount()-1);
        
        for(int i =0;i<idProgramador.length;i++)
            if(estadoProgramador[i])
                modelTblActividad.insertRow(modelTblActividad.getRowCount(), new Object[]{nombreProgramador[i],apellidoProgramador[i],rol[i]});
            else
                modelTblDisponibles.insertRow(modelTblDisponibles.getRowCount(), new Object[]{nombreProgramador[i],apellidoProgramador[i],""});
    
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSacar;
    private javax.swing.JComboBox cbRol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblActividad;
    private javax.swing.JTable tblDisponibles;
    // End of variables declaration//GEN-END:variables
}
