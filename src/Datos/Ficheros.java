/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.Trabajador;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author Borja
 */
public class Ficheros {
    
    
    
    public String generarInformacion(File archivo,ObservableList<Trabajador> listaTrabajadores) throws SQLException {
        String devuelve = "";
        try (BufferedWriter out = Files.newBufferedWriter(archivo.toPath(), StandardOpenOption.CREATE_NEW)) {
            out.write("ID"+"\t"+"NOMBRE"+"\t"+"PUESTO");
            out.newLine();
            out.write("---------------------------");
            out.newLine();
            for (int i = 0; i < listaTrabajadores.size(); i++) {
                out.write(listaTrabajadores.get(i).toString());
                out.newLine();
            }
        } catch (IOException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error ");
            alerta.setHeaderText("Error al exportar trabajadores");
            alerta.showAndWait();
        }

        return devuelve;
    }
    
    public List<String> cargaPuesto(Path archivo) {
        
        List<String> listaPuestos = new ArrayList<>();
        String puesto;
        String nPuesto;

        try (Stream<String> datos = Files.lines(archivo)) {
            Iterator<String> it = datos.iterator();
            while (it.hasNext()) {
                puesto = it.next();
                String[] trozo = puesto.split(",");
                nPuesto = trozo[0];
                listaPuestos.add(nPuesto);
            }

        } catch (IOException ex) {

        }
        return listaPuestos;
    }
    
    
}
