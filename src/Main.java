package app;
import modelo.*;
import servicos.*;
import excecoes.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Estoque estoque = new Estoque();
        MonitorEstoque monitor = new MonitorEstoque(estoque, 1);
        monitor.start();


        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("=== MENU ESTOQUE DE ROTEADORES ===");
            System.out.println("1. Adicionar Roteador");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Remover Produto");
            System.out.println("4. Buscar por Nome");
            System.out.println("5. Salvar em Arquivo");
            System.out.println("6. Carregar de Arquivo");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Marca: ");
                    String marca = sc.nextLine();
                    System.out.print("Preço: ");
                    double preco = sc.nextDouble();
                    System.out.print("Quantidade: ");
                    int quantidade = sc.nextInt();
                    System.out.print("Velocidade (Mbps): ");
                    int velocidade = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Tipo (ex: Dual Band): ");
                    String tipo = sc.nextLine();

                    Produto roteador = new Roteador(id, nome, marca, preco, quantidade, velocidade, tipo);
                    estoque.adicionarProduto(roteador);

                    roteador.cadastrar();
                    estoque.adicionarProduto(roteador);
                    System.out.println("----------------------------\n");
                    break;

                case 2:
                    try {
                        estoque.listarProdutos();
                    } catch (EstoqueVazioException e) {
                        System.out.println("⚠️ " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Informe o ID do produto a remover: ");
                    int idRemover = sc.nextInt();
                    estoque.removerProdutoPorId(idRemover);
                    break;

                case 4:
                    sc.nextLine();
                    System.out.print("Nome do produto: ");
                    String busca = sc.nextLine();
                    Produto encontrado = estoque.buscarPorNome(busca);
                    if (encontrado != null) {
                        encontrado.exibirInfo();
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                    break;

                case 5:
                    estoque.salvarEmArquivo();
                    break;

                case 6:
                    estoque.carregarDeArquivo();
                    break;

                case 0:
                    System.out.println("Encerrando sistema...");
                    //monitor.pararMonitoramento(); // Avisa a thread para parar
                    // Opcional: esperar a thread morrer antes de fechar tudo
                    try { monitor.join(2000); } catch (InterruptedException e) { e.printStackTrace(); }
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }
}
