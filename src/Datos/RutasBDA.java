/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.Ruta;
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
public class RutasBDA {
    BDA bda = new BDA();
    Connection conn;
    
    public void insertarRuta( Double kilometros, Integer minutos, Double gastos,String paradas) throws SQLException {
        conn = bda.conectar();
        String consulta = "INSERT INTO rutas(kilometros,Minutos,Gastos,Paradas) values (?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(consulta);
        
        ps.setDouble(1, kilometros);
        ps.setInt(2, minutos);
        ps.setDouble(3, gastos);
        ps.setString(4, paradas);
        bda.desconectar();
        ps.executeUpdate();

    }
    public List<Ruta> listarRutas() throws SQLException {
        conn = bda.conectar();
        List<Ruta> listaRutas = new ArrayList<>();
        String consulta = "SELECT * FROM rutas";
        PreparedStatement ps = conn.prepareStatement(consulta);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("idRuta");
            double kilometros = rs.getDouble("kilometros");
            int minutos = rs.getInt("Minutos");
            double gastos = rs.getDouble("Gastos");
            String parada=rs.getString("Paradas");
            Ruta r = new Ruta(id, kilometros, minutos, gastos,parada);
            listaRutas.add(r);
        }
        bda.desconectar();
        return listaRutas;
    }

    public int borrarRuta(int id) throws SQLException {
        conn = bda.conectar();
        Ruta r = null;
        String consulta = "DELETE FROM rutas WHERE idRuta=?";
        PreparedStatement ps = conn.prepareStatement(consulta);
        ps.setInt(1, id);
        int id1 = ps.executeUpdate();
        bda.desconectar();
        return id1;
    }

    public int modificarRuta(Ruta r) throws SQLException {
        conn = bda.conectar();
        String consulta = "UPDATE rutas set kilometros=?,Minutos=?,Gastos=?,Paradas=? where idRuta=?";
        PreparedStatement ps = conn.prepareStatement(consulta);
        ps.setDouble(1, r.getKilometros());
        ps.setInt(2, r.getMinutos());
        ps.setDouble(3, r.getGastos());
        ps.setInt(4, r.getId());
        ps.setString(5, r.getParadas());
        int filas = ps.executeUpdate();
        bda.desconectar();
        return filas;

    }
}
