package modelo;

public class Roteador extends Produto {
    private int velocidadeMbps;
    private String tipo;

    public Roteador(int id, String nome, String marca, double preco, int quantidade, int velocidadeMbps, String tipo) {
        super(id, nome, marca, preco, quantidade);
        this.velocidadeMbps = velocidadeMbps;
        this.tipo = tipo;
    }

    @Override
    public void exibirInfo() {
        System.out.println("=== Roteador ===");
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Marca: " + marca);
        System.out.println("Preço: R$" + preco);
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Velocidade: " + velocidadeMbps + " Mbps");
        System.out.println("Tipo: " + tipo);
        System.out.println("---------------------------");
    }
}
