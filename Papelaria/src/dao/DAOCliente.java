/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Cliente;

/**
 *
 * @author Dinalva
 */
public class DAOCliente {
    
    public List<Cliente> getLista() {
        String sql = "select * from cliente";
        List<Cliente> lista = new ArrayList<>();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                Cliente obj = new Cliente();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setRua(rs.getString("rua"));
                obj.setNumero(rs.getString("numero"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                lista.add(obj);
            }
        } catch(SQLException ex) {
            System.out.println("SQLException " + ex.getMessage());
        }
        return lista;
    }
    
    public boolean salvar(Cliente obj) {
        if(obj.getId() == 0) {
            return this.incluir(obj);
        }else {
            return this.alterar(obj);
        }
    }
    
    public boolean incluir(Cliente obj) {
        String sql = "insert into cliente(nome, telefone, rua, numero, bairro, cidade) values (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getTelefone());
            pst.setString(3, obj.getRua());
            pst.setString(4, obj.getNumero());
            pst.setString(5, obj.getBairro());
            pst.setString(6, obj.getCidade());
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cliente criado com sucesso");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro");
                return false;
            }
        } catch(SQLException ex) {
            System.out.println("SQLException " + ex.getMessage());
            return false;
        }
    }
    
    public boolean alterar(Cliente obj) {
        String sql = "update cliente set nome = ?, telefone = ?, rua = ?,"
                + "numero = ?, bairro = ?, cidade = ? where id = ?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getTelefone());
            pst.setString(3, obj.getRua());
            pst.setString(4, obj.getNumero());
            pst.setString(5, obj.getBairro());
            pst.setString(6, obj.getCidade());
            pst.setInt(7, obj.getId());
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Erro");
                return false;
            }
        } catch(SQLException ex) {
            System.out.println("SQLException " + ex.getMessage());
            return false;
        }
    }
    
    public boolean deletar(Cliente obj) {
        String sql = "delete from cliente where id = ?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getId());
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Cliente deletado com sucesso");
                return true;
            }else {
                JOptionPane.showMessageDialog(null, "Erro");
                return false;
            }            
        } catch(SQLException ex) {
            System.out.println("SQLException " + ex.getMessage());
            return false;
        }
    }
    
    public Cliente localizar(int id) {
        String sql = "select * from cliente where id = ?";
        Cliente obj = new Cliente();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setRua(rs.getString("rua"));
                obj.setNumero(rs.getString("numero"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                return obj;
            }
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Exception "+ ex.getMessage());
        }
        return null;
    }
}
