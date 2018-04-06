/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectopostgres;

import java.sql.*;
import javax.swing.JOptionPane; 
import javax.swing.table.DefaultTableModel; 
import java.util.ArrayList; 
import javax.swing.*;
import proyectopostgres.FormSeekSupplier;

/**
 *
 * @author Gerardo Arturo Enriquez Capetillo
 * 24/03/2018
 */
public class FormBuy extends javax.swing.JFrame 
{
    private Connection connection;
    private String user, password;
    private String idSupplier;
    
    /**
     * Creates new form FormBuy
     */
    public FormBuy() {
        initComponents();
        
        connection = null;
        user = "postgres";
        password = "holamundo";
        
        idSupplier = "1";
        openConnection(user,password);
        if(connection != null)
        {
            cargaJTable("SELECT Nombre,RazonSocial,Telefono,NoSerie,FechaCompra FROM \"compra\" INNER JOIN \"proveedor\" ON (compra.idProveedor = proveedor.idProveedor)");
            closeConnection();
        }
    }

    public void openConnection(String u, String ps)
    {
        //Si ya hay una conexión solo retorna
        if (connection != null)
            return;
        
        //Cadena de Conexión a la BD
        //Importante!!!! Verifica el nombre de la BD ↓
        String url = "jdbc:postgresql://localhost:5432/Autos";
        try
        {
            Class.forName("org.postgresql.Driver"); //Driver de conexión
            //Intenta obtener la conexión con la BD
            connection = DriverManager.getConnection(url, u, ps);
            /* Puedes utilizar este código para verificar la conexión
            if (connection != null) {
                System.out.println("Conexión abierta");
            }*/
        } catch(Exception e){
            System.out.println(e.getMessage()); //Si llega a existir algún error
        }
    }
    
    private void closeConnection()
    {
        try
        {
            if(connection!=null)
            {
                connection.close();
                connection = null;      
            }
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    private void cargaJTable(String query)
    {
        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsm;
        DefaultTableModel dtm;
        try{
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            rsm = rs.getMetaData();
            ArrayList<Object[]> data = new ArrayList<>();
           
            while(rs.next())
            {
                Object[] rows = new Object[rsm.getColumnCount()];
                for(int i = 0; i < rows.length; i ++)
                {
                    rows[i] = rs.getObject(i+1);
                }
                data.add(rows);
            }
           
            dtm = (DefaultTableModel)this.jTable1.getModel();
            dtm.setRowCount(0);
            for(int i = 0; i < data.size(); i ++)
            {
                dtm.addRow(data.get(i));
            }
            rs.close();
            ps.close();
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane,e.getMessage());
            System.out.println("Error "+query);
        }
    }
    
    private String GetId(String query)
    {
        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsm;
        String id = "-1";
        
        try{
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            rsm = rs.getMetaData();                      
           
            while(rs.next())
            {
                Object[] rows = new Object[rsm.getColumnCount()];
                for(int i = 0; i < rows.length; i ++)
                    rows[i] = rs.getObject(i+1);
                id = rows[0].toString();
                break;
            }
            rs.close();
            ps.close();
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane,e.getMessage());
            System.out.println("Error "+query);
        }
        return id;
    }
    
    private void Insert(String query) throws SQLException
    {
        openConnection(user,password);
        if(connection!=null)
        {
            try{
                Statement st;
                st = connection.createStatement();
                st.executeUpdate(query);
                st.close();
                cargaJTable("SELECT Nombre,RazonSocial,Telefono,NoSerie,FechaCompra FROM \"compra\" INNER JOIN \"proveedor\" ON (compra.idProveedor = proveedor.idProveedor)");
                closeConnection();
            }catch(Exception e)
            {
                JOptionPane.showMessageDialog(rootPane,e.getMessage());
                System.out.println("Error "+query);
            }
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane3 = new javax.swing.JTextPane();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane4 = new javax.swing.JTextPane();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTabbedPane1.setToolTipText("");

        jLabel9.setText("Modelo");

        jButton2.setText("Aceptar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel10.setText("Precio");

        jLabel1.setText("Proveedor");

        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jTextPane1);
        jTextPane1.getAccessibleContext().setAccessibleName("jtextPaneSerialNumber");

        jButton1.setText("Seleccionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre:");

        jScrollPane2.setViewportView(jTextPane2);
        jTextPane2.getAccessibleContext().setAccessibleName("jtextPaneCarName");

        jLabel3.setText("Razon Social:");

        jLabel4.setText("Telefono:");

        jScrollPane3.setViewportView(jTextPane3);
        jTextPane3.getAccessibleContext().setAccessibleName("jtextPaneCarBrand");

        jLabel5.setText("Auto");

        jLabel6.setText("No. Serie");

        jScrollPane4.setViewportView(jTextPane4);
        jTextPane4.getAccessibleContext().setAccessibleName("jtextPaneCarModel");

        jLabel7.setText("Nombre");

        jLabel8.setText("Marca");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jButton2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                            .addComponent(jButton3))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel9))
                                    .addGap(18, 18, 18))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane2)
                                .addComponent(jScrollPane3)
                                .addComponent(jScrollPane4)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                .addComponent(jScrollPane1))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(14, 14, 14)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addGap(6, 6, 6))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addGap(4, 4, 4))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        jLabel9.getAccessibleContext().setAccessibleName("jLabelCarModel");
        jButton2.getAccessibleContext().setAccessibleName("jButtonDone");
        jLabel10.getAccessibleContext().setAccessibleName("jLabelCarPriece");
        jLabel1.getAccessibleContext().setAccessibleName("jLabelSupplier");
        jButton3.getAccessibleContext().setAccessibleName("jButtonCancel");
        jButton1.getAccessibleContext().setAccessibleName("jButtonSupplier");
        jLabel2.getAccessibleContext().setAccessibleName("jLabelSupplierName");
        jLabel3.getAccessibleContext().setAccessibleName("jLabelSupplierSocial");
        jLabel4.getAccessibleContext().setAccessibleName("jLabelSupplierPhone");
        jLabel5.getAccessibleContext().setAccessibleName("jLabelCar");
        jLabel6.getAccessibleContext().setAccessibleName("jLabelCarSerialNumber");
        jLabel7.getAccessibleContext().setAccessibleName("jLabelCarName");
        jLabel8.getAccessibleContext().setAccessibleName("jLabelCarBrand");
        jSpinner1.getAccessibleContext().setAccessibleName("jSpinnerCarPriece");

        jTabbedPane1.addTab("Nueva", jPanel2);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Razon Social", "Telefono", "No Serie", "FechaCompra"
            }
        ));
        jScrollPane5.setViewportView(jTable1);

        jLabel11.setText("Compras");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Ver", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Metodo para insertar una nueva compra.
     * @param idSupplier - Codigo del agente.
     * @param idAgent - Codigo del vendedor.
     * @param serialNumber - Numero de serie del auto.
     */
    private void InsertBuy(String idSupplier, String idAgent, String serialNumber)
    {
        String query = "INSERT INTO \"compra\" (idProveedor,idAgente,NoSerie) VALUES ("
                        + idSupplier+ "," + idAgent+ ",'" + serialNumber + "')";
        
        try
        {
            Insert(query);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane,e.getMessage());
            System.out.println("Error en insertbuy");
        }
    }
    
    /**
     * Metodo para insertar un nuevo auto.
     * @param name - Nombre del auto.
     * @param model - Modelo del auto.
     * @param brand - Marca del auto.
     * @param priece  - Precio del auto.
     */
    private void InsertCar(String serial,String name, String model, String brand, String priece)
    {
        String query, queryid, id = "-1"; 
                 
        queryid = "SELECT * FROM \"auto\" WHERE Modelo = '"+model+
                "' AND Marca = '"+brand+"' AND Nombre = '"+name+"'";       
        
        openConnection(user,password);
        if(connection != null)
        {
            id = GetId(queryid);
            closeConnection();
        }
        
        query = "INSERT INTO \"auto\" (Modelo,Nombre,Marca,PrecioFabrica,Cantidad) VALUES "
                + "('" + model+"','"+ name+"','"+brand+"','"+priece+"',0)";
        
        System.out.println(queryid);
        System.out.println(query);
        try
        {
            if(id.equals("-1"))
                Insert(query);    
            openConnection(user,password);
            if(connection != null)
            {
                id = GetId(queryid);
                closeConnection();
            }
            Insert("INSERT INTO \"instancia_auto\" (NoSerie,IdAuto,Bandera) VALUES ('" + serial+"',"+id+ ",'1')"); 
            System.out.println("INSERT INTO \"instancia_auto\" (NoSerie,IdAuto,Bandera) VALUES ('" + serial+"',"+id+ ",'1')");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane,e.getMessage());
            System.out.println("Error en insertcar");
        }
    }
    
    /**
     * Evento del boton "Aceptar".
     * Efectua la consulta SQL para insertar una nueva compra.
     * @param evt 
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(!jTextPane1.getText().equals("") && !jTextPane2.getText().equals("") && !jTextPane3.getText().equals("") && !jTextPane4.getText().equals(""))
        {
            InsertCar(jTextPane1.getText(),jTextPane2.getText(),jTextPane4.getText(),jTextPane3.getText(),((Integer) jSpinner1.getValue()).toString());
            InsertBuy(idSupplier, "1", jTextPane1.getText());
            jTextPane1.setText("");
            jTextPane2.setText("");
            jTextPane3.setText("");
            jTextPane4.setText("");
        }
        else JOptionPane.showMessageDialog(rootPane,"No puedes dejar campos vacios");
    }//GEN-LAST:event_jButton2ActionPerformed
    
    /**
     * Evento del boton "Seleccionar".
     * Abre una nueva ventana para mostrar todos los proveedores.
     * @param evt 
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        FormSeekSupplier fs = new FormSeekSupplier();
        fs.setVisible(true);
        fs.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jLabel2.setText("Nombre: " + fs.name);
        jLabel3.setText("Telefono: " + fs.phone);
        jLabel4.setText("Razon Social: " + fs.social);
    }//GEN-LAST:event_jButton1ActionPerformed
    
    /**
     * Evento del boton "Cancelar".
     * Cierra la forma.
     * @param evt 
     */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed
    
    /**
     * Evento para cerrar la ventana.
     * Libera la memoria ocupada.
     * @param evt 
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       dispose();
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(FormBuy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormBuy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormBuy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormBuy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormBuy().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JTextPane jTextPane3;
    private javax.swing.JTextPane jTextPane4;
    // End of variables declaration//GEN-END:variables
}
