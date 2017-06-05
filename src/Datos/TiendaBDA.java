/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.Tienda;
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
public class TiendaBDA {

    BDA bda = new BDA();
    Connection conn;

    public List<Tienda> listarTiendas() throws SQLException {
        conn = bda.conectar();

        List<Tienda> listaTiendas = new ArrayList<>();

        String consulta = "SELECT * FROM tienda";
        PreparedStatement ps = conn.prepareCall(consulta);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int idTienda = rs.getInt("idTienda");
            String pueblo = rs.getString("pueblo");
            String direccion = rs.getString("direccion");
            String telefono = rs.getString("telefono");
            String codPostal = rs.getString("codPostal");

            Tienda tienda = new Tienda(idTienda, pueblo, direccion, telefono, codPostal);
            listaTiendas.add(tienda);
        }
        bda.desconectar();
        return listaTiendas;
    }

    public boolean consultarIDtienda(int idtienda) throws SQLException {
        conn = bda.conectar();
        boolean existe = false;

        for (Tienda velementos : listarTiendas()) {

            if (idtienda == velementos.getIdTienda()) {
                existe = true;

            }
        }
        bda.desconectar();
        return existe;
    }

    public int insertarTienda(String pueblo, String direccion, String telefono, String codPostal) throws SQLException {
        conn = bda.conectar();
        int numFilas;
        String consulta = "INSERT INTO tienda(pueblo,direccion,telefono,codPostal) values (?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(consulta);

        ps.setString(1, pueblo);
        ps.setString(2, direccion);
        ps.setString(3, telefono);
        ps.setString(4, codPostal);
        numFilas = ps.executeUpdate();
        bda.desconectar();
        return numFilas;

    }

    public int cambiarTiendas(int idTienda, String pueblo, String direccion, String telefono, String codPostal) throws SQLException {
        conn = bda.conectar();
        int numFilas;
        String consulta = "UPDATE tienda SET pueblo=?,direccion=?,telefono=?,codPostal=? WHERE idTienda =?";
        PreparedStatement ps = conn.prepareStatement(consulta);
        ps.setString(1, pueblo);
        ps.setString(2, direccion);
        ps.setString(3, telefono);
        ps.setString(4, codPostal);
        ps.setInt(5, idTienda);

        numFilas = ps.executeUpdate();
        bda.desconectar();
        return numFilas;

    }

    public int borrarTienda(int idTienda) throws SQLException {
        conn = bda.conectar();
        int numFilas;
        String consulta = " DELETE FROM tienda WHERE idTienda = ?";
        PreparedStatement ps = conn.prepareStatement(consulta);
        ps.setInt(1, idTienda);

        numFilas = ps.executeUpdate();
        bda.desconectar();
        return numFilas;

    }
}
