/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.Incidencias;
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
public class IncidenciasBDA {

    BDA bda = new BDA();
    Connection conn;

    public List<Incidencias> listarIncidencias() throws SQLException {
        List<Incidencias> lista = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        String consulta;

        conn = bda.conectar();
        if (conn != null) {

            consulta = "SELECT * FROM incidencias";
            ps = conn.prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();

            while (rs.next()) {

                int idIncidencias = rs.getInt("idIncidencias");
                String descripcion = rs.getString("Descripcion");
                int idTrabajador = rs.getInt("idTrabajador");
                int idTienda = rs.getInt("idTienda");
                String fecha = rs.getString("fecha");

                Incidencias incidencia = new Incidencias(idIncidencias, descripcion, idTrabajador, idTienda, fecha);
                lista.add(incidencia);
            }
        }
        bda.desconectar();
        return lista;
    }

    public boolean consultarIDincidencia(int incidencia) throws SQLException {
        conn = bda.conectar();
        boolean existe = false;

        for (Incidencias velementos : listarIncidencias()) {

            if (incidencia == velementos.getIdIncidencias()) {
                existe = true;

            }
        }
        bda.desconectar();
        return existe;
    }

    public int insertarIncidencia(int idTrabajador, int idTienda, String fecha, String descripcion) throws SQLException {
        conn = bda.conectar();
        int numFilas;
        String consulta = "INSERT INTO Incidencias(idTrabajador,idtienda,fecha ,descripcion) values (?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(consulta);

        ps.setDouble(1, idTrabajador);
        ps.setInt(2, idTienda);
        ps.setString(3, fecha);
        ps.setString(4, descripcion);
        numFilas = ps.executeUpdate();
        bda.desconectar();
        return numFilas;

    }

    public int borrarIncidencia(int idIncidencia) throws SQLException {
        conn = bda.conectar();
        int numFilas;
        String consulta = " DELETE FROM Incidencias WHERE idIncidencias = ?";
        PreparedStatement ps = conn.prepareStatement(consulta);
        ps.setInt(1, idIncidencia);

        numFilas = ps.executeUpdate();
        bda.desconectar();
        return numFilas;

    }

    public int cambiarIncidencia(int idIncidencias, int idTrabajador, int idTienda, String fecha, String descripcion) throws SQLException {
        conn = bda.conectar();
        int numFilas;
        String consulta = "UPDATE Incidencias SET descripcion=?,idTienda=?,idTrabajador=?,fecha=? WHERE idIncidencias =?";
        PreparedStatement ps = conn.prepareStatement(consulta);
        ps.setInt(5, idIncidencias);
        ps.setInt(3, idTrabajador);
        ps.setInt(2, idTienda);
        ps.setString(1, descripcion);
        ps.setString(4, fecha);

        numFilas = ps.executeUpdate();
        bda.desconectar();
        return numFilas;

    }
}
