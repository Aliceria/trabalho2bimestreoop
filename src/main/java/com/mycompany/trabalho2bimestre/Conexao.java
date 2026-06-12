package com.mycompany.trabalho2bimestre;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    public static Connection conectar() {
        try {
            String url = "jdbc:postgresql://localhost:5432/lanchonete";
            String usuario = "postgres";
            String senha = "123";

            Connection conn = DriverManager.getConnection(url, usuario, senha);

            System.out.println("Conectado com sucesso!");
            return conn;

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }
}