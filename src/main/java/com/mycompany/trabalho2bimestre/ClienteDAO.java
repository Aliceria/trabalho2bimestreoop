package com.mycompany.trabalho2bimestre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClienteDAO {

    public void cadastrar(Cliente cliente) {

        String sql = "INSERT INTO cliente(nome, telefone) VALUES (?, ?)";

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());

            stmt.executeUpdate();

            System.out.println("Cliente cadastrado!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void listar() {

        String sql = "SELECT * FROM cliente ORDER BY id";

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " - " +
                    rs.getString("nome") + " - " +
                    rs.getString("telefone")
                );
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void atualizar(int id, String nome, String telefone) {

        String sql = "UPDATE cliente SET nome = ?, telefone = ? WHERE id = ?";

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, nome);
            stmt.setString(2, telefone);
            stmt.setInt(3, id);

            stmt.executeUpdate();

            System.out.println("Cliente atualizado!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void excluir(int id) {

        String sql = "DELETE FROM cliente WHERE id = ?";

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Cliente excluído!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}