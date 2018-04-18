/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectopostgres;
import java.sql.*; //Clases de conexión a la BD
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane; //Clase para manejo de formas
import javax.swing.table.DefaultTableModel; //Clase para el manejo de JTable
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author rafael
 */

public class FormAddSale extends javax.swing.JFrame {

    /**
     * Creates new form FormAddSale
     */
    private FormSeekClient formSeekClient;
    private FormSeekCar formSeekCar;
    
    private Connection connection = null;
    private String usuario;
    private String contra;
    
    private String idCliente = "";
    private String nombreCliente = "";
    private String domicilio = "";
    private String telefono = "";
    private String numeroSerie = "";
    private String nombreAuto = "";
    private String marca = "";
    private String modelo = "";
    private String precioAuto = "";
    
    public FormAddSale() {
        initComponents();
        formSeekClient = new FormSeekClient(this);
        formSeekCar = new FormSeekCar(this);
        usuario = "superuser";
        contra = "root";
    }

    public void setIdCliente(String id)
    {
        idCliente = id;
    }
    
    public void setNombreCliente(String nom)
    {
        nombreCliente = "'" + nom + "'";
        lNombreCliente.setText("Nombre: " + nom);
    }
    
    public void setDomicilio(String dom)
    {
        domicilio = "'" + dom + "'";
        lDomicilio.setText("Domicilio: " + dom);
    }
    
    public void setTelefono(String tel)
    {
        telefono = "'" + tel + "'";
        lTelefono.setText("Telefono: " + tel);
    }
    
    public void setNumeroSerie(String nS)
    {
        numeroSerie = "'" + nS + "'";
        lNoSerie.setText("Numero de Serie: " + nS);
    }
    
    public void setNombreAuto(String nom)
    {
        nombreAuto = "'" + nom + "'";
        lNombreAuto.setText("Nombre: " + nom);
    }
    
    public void setMarca(String marc)
    {
        marca = "'" + marc + "'";
        lMarca.setText("Marca: " + marc);
    }
    
    public void setModelo(String mod)
    {
        modelo = "'" + mod + "'";
        lModelo.setText("Modelo: " + mod);
    }
    
    public void setPrecioAuto(String prec)
    {
        precioAuto = prec;
        tbFinalPrice.setText(1.2 * Float.parseFloat(prec) + "");
    }
    
    public void addSale() throws SQLException
    {
        openConnection(usuario,contra);
        
        if(connection!=null)
        {
            try{
                Statement st;
                st = connection.createStatement();
                String query;
                Calendar cal = Calendar.getInstance(); 
                cal.getTime(); 
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String fecha = sdf.format(cal.getTime());
                
                if(cbPaymentType.getSelectedIndex() == 1)
                {
                   query = "INSERT INTO \"venta\" (idcliente, idagente, noserie, fechaventa, montototal, noexhibiciones, adeudo, tipoventa) VALUES (" + idCliente + ", " + "1, " + numeroSerie + ", '" + fecha + "', " + tbFinalPrice.getText() + ", " + nudExhibitionNumber.getValue().toString() + ", 1, 'Credito')";
                }
                else
                {
                    query = "INSERT INTO \"venta\" (idcliente, idagente, noserie, fechaventa, montototal, noexhibiciones, adeudo, tipoventa) VALUES (" + idCliente + ", " + "1, " + numeroSerie + ", '" + fecha + "', " + tbFinalPrice.getText() + ", 1, 1, 'Contado')";
                }
                st.executeUpdate(query);
                st.close();
                closeConnection();//Cierra la conexión
            }catch(Exception e)
            {
                JOptionPane.showMessageDialog(rootPane,e.getMessage());
            }
        }
    }
        
    public void openConnection(String u, String ps)
    {
        if (connection != null)
            return;
        
        String url = "jdbc:postgresql://localhost:5432/Autos";
        try
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, u, ps);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void closeConnection()
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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lNombreCliente = new javax.swing.JLabel();
        lDomicilio = new javax.swing.JLabel();
        lTelefono = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lNoSerie = new javax.swing.JLabel();
        lNombreAuto = new javax.swing.JLabel();
        lMarca = new javax.swing.JLabel();
        lModelo = new javax.swing.JLabel();
        bSelectClient = new javax.swing.JButton();
        bSelectCar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbPaymentType = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbFinalPrice = new javax.swing.JTextPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nudInitialCharge = new javax.swing.JSpinner();
        nudExhibitionNumber = new javax.swing.JSpinner();
        bCancel = new javax.swing.JButton();
        bAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Añadir Compra");
        setMaximumSize(new java.awt.Dimension(462, 460));
        setMinimumSize(new java.awt.Dimension(462, 460));
        setResizable(false);
        setSize(new java.awt.Dimension(462, 460));

        jLabel1.setText("Cliente");

        lNombreCliente.setText("Nombre:");

        lDomicilio.setText("Domicilio:");

        lTelefono.setText("Telefono:");

        jLabel2.setText("Auto");

        lNoSerie.setText("Numero de Serie:");

        lNombreAuto.setText("Nombre:");

        lMarca.setText("Marca:");

        lModelo.setText("Modelo:");

        bSelectClient.setText("Seleccionar");
        bSelectClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSelectClientActionPerformed(evt);
            }
        });

        bSelectCar.setText("Seleccionar");
        bSelectCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSelectCarActionPerformed(evt);
            }
        });

        jLabel3.setText("Tipo de Pago:");

        jLabel4.setText("Precio Final:");

        cbPaymentType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Contado", "Credito" }));
        cbPaymentType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPaymentTypeActionPerformed(evt);
            }
        });

        tbFinalPrice.setEditable(false);
        jScrollPane1.setViewportView(tbFinalPrice);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Credito", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel5.setText("Monto Inicial:");

        jLabel6.setText("Numero de Exhibiciones:");

        nudInitialCharge.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 1.0f));

        nudExhibitionNumber.setModel(new javax.swing.SpinnerNumberModel(1, 1, 24, 1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nudExhibitionNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nudInitialCharge, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(nudInitialCharge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(nudExhibitionNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        bCancel.setText("Cancelar");
        bCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelActionPerformed(evt);
            }
        });

        bAdd.setText("Agregar");
        bAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lNombreCliente)
                                    .addComponent(lNombreAuto)
                                    .addComponent(lDomicilio))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lTelefono)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bSelectClient))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lModelo)
                                            .addComponent(lMarca)
                                            .addComponent(lNoSerie))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bSelectCar)))
                                .addGap(29, 29, 29))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbPaymentType, 0, 120, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(bCancel)
                        .addGap(58, 58, 58)
                        .addComponent(bAdd))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lNombreCliente))
                .addGap(8, 8, 8)
                .addComponent(lDomicilio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lTelefono)
                    .addComponent(bSelectClient))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lNoSerie))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lNombreAuto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lMarca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lModelo)
                    .addComponent(bSelectCar))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbPaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bCancel)
                    .addComponent(bAdd))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbPaymentTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPaymentTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPaymentTypeActionPerformed

    private void bCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_bCancelActionPerformed

    private void bSelectClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSelectClientActionPerformed
        // TODO add your handling code here:
        formSeekClient.setVisible(true);
    }//GEN-LAST:event_bSelectClientActionPerformed

    private void bSelectCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSelectCarActionPerformed
        // TODO add your handling code here:
        formSeekCar.setVisible(true);
    }//GEN-LAST:event_bSelectCarActionPerformed

    private void bAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddActionPerformed
        try {
            addSale();
            this.dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane,ex.getMessage());
        }
    }//GEN-LAST:event_bAddActionPerformed

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
            java.util.logging.Logger.getLogger(FormAddSale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormAddSale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormAddSale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormAddSale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormAddSale().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAdd;
    private javax.swing.JButton bCancel;
    private javax.swing.JButton bSelectCar;
    private javax.swing.JButton bSelectClient;
    private javax.swing.JComboBox<String> cbPaymentType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lDomicilio;
    private javax.swing.JLabel lMarca;
    private javax.swing.JLabel lModelo;
    private javax.swing.JLabel lNoSerie;
    private javax.swing.JLabel lNombreAuto;
    private javax.swing.JLabel lNombreCliente;
    private javax.swing.JLabel lTelefono;
    private javax.swing.JSpinner nudExhibitionNumber;
    private javax.swing.JSpinner nudInitialCharge;
    private javax.swing.JTextPane tbFinalPrice;
    // End of variables declaration//GEN-END:variables
}
