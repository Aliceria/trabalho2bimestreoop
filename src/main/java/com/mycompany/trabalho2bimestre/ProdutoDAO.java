package com.mycompany.trabalho2bimestre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProdutoDAO {

    public void cadastrar(Produto produto) {

        if (produto.getPreco() <= 0) {
            System.out.println("Preco invalido!");
            return;
        }

        if (produto.getEstoque() < 0) {
            System.out.println("Estoque invalido!");
            return;
        }

        String sql =
        "INSERT INTO produto(nome, preco, estoque) VALUES (?, ?, ?)";

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getEstoque());

            stmt.executeUpdate();

            System.out.println("Produto cadastrado!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void listar() {

        String sql = "SELECT * FROM produto ORDER BY id";

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {

                System.out.println(
                    rs.getInt("id") + " - " +
                    rs.getString("nome") + " - R$ " +
                    rs.getDouble("preco") + " - Estoque: " +
                    rs.getInt("estoque")
                );

            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void atualizar(int id, String nome, double preco, int estoque) {

        String sql =
        "UPDATE produto SET nome = ?, preco = ?, estoque = ? WHERE id = ?";

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, nome);
            stmt.setDouble(2, preco);
            stmt.setInt(3, estoque);
            stmt.setInt(4, id);

            stmt.executeUpdate();

            System.out.println("Produto atualizado!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void excluir(int id) {

        String sql = "DELETE FROM produto WHERE id = ?";

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Produto excluido!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}