/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.Trabajador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Borja
 */
public class TrabajadorBDA {

    BDA bda = new BDA();
    Connection conn;
    List<Trabajador> listaTrabajadores = new ArrayList<>();

    public List<Trabajador> consultar(String dni) throws SQLException {
        conn = bda.conectar();
        List<Trabajador> lista = new ArrayList<>();
        Trabajador t = null;
        String consulta = "SELECT * FROM trabajadores";
        PreparedStatement ps = conn.prepareCall(consulta);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String dni1 = rs.getString("dniNie");
            String nombre = rs.getString("contraseña");
            t = new Trabajador(dni1, nombre);
            lista.add(t);
        }
        bda.desconectar();
        return lista;
    }
    
    public String  devolverpwd(String dni) throws SQLException {
        String pwd=null;
        conn = bda.conectar();
        List<Trabajador> lista = new ArrayList<>();
        Trabajador t = null;
        String consulta = "SELECT * FROM trabajadores where dniNie=?";
        PreparedStatement ps = conn.prepareStatement(consulta);
        ps.setString(1, dni);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
//            String dni1 = rs.getString("dniNie");
            pwd = rs.getString("contraseña");
//            t = new Trabajador(dni1, nombre);
//            lista.add(t);
        }
        bda.desconectar();
        return pwd;
    }
    
    public List<Trabajador> listarTrabajadores() throws SQLException {
        conn = bda.conectar();
        String consulta = "SELECT * FROM trabajadores";
        PreparedStatement ps = conn.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id1 = rs.getInt("idTrabajador");
            String nombre = rs.getNString("nombre");
            String puesto = rs.getNString("puesto");
            String dni = rs.getNString("dniNie");
            Double salario = rs.getDouble("salario");
            String contraseña = rs.getNString("contraseña");
            int idTienda = rs.getInt("idTienda");
            int idHorario = rs.getInt("idHorario");
            int idRuta=rs.getInt("idRuta");
            Trabajador t = new Trabajador(id1, nombre, puesto, dni, salario, contraseña, idTienda, idHorario, idRuta);
            listaTrabajadores.add(t);
        }
        bda.desconectar();
        return listaTrabajadores;
    }
    
    public void insertarTrabajador(String nombre, String puesto, String dniNie, Double salario, String contraseña, int idTienda, int idHorario,int idRuta) throws SQLException {
        conn = bda.conectar();
        String consulta = "INSERT INTO trabajadores (nombre, puesto, dniNie, salario, contraseña, idTienda, idHorario,idRuta, IRPF) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(consulta);
        
        ps.setString(1, nombre);
        ps.setString(2, puesto);
        ps.setString(3, dniNie);
        ps.setDouble(4, salario);
        ps.setString(5, contraseña);
        ps.setInt(6, idTienda);
        ps.setInt(7, idHorario);
        ps.setInt(8, idRuta);
        ps.setDouble(9, 10);
        ps.executeUpdate();
        bda.desconectar();

    }
    
    public int modificarTrabajador(Trabajador t) throws SQLException {
        conn = bda.conectar();
        String consulta = "UPDATE trabajadores set nombre=?,puesto=?, dniNie=?,salario=?, contraseña=?, idTienda=?, idHorario=?, idRuta=? where idTrabajador=?";
        PreparedStatement ps = conn.prepareStatement(consulta);
        ps.setString(1, t.getNombre());
        ps.setString(2, t.getPuesto());
        ps.setString(3,t.getDNI());
        ps.setDouble(4, t.getSalario());
        ps.setString(5, t.getContraseña());
        ps.setInt(6, t.getIdTienda());
        ps.setInt(7, t.getIdHorario());
        ps.setInt(8, t.getIdRuta());
        ps.setInt(9, t.getIdTrabajador());
        
        int filas = ps.executeUpdate();
        bda.desconectar();
        return filas;
    }
    
    public int borrarTrabajador(int id) throws SQLException {
        conn = bda.conectar();
        Trabajador r = null;
        String consulta = "DELETE FROM trabajadores WHERE idTrabajador=?";
        PreparedStatement ps = conn.prepareStatement(consulta);
        ps.setInt(1, id);
        int id1 = ps.executeUpdate();
        bda.desconectar();
        return id1;
    }

}
