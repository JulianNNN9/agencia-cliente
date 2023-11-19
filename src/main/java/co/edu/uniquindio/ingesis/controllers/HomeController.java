package co.edu.uniquindio.ingesis.controllers;

import co.edu.uniquindio.ingesis.exceptions.*;
import co.edu.uniquindio.ingesis.enums.ReservationStatus;
import co.edu.uniquindio.ingesis.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class HomeController {

    private final AgenciaCliente agenciaCliente = AgenciaCliente.getInstance();
    @FXML
    public Button paquetesClienteBtn, guiasClienteBtn;

    private double xOffset = 0;
    private double yOffset = 0;

    //Calificar guia------------------------------

    @FXML
    public Label bienvenidoLabel;
    @FXML
    public Pane bienvenidoPane;
    @FXML
    public Pane calificarGuiaPane;
    @FXML
    public Label calificarGuiaLabelPrincipal;
    @FXML
    public RadioButton radioBtton1Estrella, radioBtton2Estrella, radioBtton3Estrella, radioBtton4Estrella, radioBtton5Estrella;
    @FXML
    public Button confirmarCalificacionGuiaButton;
    ToggleGroup groupCalificacionGuia = new ToggleGroup();

    //Calificar destinos-----------------------------------

    @FXML
    public Pane calificarDestinosPane;
    @FXML
    public RadioButton radioBtton1EstrellaDestino, radioBtton2EstrellaDestino, radioBtton3EstrellaDestino, radioBtton4EstrellaDestino, radioBtton5EstrellaDestino;
    @FXML
    public ImageView cargaImagenDestinoCalificar;
    @FXML
    public Label cargarNombreDestinoCalificar;
    @FXML
    public Button calificarDestinoButton;
    @FXML
    public TextArea txtAreaComentario;
    ToggleGroup groupCalificacionDestino = new ToggleGroup();

    //----------------------Modificar perfil----------------------
    @FXML
    private HBox hboxCliente;
    @FXML
    public TableView<TouristPackage> tblPq;
    @FXML
    public Pane perfilPane;
    @FXML
    public TextField txtFldNombre;
    @FXML
    private TableColumn<TouristPackage,TouristPackage> colPqNombre, colPqPrecio,colPqCupo,colPqFechaInicio,colPqFechaFinal,colPqDuration;
    @FXML
    public TextField txtFldMail;
    @FXML
    public TextField txtFldNumero;
    @FXML
    public TextField txtFldResidencia;
    @FXML
    public Button confirmarEdicionButton;
    @FXML
    public TableView<Reservation> historialReservacionesTable;
    @FXML
    private TableColumn<Reservation, String> packageColumn, startDateColumn, endDateColumn, numberOfPeopleColumn, estadoReserva;
    @FXML
    private ObservableList<Reservation> reservations = FXCollections.observableArrayList();
    @FXML
    public Button cancelarReservaButton, confirmarReservaButton;
    private boolean isTableLoaded = false;
    private List<Reservation> reservationsData = new ArrayList<>();


    //----------------------Reservar----------------------
    @FXML
    public Pane reservarPane;
    @FXML
    public Button reservarButton;
    @FXML
    public TextField txtFldNombrePaquete, txtFldCuposDeseados;
    @FXML
    public TableView<TouristPackage> packagesTable;
    @FXML
    ObservableList<TouristPackage> packageObservableList = FXCollections.observableArrayList();
    @FXML
    public TableColumn<TouristPackage, TouristPackage> namePackageCol, priceCol, quotaCol, startDateCol, durationCol;
    @FXML
    public RadioButton radioBttonSI, radioBttonNO;
    @FXML
    public ChoiceBox<String> choiceBoxGuias;
    @FXML
    public Label seleccionarGuiaLabel;
    @FXML
    public Button hacerReservacionButton;
    ToggleGroup group = new ToggleGroup();

    //----------------------Registrar cliente----------------------

    @FXML
    private AnchorPane registroPanee;
    @FXML
    public TextField nombreTF, idTF,mailTF ,telefonoTF,residenciaTF;
    @FXML
    public PasswordField passwordFldRegistro;

    //----------------------Home pane----------------------
    @FXML
    private AnchorPane homePane;
    @FXML
    private ImageView cerrarVentanaImgvPrincipal;
    @FXML
    private ImageView cerrarVentanaImgvCliente;
    @FXML
    private Button homeBtn;
    //----------------------Paquetes pane----------------------
    @FXML
    private AnchorPane nuestrosPaquetesPane;
    @FXML
    public TextField barraBusquedaTF;
    @FXML
    private Button paquetesBtn;
    @FXML
    public ImageView mostrarDestinoImg, imagenSiguienteImg, imagenAnteriorImg;
    @FXML
    public Label vistaPreviaLabel;

    //----------------------Guias pane----------------------
    @FXML
    private AnchorPane nuestrosGuiasPane;
    @FXML
    private Button guiasBtn;

    //----------------------Iniciar Sesion Pane----------------------
    @FXML
    public TextField txtFldID;
    @FXML
    public PasswordField passwordFldInicioSesion;
    @FXML
    private Button iniciaSecionBtn;
    @FXML
    private AnchorPane iniciarsesionPane;
    @FXML
    private TextArea infoTA;
    @FXML
    private HBox hboxPanePrincipal;
    @FXML
    private TableView<String> tblDe;
    @FXML
    private TableColumn<String,String>  colDeNombre, colDeCiudad, colDeDescription, colDeClima;
    @FXML
    ObservableList<String> dE = FXCollections.observableArrayList();
    @FXML
    ObservableList<TouristPackage>  tP = FXCollections.observableArrayList();

    //----------------------Sesion actual----------------------
    String  clientID, passwordID,fullName, mail, phoneNumber, residence;

    //----------------------Nuestros guias----------------------

    //Guia 1
    @FXML
    public ImageView fotoMejorGuia1;
    @FXML
    public Label calificacionMejorGuia1Label, nombreMejorGuia1Label, idiomasMejorGuia1Label;

    //Guia 2
    @FXML
    public ImageView fotoMejorGuia2;
    @FXML
    public Label calificacionMejorGuia2Label, nombreMejorGuia2Label, idiomasMejorGuia2Label;

    //Guia 3
    @FXML
    public ImageView fotoMejorGuia3;
    @FXML
    public Label calificacionMejorGuia3Label, nombreMejorGuia3Label, idiomasMejorGuia3Label;




    public void initialize() {

        hboxPanePrincipal.setOnMousePressed((MouseEvent event) -> {
            // Guarda las coordenadas iniciales del clic del ratón
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        hboxPanePrincipal.setOnMouseDragged((MouseEvent event) -> {
            // Calcula el desplazamiento y ajusta las coordenadas de la ventana
            hboxPanePrincipal.getScene().getWindow().setX(event.getScreenX() - xOffset);
            hboxPanePrincipal.getScene().getWindow().setY(event.getScreenY() - yOffset);
        });

        hboxCliente.setOnMousePressed((MouseEvent event) -> {
            // Guarda las coordenadas iniciales del clic del ratón
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        hboxCliente.setOnMouseDragged((MouseEvent event) -> {
            // Calcula el desplazamiento y ajusta las coordenadas de la ventana
            hboxCliente.getScene().getWindow().setX(event.getScreenX() - xOffset);
            hboxCliente.getScene().getWindow().setY(event.getScreenY() - yOffset);
        });

        imagenAnteriorImg.setVisible(false);
        imagenSiguienteImg.setVisible(false);
        vistaPreviaLabel.setVisible(false);

        cancelarReservaButton.setVisible(false);
        confirmarReservaButton.setVisible(false);

        choiceBoxGuias.setVisible(false);
        seleccionarGuiaLabel.setVisible(false);

        radioBttonSI.setToggleGroup(group);
        radioBttonNO.setToggleGroup(group);

        radioBtton1Estrella.setToggleGroup(groupCalificacionGuia);
        radioBtton2Estrella.setToggleGroup(groupCalificacionGuia);
        radioBtton3Estrella.setToggleGroup(groupCalificacionGuia);
        radioBtton4Estrella.setToggleGroup(groupCalificacionGuia);
        radioBtton5Estrella.setToggleGroup(groupCalificacionGuia);

        radioBtton1EstrellaDestino.setToggleGroup(groupCalificacionDestino);
        radioBtton2EstrellaDestino.setToggleGroup(groupCalificacionDestino);
        radioBtton3EstrellaDestino.setToggleGroup(groupCalificacionDestino);
        radioBtton4EstrellaDestino.setToggleGroup(groupCalificacionDestino);
        radioBtton5EstrellaDestino.setToggleGroup(groupCalificacionDestino);

        radioBttonSI.setOnAction(event -> {
            choiceBoxGuias.setVisible(true);
            seleccionarGuiaLabel.setVisible(true);
        });

        radioBttonNO.setOnAction(event -> {
            choiceBoxGuias.getSelectionModel().clearSelection();
            choiceBoxGuias.setVisible(false);
            seleccionarGuiaLabel.setVisible(false);
        });

        elementosSheaarchBar();
        seleccionDestinos();

        //----------------------Modificar perfil----------------------

        txtFldNombre.setEditable(false);
        txtFldMail.setEditable(false);
        txtFldNumero.setEditable(false);
        txtFldResidencia.setEditable(false);
        confirmarEdicionButton.setVisible(false);

        //txtField numericos

        txtFldNumero.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume(); // Bloquea la entrada no numérica
            }
        });

        txtFldCuposDeseados.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume(); // Bloquea la entrada no numérica
            }
        });

        //Hacer reservación

        txtFldNombrePaquete.setEditable(false);

        packageObservableList = packagesTable.getItems();

        if (agenciaCliente.getTouristPackages() != null) {
            packageObservableList.addAll(agenciaCliente.getTouristPackages());
        }

        this.namePackageCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        this.quotaCol.setCellValueFactory(new PropertyValueFactory<>("quota"));
        this.startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        this.durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));

        packagesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtFldNombrePaquete.setText(newSelection.getName());
            }
        });


        //----------------------Nuestro guias----------------------

        agenciaCliente.getTouristGuides().sort(Comparator.comparingDouble(TouristGuide::getRating).reversed());

        // Obtiene los tres primeros guías después de ordenar
        TouristGuide guiaMejorCalificacion1 = agenciaCliente.getTouristGuides().size() > 0 ? agenciaCliente.getTouristGuides().get(0) : null;
        TouristGuide guiaMejorCalificacion2 = agenciaCliente.getTouristGuides().size() > 1 ? agenciaCliente.getTouristGuides().get(1) : null;
        TouristGuide guiaMejorCalificacion3 = agenciaCliente.getTouristGuides().size() > 2 ? agenciaCliente.getTouristGuides().get(2) : null;

        if (guiaMejorCalificacion1 != null){

            String [] firstName = guiaMejorCalificacion1.getFullName().split(" ");

            fotoMejorGuia1.setImage(cargarImagen(guiaMejorCalificacion1.getRutaFoto()));
            calificacionMejorGuia1Label.setText("Calificación : " + guiaMejorCalificacion1.getRating());
            nombreMejorGuia1Label.setText("Nombre : " + firstName[0]);
            idiomasMejorGuia1Label.setText("Idiomas : " + guiaMejorCalificacion1.getLanguages().toString());
        }

        if (guiaMejorCalificacion2 != null){

            String [] firstName = guiaMejorCalificacion2.getFullName().split(" ");

            fotoMejorGuia2.setImage(cargarImagen(guiaMejorCalificacion2.getRutaFoto()));
            calificacionMejorGuia2Label.setText("Calificación : " + guiaMejorCalificacion2.getRating());
            nombreMejorGuia2Label.setText("Nombre : " + firstName[0]);
            idiomasMejorGuia2Label.setText("Idiomas : " + guiaMejorCalificacion2.getLanguages().toString());
        }

        if (guiaMejorCalificacion3 != null){

            String [] firstName = guiaMejorCalificacion3.getFullName().split(" ");

            fotoMejorGuia3.setImage(cargarImagen(guiaMejorCalificacion3.getRutaFoto()));
            calificacionMejorGuia3Label.setText("Calificación : " + guiaMejorCalificacion3.getRating());
            nombreMejorGuia3Label.setText("Nombre : " + firstName[0]);
            idiomasMejorGuia3Label.setText("Idiomas : " + guiaMejorCalificacion3.getLanguages().toString());
        }


    }

    public void calificarServicioGuia(){

        Optional<Client> optionalClient = agenciaCliente.getClients().stream().filter(client ->  client.getUserId().equals(clientID)).findFirst();

        if (optionalClient.isPresent() && optionalClient.get().getReservationList() != null){

            List<LocalDate> endDates = optionalClient.get().getReservationList().stream()
                    .map(Reservation::getEndDate)
                    .toList();

            for (LocalDate endDate : endDates) {

                if (LocalDate.now().isAfter(endDate)) {

                    Reservation reservation = optionalClient.get().getReservationList().stream()
                            .filter(r -> r.getEndDate().equals(endDate))
                            .findFirst()
                            .orElse(null);

                    if (reservation != null && reservation.getReservationStatus().equals(ReservationStatus.CONFIRMED) && reservation.getTouristGuide() != null) {

                        hboxCliente.setVisible(false);
                        visibilitiesClient(false, false, false, false, true);

                        calificarGuiaLabelPrincipal.setText("Califica a nuestro guía, " + Arrays.stream(reservation.getTouristGuide().getFullName().split(" ")).findFirst());

                        confirmarCalificacionGuiaButton.setOnAction(actionEvent -> {

                            agenciaCliente.calificarGuia(reservation.getTouristGuide(), groupCalificacionGuia.getSelectedToggle().toString());
                            hboxCliente.setVisible(true);
                            visibilitiesClient(true, false, false, false, false);
                        });

                    }
                }
            }
        }
    }

    public void calificarDestinos(){

        Optional<Client> optionalClient =  agenciaCliente.getClients().stream().filter(client -> client.getUserId().equals(clientID)).findFirst();

        if (optionalClient.isPresent()){

            if (optionalClient.get().getReservationList() != null){

                for (Reservation reservation : optionalClient.get().getReservationList()){

                    if (LocalDate.now().isAfter(reservation.getEndDate()) && reservation.getReservationStatus().equals(ReservationStatus.CONFIRMED)){

                        hboxCliente.setVisible(false);
                        visibilitiesClient(false, false, false, true, false);

                        for (String nombreDestinoCalificar : reservation.getTouristPackage().getDestinosName()) {

                            Optional<Destino> optionalDestino = agenciaCliente.getDestinos()
                                    .stream()
                                    .filter(destino -> destino.getName().equals(nombreDestinoCalificar))
                                    .findFirst();

                            cargarNombreDestinoCalificar.setText(nombreDestinoCalificar);
                            cargaImagenDestinoCalificar.setImage(new Image(optionalDestino.get().getImagesHTTPS().get(0)));

                            optionalDestino.ifPresent(destino -> calificarDestinoButton.setOnAction(actionEvent -> {
                                agenciaCliente.calificarDestino(destino, txtAreaComentario.getText(), groupCalificacionDestino.getSelectedToggle().toString());

                                groupCalificacionDestino.getSelectedToggle().setSelected(false);
                                txtAreaComentario.clear();
                            }));
                        }

                        hboxCliente.setVisible(true);
                        visibilitiesClient(true, false, false, false, false);

                    }

                }
            }


        }

        calificarServicioGuia();

    }

    public void onHacerReservacionClick() {

        String mensaje = agenciaCliente.hacerReservacion(clientID, mail, choiceBoxGuias.getSelectionModel().getSelectedItem(), txtFldCuposDeseados.getText(), txtFldNombrePaquete.getText());

        createAlertInfo("Reserva", "Información", mensaje);

        group.getSelectedToggle().setSelected(false);
        choiceBoxGuias.getSelectionModel().clearSelection();
        txtFldCuposDeseados.clear();
        txtFldNombrePaquete.clear();
        seleccionarGuiaLabel.setVisible(false);
        choiceBoxGuias.setVisible(false);
    }

    private void cargarTablaHistoricoReservas() {

        if (!isTableLoaded) {

            isTableLoaded = true;

            reservations = historialReservacionesTable.getItems();

            historialReservacionesTable.setItems(reservations);

            packageColumn.setCellValueFactory(cellData -> {
                StringProperty property = new SimpleStringProperty();
                TouristPackage touristPackage = cellData.getValue().getTouristPackage();
                if (touristPackage != null) {
                    property.set(touristPackage.getName());
                }
                return property;
            });
            this.startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            this.endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            this.numberOfPeopleColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfPeople"));
            this.estadoReserva.setCellValueFactory(new PropertyValueFactory<>("reservationStatus"));

            historialReservacionesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    if (newSelection.getReservationStatus() == ReservationStatus.CONFIRMED) {
                        cancelarReservaButton.setVisible(true);
                        confirmarReservaButton.setVisible(false);

                        cancelarReservaButton.setOnAction(event -> {
                            agenciaCliente.cancelarReserva(newSelection);
                            cancelarReservaButton.setVisible(false);
                            historialReservacionesTable.refresh();
                        });

                    }
                    if (newSelection.getReservationStatus() == ReservationStatus.PENDING) {
                        cancelarReservaButton.setVisible(true);
                        confirmarReservaButton.setVisible(true);

                        cancelarReservaButton.setOnAction(event -> {
                            agenciaCliente.cancelarReserva(newSelection);
                            cancelarReservaButton.setVisible(false);
                            historialReservacionesTable.refresh();
                        });

                        confirmarReservaButton.setOnAction(actionEvent -> {
                            agenciaCliente.confirmarReserva(newSelection);
                            confirmarReservaButton.setVisible(false);
                            historialReservacionesTable.refresh();
                        });
                    }
                    if (newSelection.getReservationStatus() == ReservationStatus.CANCELED) {
                        cancelarReservaButton.setVisible(false);
                        confirmarReservaButton.setVisible(false);
                    }
                }
            });
        } else {

            Optional<Client> client = agenciaCliente.getClients().stream().filter(client1 -> client1.getUserId().equals(clientID)).findFirst();

            List<Reservation> reservationsData = new ArrayList<>();

            if (client.isPresent() && client.get().getReservationList() != null) {
                reservationsData = client.get().getReservationList();
            }

            List<Reservation> existingReservations = new ArrayList<>(reservations);

            for (Reservation newReservation : reservationsData) {
                if (!existingReservations.contains(newReservation)) {
                    reservations.add(newReservation);
                    historialReservacionesTable.refresh();
                }
            }
        }
    }

    public void onReservarClick() {

        List<String> guiasName = agenciaCliente.getTouristGuides().stream()
                .map(TouristGuide::getFullName)
                .toList();

        if (!guiasName.equals(choiceBoxGuias.getItems())) {
            choiceBoxGuias.getItems().setAll(guiasName);
        }

        visibilitiesClient(false, false, true, false, false);
    }

    private void seleccionDestinos() {

        dE = tblDe.getItems();

        tblPq.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            boolean seleccionado = newValue != null;

            if (seleccionado) {
                if (Objects.requireNonNull(newValue).getDestinosName() != null) {
                    dE.clear();
                    dE.addAll(newValue.getDestinosName());
                    }
                }
        });

        tblDe.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            boolean seleccionado = newValue != null;

            if (seleccionado){
                Optional<Destino> optionalDestino =  agenciaCliente.getDestinos().stream().filter(destino -> destino.getName().equals(newValue)).findFirst();

                List<String> listaRutas;

                if (optionalDestino.isPresent()){
                    listaRutas = optionalDestino.get().getImagesHTTPS();
                    if (!listaRutas.isEmpty()){
                        mostrarDestinoImg.setImage(cargarImagen(listaRutas.get(0)));
                        imagenAnteriorImg.setVisible(true);
                        imagenSiguienteImg.setVisible(true);
                        vistaPreviaLabel.setVisible(true);
                    } else {
                        mostrarDestinoImg.setImage(null);
                        imagenAnteriorImg.setVisible(false);
                        imagenSiguienteImg.setVisible(false);
                        vistaPreviaLabel.setVisible(false);
                    }
                } else {
                    listaRutas = null;
                }

                if (listaRutas != null){

                    final int[] i = {0};

                    imagenSiguienteImg.setOnMouseClicked(mouseEvent -> {
                        if (i[0] + 1 < listaRutas.size()) {
                            ++i[0];
                            mostrarDestinoImg.setImage(cargarImagen(listaRutas.get(i[0])));
                        }
                    });

                    imagenAnteriorImg.setOnMouseClicked(mouseEvent -> {
                        if (i[0] > 0) {
                            --i[0];
                            mostrarDestinoImg.setImage(cargarImagen(listaRutas.get(i[0])));
                        }
                    });
                }


            }

        });

        this.colDeNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));

    }

    public Image cargarImagen(String ruta){
        File file = new File(ruta);
        return new Image(String.valueOf(file.toURI()));
    }

    public void cargarDatos(){
        txtFldNombre.setText(fullName);
        txtFldMail.setText(mail);
        txtFldNumero.setText(phoneNumber);
        txtFldResidencia.setText(residence);
    }

    public void sesionIniciada(Client client){
        clientID = client.getUserId();
        passwordID = client.getPassword();
        fullName = client.getFullName();
        mail = client.getMail();
        phoneNumber = client.getPhoneNumber();
        residence = client.getResidence();
    }

    public void onModificarPerfilClick() {
        txtFldNombre.setEditable(true);
        txtFldMail.setEditable(true);
        txtFldNumero.setEditable(true);
        txtFldResidencia.setEditable(true);
        confirmarEdicionButton.setVisible(true);
    }

    public void onConfirmarEdicionClick() {

        agenciaCliente.modificarPerfil(clientID, txtFldNombre.getText(), txtFldMail.getText(), txtFldNumero.getText(), txtFldResidencia.getText());

        txtFldNombre.setEditable(false);
        txtFldMail.setEditable(false);
        txtFldNumero.setEditable(false);
        txtFldResidencia.setEditable(false);
        confirmarEdicionButton.setVisible(false);
    }

    public void onPerfilClick() {
        cargarTablaHistoricoReservas();
        visibilitiesClient(false, true, false, false, false);
    }

    public void onConfiRegistrarClienteClick() throws AtributoVacioException {

        if(idTF.getText() == null || idTF.getText().isBlank() ||
                passwordFldRegistro.getText() == null || passwordFldRegistro.getText().isBlank() ||
                nombreTF.getText() == null || nombreTF.getText().isBlank() ||
                mailTF.getText() == null || mailTF.getText().isBlank() ||
                telefonoTF.getText() == null || telefonoTF.getText().isBlank() ||
                residenciaTF.getText() == null || residenciaTF.getText().isBlank()){

            createAlertError("Error", "Se ha hecho un intento de registro de cliente con campos vacios.");
            throw new AtributoVacioException("Se ha hecho un intento de registro de cliente con campos vacios.");
        }

        String mensaje = agenciaCliente.registrarCliente(idTF.getText(),passwordFldRegistro.getText(),nombreTF.getText(),mailTF.getText(),telefonoTF.getText(),residenciaTF.getText());

        createAlertInfo("Registro cliente", "Información", mensaje);

        visibilitiesRegister(true, false);

    }


    @FXML
    private void handleButtonAction(ActionEvent event){

        if (event.getTarget() == homeBtn) {
            visibilitiesPrincipal(true,false,false,false);}
        if (event.getTarget() == paquetesBtn) {
            visibilitiesPrincipal(false,true,false,false);}
        if (event.getTarget() == guiasBtn) {
            visibilitiesPrincipal(false,false,true,false);}
        if (event.getTarget() == iniciaSecionBtn) {
            txtFldID.clear();
            passwordFldInicioSesion.clear();
            visibilitiesPrincipal(false,false,false,true);}

    }

    public void visibilitiesPrincipal(boolean pane1, boolean pane2 , boolean pane3, boolean pane5 ){
        homePane.setVisible(pane1);
        nuestrosPaquetesPane.setVisible(pane2);
        nuestrosGuiasPane.setVisible(pane3);
        iniciarsesionPane.setVisible(pane5);
    }

    public void visibilitiesClient(boolean pane1,boolean pane2, boolean pane3, boolean pane4, boolean pane5){
        bienvenidoPane.setVisible(pane1);
        perfilPane.setVisible(pane2);
        reservarPane.setVisible(pane3);
        calificarDestinosPane.setVisible(pane4);
        calificarGuiaPane.setVisible(pane5);
        nuestrosPaquetesPane.setVisible(false);
        nuestrosGuiasPane.setVisible(false);
        iniciarsesionPane.setVisible(false);
    }

    public void visibilitiesRegister(boolean pan1, boolean pan2){
        iniciarsesionPane.setVisible(pan1);
        registroPanee.setVisible(pan2);
    }

    public void onExitButtonClick() {
        Stage stage = (Stage) cerrarVentanaImgvPrincipal.getScene().getWindow();
        stage.close();
    }

    public void onLogInButtonClick() throws IOException, AtributoVacioException, WrongPasswordException {

        if (txtFldID.getText() == null || txtFldID.getText().isBlank() ||
                passwordFldInicioSesion.getText() == null || passwordFldInicioSesion.getText().isBlank()){

            createAlertError("Error", "Se ha hecho un intento de inicio de sesion con campos vacios.");
            throw new AtributoVacioException("Se ha hecho un intento de inicio de sesion con campos vacios.");
        }

        String sesion = agenciaCliente.LogIn(txtFldID.getText(), passwordFldInicioSesion.getText());

        Optional<Client> optionalClient = agenciaCliente.getClients().stream().filter(client -> client.getUserId().equals(txtFldID.getText())).findFirst();

        if(sesion.equals("Client")){

            if (agenciaCliente.getClients().stream().noneMatch(client -> client.getPassword().equals(passwordFldInicioSesion.getText()))){
                createAlertError("Error", "La contraseña ingresada es incorrecta.");
                throw new WrongPasswordException("Se ha hecho un intento de inicio de sesión con datos incorrectos.");
            }

            hboxPanePrincipal.setVisible(false);
            hboxCliente.setVisible(true);
            visibilitiesClient(true, false, false, false, false);

            optionalClient.ifPresent(this::sesionIniciada);

            if (optionalClient.isPresent()){
                if (!agenciaCliente.alertarPorDescuentoEnReservas(optionalClient.get()).isEmpty()){
                    createAlertInfo("!Cliente¡", "Información", agenciaCliente.alertarPorDescuentoEnReservas(optionalClient.get()));
                }
            }

            cargarDatos();
            calificarDestinos();

            String[] firstName = fullName.split(" ");

            bienvenidoLabel.setText("Bienvenido de nuevo, " + firstName[0]);

        } else if (sesion.equals("Admin")) {

            if (agenciaCliente.getAdmins().stream().noneMatch(admin -> admin.getPassword().equals(passwordFldInicioSesion.getText()))){
                createAlertError("Error", "La contraseña ingresada es incorrecta.");
                throw new WrongPasswordException("Se ha hecho un intento de inicio de sesión con datos incorrectos.");
            }

            generateWindow("src/main/resources/views/adminView.fxml", cerrarVentanaImgvPrincipal);

        } else {
            createAlertError("El usuario ingresado no existe", "Verifique los datos");
        }

    }
    public void onConocerNuestrosPaquetesClick() {
        visibilitiesPrincipal(false,true,false,false);
    }

    private void elementosSheaarchBar() {

        this.colPqNombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.colPqPrecio.setCellValueFactory(new PropertyValueFactory<>("price"));
        this.colPqCupo.setCellValueFactory(new PropertyValueFactory<>("quota"));
        this.colPqFechaInicio.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        this.colPqFechaFinal.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        this.colPqDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));

        tP.addAll(agenciaCliente.getTouristPackages());

        tblPq.setItems(tP);

        FilteredList<TouristPackage> filterData = new FilteredList<>(tP,b->true);
        barraBusquedaTF.textProperty().addListener((observable, oldValue, newValue) -> filterData.setPredicate(touristPackage ->{

            if(newValue.isEmpty() || newValue.isBlank()){
                return true;
            }

            String searchKeyword = newValue.toLowerCase();

            if(touristPackage.getName().toLowerCase().contains(searchKeyword)){
                return true;

            }else if(touristPackage.getPrice().toString().contains(searchKeyword)){
                return true;

            }else if(touristPackage.getQuota().toString().contains(searchKeyword)){
                return true;

            }else if(touristPackage.getStartDate().toString().contains(searchKeyword)){
                return true;

            }else return touristPackage.getEndDate().toString().contains(searchKeyword);

        }));

        SortedList<TouristPackage> sortedData = new SortedList<>(filterData);
        sortedData.comparatorProperty().bind(tblPq.comparatorProperty());

        tblPq.setItems(sortedData);

        dE = tblDe.getItems();
    }

    public void registroExit() {
        visibilitiesRegister(true,false);

        nombreTF.clear();
        idTF.clear();
        mailTF.clear();
        telefonoTF.clear();
        residenciaTF.clear();
        passwordFldRegistro.clear();
    }
    public void onRegisterButtonClck() {visibilitiesRegister(false,true);}
    
    

    public void onLogOutButtonClick() throws IOException {

        File url = new File("src/main/resources/views/homeView.fxml");
        FXMLLoader loader = new FXMLLoader(url.toURL());
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.show();

        Stage stage1 = (Stage) cerrarVentanaImgvCliente.getScene().getWindow();
        stage1.close();
    }


    public void createAlertError(String titleError, String contentError){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titleError);
        alert.setContentText(contentError);
        alert.showAndWait();
    }

    public void createAlertInfo(String titleError, String headerError, String contentError){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleError);
        alert.setHeaderText(headerError);
        alert.setContentText(contentError);
        alert.showAndWait();
    }

    public void generateWindow(String path, ImageView close) throws IOException {

        File url = new File(path);
        FXMLLoader loader = new FXMLLoader(url.toURL());
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setResizable(false);
        stage.show();

        Stage stage1 = (Stage) close.getScene().getWindow();
        stage1.close();
    }

    public void onPaquetesClienteClick() {
        visibilitiesClient(false, false, false, false, false);
        nuestrosPaquetesPane.setVisible(true);
    }

    public void onGuiasClienteClick() {
        visibilitiesClient(false, false, false, false, false);
        nuestrosGuiasPane.setVisible(true);
    }
}