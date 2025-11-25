package servicos;

public class MonitorEstoque extends Thread {
    private Estoque estoque;
    private int intervaloSegundos;

    public MonitorEstoque(Estoque estoque, int intervaloSegundos) {
        this.estoque = estoque;
        this.intervaloSegundos = intervaloSegundos;
    }

    public void verificarEstoque() {
        if (estoque.isVazio()) {
            System.out.println("[ALERTA] O estoque está vazio!");
        } else {
            System.out.println("[INFO] O estoque contém produtos.");
        }
    }

    @Override
    public void run() {
        try {

                verificarEstoque();
                Thread.sleep(intervaloSegundos * 10);

        } catch (InterruptedException e) {
            System.out.println("Monitor de estoque interrompido.");
        }
    }
}
