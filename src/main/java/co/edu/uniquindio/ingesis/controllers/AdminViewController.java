package co.edu.uniquindio.ingesis.controllers;

import co.edu.uniquindio.ingesis.exceptions.*;
import co.edu.uniquindio.ingesis.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class AdminViewController {

    private final AgenciaCliente agenciaCliente = AgenciaCliente.getInstance();

    private double xOffset = 0;
    private double yOffset = 0;

    //Ventana principal

    @FXML
    public Pane principalPane;
    @FXML
    public ImageView imgViewExitButton;
    @FXML
    public Button manageDestinationsButton, managePackagesButton, manageGuidesButton, statisticsButton;

    //Ventana gestionar destinos

    @FXML
    public Pane manageDestinationsPane;
    @FXML
    public ImageView imgViewBackDestinationsButton;
    @FXML
    public TextField txtFldName, txtFldCity, txtFldDescription;
    @FXML
    public ChoiceBox<String> choiceBoxClima;
    @FXML
    public TextField txtFldRuta;
    @FXML
    public Label moverVentana;
    @FXML
    ObservableList<String> observableListRutas;
    @FXML
    public TableView<String> imagesRoutesTable;
    @FXML
    public TableColumn<String, String> rutasCol;
    @FXML
    public TableView<Destino> destinationsTable;
    @FXML
    ObservableList<Destino> destinoObservableList;
    @FXML
    public TableColumn<Destino, Destino> nameDestinationCol, cityCol, descriptionCol,weatherCol;
    public Button limpiarCamposDestinos, examinarRutaButton, deleteButtonDestination, modifyButtonDestination, addButtonDestination, deleteButtonImageDestination, addButtonImageDestination;

    //Ventana gestionar paquetes

    @FXML
    public TableView<String> destinationsNameTable;
    @FXML
    ObservableList<String> observableListDestinationName;
    @FXML
    public TableColumn<String, String> destinosNameCol;
    @FXML
    public ChoiceBox<String> choiceBoxDestinationName;
    @FXML
    public Pane managePackagesPane;
    @FXML
    public TableView<TouristPackage> packagesTable;
    @FXML
    ObservableList<TouristPackage> packageObservableList;
    @FXML
    public TableColumn<TouristPackage, TouristPackage> namePackageCol, priceCol, quotaCol, startDateCol, durationCol;
    @FXML
    public ImageView imgViewBackPackagesButton;
    @FXML
    public Button limpiarCamposPaquetes, addButtonPackages, modifyButtonPackages, deleteButtonPackages, addButtonDestinationName, deleteButtonDestinationName;
    @FXML
    public TextField txtFldPackageName,txtFldPrice, txtFldQuota;
    @FXML
    public DatePicker datePckrStartDate, datePckrEndDate;

    //Ventana gestionar guias
    @FXML
    public TableView<String> guidesLenguajeTable;
    @FXML
    ObservableList<String> observableListLenguajes;
    @FXML
    public TableColumn<String, String> lenguajeCol;
    @FXML
    public Pane manageGuidesPane;
    @FXML
    public TableView<TouristGuide> guidesTable;
    @FXML
    ObservableList<TouristGuide> touristGuideObservableList;
    @FXML
    public TableColumn<TouristGuide, TouristGuide> idGuideCol, fullNameGuideCol, experienceCol, ratingCol;
    @FXML
    public ImageView imgViewBackGuidesButton;
    @FXML
    public Button limpiarCamposGuides, addButtonGuides, modifyButtonGuides, deleteButtonGuide, addLenguajeButton, deleteLenguajeButton;
    @FXML
    public TextField txtFldGuideId, txtFldFullNameGuide, txtFldExperience, txtFldRating, txtFldLenguaje, txtFldRutaFoto;

    //Ventana estadísticas

    @FXML
    public Pane statisticsPane;
    @FXML
    public ImageView imgViewBackStatisticsButton;
    @FXML
    private BarChart<String, Number> destinationsChart;
    @FXML
    private CategoryAxis destinationsXAxis;
    @FXML
    private NumberAxis destinationsYAxis;
    @FXML
    private BarChart<String, Number> guidesChart;
    @FXML
    private CategoryAxis guidesXAxis;
    @FXML
    private NumberAxis guidesYAxis;
    private List<TouristGuide> guides = agenciaCliente.getTouristGuides();
    @FXML
    private BarChart<String, Number> packagesChart;
    @FXML
    private CategoryAxis packagesXAxis;
    @FXML
    private NumberAxis packagesYAxis;
    private List<Reservation> reservations = agenciaCliente.getReservations();

    public void initialize(){

        moverVentana.setOnMousePressed((MouseEvent event) -> {
            // Guarda las coordenadas iniciales del clic del ratón
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        moverVentana.setOnMouseDragged((MouseEvent event) -> {
            // Calcula el desplazamiento y ajusta las coordenadas de la ventana
            moverVentana.getScene().getWindow().setX(event.getScreenX() - xOffset);
            moverVentana.getScene().getWindow().setY(event.getScreenY() - yOffset);
        });

        //Imagenes

        File file = new File("src/main/resources/icons/4103083.png");
        Image backButton = new Image(String.valueOf(file.toURI()));

        imgViewBackDestinationsButton.setImage(backButton);
        imgViewBackPackagesButton.setImage(backButton);
        imgViewBackGuidesButton.setImage(backButton);
        imgViewBackStatisticsButton.setImage(backButton);

        File file1 = new File("src/main/resources/icons/LogOut.png");
        Image exitButton = new Image(String.valueOf(file1.toURI()));

        imgViewExitButton.setImage(exitButton);

        //txtFields de numeros

        txtFldQuota.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume(); // Bloquea la entrada no numérica
            }
        });

        txtFldPrice.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume(); // Bloquea la entrada no numérica
            }
        });

        txtFldRating.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[1-5]")) {
                event.consume(); // Bloquea la entrada no numérica o fuera del rango
            }
        });

        // Limita el campo de texto a un solo dígito
        txtFldRating.lengthProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                if (!txtFldRating.getText().isEmpty()) {
                    txtFldRating.setText(txtFldRating.getText().substring(0, 1));
                }
            }
        });

        //------------------------DESTINOS----------------------------

        //Tabla de rutas de imagenes

        imagesRoutesTable.setDisable(true);
        examinarRutaButton.setDisable(true);
        addButtonImageDestination.setDisable(true);
        deleteButtonImageDestination.setDisable(true);
        txtFldRuta.setDisable(true);
        modifyButtonDestination.setDisable(true);

        observableListRutas = imagesRoutesTable.getItems();

        destinationsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            boolean seleccionado = newValue != null;

            imagesRoutesTable.setDisable(!seleccionado);
            examinarRutaButton.setDisable(!seleccionado);
            addButtonImageDestination.setDisable(!seleccionado);
            deleteButtonImageDestination.setDisable(!seleccionado);
            txtFldRuta.setDisable(!seleccionado);
            modifyButtonDestination.setDisable(!seleccionado);

            if (seleccionado) {
                if (Objects.requireNonNull(newValue).getImagesHTTPS() != null) {
                    observableListRutas.setAll(newValue.getImagesHTTPS());
                }
            }

            this.rutasCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));

        });

        //Tabla de destinos

        destinoObservableList = destinationsTable.getItems();

        if (agenciaCliente.getDestinos() != null) {
            destinoObservableList.addAll(agenciaCliente.getDestinos());
        }

        this.nameDestinationCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        this.descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.weatherCol.setCellValueFactory(new PropertyValueFactory<>("weather"));

        destinationsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtFldName.setText(newSelection.getName());
                txtFldCity.setText(newSelection.getCity());
                txtFldDescription.setText(newSelection.getDescription());
                choiceBoxClima.setValue(newSelection.getWeather());
            }
        });

        //------------------------PAQUETES----------------------------

        //Destinos en paquetes

        destinationsNameTable.setDisable(true);
        addButtonDestinationName.setDisable(true);
        deleteButtonDestinationName.setDisable(true);
        choiceBoxDestinationName.setDisable(true);
        modifyButtonPackages.setDisable(true);

        observableListDestinationName = destinationsNameTable.getItems();

        packagesTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            boolean seleccionado = newValue != null;

            destinationsNameTable.setDisable(!seleccionado);
            addButtonDestinationName.setDisable(!seleccionado);
            deleteButtonDestinationName.setDisable(!seleccionado);
            choiceBoxDestinationName.setDisable(!seleccionado);
            modifyButtonPackages.setDisable(!seleccionado);

            if (seleccionado) {
                if (Objects.requireNonNull(newValue).getDestinosName() != null) {
                    observableListDestinationName.setAll(newValue.getDestinosName());
                }
            }

            this.destinosNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));

        });

        //Paquetes

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
                txtFldPackageName.setText(newSelection.getName());
                txtFldPrice.setText(String.valueOf(newSelection.getPrice()));
                txtFldQuota.setText(String.valueOf(newSelection.getQuota()));
                datePckrStartDate.setValue(newSelection.getStartDate());
                datePckrEndDate.setValue(newSelection.getEndDate());
            }
        });

        //------------------------GUIAS----------------------------

        //Lenguajes del guia

        guidesLenguajeTable.setDisable(true);
        addLenguajeButton.setDisable(true);
        deleteLenguajeButton.setDisable(true);
        txtFldLenguaje.setDisable(true);
        modifyButtonGuides.setDisable(true);

        observableListLenguajes = guidesLenguajeTable.getItems();

        guidesTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            boolean seleccionado = newValue != null;

            guidesLenguajeTable.setDisable(!seleccionado);
            addLenguajeButton.setDisable(!seleccionado);
            deleteLenguajeButton.setDisable(!seleccionado);
            txtFldLenguaje.setDisable(!seleccionado);
            modifyButtonGuides.setDisable(!seleccionado);

            if (seleccionado) {
                if (Objects.requireNonNull(newValue).getLanguages() != null) {
                    observableListLenguajes.setAll(newValue.getLanguages());
                }
            }

            this.lenguajeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));

        });

        //Guias

        touristGuideObservableList = guidesTable.getItems();

        if (agenciaCliente.getTouristGuides() != null) {
            touristGuideObservableList.addAll(agenciaCliente.getTouristGuides());
        }

        this.idGuideCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.fullNameGuideCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        this.experienceCol.setCellValueFactory(new PropertyValueFactory<>("experience"));
        this.ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));

        guidesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtFldGuideId.setText(newSelection.getId());
                txtFldFullNameGuide.setText(String.valueOf(newSelection.getFullName()));
                txtFldExperience.setText(newSelection.getExperience());
                txtFldRating.setText(String.valueOf(newSelection.getRating()));
                txtFldRutaFoto.setText(newSelection.getRutaFoto());
            }
        });

        //------------------------ESTADISTICAS-------------------------

        // Obtener las reservas desde travelAgency

        Map<String, Integer> cuentaReservasPorDestino = getIntegerMap();

        // Ordenar el mapa por la cantidad de reservas en orden descendente
        List<Map.Entry<String, Integer>> listaOrdenada = cuentaReservasPorDestino.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .toList();

        // Crear listas observables a partir de los datos ordenados
        ObservableList<String> destinations = FXCollections.observableArrayList();
        ObservableList<Integer> reservationCounts = FXCollections.observableArrayList();

        for (Map.Entry<String, Integer> entry : listaOrdenada) {
            destinations.add(entry.getKey());
            reservationCounts.add(entry.getValue());
        }

        // Configura el gráfico de destinos más reservados
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (int i = 0; i < destinations.size(); i++) {
            series.getData().add(new XYChart.Data<>(destinations.get(i), reservationCounts.get(i)));
        }
        destinationsChart.getData().add(series);
        destinationsChart.setTitle("Destinos Más Reservados");
        destinationsXAxis.setLabel("Destinos");
        destinationsYAxis.setLabel("Reservas");



        // Ordenar los guías por rating en orden descendente

        guides.sort((g1, g2) -> Double.compare(g2.getRating(), g1.getRating()));

        // Tomar los 5 guías mejor puntuados o el número que desees
        List<TouristGuide> topGuides = guides.subList(0, Math.min(5, guides.size()));

        // Configurar el gráfico de guías mejor puntuados
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        for (TouristGuide guide : topGuides) {
            series1.getData().add(new XYChart.Data<>(guide.getFullName(), guide.getRating()));
        }

        guidesChart.getData().add(series1);
        guidesChart.setTitle("Guías Mejor Puntuados");
        guidesXAxis.setLabel("Guías");
        guidesYAxis.setLabel("Puntuación");



        //Crear un mapa para contabilizar las reservas por nombre de paquete

        Map<String, Integer> packageReservationCounts = getStringIntegerMap();

        // Convertir el mapa en una lista de pares (nombre del paquete, número de reservas)
        ObservableList<XYChart.Data<String, Number>> packageData = FXCollections.observableArrayList();

        for (String packageName : packageReservationCounts.keySet()) {
            int reservationCount = packageReservationCounts.get(packageName);
            packageData.add(new XYChart.Data<>(packageName, reservationCount));
        }

        // Configurar el gráfico de paquetes más reservados
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setData(packageData);
        packagesChart.getData().add(series2);
        packagesChart.setTitle("Paquetes Más Reservados");
        packagesXAxis.setLabel("Paquetes");
        packagesYAxis.setLabel("Número de Reservas");
    }

    private Map<String, Integer> getIntegerMap() {
        List<Reservation> reservations = agenciaCliente.getReservations();

        // Crear un mapa para contar las repeticiones de los nombres de destinos
        Map<String, Integer> cuentaReservasPorDestino = new HashMap<>();

        for (Reservation reservation : reservations) {
            TouristPackage touristPackage = reservation.getTouristPackage();

            if (touristPackage != null) {
                List<String> destinos = touristPackage.getDestinosName();

                for (String destino : destinos) {
                    cuentaReservasPorDestino.put(destino, cuentaReservasPorDestino.getOrDefault(destino, 0) + 1);
                }
            }
        }

        return cuentaReservasPorDestino;
    }

    private Map<String, Integer> getStringIntegerMap() {
        Map<String, Integer> packageReservationCounts = new HashMap<>();

        for (Reservation reservation : reservations) {
            TouristPackage touristPackage = reservation.getTouristPackage();

            if (touristPackage != null) {
                String packageName = touristPackage.getName();
                packageReservationCounts.put(packageName, packageReservationCounts.getOrDefault(packageName, 0) + 1);
            }
        }

        return packageReservationCounts;
    }

    //----------------------------Destinations-----------------------------

    public void onLimpiarCamposDestinoClick() {
        limpiarCamposDestinations();
        imagesRoutesTable.setDisable(true);
        imagesRoutesTable.getItems().clear();
        examinarRutaButton.setDisable(true);
        addButtonImageDestination.setDisable(true);
        deleteButtonImageDestination.setDisable(true);
        txtFldRuta.setDisable(true);
    }

    @FXML
    private void agregarElementoDestinations() throws RepeatedInformationException, AtributoVacioException {

        if (txtFldName.getText().isEmpty() || txtFldName.getText() == null ||
                txtFldCity.getText().isEmpty() || txtFldCity.getText() == null ||
                txtFldDescription.getText().isEmpty() || txtFldDescription.getText() == null ||
                choiceBoxClima.getValue().isEmpty() || choiceBoxClima.getValue() == null) {

            createAlertError("Error", "Se ha intentado agregar un destino con campos vacios.");
            throw new AtributoVacioException("Se ha intentado agregar un destino con campos vacios.");
        }

        Destino nuevoDestino = Destino.builder()
                .name(txtFldName.getText())
                .city(txtFldCity.getText())
                .imagesHTTPS(new ArrayList<>())
                .description(txtFldDescription.getText())
                .weather(choiceBoxClima.getValue())
                .build();

        if (destinoObservableList.stream().anyMatch(destination -> destination.getName().equals(nuevoDestino.getName()))){
            createAlertError("Error", "Se ha intentado crear un Destino existente.");
            throw new RepeatedInformationException("Se ha intentado crear un Destino existente.");
        }

        destinoObservableList.add(nuevoDestino);

        agenciaCliente.agregarDestino(nuevoDestino);

        limpiarCamposDestinations();

        imagesRoutesTable.setDisable(true);
        examinarRutaButton.setDisable(true);
        addButtonImageDestination.setDisable(true);
        deleteButtonImageDestination.setDisable(true);
        txtFldRuta.setDisable(true);
        modifyButtonDestination.setDisable(true);

        imagesRoutesTable.refresh();
    }

    @FXML
    private void modificarElementoDestinations() throws AtributoVacioException {

        if (destinationsTable.getSelectionModel().getSelectedIndex() >= 0) {

            Destino selectedDestino = destinationsTable.getSelectionModel().getSelectedItem();

            if (txtFldName.getText().isEmpty() ||
                    txtFldCity.getText().isEmpty() ||
                    txtFldDescription.getText().isEmpty() ||
                    choiceBoxClima.getValue().isEmpty()) {

                createAlertError("Error", "Se ha intentado agregar un destino con campos vacios.");
                throw new AtributoVacioException("Se ha intentado agregar un destino con campos vacios.");
            }

            agenciaCliente.modificarDestino(selectedDestino, txtFldName.getText(), txtFldCity.getText(), txtFldDescription.getText(), choiceBoxClima.getValue());

            selectedDestino.setName(txtFldName.getText());
            selectedDestino.setCity(txtFldCity.getText());
            selectedDestino.setWeather(choiceBoxClima.getValue());
            selectedDestino.setDescription(txtFldDescription.getText());

            limpiarCamposDestinations();

            destinationsTable.refresh();

            imagesRoutesTable.setDisable(true);
            examinarRutaButton.setDisable(true);
            addButtonImageDestination.setDisable(true);
            deleteButtonImageDestination.setDisable(true);
            txtFldRuta.setDisable(true);
            modifyButtonDestination.setDisable(true);

            imagesRoutesTable.refresh();
        }

    }

    @FXML
    private void eliminarElementoDestinations() {
        if (destinationsTable.getSelectionModel().getSelectedIndex() >= 0) {

            Destino selectedDestino = destinationsTable.getSelectionModel().getSelectedItem();

            agenciaCliente.eliminarDestino(selectedDestino);

            destinoObservableList.remove(selectedDestino);

            limpiarCamposDestinations();

            imagesRoutesTable.setDisable(true);
            examinarRutaButton.setDisable(true);
            addButtonImageDestination.setDisable(true);
            deleteButtonImageDestination.setDisable(true);
            txtFldRuta.setDisable(true);
            modifyButtonDestination.setDisable(true);

            imagesRoutesTable.refresh();
        }
    }

    @FXML
    public void seleccionarImagen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.jpeg", "*.png", "*.gif", "*.bmp")
        );

        File imagenSeleccionada = fileChooser.showOpenDialog(getStage());

        if (imagenSeleccionada != null) {
            String rutaImagen = imagenSeleccionada.getAbsolutePath();
            txtFldRuta.setText(rutaImagen);
        }
    }

    @FXML
    private Stage getStage() {
        return (Stage) txtFldRuta.getScene().getWindow();
    }

    @FXML
    public void agregarRutaImagenDestinations() throws RepeatedInformationException, EmptyAttributeException {

        if (txtFldRuta.getText() == null || txtFldRuta.getText().isEmpty()){
            createAlertError("Error", "Atributos vacios");
            throw new EmptyAttributeException("Atributos vacíos.");
        }

        String destinoName = txtFldName.getText();

        Optional<Destino> destinoSeleccionadoOpcional = agenciaCliente.getDestinos().stream()
                .filter(destino -> destino.getName().equals(destinoName))
                .findFirst();

        if (destinoSeleccionadoOpcional.isPresent()) {

            if (observableListRutas.stream().anyMatch(string -> string.equals(txtFldRuta.getText()))){
                createAlertError("Error", "Se ha intentado añadir una ruta existente.");
                throw new RepeatedInformationException("Se ha intentado añadir una ruta existente.");
            }

            observableListRutas.add(txtFldRuta.getText());
            agenciaCliente.agregarImagenDestino(txtFldRuta.getText(), destinoSeleccionadoOpcional.get());

        } else {

            Destino nuevoDestino = Destino.builder()
                    .name(txtFldName.getText())
                    .city(txtFldCity.getText())
                    .imagesHTTPS(new ArrayList<>())
                    .description(txtFldDescription.getText())
                    .weather(choiceBoxClima.getValue())
                    .build();

            observableListRutas.add(txtFldRuta.getText());
            agenciaCliente.agregarImagenDestino(txtFldRuta.getText(), nuevoDestino);

            agenciaCliente.agregarDestino(nuevoDestino);
        }

        txtFldRuta.clear();
    }

    @FXML
    public void eliminarRutaImagenDestinations() {

        if (imagesRoutesTable.getSelectionModel().getSelectedIndex() >= 0) {

            String selectedRuta = imagesRoutesTable.getSelectionModel().getSelectedItem();
            observableListRutas.remove(selectedRuta);

            String destinoName = txtFldName.getText();

            Optional<Destino> destinoSeleccionadoOpcional = agenciaCliente.getDestinos().stream()
                    .filter(destino -> destino.getName().equals(destinoName))
                    .findFirst();

            destinoSeleccionadoOpcional.ifPresent(destino -> agenciaCliente.eliminarRuta(destinoSeleccionadoOpcional.get(), selectedRuta));

        }

        txtFldRuta.clear();
    }

    @FXML
    private void limpiarCamposDestinations() {
        txtFldName.clear();
        txtFldCity.clear();
        choiceBoxClima.setValue(null);
        imagesRoutesTable.getItems().clear();
        txtFldDescription.clear();
    }

    //----------------------------Packages-----------------------------

    public void onLimpiarCamposPaquetesClick() {
        limpiarCamposPackages();
        destinationsNameTable.setDisable(true);
        destinationsNameTable.getItems().clear();
        addButtonDestinationName.setDisable(true);
        deleteButtonDestinationName.setDisable(true);
        choiceBoxDestinationName.setDisable(true);
    }

    @FXML
    private void agregarElementoPackages() throws RepeatedInformationException, EmptyAttributeException, ErrorEnIngresoFechasException {

        long duration = 0;

        if (datePckrStartDate.getValue() != null && datePckrEndDate.getValue() != null){
            duration = datePckrStartDate.getValue().until(datePckrEndDate.getValue(), ChronoUnit.DAYS);
        }

        if (txtFldPrice.getText().isEmpty() || txtFldPrice.getText() == null || txtFldQuota.getText().isEmpty() || txtFldQuota.getText() == null){
            createAlertError("Error", "Atributos vacios");
            throw new EmptyAttributeException("Atributos vacíos.");
        }

        if ( txtFldPackageName.getText() == null || txtFldPackageName.getText().isEmpty() ||
                txtFldPrice.getText() == null || txtFldPrice.getText().isEmpty() ||
                txtFldQuota.getText() == null || txtFldQuota.getText().isEmpty() ||
                datePckrStartDate.getValue() == null ||
                datePckrEndDate.getValue() == null){

            createAlertError("Error", "Atributos vacios");
            throw new EmptyAttributeException("Atributos vacíos.");
        }

        if (LocalDate.now().isAfter(datePckrEndDate.getValue())){
            createAlertError("Error", "Las fechas fueron incorrectamente colocadas.");
            throw new ErrorEnIngresoFechasException("Las fechas fueron incorrectamente colocadas.");
        }

        TouristPackage nuevoPaquete = TouristPackage.builder()
                .name(txtFldPackageName.getText())
                .price(Double.valueOf(txtFldPrice.getText()))
                .quota(Integer.parseInt(txtFldQuota.getText()))
                .startDate(datePckrStartDate.getValue())
                .endDate(datePckrEndDate.getValue())
                .duration(duration)
                .build();

        if (packageObservableList.stream().anyMatch(touristPackage -> touristPackage.getName().equals(nuevoPaquete.getName()))){
            createAlertError("Error", "Se ha intentado crear un paquete existente.");
            throw new RepeatedInformationException("Se ha intentado crear un paquete existente.");
        }

        packageObservableList.add(nuevoPaquete);
        agenciaCliente.agregarPaquete(nuevoPaquete);

        limpiarCamposPackages();

        destinationsNameTable.setDisable(true);
        addButtonDestinationName.setDisable(true);
        deleteButtonDestinationName.setDisable(true);
        choiceBoxDestinationName.setDisable(true);
        modifyButtonPackages.setDisable(true);

        destinationsNameTable.refresh();
    }

    @FXML
    private void modificarElementoPackages() throws AtributoVacioException {

        if (packagesTable.getSelectionModel().getSelectedIndex() >= 0) {

            TouristPackage selectedPackage = packagesTable.getSelectionModel().getSelectedItem();

            if ( txtFldPackageName.getText() == null || txtFldPackageName.getText().isEmpty() ||
                    Double.parseDouble(txtFldPrice.getText()) < 0 ||
                    Integer.parseInt(txtFldQuota.getText()) < 0 ||
                    datePckrStartDate.getValue() == null ||
                    datePckrEndDate.getValue() == null){

                createAlertError("Error", "Se ha intentado agregar un destino con campos vacios.");
                throw new AtributoVacioException("Se ha intentado agregar un destino con campos vacios.");
            }

            agenciaCliente.modificarPaquete(selectedPackage, txtFldPackageName.getText(), Double.parseDouble(txtFldPrice.getText()), Integer.parseInt(txtFldQuota.getText()), datePckrStartDate.getValue(), datePckrEndDate.getValue());

            selectedPackage.setName(txtFldPackageName.getText());
            selectedPackage.setPrice(Double.parseDouble(txtFldPrice.getText()));
            selectedPackage.setQuota(Integer.parseInt(txtFldQuota.getText()));
            selectedPackage.setStartDate(datePckrStartDate.getValue());
            selectedPackage.setEndDate(datePckrEndDate.getValue());

            limpiarCamposPackages();

            packagesTable.refresh();

            destinationsNameTable.setDisable(true);
            addButtonDestinationName.setDisable(true);
            deleteButtonDestinationName.setDisable(true);
            choiceBoxDestinationName.setDisable(true);
            modifyButtonPackages.setDisable(true);

            destinationsNameTable.refresh();

            System.out.println(selectedPackage);
        }

    }

    @FXML
    private void eliminarElementoPackages() {

        if (packagesTable.getSelectionModel().getSelectedIndex() >= 0) {
            TouristPackage selectedPackage = packagesTable.getSelectionModel().getSelectedItem();

            agenciaCliente.eliminarPaquete( selectedPackage);
            packageObservableList.remove(selectedPackage);

            limpiarCamposPackages();

            destinationsNameTable.setDisable(true);
            addButtonDestinationName.setDisable(true);
            deleteButtonDestinationName.setDisable(true);
            choiceBoxDestinationName.setDisable(true);
            modifyButtonPackages.setDisable(true);

            destinationsNameTable.refresh();
        }
    }

    @FXML
    public void agregarDestinoEnPaquete() throws RepeatedInformationException {

        String paqueteName = txtFldPackageName.getText();

        Optional<TouristPackage> paqueteSeleccionadoOpcional = agenciaCliente.getTouristPackages().stream()
                .filter(touristPackage -> touristPackage.getName().equals(paqueteName))
                .findFirst();

        if (paqueteSeleccionadoOpcional.isPresent()){

            if (observableListDestinationName.stream().anyMatch(string -> string.equals(choiceBoxDestinationName.getSelectionModel().getSelectedItem()))){
                createAlertError("Error", "Se ha intentado agregar un destino existente.");
                throw new RepeatedInformationException("Se ha intentado agregar un destino existente.");
            }

            observableListDestinationName.add(choiceBoxDestinationName.getSelectionModel().getSelectedItem());

            agenciaCliente.agregarDestinoEnPaquete(choiceBoxDestinationName.getSelectionModel().getSelectedItem(), paqueteSeleccionadoOpcional.get());

        } else {

            long duration = 0;

            if (datePckrStartDate.getValue() != null && datePckrEndDate.getValue() != null){
                duration = datePckrStartDate.getValue().until(datePckrEndDate.getValue(), ChronoUnit.DAYS);
            }

            TouristPackage nuevoPaquete = TouristPackage.builder()
                    .name(txtFldPackageName.getText())
                    .price(Double.valueOf(txtFldPrice.getText()))
                    .quota(Integer.parseInt(txtFldQuota.getText()))
                    .startDate(datePckrStartDate.getValue())
                    .endDate(datePckrEndDate.getValue())
                    .duration(duration)
                    .build();

            if (observableListDestinationName.stream().anyMatch(string -> string.equals(choiceBoxDestinationName.getSelectionModel().getSelectedItem()))){
                createAlertError("Error", "Se ha intentado agregar un destino existente.");
                throw new RepeatedInformationException("Se ha intentado agregar un destino existente.");
            }

            observableListDestinationName.add(choiceBoxDestinationName.getSelectionModel().getSelectedItem());

            agenciaCliente.agregarDestinoEnPaquete(choiceBoxDestinationName.getSelectionModel().getSelectedItem(), nuevoPaquete);

            agenciaCliente.agregarPaquete( nuevoPaquete);
        }

    }

    @FXML
    public void eliminarDestinoEnPaquete() {

        if (destinationsNameTable.getSelectionModel().getSelectedIndex() >= 0) {

            String selectedDestino = destinationsNameTable.getSelectionModel().getSelectedItem();
            observableListDestinationName.remove(selectedDestino);

            String packageName = txtFldPackageName.getText();

            Optional<TouristPackage> packageSeleccionadoOpcional = agenciaCliente.getTouristPackages().stream()
                    .filter(touristPackage -> touristPackage.getName().equals(packageName))
                    .findFirst();

            if (packageSeleccionadoOpcional.isPresent()){
                agenciaCliente.eliminarDestinoName(packageSeleccionadoOpcional.get(), selectedDestino);
            }

        }

        choiceBoxDestinationName.getSelectionModel().clearSelection();

    }

    @FXML
    private void limpiarCamposPackages() {
        txtFldPackageName.clear();
        txtFldPrice.clear();
        txtFldQuota.clear();
        datePckrEndDate.setValue(null);
        datePckrStartDate.setValue(null);
        destinationsNameTable.getItems().clear();
    }

    //----------------------------Guides-----------------------------

    public void onLimpiarCamposGuiasClick() {
        limpiarCamposGuias();
        guidesLenguajeTable.setDisable(true);
        guidesLenguajeTable.getItems().clear();
        addLenguajeButton.setDisable(true);
        deleteLenguajeButton.setDisable(true);
        txtFldLenguaje.setDisable(true);
    }

    @FXML
    public void agregarGuiaButton() throws RepeatedInformationException, AtributoVacioException {

        if (txtFldGuideId.getText() == null || txtFldGuideId.getText().isEmpty() ||
                txtFldFullNameGuide.getText() == null || txtFldFullNameGuide.getText().isEmpty() ||
                txtFldExperience.getText() == null || txtFldExperience.getText().isEmpty() ||
                txtFldRating.getText() == null){

            createAlertError("Error", "Se ha intentado agregar un guia con campos vacios.");
            throw new AtributoVacioException("Se ha intentado agregar un guia con campos vacios.");
        }

        TouristGuide nuevoGuia = TouristGuide.builder()
                .id(txtFldGuideId.getText())
                .fullName(txtFldFullNameGuide.getText())
                .languages(new ArrayList<>())
                .experience(txtFldExperience.getText())
                .rating(Double.valueOf(txtFldRating.getText()))
                .rutaFoto(txtFldRutaFoto.getText())
                .build();

        if (touristGuideObservableList.stream().anyMatch(touristGuide -> touristGuide.getId().equals(nuevoGuia.getId()))){
            createAlertError("Error", "Se ha intentado registrar un guia existente.");
            throw new RepeatedInformationException("Se ha intentado registrar un guia existente.");
        }

        touristGuideObservableList.add(nuevoGuia);

        agenciaCliente.agregarGuia( nuevoGuia);

        limpiarCamposGuias();

        guidesLenguajeTable.setDisable(true);
        addLenguajeButton.setDisable(true);
        deleteLenguajeButton.setDisable(true);
        txtFldLenguaje.setDisable(true);

        guidesLenguajeTable.refresh();

    }

    @FXML
    public void modificarGuiaButton() throws AtributoVacioException {

        if (guidesTable.getSelectionModel().getSelectedIndex() >= 0) {

            TouristGuide selectedGuia = guidesTable.getSelectionModel().getSelectedItem();

            if (txtFldGuideId.getText() == null || txtFldGuideId.getText().isEmpty() ||
                    txtFldFullNameGuide.getText() == null || txtFldFullNameGuide.getText().isEmpty() ||
                    txtFldExperience.getText() == null || txtFldExperience.getText().isEmpty() ||
                    txtFldRating.getText() == null ||
                    txtFldRutaFoto.getText() == null || txtFldRutaFoto.getText().isEmpty()){

                createAlertError("Error", "Se ha intentado agregar un guia con campos vacios.");
                throw new AtributoVacioException("Se ha intentado agregar un guia con campos vacios.");
            }

            agenciaCliente.modificarGuia(selectedGuia, txtFldGuideId.getText(), txtFldFullNameGuide.getText(), txtFldExperience.getText(), txtFldRating.getText(), txtFldRutaFoto.getText());

            selectedGuia.setId(txtFldGuideId.getText());
            selectedGuia.setFullName(txtFldFullNameGuide.getText());
            selectedGuia.setExperience(txtFldExperience.getText());
            selectedGuia.setRating(Double.valueOf(txtFldRating.getText()));
            selectedGuia.setRutaFoto(txtFldRutaFoto.getText());

            limpiarCamposGuias();

            guidesTable.refresh();

            guidesLenguajeTable.setDisable(true);
            addLenguajeButton.setDisable(true);
            deleteLenguajeButton.setDisable(true);
            txtFldLenguaje.setDisable(true);

            guidesLenguajeTable.refresh();
        }

    }

    @FXML
    public void eliminarGuiaButton() {

        if (guidesTable.getSelectionModel().getSelectedIndex() >= 0) {

            TouristGuide selectedGuia = guidesTable.getSelectionModel().getSelectedItem();

            agenciaCliente.eliminarGuia( selectedGuia);
            touristGuideObservableList.remove(selectedGuia);

            limpiarCamposGuias();

            guidesLenguajeTable.setDisable(true);
            addLenguajeButton.setDisable(true);
            deleteLenguajeButton.setDisable(true);
            txtFldLenguaje.setDisable(true);

            guidesLenguajeTable.refresh();
        }
    }

    @FXML
    public void agregarLenguajeGuia() throws RepeatedInformationException, AtributoVacioException {

        String guiaID = txtFldGuideId.getText();

        Optional<TouristGuide> guiaSeleccionadoOpcional = agenciaCliente.getTouristGuides().stream()
                .filter(touristGuide -> touristGuide.getId().equals(guiaID))
                .findFirst();

        if (txtFldLenguaje.getText() == null || txtFldLenguaje.getText().isEmpty()){
            createAlertError("Error", "Se ha intentado agregar un idioma vacio.");
            throw new AtributoVacioException("Se ha intentado agregar un idioma vacio.");
        }

        if (guiaSeleccionadoOpcional.isPresent()){

            if (observableListLenguajes.stream().anyMatch(string -> string.equals(txtFldLenguaje.getText()))){
                createAlertError("Error", "Se ha intentado agregar un lenaguje existente.");
                throw new RepeatedInformationException("Se ha intentado agregar un lenaguje existente.");
            }

            observableListLenguajes.add(txtFldLenguaje.getText());

            agenciaCliente.agregarLeaguajeGuia( txtFldLenguaje.getText(), guiaSeleccionadoOpcional.get());
        } else {

            TouristGuide nuevoGuia = TouristGuide.builder()
                    .id(txtFldGuideId.getText())
                    .fullName(txtFldFullNameGuide.getText())
                    .experience(txtFldExperience.getText())
                    .rating(Double.valueOf(txtFldRating.getText()))
                    .build();

            if (observableListLenguajes.stream().anyMatch(string -> string.equals(txtFldLenguaje.getText()))){
                createAlertError("Error", "Se ha intentado agregar un lenaguje existente.");
                throw new RepeatedInformationException("Se ha intentado agregar un lenaguje existente.");
            }

            observableListLenguajes.add(txtFldLenguaje.getText());

            agenciaCliente.agregarLeaguajeGuia( txtFldLenguaje.getText(), nuevoGuia);

            agenciaCliente.agregarGuia( nuevoGuia);
        }

        txtFldLenguaje.clear();
    }

    @FXML
    public void elimiarLenguajeGuia() {

        if (guidesLenguajeTable.getSelectionModel().getSelectedIndex() >= 0) {

            String selectedLenguaje = guidesLenguajeTable.getSelectionModel().getSelectedItem();
            observableListLenguajes.remove(selectedLenguaje);

            String guideID = txtFldGuideId.getText();

            Optional<TouristGuide> guideSeleccionadoOpcional = agenciaCliente.getTouristGuides().stream()
                    .filter(touristGuide -> touristGuide.getId().equals(guideID))
                    .findFirst();

            guideSeleccionadoOpcional.ifPresent(touristGuide -> agenciaCliente.eliminarLenguaje(guideSeleccionadoOpcional.get(), selectedLenguaje));

        }

        choiceBoxDestinationName.getSelectionModel().clearSelection();

    }

    @FXML
    public void seleccionarImagenGuia() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.jpeg", "*.png", "*.gif", "*.bmp")
        );

        File imagenSeleccionada = fileChooser.showOpenDialog(getStage());

        if (imagenSeleccionada != null) {
            String rutaImagen = imagenSeleccionada.getAbsolutePath();
            txtFldRutaFoto.setText(rutaImagen);
        }
    }

    private void limpiarCamposGuias() {
        txtFldGuideId.clear();
        txtFldFullNameGuide.clear();
        txtFldExperience.clear();
        txtFldRating.clear();
        txtFldRutaFoto.clear();
        guidesLenguajeTable.getItems().clear();
    }


    @FXML
    private void handleButtonAction(ActionEvent event){

        if (event.getTarget() == manageDestinationsButton){visibilities(false,true,false,false,false,false);}
        if (event.getTarget() == managePackagesButton){

            List<String> destinationNames = agenciaCliente.getDestinos().stream()
                    .map(Destino::getName)
                    .toList();

            if (!destinationNames.equals(choiceBoxDestinationName.getItems())) {
                choiceBoxDestinationName.getItems().setAll(destinationNames);
            }

            visibilities(false,false,false,true,false,false);
        }
        if (event.getTarget() == manageGuidesButton) {visibilities(false,false,false,false,true,false);}
        if (event.getTarget() == statisticsButton) {visibilities(false,false,false,false,false,true);}

    }

    public void visibilities(boolean pane1, boolean pane2 , boolean pane3, boolean pane4, boolean pane5, boolean pane6 ){

        principalPane.setVisible(pane1);
        manageDestinationsPane.setVisible(pane2);
        managePackagesPane.setVisible(pane4);
        manageGuidesPane.setVisible(pane5);
        statisticsPane.setVisible(pane6);

    }

    public void onBackButtonClick(MouseEvent mouseEvent) {

        if (mouseEvent.getTarget() == imgViewBackDestinationsButton){

            limpiarCamposDestinations();

            imagesRoutesTable.setDisable(true);
            examinarRutaButton.setDisable(true);
            addButtonImageDestination.setDisable(true);
            deleteButtonImageDestination.setDisable(true);
            txtFldRuta.setDisable(true);
            modifyButtonDestination.setDisable(true);

            visibilities(true, false, false, false, false, false);

        }
        if (mouseEvent.getTarget() == imgViewBackPackagesButton){

            limpiarCamposPackages();

            destinationsNameTable.setDisable(true);
            addButtonDestinationName.setDisable(true);
            deleteButtonDestinationName.setDisable(true);
            choiceBoxDestinationName.setDisable(true);
            modifyButtonPackages.setDisable(true);

            visibilities(true, false,false, false, false, false);
        }
        if (mouseEvent.getTarget() == imgViewBackGuidesButton){

            limpiarCamposGuias();

            guidesLenguajeTable.setDisable(true);
            addLenguajeButton.setDisable(true);
            deleteLenguajeButton.setDisable(true);
            txtFldLenguaje.setDisable(true);

            visibilities(true, false,false, false, false, false);
        }
        if (mouseEvent.getTarget() == imgViewBackStatisticsButton){visibilities(true, false,false, false, false, false);}

    }

    public void onExitButtonClick() throws IOException {

        File url = new File("src/main/resources/views/homeView.fxml");
        FXMLLoader loader = new FXMLLoader(url.toURL());
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setResizable(false);
        stage.show();

        Stage stage1 = (Stage) imgViewExitButton.getScene().getWindow();
        stage1.close();

    }

    public void createAlertError(String titleError, String contentError){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titleError);
        alert.setContentText(contentError);
        alert.show();
    }
}
