package src;

import java.util.List;

public class Farmacia implements Setor {
    Farmacia() {
        
    }

    @Override
    public int getId()
    {
        return 1; 
    }

    Setor retornaSetor()
    {
        return this;
    }

    
    // Atributos
    Estoque estoque;
    private static final String PRODUTOS_CSV = "data/produtos.csv";
    private static final String FORNECEDORES_CSV = "data/fornecedores.csv";
    @Override
    public void entradaProduto(Produto produto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'entradaProduto'");
    }

    @Override
    public void saidaProduto(Produto produto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saidaProduto'");
    }

    @Override
    public void salvarDados() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'salvarDados'");
    }

    @Override
    public void carregarDados() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'carregarDados'");
    }
    
}
