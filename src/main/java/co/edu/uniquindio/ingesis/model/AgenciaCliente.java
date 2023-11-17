package co.edu.uniquindio.ingesis.model;

import co.edu.uniquindio.ingesis.datos.*;
import co.edu.uniquindio.ingesis.socket.Mensaje;
import lombok.extern.java.Log;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Log
public class AgenciaCliente {

    private static AgenciaCliente agenciaCliente;

    private static final String HOST = "localhost";
    private static final int PUERTO = 1234;

    public static AgenciaCliente getInstance(){
        if(agenciaCliente == null){
            agenciaCliente = new AgenciaCliente();
        }

        return agenciaCliente;
    }


    public String registrarCliente(String userID, String contrasenia, String nombre, String mail, String telefono, String residencia){

        //Se intenta abrir una conexión a un servidor remoto usando un objeto Socket
        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            //Se crea un cliente con los datos obtenidos desde la ventana
            Client cliente = Client.builder()
                    .userId(userID)
                    .password(contrasenia)
                    .fullName(nombre)
                    .mail(mail)
                    .phoneNumber(telefono)
                    .residence(residencia)
                    .build();

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .contenido(cliente)
                    .tipo("agregarCliente")
                    .build() );

            //Obtenemos la respuesta del servidor
            String respuesta = in.readObject().toString();
            
            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String hacerReservacion(String clientID, String mail, String selectedItem, String text, String text1) {

        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            //Se crea un cliente con los datos obtenidos desde la ventana
            ReservaDatos reservaDatos = ReservaDatos.builder()
                    .clientID(clientID)
                    .mailClient(mail)
                    .selectedGuia(selectedItem)
                    .nroCupos(text)
                    .selectedPackageName(text1)
                    .build();

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .tipo("hacerReservacion")
                    .contenido(reservaDatos).build() );

            //Obtenemos la respuesta del servidor
            String respuesta = in.readObject().toString();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public List<Reservation> getReservations() {

        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .tipo("getReservations").build() );

            //Obtenemos la respuesta del servidor
            List<Reservation>  respuesta = (List<Reservation> ) in.readObject();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }


    }

    public List<TouristGuide> getTouristGuides() {
        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .tipo("getTouristGuide").build() );

            //Obtenemos la respuesta del servidor
            List<TouristGuide>  respuesta = (List<TouristGuide> ) in.readObject();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<Destino> getDestinos() {

        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .tipo("getDestinos").build() );

            //Obtenemos la respuesta del servidor
            List<Destino>  respuesta = (List<Destino> ) in.readObject();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<TouristPackage> getTouristPackages() {
        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .tipo("getTouristPackage").build() );

            //Obtenemos la respuesta del servidor
            List<TouristPackage>  respuesta = (List<TouristPackage> ) in.readObject();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String agregarDestino(Destino nuevoDestino) {
        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .contenido(nuevoDestino)
                    .tipo("agregarDestino").build() );

            //Obtenemos la respuesta del servidor
            String respuesta = in.readObject().toString();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String modificarDestino(Destino destino, String nuevoName, String nuevaCiudad, String nuevaDescripcion, String nuevoClima) {
        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            ModificarDestinoDatos modificarDestinoDatos = ModificarDestinoDatos.builder()
                    .selectedDestino(destino)
                    .nuevoName(nuevoName)
                    .nuevaCiudad(nuevaCiudad)
                    .nuevaDescripcion(nuevaDescripcion)
                    .nuevoClima(nuevoClima).build();


            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .contenido(modificarDestinoDatos)
                    .tipo("modificarDestino").build() );

            //Obtenemos la respuesta del servidor
            String respuesta = in.readObject().toString();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String eliminarDestino( Destino selectedDestino) {
        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .contenido(selectedDestino)
                    .tipo("eliminarDestino").build() );

            //Obtenemos la respuesta del servidor
            String respuesta = in.readObject().toString();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String agregarImagenDestino(String ruta, Destino destino) {

        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            AgregarImagenDestinoDatos agregarImagenDestinoDatos = AgregarImagenDestinoDatos.builder()
                    .ruta(ruta)
                    .destino(destino)
                    .build();

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .contenido(agregarImagenDestinoDatos)
                    .tipo("agregarImagenDestino").build() );

            //Obtenemos la respuesta del servidor
            String respuesta = in.readObject().toString();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String eliminarRuta(Optional<Destino> destinoSeleccionadoOpcional, String selectedRuta) {
        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            EliminarRutaDatos eliminarRutaDatos = EliminarRutaDatos.builder()
                    .destinoSeleccionadoOpcional(destinoSeleccionadoOpcional)
                    .selectedRuta(selectedRuta)
                    .build();

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .contenido(eliminarRutaDatos)
                    .tipo("eliminarRuta").build() );

            //Obtenemos la respuesta del servidor
            String respuesta = in.readObject().toString();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String agregarPaquete(TouristPackage nuevoPaquete) {
        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .contenido(nuevoPaquete)
                    .tipo("agregarPaquete").build() );

            //Obtenemos la respuesta del servidor
            String respuesta = in.readObject().toString();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String modificarPaquete(TouristPackage selectedPackage, String nombrePaquete, double precio, int cupo, LocalDate fechaInicio, LocalDate fechaFin) {
        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            ModificarPaquetesDatos modificarPaquetesDatos = ModificarPaquetesDatos.builder()
                    .selectedPackage(selectedPackage)
                    .nombrePaquete(nombrePaquete)
                    .precio(precio)
                    .cupo(cupo)
                    .fechaInicio(fechaInicio)
                    .fechaFin(fechaFin)
                    .build();

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .contenido(modificarPaquetesDatos)
                    .tipo("modificarPaquete").build() );

            //Obtenemos la respuesta del servidor
            String respuesta = in.readObject().toString();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String eliminarPaquete(TouristPackage selectedPackage) {
        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .contenido(selectedPackage)
                    .tipo("eliminarPaquete").build() );

            //Obtenemos la respuesta del servidor
            String respuesta = in.readObject().toString();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String agregarDestinoEnPaquete(String selectedDestino, TouristPackage touristPackage) {
        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            AgregarDestinoEnPaqueteDatos agregarDestinoEnPaqueteDatos = AgregarDestinoEnPaqueteDatos.builder()
                    .selectedDestino(selectedDestino)
                    .touristPackage(touristPackage)
                    .build();

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .contenido(agregarDestinoEnPaqueteDatos)
                    .tipo("agregarDestinoEnPaquete").build() );

            //Obtenemos la respuesta del servidor
            String respuesta = in.readObject().toString();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String eliminarDestinoName(Optional<TouristPackage> packageSeleccionadoOpcional, String selectedDestino) {
        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            EliminarDestinoName eliminarDestinoName = EliminarDestinoName.builder()
                    .packageSeleccionadoOpcional(packageSeleccionadoOpcional)
                    .selectedDestino(selectedDestino)
                    .build();

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .contenido(eliminarDestinoName)
                    .tipo("eliminarDestinoName").build() );

            //Obtenemos la respuesta del servidor
            String respuesta = in.readObject().toString();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String agregarGuia(TouristGuide nuevoGuia) {
        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .contenido(nuevoGuia)
                    .tipo("agregarGuia").build() );

            //Obtenemos la respuesta del servidor
            String respuesta = in.readObject().toString();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String modificarGuia(TouristGuide selectedGuia, String guideID, String fullNameGuide, String experience, String rating) {
        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            ModificarGuiaDatos modificarGuiaDatos = ModificarGuiaDatos.builder()
                    .selectedGuia(selectedGuia)
                    .guideID(guideID)
                    .fullNameGuide(fullNameGuide)
                    .experience(experience)
                    .rating(rating)
                    .build();

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .contenido(modificarGuiaDatos)
                    .tipo("modificarGuia").build() );

            //Obtenemos la respuesta del servidor
            String respuesta = in.readObject().toString();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String eliminarGuia(TouristGuide selectedGuia) {
        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .contenido(selectedGuia)
                    .tipo("eliminarGuia").build() );

            //Obtenemos la respuesta del servidor
            String respuesta = in.readObject().toString();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String agregarLeaguajeGuia(String lenguaje, TouristGuide touristGuide) {
        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            AgregarLenguajeGuiaDatos agregarLenguajeGuiaDatos = AgregarLenguajeGuiaDatos.builder()
                    .lenguaje(lenguaje)
                    .touristGuide(touristGuide)
                    .build();

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .contenido(agregarLenguajeGuiaDatos)
                    .tipo("agregarLenguajeGuia").build() );

            //Obtenemos la respuesta del servidor
            String respuesta = in.readObject().toString();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String eliminarLenguaje(Optional<TouristGuide> guideSeleccionadoOpcional, String selectedLenguaje) {
        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            EliminarLenguajeDatos eliminarLenguajeDatos = EliminarLenguajeDatos.builder()
                    .guideSeleccionadoOpcional(guideSeleccionadoOpcional)
                    .selectedLenguaje(selectedLenguaje)
                    .build();

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .contenido(eliminarLenguajeDatos)
                    .tipo("eliminarLenguaje").build() );

            //Obtenemos la respuesta del servidor
            String respuesta = in.readObject().toString();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<Client> getClients() {
        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .tipo("getClientes").build() );

            //Obtenemos la respuesta del servidor
            List<Client>  respuesta = (List<Client> ) in.readObject();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String calificarGuia(TouristGuide touristGuide, String estrellas) {
        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            CalificarGuiasDatos calificarGuiasDatos = CalificarGuiasDatos.builder()
                    .touristGuide(touristGuide)
                    .estrellas(estrellas)
                    .build();

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .contenido(calificarGuiasDatos)
                    .tipo("calificarGuia").build() );

            //Obtenemos la respuesta del servidor
            String respuesta = in.readObject().toString();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String calificarDestino(Destino destino, String comentario, String estrellas) {
        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            CalificarDestinoDatos calificarDestinoDatos = CalificarDestinoDatos.builder()
                    .destino(destino)
                    .comentario(comentario)
                    .estrellas(estrellas)
                    .build();

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .contenido(calificarDestinoDatos)
                    .tipo("calificarDestino").build() );

            //Obtenemos la respuesta del servidor
            String respuesta = in.readObject().toString();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String cancelarReserva(Reservation reservation) {
        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .contenido(reservation)
                    .tipo("cancelarReserva").build() );

            //Obtenemos la respuesta del servidor
            String respuesta = in.readObject().toString();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String confirmarReserva(Reservation reservation) {
        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .contenido(reservation)
                    .tipo("confirmarReserva").build() );

            //Obtenemos la respuesta del servidor
            String respuesta = in.readObject().toString();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String modificarPerfil(String clientID, String nuevoNombre, String nuevoMail, String nuevoNumero, String nuevaResidencia) {
        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            ModificarPerfilDatos modificarPerfilDatos = ModificarPerfilDatos.builder()
                    .clientID(clientID)
                    .nuevoNombre(nuevoNombre)
                    .nuevoMail(nuevoMail)
                    .nuevoNumero(nuevoNumero)
                    .nuevaResidencia(nuevaResidencia)
                    .build();

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .contenido(modificarPerfilDatos)
                    .tipo("modificarPerfil").build() );

            //Obtenemos la respuesta del servidor
            String respuesta = in.readObject().toString();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String LogIn(String Id, String password) {
        try (Socket socket = new Socket(HOST, PUERTO)){

            //Se crean flujos de datos de entrada y salida para comunicarse a través del socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            LogInDatos logInDatos = LogInDatos.builder()
                    .Id(Id)
                    .password(password)
                    .build();

            //Se envía un mensaje al servidor con los datos de la petición
            out.writeObject( Mensaje.builder()
                    .contenido(logInDatos)
                    .tipo("logIn").build() );

            //Obtenemos la respuesta del servidor
            String  respuesta = (String ) in.readObject();

            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();

            return respuesta;

        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

