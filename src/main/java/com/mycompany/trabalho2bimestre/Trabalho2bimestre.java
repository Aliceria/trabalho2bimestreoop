package com.mycompany.trabalho2bimestre;

import java.util.Scanner;

public class Trabalho2bimestre {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ClienteDAO clienteDAO = new ClienteDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        PedidoDAO pedidoDAO = new PedidoDAO();

        int opcao;

        do {

            System.out.println("\n=== SISTEMA DE LANCHONETE ===");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Listar clientes");
            System.out.println("3 - Cadastrar produto");
            System.out.println("4 - Listar produtos");
            System.out.println("5 - Atualizar produto");
            System.out.println("6 - Excluir produto");
            System.out.println("7 - Finalizar pedido");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:

                    System.out.print("Nome do cliente: ");
                    String nomeCliente = scanner.nextLine();

                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();

                    Cliente cliente = new Cliente(nomeCliente, telefone);
                    clienteDAO.cadastrar(cliente);

                    break;

                case 2:

                    clienteDAO.listar();

                    break;

                case 3:

                    System.out.print("Nome do produto: ");
                    String nomeProduto = scanner.nextLine();

                    System.out.print("Preco: ");
                    double preco = scanner.nextDouble();

                    System.out.print("Estoque: ");
                    int estoque = scanner.nextInt();

                    Produto produto = new Produto(nomeProduto, preco, estoque);
                    produtoDAO.cadastrar(produto);

                    break;

                case 4:

                    produtoDAO.listar();

                    break;

                case 5:

                    System.out.print("ID do produto: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();

                    System.out.print("Novo preco: ");
                    double novoPreco = scanner.nextDouble();

                    System.out.print("Novo estoque: ");
                    int novoEstoque = scanner.nextInt();

                    produtoDAO.atualizar(
                            idAtualizar,
                            novoNome,
                            novoPreco,
                            novoEstoque
                    );

                    break;

                case 6:

                    System.out.print("ID do produto: ");
                    int idExcluir = scanner.nextInt();

                    produtoDAO.excluir(idExcluir);

                    break;

                case 7:

                    System.out.print("ID do cliente: ");
                    int idCliente = scanner.nextInt();

                    System.out.print("ID do produto: ");
                    int idProduto = scanner.nextInt();

                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Forma de pagamento: ");
                    String formaPagamento = scanner.nextLine();

                    pedidoDAO.finalizarPedido(
                            idCliente,
                            idProduto,
                            quantidade,
                            formaPagamento
                    );

                    break;

                case 0:

                    System.out.println("Sistema encerrado.");

                    break;

                default:

                    System.out.println("Opcao invalida!");

                    break;
            }

        } while (opcao != 0);

    }
}