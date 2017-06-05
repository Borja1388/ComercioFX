/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.Horario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Borja
 */
public class HorariosBDA {

    BDA bda = new BDA();
    Connection conn;

    public boolean consultarIDhorario(int idHorario) throws SQLException {
        conn = bda.conectar();
        boolean existe = false;

        for (Horario velementos : listarHorarios()) {

            if (idHorario == velementos.getIdHorarios()) {
                existe = true;

            }
        }
        bda.desconectar();
        return existe;
    }

    public ArrayList<Horario> listarHorarios() throws SQLException {
        conn = bda.conectar();
        int id;
        String horaEntrada;
        String horaSalida;
        String fechaInicio;
        String fechasalida;

        ArrayList<Horario> listaHorarios = new ArrayList<>();
        String consulta = "SELECT * FROM horarios";
        PreparedStatement ps = conn.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            id = rs.getInt("idHorario");
            horaEntrada = rs.getString("horaEntrada");
            horaSalida = rs.getString("horaSalida");
            fechaInicio = rs.getString("fechaInicio");
            fechasalida = rs.getString("fechaFin");

            Horario horarios = new Horario(id, horaEntrada, horaSalida, fechaInicio, fechasalida);
            listaHorarios.add(horarios);
        }
        bda.desconectar();
        return listaHorarios;
    }
    
    public int insertarHorario( String horaEntrada, String horaSalida, String fechaInicio, String fechaFin) throws SQLException {
        conn = bda.conectar();
        int numFilas;
        String consulta = "INSERT INTO horarios(horaEntrada,horaSalida,fechaInicio,fechaFin) values (?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(consulta);
        
        ps.setString(1, horaEntrada);
        ps.setString(2, horaSalida);
        ps.setString(3, fechaInicio);
        ps.setString(4, fechaFin);
        numFilas = ps.executeUpdate();
        bda.desconectar();
        return numFilas;

    }
    
    public int cambiarHorario(int idHorario, String horaEntrada, String horaSalida, String fechaInicio, String fechaFin) throws SQLException {
        conn = bda.conectar();
        int numFilas;
        String consulta = "UPDATE horarios SET HoraEntrada=?,HoraSalida=?,fechaInicio=?,fechaFin=? WHERE idHorario = ?";
        PreparedStatement ps = conn.prepareStatement(consulta);

        ps.setString(1, horaEntrada);
        ps.setString(2, horaSalida);
        ps.setString(3, fechaInicio);
        ps.setString(4, fechaFin);
        ps.setInt(5, idHorario);

        numFilas = ps.executeUpdate();
        bda.desconectar();
        return numFilas;

    }
    
     public int borrarHorario(int idHorario) throws SQLException {
        int numFilas = 0;
        conn = bda.conectar();

        String consulta = " DELETE FROM horarios WHERE idHorario = ?";
        PreparedStatement ps;
        ps = conn.prepareStatement(consulta);
        ps.setInt(1, idHorario);

        numFilas = ps.executeUpdate();
        bda.desconectar();
        return numFilas;

    }
}
