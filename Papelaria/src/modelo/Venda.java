/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.beans.Transient;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Dinalva
 */
public class Venda implements Serializable{
    private int id;
    private double valor;
    private Calendar data;
    private Funcionario funcionario;
    private Cliente cliente;

    public Venda(int id, double valor, Calendar data, Funcionario funcionario, Cliente cliente) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.funcionario = funcionario;
        this.cliente = cliente;
    }

    public Venda() {
    }

    @Transient
    public String getDataFormatada() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data.getTime());
    }
    
    @Override
    public String toString() {
        return "Venda{" + "id=" + id + ", valor=" + valor + ", data=" + data + ", funcionario=" + funcionario.getId() + ", cliente=" + cliente.getId() + '}';
    }

    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.id;
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
        final Venda other = (Venda) obj;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }   

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
}
