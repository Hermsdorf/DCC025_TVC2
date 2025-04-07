package src;

public class Produto {

    Produto(String nome) {
        this.nome = nome;
        this.fornecedor_id= 0;
        this.qtd = 0;
    }

    Produto(Fornecedor fornecedor, String nome) {
        this.fornecedor_id = fornecedor.getId();
        this.nome = nome;
        this.qtd = 0;
    }

    Produto(int ID_fornecedor, String nome) {
        this.fornecedor_id = ID_fornecedor;
        this.nome = nome;
        this.qtd = 0;
    }

    // Atributos
    private String nome;
    private int id;
    private int qtd;
    private int fornecedor_id ;

    // Métodos
    void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor_id = fornecedor.getId();
    }

    void setFornecedor_id(int fornecedor_id) {
        this.fornecedor_id = fornecedor_id;
    }
    
    int getFornecedor_id() {
        return fornecedor_id;
    }
    void setNome(String nome) {
        this.nome = nome;
    }
    String getNome() {
        return nome;
    }
    void setQtd(int qtd) {
        this.qtd = qtd;
    }
    int getQtd() {
        return qtd;
    }
    void setId(int id) {
        this.id = id;
    }
    int getId() {
        return id;
    }
    

}
