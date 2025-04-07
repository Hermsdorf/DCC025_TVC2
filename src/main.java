package src;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        // Limpa o console
        clearConsole();

        System.out.print("\n");
        System.out.print("Inicializando Farmacia...");
        Farmacia farmacia = new Farmacia();
        System.out.println("Inicializando Sistema Almoxarifado...");
        Almoxarifado almoxarifado = new Almoxarifado();
        System.out.print("\n");

        almoxarifado.listarEstoque();

        System.out.print("\n");
        Scanner scanner = new Scanner(System.in);
        int opcao;
        boolean sair = true;
        while (sair) {

            System.out.print("\n\n");

            System.out.print("|---------------MENU--------------|\n\n");
            System.out.print("|---------------------------------|\n");
            System.out.print("| Opção 1 - Entrada Produto       |\n");
            System.out.print("| Opção 2 - Cadastrar Fornecedor  |\n");
            System.out.print("| Opção 3 - Listar Estoque        |\n");
            System.out.print("| Opção 4 - Listar Fornecedores   |\n");
            System.out.print("| Opção 5 - Retirada Produto      |\n");
            System.out.print("| Opção 0 - Sair                  |\n");
            System.out.print("|---------------------------------|\n");
            System.out.print("Digite uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {

                case 1:
                    System.out.print("Digite o nome do produto: ");
                    String nomeProduto = scanner.nextLine();
                    Produto produto = new Produto(nomeProduto);
                    produto.setId(almoxarifado.estoque.produtos.size() + 1); // Atribui um ID único ao produto

                    System.out.println("Selecione um dos fornecedores cadastrados! ");
                    // Listando fornecedores cadastrados
                    System.out.println("ID - Nome");
                    System.out.println("0 - Cadastrat fornecedor");
                    almoxarifado.listaSimplesFornecedores();
                    System.out.print("Digite o ID do fornecedor ou 0 para cadastrar um novo: ");
                    int idFornecedor = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer do scanner
                    if (idFornecedor == 0) {
                        almoxarifado.cadastrarFornecedor();
                        idFornecedor = almoxarifado.fornecedores.getLast().getId(); // Último fornecedor cadastrado
                    }
                    produto.setFornecedor_id(idFornecedor);

                    System.out.print("Digite o tamanho do lote do produto(Unidades): ");
                    produto.setQtd(scanner.nextInt());
                    scanner.nextLine(); // Limpa o buffer do scanner
                    almoxarifado.entradaProduto(produto);

                    clearConsole();
                    System.out.print("\n");

                    System.out.println("Produto adicionado ao estoque com sucesso!");
                    almoxarifado.listarEstoque();
                    System.out.print("\n");
                    break;

                case 2:
                    almoxarifado.cadastrarFornecedor();
                    clearConsole();
                    System.out.print("\n");
                    System.out.println("Fornecedor cadastrado com sucesso!");
                    almoxarifado.listarFornecedores();
                    System.out.print("\n");
                    break;

                case 3:
                    clearConsole();
                    System.out.print("\n");
                    almoxarifado.listarEstoque();
                    break;
                case 4:
                    clearConsole();
                    System.out.print("\n");
                    almoxarifado.listarFornecedores();
                    break;
                
                case 5:
                    almoxarifado.listarEstoque();
                    System.out.print("Digite o ID do produto a ser retirado: ");
                    int idProduto = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer do scanner
                    
                    System.out.print("Digite a quantidade a ser retirada: ");
                    int quantidadeRetirada = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer do scanner

                    System.out.println("Qual setor deseja retirar o produto?");
                    System.out.println("1 - Farmacia");
                    int setor = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer do scanner
                    if(setor == 1) {
                        almoxarifado.saidaProduto(idProduto,quantidadeRetirada, farmacia.retornaSetor());
                        System.out.println("Produto retirado pela Farmacia!");
                    } else {
                        System.out.println("Setor inválido!");
                    }
                    clearConsole();
                    System.out.print("\n");
                    almoxarifado.listarEstoque();
                    break;

                case 0:
                    System.out.println("Saindo do sistema...");
                    sair = false;
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        }

        System.out.println("Sistema encerrado.");

    }

    public final static void clearConsole() {
        System.out.print("\033[H\033[2J");
    }

}
