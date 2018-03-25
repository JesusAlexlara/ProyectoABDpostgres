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
import javax.swing.JTable;

/**
 *
 * @author Gerardo Arturo Enriquez Capetillo
 * 24/03/2018
 */
public class PostgresConnection 
{
    private Connection connection = null;
    private String user, password, database;
    
    public PostgresConnection(String user, String password)
    {
        this.user = user;
        this.password = password;
        this.database = "Autos";
        openConnection();
    }
    
    /**
     * Metodo para abrir la conexion con la base de datos.
     */
    private void openConnection()
    {
        if (connection != null)
            return;
        
        String url = "jdbc:postgresql://localhost:5432/" + database;
        try
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Conexión abierta");
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Método para cerrar la conexion con la base de datos.
     */
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
    
    /**
     * Método para insertar un nuevo registro
     * @param query - Consulta SQL.
     * @return - Regresa true si se pudo hacer la insercion y false si no.
     * @throws SQLException 
     */
    public Boolean insertInto(String query) throws SQLException
    {
        Statement st;
        Boolean answer = false;
        openConnection();
        
        if(connection != null)
        {
            try{               
                st = connection.createStatement();
                System.out.println(query);
                st.executeUpdate(query);
                st.close();
                answer = true;
            }catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        closeConnection();
        return answer;
    }
    
    public void cargaJTable(String table, JTable jTable)
    {
        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsm;
        DefaultTableModel dtm;
        try{
            ps = connection.prepareStatement("SELECT * FROM \"" + table + "\"");
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
           
            dtm = (DefaultTableModel)jTable.getModel();
            dtm.setRowCount(0);
            for(int i = 0; i < data.size(); i ++)
            {
                dtm.addRow(data.get(i));
            }
            rs.close();
            ps.close();
           
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
