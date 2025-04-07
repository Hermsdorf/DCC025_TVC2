package src;

import java.util.Scanner;

public class main {

    public static final int FARMACIA = 1;  
    public static final int CENTRO_CIRURGICO = 2;
    public static final int NUTRICAO = 3;
    public static final int ALMOXARIFADO = 4;
    public static void main(String[] args) {

        // Limpa o console
        clearConsole();

        System.out.print("\n");
        System.out.print("Inicializando Farmacia...");
        System.out.print("\n");
        Farmacia farmacia = new Farmacia();
        System.out.println("Inicializando Sistema Almoxarifado...");
        Almoxarifado almoxarifado = new Almoxarifado();
        System.out.print("\n");

        //almoxarifado.listarEstoque();

        //System.out.print("\n");
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
                    produto.setId(0); // Atribui um ID único ao produto

                    System.out.println("Selecione um dos fornecedores cadastrados! ");
                    // Listando fornecedores cadastrados
                    System.out.println("ID - Nome");
                    System.out.println("0 - Cadastrat fornecedor");
                    almoxarifado.listaSimplesFornecedores();
                    System.out.print("Digite o ID do fornecedor ou 0 para cadastrar um novo: ");
                    int idFornecedor = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer do scanner
                    if (idFornecedor == 0) {
                        idFornecedor = almoxarifado.cadastrarFornecedor();
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
                    System.out.println("Selecione o setor desejado:");
                    almoxarifado.listaSetores();
                    System.out.print("Digite o ID do setor: ");
                    int idSetor_estoque = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer do scanner
                    if (idSetor_estoque == FARMACIA) {
                        clearConsole();
                        System.out.print("\n");
                        farmacia.listarEstoque();
                    } else if (idSetor_estoque == CENTRO_CIRURGICO) {
                        clearConsole();
                        System.out.print("\n");
                        System.out.println("Centro Cirúrgico não implementado.");
                    } else if (idSetor_estoque == NUTRICAO) {
                        clearConsole();
                        System.out.print("\n");
                        System.out.println("Nutrição não implementado.");
                    }else if (idSetor_estoque == ALMOXARIFADO) {
                        clearConsole();
                        System.out.print("\n");
                        almoxarifado.listarEstoque();
                    } else {
                        clearConsole();
                        System.out.print("\n");
                        System.out.println("Setor inválido.");
                    }
                    System.out.print("\n");
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
                    
                    Produto produtoRetirado = almoxarifado.getProduto(idProduto);

                    System.out.print("Digite a quantidade a ser retirada: ");
                    int quantidadeRetirada = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer do scanner

                    System.out.println("Qual setor deseja retirar o produto?");
                    almoxarifado.listaSetores();

                    System.out.print("Digite o ID do setor: ");
                    int idSetor = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer do scanner
                    if(idSetor == FARMACIA)
                    {
                        almoxarifado.saidaProduto(produtoRetirado, quantidadeRetirada, farmacia);
                        farmacia.listarEstoque();
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
