/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.GestionTrabajadores;

import Datos.BDA;
import Datos.Ficheros;
import Datos.TrabajadorBDA;
import Modelo.Trabajador;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author daw
 */
public class GestionTrabajadoresController implements Initializable {

    BDA bda;
    List<Trabajador> listaTrab;
    Trabajador t;
    TrabajadorBDA tBDA=new TrabajadorBDA();
    ObservableList<String> listaPuestos;
    Path archivo = Paths.get("src/imagnes/Puestos.txt");
    Ficheros f=new Ficheros();
    private DirectoryChooser d1 = new DirectoryChooser();
    private ObservableList<Trabajador> listaTrabajadores;
    public void setBda(BDA bda) {
        this.bda = bda;
    }
     
     
    @FXML
    private Tab infoTrabajador;
    @FXML
    private TextField id;
    @FXML
    private TextField nombre;
    @FXML
    private ComboBox<String> puesto;
    @FXML
    private TextField dni;
    @FXML
    private TextField salario;
    @FXML
    private TextField contraseña;
    @FXML
    private Button modificarTrabajador;
    @FXML
    private Button eliminarTrabajador;
    @FXML
    private TextField idModif;
    @FXML
    private TextField idEliminar;
    @FXML
    private TextField contraseñaModif;
    @FXML
    private TextField salarioModif;
    @FXML
    private ComboBox<String> puestoModif;
    @FXML
    private TextField nombreModif;
    @FXML
    private TextField idHorario;
    @FXML
    private TextField idTienda;
    @FXML
    private TextField dniModif;
    @FXML
    private TextField idTiendaModif;
    @FXML
    private TextField idHorarioModif;
    @FXML
    private TableColumn<Trabajador, Integer> idTiendaColum;
    @FXML
    private TableColumn<Trabajador, Integer> idHorarioColum;
    @FXML
    private Button exportar;
    @FXML
    private TableColumn<Trabajador, Integer> idRutaColum;
    @FXML
    private TextField idRuta;
    @FXML
    private TextField idRutaModif;
    @FXML
    private TabPane tab;
    @FXML
    private Label contraModif;
    @FXML
    private Label rModif;
    @FXML
    private Button añadirTrabajor;
    @FXML
    private TableView<Trabajador> tableView;
    @FXML
    private TableColumn<Trabajador, Integer> idColum;
    @FXML
    private TableColumn<Trabajador, String> nombreColum;
    @FXML
    private TableColumn<Trabajador, String> puestoColum;
    @FXML
    private TableColumn<Trabajador, String> dniColum;
    @FXML
    private TableColumn<Trabajador, Double> salarioColum;
    @FXML
    private TableColumn<Trabajador, String> ContraseñaColum;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void añadirTrabajador(ActionEvent event) throws SQLException {
        Integer idRuta1;
        try {
//            Integer idTrabajador = Integer.parseInt(id.getText());
            String nombreTrabajador = nombre.getText();
            String puestoTrabajador = puesto.getValue();
            String dniNieTrabajador = dni.getText();
            Double salarioTrabajador = Double.parseDouble(salario.getText());
            String contraseñaTrabajador = contraseña.getText();
            Integer idTiendas = Integer.parseInt(idTienda.getText());
            Integer idHorarios = Integer.parseInt(idHorario.getText());
            if(idRuta.getText().equalsIgnoreCase("")){
                idRuta1=0;
            }else{
             idRuta1 = Integer.parseInt(idRuta.getText());   
            }
            

            tBDA.insertarTrabajador(nombreTrabajador, puestoTrabajador, dniNieTrabajador, salarioTrabajador, contraseñaTrabajador, idTiendas, idHorarios, idRuta1);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Trabajador");
            a.setHeaderText("El trabajador a sido introducida con exito");
            a.show();
        } catch (SQLException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Trabajador");
            a.setHeaderText("Error al insertar el trabajador : " + ex.getMessage());
            a.show();
        }
    }

    @FXML
    private void modificarTrabajador(ActionEvent event) throws SQLException {
        Trabajador t;
        int idTrabajador = Integer.parseInt(idModif.getText());
        String nombre1 = nombreModif.getText();
        String puesto1 = puestoModif.getValue();
        String dni1 = dniModif.getText();
        double salario1 = Double.parseDouble(salarioModif.getText());
        String contraseña1 = contraseñaModif.getText();
        int idTienda1 = Integer.parseInt(idTiendaModif.getText());
        int idHorario1 = Integer.parseInt(idHorarioModif.getText());
        int idRuta1 = Integer.parseInt(idRutaModif.getText());

        t = new Trabajador(idTrabajador, nombre1, puesto1, dni1, salario1, contraseña1, idTienda1, idHorario1, idRuta1);
        int filas = tBDA.modificarTrabajador(t);
        if (filas == 0) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("La id del trabajador no existe");
            a.show();
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Trabajador");
            a.setHeaderText("Trabajador modificado con exito");
            a.show();
        }
    }

    @FXML
    private void eliminarTrabajador(ActionEvent event) {
        try {
            int idTrabajador = Integer.parseInt(idEliminar.getText());
            tBDA.borrarTrabajador(idTrabajador);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Trabajador");
            a.setHeaderText("El trabajador ha sido eliminado con exito");
            a.show();
        } catch (SQLException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("Error al borrar el trabajador: " + ex.getMessage());
            a.show();
        }
    }

    public void cargarPuestos() {
        List<String> puestos;
        puestos = f.cargaPuesto(archivo);
        listaPuestos = FXCollections.observableArrayList(puestos);
        puesto.setItems(listaPuestos);
        puestoModif.setItems(listaPuestos);
        puesto.setValue("Elige Puesto");
        puestoModif.setValue("Elige Puesto");
    }

    @FXML
    private void clicarExportar(MouseEvent event) throws SQLException {
        File archivo1 = d1.showDialog(new Stage());
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH-mm-ss");
        LocalDateTime fecha = LocalDateTime.now();
        String fechaFormate = fecha.format(formato);
        Path archi = Paths.get(archivo1.getAbsolutePath() + "\\" + fechaFormate + ".txt");
        f.generarInformacion(archi.toFile(),listaTrabajadores);
    }

    @FXML
    private void clicarInformacionTrabajador(Event event) throws SQLException {

        
        listaTrab = tBDA.listarTrabajadores();
        listaTrabajadores = FXCollections.observableArrayList(listaTrab);
        tableView.setItems(listaTrabajadores);

        idColum.setCellValueFactory(new PropertyValueFactory<>("idTrabajador"));
        nombreColum.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        puestoColum.setCellValueFactory(new PropertyValueFactory<>("puesto"));
        dniColum.setCellValueFactory(new PropertyValueFactory<>("DNI"));
        salarioColum.setCellValueFactory(new PropertyValueFactory<>("salario"));
        ContraseñaColum.setCellValueFactory(new PropertyValueFactory<>("contraseña"));
        idTiendaColum.setCellValueFactory(new PropertyValueFactory<>("idTienda"));
        idHorarioColum.setCellValueFactory(new PropertyValueFactory<>("idHorario"));
        idRutaColum.setCellValueFactory(new PropertyValueFactory<>("idRuta"));
        listaTrab.clear();
    }

    @FXML
    private void cambiarTrabajador(Event event) {

    }

    @FXML
    private void guardarTrabajador(MouseEvent event) {
        t = tableView.getSelectionModel().getSelectedItem();
        idModif.setText(t.getIdTrabajador() + "");
        nombreModif.setText(t.getNombre());
        dniModif.setText(t.getDNI());
        puestoModif.setValue(t.getPuesto());
        salarioModif.setText(t.getSalario() + "");
        if (t.getPuesto().equalsIgnoreCase("Vendedor") || t.getPuesto().equalsIgnoreCase("Repartidor")) {
            contraseñaModif.setDisable(false);
            
        } else {
            contraseñaModif.setText(t.getContraseña());
        }

        idTiendaModif.setText(t.getIdTienda() + "");
        idHorarioModif.setText(t.getIdHorario() + "");
        if (t.getPuesto().equalsIgnoreCase("Vendedor") || t.getPuesto().equalsIgnoreCase("Gerente")) {
            idRutaModif.setDisable(false);
            
        } else {
            idRutaModif.setText(t.getIdRuta() + "");
        }

    }

    @FXML
    private void clicarCombo(ActionEvent event) {
        if (puesto.getValue().equalsIgnoreCase("Vendedor")) {
            contraseña.setDisable(true);
            idRuta.setDisable(true);
        } else if(puesto.getValue().equalsIgnoreCase("Repartidor")){
            contraseña.setDisable(true);
            idRuta.setDisable(false);
        } else if(puesto.getValue().equalsIgnoreCase("Gerente")){
            contraseña.setVisible(true);
            idRuta.setDisable(true);
        }
        
    }
}
