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
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Venda;

/**
 *
 * @author Dinalva
 */
public class DAOVenda {
    DAOCliente daoCliente = new DAOCliente();
    DAOFuncionario daoFuncionario = new DAOFuncionario();
    
    public List<Venda> getLista() {
        String sql = "select * from venda";
        List<Venda> lista = new ArrayList<>();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                Venda obj = new Venda();
                obj.setId(rs.getInt("id"));
                obj.setValor(rs.getDouble("valor"));
                Date dt = rs.getDate("dataVenda");
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                obj.setData(c);
                obj.setCliente(daoCliente.localizar(rs.getInt("idCliente")));
                obj.setFuncionario(daoFuncionario.localizar(rs.getInt("idFuncionario")));
                lista.add(obj);
            }
        } catch(SQLException ex) {
            System.out.println("SQLException " + ex.getMessage());
        }
        return lista;
    }
    
    public boolean salvar(Venda obj) {
        if(obj.getId() == 0) {
            return this.incluir(obj);
        }else {
            return this.alterar(obj);
        }
    }
    
    public boolean incluir(Venda obj) {
        String sql = "insert into venda(valor, dataVenda, idCliente, idFuncionario) values (?, ?, ?, ?)";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setDouble(1, obj.getValor());
            pst.setDate(2, new Date(obj.getData().getTimeInMillis()));
            pst.setInt(3, obj.getCliente().getId());
            pst.setInt(4, obj.getFuncionario().getId());
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Venda criada com sucesso");
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
    
    public boolean alterar(Venda obj) {
        String sql = "update venda set valor = ?, dataVenda = ?, idCliente = ?, idFuncionario = ? where id = ?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setDouble(1, obj.getValor());
            pst.setDate(2, new Date(obj.getData().getTimeInMillis()));
            pst.setInt(3, obj.getCliente().getId());
            pst.setInt(4, obj.getFuncionario().getId());
            pst.setInt(5, obj.getId());
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Venda alterada com sucesso");
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
    
    public boolean deletar(Venda obj) {
        String sql = "delete from venda where id = ?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getId());
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Venda deletada com sucesso");
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
    
    public Venda localizar(int id) {
        String sql = "select * from venda where id = ?";
        Venda obj = new Venda();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setValor(rs.getDouble("valor"));
                Date dt = rs.getDate("dataVenda");
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                obj.setData(c);
                obj.setCliente(daoCliente.localizar(rs.getInt("idCliente")));
                obj.setFuncionario(daoFuncionario.localizar(rs.getInt("idFuncionario")));
                return obj;
            }
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Exception "+ ex.getMessage());
        }
        return null;
    }
}
