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
import modelo.ItensVenda;

/**
 *
 * @author Dinalva
 */
public class DAOItensVenda {
    DAOProduto daoProduto = new DAOProduto();
    DAOVenda daoVenda = new DAOVenda();
    public List<ItensVenda> getLista() {
        String sql = "select * from itensVenda";
        List<ItensVenda> lista = new ArrayList<>();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                ItensVenda obj = new ItensVenda();
                obj.setId(rs.getInt("id"));
                obj.setQuantidade(rs.getInt("quantidade"));
                obj.setSubtotal(rs.getDouble("subtotal"));
                obj.setProduto(daoProduto.localizar(rs.getInt("idProduto")));
                obj.setVenda(daoVenda.localizar(rs.getInt("idVenda")));
                lista.add(obj);
            }
        } catch(SQLException ex) {
            System.out.println("SQLException " + ex.getMessage());
        }
        return lista;
    }
    
    public boolean salvar(ItensVenda obj) {
        if(obj.getId() == 0) {
            return this.incluir(obj);
        }else {
            return this.alterar(obj);
        }
    }
    
    public boolean incluir(ItensVenda obj) {
        String sql = "insert into itensVenda(quantidade, subtotal, idProduto, idVenda) values (?, ?, ?, ?)";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getQuantidade());
            pst.setDouble(2, obj.getSubtotal());
            pst.setInt(3, obj.getProduto().getId());
            pst.setInt(4,obj.getVenda().getId());
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Item criado com sucesso");
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
    
    public boolean alterar(ItensVenda obj) {
        String sql = "update itensVenda set quantidade = ?, subtotal = ?,"
                + "idProduto = ?, idVenda = ?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getQuantidade());
            pst.setDouble(2, obj.getSubtotal());
            pst.setInt(3, obj.getProduto().getId());
            pst.setInt(4,obj.getVenda().getId());
            pst.setInt(5, obj.getId());
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Item alterado com sucesso");
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
    
    public boolean deletar(ItensVenda obj) {
        String sql = "delete from itensVenda where id = ?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getId());
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Item deletado com sucesso");
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
}
