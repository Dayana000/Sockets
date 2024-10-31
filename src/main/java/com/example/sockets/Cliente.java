package com.example.sockets;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Cliente extends Application {

    private TextField usernameField;
    private PasswordField passwordField;
    private Label statusLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cliente - Inicio de Sesión");

        usernameField = new TextField();
        usernameField.setPromptText("Usuario");

        passwordField = new PasswordField();
        passwordField.setPromptText("Contraseña");

        Button loginButton = new Button("Iniciar Sesión");
        loginButton.setOnAction(e -> iniciarSesion());

        Button registerButton = new Button("Registrarse");
        registerButton.setOnAction(e -> statusLabel.setText("Función de registro no implementada."));

        statusLabel = new Label();

        VBox vbox = new VBox(10, usernameField, passwordField, loginButton, registerButton, statusLabel);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void iniciarSesion() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try (Socket socket = new Socket("192.168.195.97", 5000);
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            output.println(username);
            output.println(password);

            String response = input.readLine();
            statusLabel.setText(response);

        } catch (IOException e) {
            statusLabel.setText("Error de conexión con el servidor.");
            e.printStackTrace();
        }
    }
}

