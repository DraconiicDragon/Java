/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Dinalva
 */
public class Produto implements Serializable{
    private int id;
    private String nome;
    private String unidade;
    private int estoque;
    private double precoVenda;
    private double precoCompra;    
    private Fornecedor fornecedor;

    public Produto(int id, String nome, String unidade, int estoque, double precoVenda, double precoCompra, Fornecedor fornecedor) {
        this.id = id;
        this.nome = nome;
        this.unidade = unidade;
        this.estoque = estoque;
        this.precoVenda = precoVenda;
        this.precoCompra = precoCompra;
        this.fornecedor = fornecedor;
    }   

    public Produto() {
    }

    @Override
    public String toString() {
        return this.id + " - " + this.nome;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
              
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }   
    
    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }
    
    
    
}
