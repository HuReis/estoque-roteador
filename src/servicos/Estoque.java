package servicos;

import modelo.Produto;
import modelo.Roteador;
import excecoes.EstoqueVazioException;
import java.io.*;
import java.util.*;

public class Estoque {
    private List<Produto> listaProdutos;

    public Estoque() {
        listaProdutos = new ArrayList<>();
    }

    public void adicionarProduto(Produto p) {
        listaProdutos.add(p);
        System.out.println("Produto adicionado ao estoque: " + p.getNome());
    }

    public void removerProdutoPorId(int id) {
        listaProdutos.removeIf(p -> p.getId() == id);
        System.out.println("Produto removido com ID: " + id);
    }

    public void listarProdutos() throws EstoqueVazioException {
        if (listaProdutos.isEmpty()) {
            throw new EstoqueVazioException("O estoque está vazio!");
        }
        System.out.println("=== Lista de Produtos ===");
        for (Produto p : listaProdutos) {
            p.exibirInfo();
        }
    }

    public Produto buscarPorNome(String nome) {
        for (Produto p : listaProdutos) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }

    public void salvarEmArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("estoque.txt"))) {
            for (Produto p : listaProdutos) {
                writer.write(p.getId() + ";" + p.getNome() + ";" + p.getMarca() + ";" + p.getPreco() + ";" + p.getQuantidade());
                writer.newLine();
            }
            System.out.println("Estoque salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o estoque: " + e.getMessage());
        }
    }

    public void carregarDeArquivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader("estoque.txt"))) {
            listaProdutos.clear();
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String marca = dados[2];
                double preco = Double.parseDouble(dados[3]);
                int quantidade = Integer.parseInt(dados[4]);
                Produto p = new Roteador(id, nome, marca, preco, quantidade, 0, "Desconhecido");
                listaProdutos.add(p);
            }
            System.out.println("Estoque carregado do arquivo!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar o estoque: " + e.getMessage());
        }
    }

    public boolean isVazio() {
        return listaProdutos.isEmpty();
    }
}
