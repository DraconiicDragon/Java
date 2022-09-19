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
import modelo.Produto;

/**
 *
 * @author Dinalva
 */
public class DAOProduto {
    DAOFornecedor daoFornecedor = new DAOFornecedor();
    public List<Produto> getLista() {
        String sql = "select * from produto ";
        List<Produto> lista = new ArrayList<>();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                Produto obj = new Produto();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setUnidade(rs.getString("unidade"));
                obj.setEstoque(rs.getInt("estoque"));
                obj.setPrecoVenda(rs.getDouble("precoVenda"));
                obj.setPrecoCompra(rs.getDouble("precoCompra"));
                obj.setFornecedor(daoFornecedor.localizar(rs.getInt("idFornecedor")));
                lista.add(obj);
            }
        } catch(SQLException ex) {
            System.out.println("SQLException " + ex.getMessage());
        }
        return lista;
    }
    
    public boolean salvar(Produto obj) {
        if(obj.getId() == 0) {
            return this.incluir(obj);
        }else {
            return this.alterar(obj);
        }
    }
    
    private boolean incluir(Produto obj) {
        String sql = "insert into produto(nome, unidade, estoque, precoVenda,"
                + "precoCompra, idFornecedor) values (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getUnidade());
            pst.setInt(3, obj.getEstoque());
            pst.setDouble(4, obj.getPrecoVenda());
            pst.setDouble(5, obj.getPrecoCompra());
            pst.setInt(6, obj.getFornecedor().getId());
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Produto criado com sucesso");
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
    
    private boolean alterar(Produto obj) {
        String sql = "update produto set nome = ?, unidade = ?, estoque = ?,"
                + "precoVenda = ?, precoCompra = ?, idFornecedor = ? where id = ?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getUnidade());
            pst.setInt(3, obj.getEstoque());
            pst.setDouble(4, obj.getPrecoVenda());
            pst.setDouble(5, obj.getPrecoCompra());
            pst.setInt(6, obj.getFornecedor().getId());
            pst.setInt(7, obj.getId());
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Produto alterado com sucesso");
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
    
    public boolean deletar(Produto obj) {
        String sql = "delete from produto where id = ?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getId());
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Produto deletado com sucesso");
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
    
    public Produto localizar(int id) {
        String sql = "select * from produto where id = ?";
        Produto obj = new Produto();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setUnidade(rs.getString("unidade"));
                obj.setEstoque(rs.getInt("estoque"));
                obj.setPrecoVenda(rs.getDouble("precoVenda"));
                obj.setPrecoCompra(rs.getDouble("precoCompra"));
                obj.setFornecedor(daoFornecedor.localizar(rs.getInt("idFornecedor")));
                return obj;
            }
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Exception "+ ex.getMessage());
        }
        return null;
    }
    
}
