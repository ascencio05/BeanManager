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
public class Actividades extends javax.swing.JFrame {
    int idActividad =0,idProyecto;
    String[] nombreProgramador,apellidoProgramador;
    int[] idProgramador;
    boolean[] estadoProgramador,estadoProgramadorOriginal;
    Statement st;
    ResultSet rs;
    DefaultTableModel modelTblDisponibles,modelTblActividad;
     public Usuario session;
    /**
     * Creates new form Actividades
     */
    public Actividades() {
        initComponents();
        iniciarVentana();
    }
    public Actividades(Usuario session,int idProyecto) {
        this.session=session;
        this.idProyecto=idProyecto;
        initComponents();
        iniciarVentana();
    }
    public Actividades(Usuario session,int idProyecto,int idActividad) {
        this.session=session;
        this.idProyecto=idProyecto;
        this.idActividad=idActividad;
        initComponents();
        iniciarVentana();
        try{
            
            rs=st.executeQuery("SELECT idTipo,titulo,descripcion,fechaInicio,fechaFinal FROM Actividades WHERE idActividad="+idActividad);
            rs.next();
            txtNombre.setText(rs.getString("titulo"));
            txtDescripcion.setText(rs.getString("descripcion"));
            txtFechaInicio.setText(rs.getString("fechaInicio").replace("-", ""));
            txtFechaFinal.setText(rs.getString("fechaFinal").replace("-", ""));
            for(int i=0;i<cbTipo.getComponentCount();i++ )
            {
                Item Pivote= (Item)cbTipo.getItemAt(i);
                if(Pivote.getId()==rs.getInt("idTipo"))
                    cbTipo.setSelectedIndex(i);
            }
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error en BDD: "+e.toString());
        }
        
        
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
            rs=st.executeQuery("SELECT idTipo,tipo FROM TiposActividad WHERE eliminado=0");
            while(rs.next())
                cbTipo.addItem(new Item(rs.getInt("idTipo"),rs.getString("tipo")));
            
            rs=st.executeQuery("SELECT count(*) FROM Usuarios A INNER JOIN Integrantes B on (A.idUsuario=B.idUsuario) WHERE B.eliminado=0 AND B.idProyecto="+idProyecto);
            rs.next();
            idProgramador = new int[rs.getInt("count(*)")];
            estadoProgramador = new boolean[rs.getInt("count(*)")];
            estadoProgramadorOriginal=new boolean[rs.getInt("count(*)")];
            nombreProgramador=new String[rs.getInt("count(*)")];
            apellidoProgramador=new String[rs.getInt("count(*)")];
            
            rs=st.executeQuery("SELECT A.idUsuario,A.nombre,A.apellido FROM Usuarios A INNER JOIN Integrantes B on (A.idUsuario=B.idUsuario) WHERE B.eliminado=0 AND B.idProyecto="+idProyecto);
            int i=0;
            while(rs.next())
            {
                idProgramador[i]=rs.getInt("A.idUsuario");
                nombreProgramador[i]=rs.getString("A.nombre");
                apellidoProgramador[i]=rs.getString("A.apellido");
                estadoProgramador[i]=false;
                estadoProgramadorOriginal[i]=false;

                i++;
            } 
             //PERMISOS (SeguridadProyectos(IDMODULO,IDUSUARIO,CONNECTION,AGREGAR[],MODIFICAR[],ELIMINAR[])
               Component[] agregar={btnAgregar};
               Component[] eliminar={btnSacar};
               Component[] modificar=null;
               SeguridadProyectos Seg;
               Seg=new SeguridadProyectos(2,session,agregar,modificar,eliminar);
              
  
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtFechaInicio = new javax.swing.JFormattedTextField();
        txtFechaFinal = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDisponibles = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblActividad = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnSacar = new javax.swing.JButton();
        cbTipo = new javax.swing.JComboBox();
        btnAtras = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setText("Tipo Actividad :");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setText("Nombre de Actividad :");
        jLabel2.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setText("Fecha Final :");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setText("Fecha Inicio :");

        try {
            txtFechaInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFechaInicio.setToolTipText("(yyyy-MM-dd)");
        txtFechaInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaInicioActionPerformed(evt);
            }
        });

        try {
            txtFechaFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFechaFinal.setToolTipText("(yyyy-MM-dd)");
        txtFechaFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaFinalActionPerformed(evt);
            }
        });

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

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel5.setText("Descripción Actividad :");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane2.setViewportView(txtDescripcion);

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel6.setText("Usuarios en la Actividad:");

        tblActividad.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblActividad);

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel7.setText("Usuarios Disponibles:");
        jLabel7.setToolTipText("");

        btnAgregar.setText("Agregar>");
        btnAgregar.setToolTipText("Agregar usuario a la actividad.");
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

        cbTipo.setToolTipText("Elegir un tipo");
        cbTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTipoItemStateChanged(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnAtras))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbTipo, 0, 196, Short.MAX_VALUE)))))
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(btnGuardar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSacar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(24, 24, 24)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(txtFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addComponent(jLabel6))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(btnAgregar)
                                .addGap(18, 18, 18)
                                .addComponent(btnSacar))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(277, 277, 277)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFechaInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaInicioActionPerformed

    private void txtFechaFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaFinalActionPerformed

    private void cbTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTipoItemStateChanged
        
    }//GEN-LAST:event_cbTipoItemStateChanged

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
                    estadoProgramador[i]=false;
                cont++;
           }
       
       ponerProgramadores();
    }//GEN-LAST:event_btnSacarMouseClicked

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed

    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnAtrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasMouseClicked
  
        IndexProyecto Index = new IndexProyecto(session);
        Index.session=this.session;
        Index.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnAtrasMouseClicked

    private void btnGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseClicked
   
        String nombre,fechaInicio,fechaFinal,descripcion,query= new String(),query2= new String();
        int tipoActividad;
        boolean pruebas=true;
        nombre=txtNombre.getText();
        fechaInicio=txtFechaInicio.getText();
        fechaFinal=txtFechaFinal.getText();
        Item pivote= (Item)cbTipo.getSelectedItem();
        tipoActividad=pivote.getId();
        descripcion=txtDescripcion.getText();
        
        int fI=Integer.parseInt(fechaInicio.replace("-", ""));
        int fF=Integer.parseInt(fechaFinal.replace("-", ""));
        if(fI>fF)
        {
            pruebas=false;
            JOptionPane.showMessageDialog(null, "La fecha final debe ser mayor a la fecha de incio.");
            txtFechaInicio.setText("");
            txtFechaFinal.setText("");
        }
        
        fI=Integer.parseInt(fechaInicio.substring(5,7));
        fF=Integer.parseInt(fechaFinal.substring(5,7));
        
        if((fI<1||fI>12||fF<1||fF>12)&&pruebas)
        {
            pruebas=false;
            JOptionPane.showMessageDialog(null, "(yy-MM-dd) Meses solo del 1 al 12");
            txtFechaInicio.setText("");
            txtFechaFinal.setText("");
        }
        
        int fIDia=Integer.parseInt(fechaInicio.substring(8,10));
        int fFDia=Integer.parseInt(fechaFinal.substring(8,10));

            if(fI==2&&fIDia>28&&pruebas)
            {
                pruebas=false;
                JOptionPane.showMessageDialog(null, "(yy-MM-dd) Febrero solo llega  28 dias");
                txtFechaInicio.setText("");
            }
            if(fF==2&&fFDia>28&&pruebas)
            {
                pruebas=false;
                JOptionPane.showMessageDialog(null, "(yy-MM-dd) Febrero solo llega  28 dias");
                txtFechaFinal.setText("");
            }
            if((fI==4||fI==6||fI==9||fI==11)&&fIDia>30&&pruebas)
            {
                pruebas=false;
                JOptionPane.showMessageDialog(null, "(yy-MM-dd) Mes solo llega a 30 dias");
                txtFechaInicio.setText("");
            }
            if((fF==4||fF==6||fF==9||fF==11)&&fFDia>30&&pruebas)
            {
                pruebas=false;
                JOptionPane.showMessageDialog(null, "(yy-MM-dd) Mes solo llega a 30 dias");
                txtFechaFinal.setText("");
            }


        if(nombre.isEmpty()&&pruebas)
        {
            JOptionPane.showMessageDialog(null, "Debe colocar un titulo a la actividad");
                pruebas=false;
        }
        if(pruebas)
        {
                if(idActividad<=0)
                    query="INSERT INTO Actividades(idProyecto,idTipo,titulo,descripcion,fechaInicio,fechaFinal,estado,eliminado) VALUES("+idProyecto+","+tipoActividad+",'"+nombre+"','"+descripcion+"','"+fechaInicio+"','"+fechaFinal+"',0,0)";
                else
                    query="UPDATE Actividades SET idTipo="+tipoActividad+", titulo='"+nombre+"',descripcion='"+descripcion+"',fechaInicio='"+fechaInicio+"',fechaFinal='"+fechaFinal+"' WHERE idActividad="+idActividad;
                try{ st.executeUpdate(query);}catch(Exception e){ JOptionPane.showMessageDialog(null, "Error en BDD: "+e.toString());}

                if(idActividad<=0)
                {
                    try
                    {
                        rs=st.executeQuery("SELECT MAX(idActividad) FROM Actividades");
                        rs.next();
                        idActividad=rs.getInt("MAX(idActividad)");
                    }
                    catch(Exception e)
                    { JOptionPane.showMessageDialog(null, "Error en BDD: "+e.toString());}
                }

                for(int i = 0 ; i<idProgramador.length;i++)
                {
                    if(estadoProgramador[i]!=estadoProgramadorOriginal[i])
                    {
                        if(!estadoProgramadorOriginal[i])
                             query2="INSERT INTO ActividadesxUsuarios(idActividad,idUsuario) VALUES("+idActividad+","+idProgramador[i]+");";
                        else
                             query2="UPDATE ActividadesxUsuarios SET eliminado=1 WHERE idUsuario="+idProgramador[i];
                        try{st.executeUpdate(query2);}catch(Exception e){ JOptionPane.showMessageDialog(null, "Error en BDD: "+e.toString());}
                    }
                }        
                        IndexProyecto Index = new IndexProyecto(session);
                        Index.session=this.session;
                        Index.setVisible(true);
                        this.setVisible(false);       
        
        }
    }//GEN-LAST:event_btnGuardarMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

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
            java.util.logging.Logger.getLogger(Actividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Actividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Actividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Actividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Actividades().setVisible(true);
            }
        });
    }

    
    public void mostrarProgramadores()
    {
        
        if(idActividad>0)
            try{
            rs=st.executeQuery("SELECT idUsuario FROM ActividadesxUsuarios WHERE eliminado=0 AND idActividad="+idActividad);
            
            while(rs.next())
                for(int i =0;i<idProgramador.length;i++)
                    if(idProgramador[i]==rs.getInt("idUsuario"))
                    {
                       estadoProgramador[i]=true;
                       estadoProgramadorOriginal[i]=true;
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
                modelTblActividad.insertRow(modelTblActividad.getRowCount(), new Object[]{nombreProgramador[i],apellidoProgramador[i]});
            else
                modelTblDisponibles.insertRow(modelTblDisponibles.getRowCount(), new Object[]{nombreProgramador[i],apellidoProgramador[i]});
    
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSacar;
    private javax.swing.JComboBox cbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblActividad;
    private javax.swing.JTable tblDisponibles;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JFormattedTextField txtFechaFinal;
    private javax.swing.JFormattedTextField txtFechaInicio;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
