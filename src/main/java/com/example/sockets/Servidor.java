package com.example.sockets;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000, 50, InetAddress.getByName("192.168.195.97"))) {
            System.out.println("Servidor iniciado en 192.168.195.97:5000");

            while (true) {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clienteSocket.getInetAddress().getHostAddress());

                BufferedReader input = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
                PrintWriter output = new PrintWriter(clienteSocket.getOutputStream(), true);

                String username = input.readLine();
                String password = input.readLine();

                if (username.equals("admin") && password.equals("12345")) {
                    output.println("OK:)");
                } else {
                    output.println("Credenciales incorrectas");
                }

                clienteSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
