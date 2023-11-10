package co.edu.uniquindio.agenciacliente.model;

import co.edu.uniquindio.agenciacliente.socket.Mensaje;
import lombok.extern.java.Log;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@Log
public class AgenciaCliente {

    private static final String HOST = "localhost";
    private static final int PUERTO = 1234;

    public static void registrarCliente(String userID, String contrasenia, String nombre, String mail, String telefono, String residencia){
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
                    .tipo("agregarCliente")
                    .contenido(cliente).build() );
            //Obtenemos la respuesta del servidor
            Object respuesta = in.readObject();
            //Se imprime la respuesta del servidor. Según la respuesta se podría lanzar una excepción
            System.out.println(respuesta);
            //Se cierran los flujos de entrada y de salida para liberar los recursos
            in.close();
            out.close();
        }catch (Exception e){
            log.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
