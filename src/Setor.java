package src;



import java.util.Set;   

public interface Setor {

    public static final int ALMOXATIFADO = 0;
    public static final int FARMACIA = 1;  
    public static final int CENTRO_CIRURGICO = 2;
    public static final int NUTRICAO = 3;

    
    public void entradaProduto(Produto produto);
    public void saidaProduto(Produto produto);
    public void salvarDados();
    public void carregarDados();

    static void listaSetores()
    {
        System.out.println("Setores disponíveis:");
        System.out.println("ID - NOME");
        for (int i = 0; i < setores.length; i++)
        {
            System.out.println(i + " - " + setoresString[i]);
        }
    }

    int getId();

    public static int setores[] = {ALMOXATIFADO, FARMACIA, CENTRO_CIRURGICO, NUTRICAO};
    public static String setoresString[] = {"ALMOXATIFADO", "FARMACIA", "CENTRO_CIRURGICO", "NUTRICAO"};
    
}
