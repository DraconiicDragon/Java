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
import modelo.Fornecedor;

/**
 *
 * @author Dinalva
 */
public class DAOFornecedor {
    
    public List<Fornecedor> getLista() {
        String sql = "select * from fornecedor";
        List<Fornecedor> lista = new ArrayList<>();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                Fornecedor obj = new Fornecedor();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setDescricao(rs.getString("descricao"));
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
    
    public boolean salvar(Fornecedor obj) {
        if(obj.getId() == 0) {
            return this.incluir(obj);
        }else {
            return this.alterar(obj);
        }
    }
    
    private boolean incluir(Fornecedor obj) {
        String sql = "insert into fornecedor(nome, telefone, descricao, rua,"
                + "numero, bairro, cidade) values (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getTelefone());
            pst.setString(3, obj.getDescricao());
            pst.setString(4, obj.getRua());
            pst.setString(5, obj.getNumero());
            pst.setString(6, obj.getBairro());
            pst.setString(7, obj.getCidade());
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Fornecedor criado com sucesso");
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
    
    private boolean alterar(Fornecedor obj) {
        String sql = "update fornecedor set nome = ?, telefone = ?, descricao = ?,"
                + "rua = ?, numero = ?, bairro = ?, cidade = ? where id = ?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getTelefone());
            pst.setString(3, obj.getDescricao());
            pst.setString(4, obj.getRua());           
            pst.setString(5, obj.getNumero());
            pst.setString(6, obj.getBairro());
            pst.setString(7, obj.getCidade());
            pst.setInt(8, obj.getId());
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Fornecedor alterado com sucesso");
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
    
    public boolean deletar(Fornecedor obj) {
        String sql = "delete from fornecedor where id = ?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getId());
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Fornecedor deletado com sucesso");
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
    public Fornecedor localizar(int id) {
        String sql = "select * from fornecedor where id = ?";
        Fornecedor obj = new Fornecedor();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setDescricao(rs.getString("descricao"));
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
