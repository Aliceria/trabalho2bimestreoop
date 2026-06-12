package com.mycompany.trabalho2bimestre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PedidoDAO {

    public void finalizarPedido(int idCliente, int idProduto, int quantidade, String formaPagamento) {

        Connection conn = null;

        try {
            conn = Conexao.conectar();
            conn.setAutoCommit(false);

            double precoProduto = 0;
            int estoqueAtual = 0;

            String sqlProduto = "SELECT preco, estoque FROM produto WHERE id = ?";
            PreparedStatement stmtProduto = conn.prepareStatement(sqlProduto);
            stmtProduto.setInt(1, idProduto);
            ResultSet rsProduto = stmtProduto.executeQuery();

            if (rsProduto.next()) {
                precoProduto = rsProduto.getDouble("preco");
                estoqueAtual = rsProduto.getInt("estoque");
            } else {
                System.out.println("Produto nao encontrado!");
                conn.rollback();
                return;
            }

            if (quantidade <= 0) {
                System.out.println("Quantidade invalida!");
                conn.rollback();
                return;
            }

            if (estoqueAtual < quantidade) {
                System.out.println("Estoque insuficiente!");
                conn.rollback();
                return;
            }

            double total = precoProduto * quantidade;

            String sqlPedido = "INSERT INTO pedido(id_cliente, data_pedido, total, status) VALUES (?, CURRENT_DATE, ?, ?)";

            PreparedStatement stmtPedido = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);
            stmtPedido.setInt(1, idCliente);
            stmtPedido.setDouble(2, total);
            stmtPedido.setString(3, "Finalizado");
            stmtPedido.executeUpdate();

            ResultSet rsPedido = stmtPedido.getGeneratedKeys();
            int idPedido = 0;

            if (rsPedido.next()) {
                idPedido = rsPedido.getInt(1);
            }

            String sqlItem = "INSERT INTO item_pedido(id_pedido, id_produto, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";

            PreparedStatement stmtItem = conn.prepareStatement(sqlItem);
            stmtItem.setInt(1, idPedido);
            stmtItem.setInt(2, idProduto);
            stmtItem.setInt(3, quantidade);
            stmtItem.setDouble(4, precoProduto);
            stmtItem.executeUpdate();

            String sqlEstoque = "UPDATE produto SET estoque = estoque - ? WHERE id = ?";

            PreparedStatement stmtEstoque = conn.prepareStatement(sqlEstoque);
            stmtEstoque.setInt(1, quantidade);
            stmtEstoque.setInt(2, idProduto);
            stmtEstoque.executeUpdate();

            String sqlPagamento = "INSERT INTO pagamento(id_pedido, forma_pagamento, valor, status) VALUES (?, ?, ?, ?)";

            PreparedStatement stmtPagamento = conn.prepareStatement(sqlPagamento);
            stmtPagamento.setInt(1, idPedido);
            stmtPagamento.setString(2, formaPagamento);
            stmtPagamento.setDouble(3, total);
            stmtPagamento.setString(4, "Pago");
            stmtPagamento.executeUpdate();

            conn.commit();

            System.out.println("Pedido finalizado com sucesso!");
            System.out.println("Total: R$ " + total);

        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (Exception erroRollback) {
                System.out.println("Erro no rollback: " + erroRollback.getMessage());
            }

            System.out.println("Erro: " + e.getMessage());

        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println("Erro ao fechar conexao: " + e.getMessage());
            }
        }
    }
}