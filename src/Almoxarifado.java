package src;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Almoxarifado implements Setor {
    Almoxarifado() {

        this.estoque = new Estoque();
        this.fornecedores = new ArrayList<>();
        this.carregarDados(); // Carrega os dados do arquivo CSV ao iniciar o sistema
    }

    @Override
    public int getId()
    {
        return 0; // ID do Almoxarifado é 0, pois não é um setor específico
    }

    // Atributos
    Estoque estoque;
    List<Fornecedor> fornecedores;
    private static final String PRODUTOS_CSV = "data/produtos.csv";
    private static final String FORNECEDORES_CSV = "data/fornecedores.csv";

    public void entradaProduto(Produto produto, Fornecedor fornecedor) {

        // Verifica dse o fornecedor do produto é o usual
        if (fornecedor == null) 
        {
            if (produto.getFornecedor_id() < 0 || produto.getFornecedor_id() > fornecedores.size()) 
            {
                produto.setFornecedor_id(0); // Fornecedor não informado                
            } 
            else 
            {
                fornecedor = fornecedores.get(produto.getFornecedor_id() - 1); // Fornecedor informado
            }
        }
        else 
        {
            produto.setFornecedor(fornecedor);
        }

        if(estoque.verificaProduto(produto.getId()))
        {
            estoque.produtos.get(produto.getId() - 1).setQtd(estoque.produtos.get(produto.getId() - 1).getQtd() + produto.getQtd());
            System.out.println("Produto já existe no estoque. Atualizando quantidade.");
        }
        else
        {
            estoque.adicionarProduto(produto);
            System.out.println("Produto adicionado ao estoque.");
        }


        salvarDados(); // Salva os produtos no arquivo CSV

    }

    @Override
    public void entradaProduto(Produto produto) {
        entradaProduto(produto, null);
    }

    @Override
    public void saidaProduto(Produto produto) 
    {


    }

    public void saidaProduto(int id,int qtd, Setor setor)
    {
        if(estoque.verificaProduto(id))
        {
            estoque.retiradaProduto(id, qtd);
            System.out.println("Produto retirado do estoque.");
        }
        else
        {
            System.out.println("Produto não encontrado no estoque.");
        }
        salvarDados(); // Salva os produtos no arquivo CSV

    }

    void listarEstoque() {
        List<Produto> produtos = estoque.listarProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Estoque vazio!");
        } else {
            System.out.println("Produtos no estoque:");
            System.out.println("ID - Qtd. - Nome - Fornecedor");
            for (Produto produto : produtos) {
                Fornecedor fornecedor = null;
                if (produto.getFornecedor_id() != 0) {
                    fornecedor = fornecedores.get(produto.getFornecedor_id() - 1);
                }
                System.out.println(produto.getId() + " - " + produto.getQtd() + " - " + produto.getNome() + " - "
                        + (fornecedor != null ? fornecedor.getNome() : "Fornecedor não informado"));
            }

        }
    }

    public void cadastrarFornecedor() {
        Fornecedor fornecedor = new Fornecedor();
        Scanner scanner = new Scanner(System.in);
        String teclado;
        System.out.println("Digite o nome do fornecedor: ");
        teclado = scanner.nextLine();
        fornecedor.setNome(teclado);
        System.out.println("Digite o CNPJ do fornecedor: ");
        teclado = scanner.nextLine();
        fornecedor.setCnpj(teclado);
        System.out.println("Digite o endereço do fornecedor: ");
        teclado = scanner.nextLine();
        fornecedor.setEndereco(teclado);
        System.out.println("Digite o telefone do fornecedor: ");
        teclado = scanner.nextLine();
        fornecedor.setTelefone(teclado);
        fornecedor.setId(fornecedores.size() + 1); // Atribui um ID único ao fornecedor

        this.fornecedores.add(fornecedor);
        System.out.println("Fornecedor cadastrado com sucesso!");
        System.out.println(fornecedor.toString());
        salvarFornecedores();

    }

    public void cadastroFornecedor_teste(Fornecedor fornecedor) {
        this.fornecedores.add(fornecedor);
        System.out.println("Fornecedor cadastrado com sucesso!");
        System.out.println(fornecedor.toString());
        salvarFornecedores();
    }

    public void listarFornecedores() {
        if (fornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor cadastrado!");
        } else {
            System.out.println("Fornecedores cadastrados:");
            for (Fornecedor fornecedor : fornecedores) {
                System.out.println(fornecedor.getId() + " - " + fornecedor.getNome() + " - " + fornecedor.getCnpj()
                        + " - " + fornecedor.getEndereco() + " - " + fornecedor.getTelefone());
            }
        }
    }

    public void listaSimplesFornecedores() {
        if (fornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor cadastrado!");
        } else {
            for (Fornecedor fornecedor : fornecedores) {
                System.out.println(fornecedor.getId() + " - " + fornecedor.getNome());
            }
        }
    }

    private void salvarProdutos() {
        try {
            List<String> linhas = new ArrayList<>();
            linhas.add("ID,Qtd,Nome,ID_fornecedor"); // Cabeçalho
            for (Produto produto : estoque.listarProdutos()) {
                String linha = produto.getId() + "," + produto.getQtd() + "," + produto.getNome() + ","
                        + produto.getFornecedor_id();
                linhas.add(linha);
            }
            Files.write(Paths.get(PRODUTOS_CSV), linhas);
        } catch (IOException e) {
            System.err.println("Erro ao salvar produtos: " + e.getMessage());
        }
    }

    private void carregarProdutos() {
        System.out.println("    Carregando produtos...");
        try {
            List<String> linhas = Files.readAllLines(Paths.get(PRODUTOS_CSV));
            for (String linha : linhas.subList(1, linhas.size())) { // Ignora o cabeçalho
                String[] partes = linha.split(",");
                Produto produto = new Produto(partes[2]);
                produto.setId(Integer.parseInt(partes[0]));
                produto.setQtd(Integer.parseInt(partes[1]));
                produto.setFornecedor_id(Integer.parseInt(partes[3]));
                estoque.adicionarProduto(produto);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar produtos: " + e.getMessage());
        }
        System.out.println("    Produtos carregados com sucesso!");
    }

    private void salvarFornecedores() {
        try {
            List<String> linhas = new ArrayList<>();
            linhas.add("ID,Nome,CNPJ,Endereco,Telefone"); // Cabeçalho
            for (Fornecedor fornecedor : fornecedores) {
                String linha = fornecedor.getId() + "," + fornecedor.getNome() + "," + fornecedor.getCnpj() + ","
                        + fornecedor.getEndereco() + "," + fornecedor.getTelefone();
                linhas.add(linha);
            }
            Files.write(Paths.get(FORNECEDORES_CSV), linhas);
        } catch (IOException e) {
            System.err.println("Erro ao salvar fornecedores: " + e.getMessage());
        }

        System.out.println("Fornecedores salvos com sucesso!");
    }

    private void carregarFornecedores() {
        System.out.println("    Carregando fornecedores...");
        try {
            List<String> linhas = Files.readAllLines(Paths.get(FORNECEDORES_CSV));
            for (String linha : linhas.subList(1, linhas.size())) { // Ignora o cabeçalho
                String[] partes = linha.split(",");
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(Integer.parseInt(partes[0]));
                fornecedor.setNome(partes[1]);
                fornecedor.setCnpj(partes[2]);
                fornecedor.setEndereco(partes[3]);
                fornecedor.setTelefone(partes[4]);
                fornecedores.add(fornecedor);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar fornecedores: " + e.getMessage());
        }
        System.out.println("    Fornecedores carregados com sucesso!");
    }

    @Override
    public void salvarDados() {
        salvarProdutos();
        salvarFornecedores();
    }

    @Override
    public void carregarDados() {
        System.out.println("Carregando dados base de dados...");
        carregarProdutos();
        carregarFornecedores();
        System.out.println("Base de dados carregada com sucesso!");
    }

	public void listaSetores() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'listaSetores'");
	}

    // Salva dados no JSON (versão simplificada)

}
