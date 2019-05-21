package DAO;

import Modelo.pacienteDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {
    
    public List<pacienteDTO> select() {
        List<pacienteDTO> lista = new ArrayList<pacienteDTO>();
        try{
            Connection miConexion = DriverManager.getConnection("jdbc:derby://localhost:1527/RVDHOSPITAL", "DOO123", "DOO123");
            Statement st = miConexion.createStatement();
            String query = "SELECT * FROM PACIENTES";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                pacienteDTO paciente = new pacienteDTO();
                paciente.setId(rs.getInt("ID"));
                paciente.setNombre(rs.getString("NOMBRE"));
                paciente.setTelefono(rs.getInt("TELEFONO"));
               // System.out.println(usuario.getId() + " " + usuario.getNombre());
                lista.add(paciente);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return lista;
    }

    public void insert(pacienteDTO paciente) {
        try{
            Connection miConexion = DriverManager.getConnection("jdbc:derby://localhost:1527/RVDHOSPITAL", "DOO123", "DOO123");
            //Statement st = miConexion.createStatement();
            String query = "INSERT INTO PACIENTES(DOCTOR, MEDICAMENTO1, MEDICAMENTO2, MEDICAMENTO3, MEDICAMENTO4) values (?, ?, ?, ?, ?) ";
            PreparedStatement ps = miConexion.prepareStatement(query);
            ps.setString(1, paciente.getDoctor());
            ps.setString(2, paciente.getMedicamento1());
            ps.setString(3, paciente.getMedicamento2());
            ps.setString(4, paciente.getMedicamento3());
            ps.setString(5, paciente.getMedicamento4());
            ps.executeUpdate();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void update(pacienteDTO paciente) {
        try{
            Connection miConexion = DriverManager.getConnection("jdbc:derby://localhost:1527/RVDHOSPITAL", "DOO123", "DOO123");
            //Statement st = miConexion.createStatement();
            String query = "UPDATE PACIENTES SET DOCTOR = ?, Medicamento1 = ?, MEDICAMENTO2 = ?, MEDICAMENTO3 = ?, MEDICAMENTO4 = ? where DOCTOR = ? ";
            PreparedStatement ps = miConexion.prepareStatement(query);
            ps.setString(1, paciente.getDoctor());
            ps.setString(2, paciente.getMedicamento1());
            ps.setString(3, paciente.getMedicamento2());
            ps.setString(4, paciente.getMedicamento3());
            ps.setString(5, paciente.getMedicamento4());
            ps.setString(6, paciente.getDoctor());
            ps.executeUpdate();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        try{
            Connection miConexion = DriverManager.getConnection("jdbc:derby://localhost:1527/RVDHOSPITAL", "DOO123", "DOO123");
            Statement st = miConexion.createStatement();
            String query;
            if(id == 0){
              query = "DELETE FROM USUARIO WHERE ID > 0";   
            }else{
             query = "DELETE FROM USUARIO WHERE ID ="+id;
            }
            st.executeUpdate(query);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
