package modelo;

import interfaces.Gerenciavel;

public abstract class Produto implements Gerenciavel {
    protected int id;
    protected String nome;
    protected String marca;
    protected double preco;
    protected int quantidade;

    public Produto(int id, String nome, String marca, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getMarca() { return marca; }
    public double getPreco() { return preco; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    @Override
    public void cadastrar() {
        System.out.println("Produto cadastrado: " + nome);
    }

    @Override
    public void remover() {
        System.out.println("Produto removido: " + nome);
    }

    public abstract void exibirInfo();
}
