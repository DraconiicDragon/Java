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
public class ItensVenda implements Serializable{
    private int id;
    private int quantidade;
    private double subtotal;
    private Produto produto;
    private Venda venda;

    public ItensVenda(int id, int quantidade, double subtotal, Produto produto, Venda venda) {
        this.id = id;
        this.quantidade = quantidade;
        this.subtotal = subtotal;
        this.produto = produto;
        this.venda = venda;
    }

    public ItensVenda() {
    }

    @Override
    public String toString() {
        return "ItensVenda{" + "id=" + id + ", quantidade=" + quantidade + ", subtotal=" + subtotal + ", produto=" + produto.getNome() + ", venda=" + venda.getId() + '}';
    }

    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.id;
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
        final ItensVenda other = (ItensVenda) obj;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    
    
    
}
