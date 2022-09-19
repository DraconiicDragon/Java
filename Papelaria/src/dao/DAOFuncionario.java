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
import modelo.Funcionario;

/**
 *
 * @author Dinalva
 */
public class DAOFuncionario {
    
    public List<Funcionario> getLista() {
        String sql = "select * from funcionario";
        List<Funcionario> lista = new ArrayList<>();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                Funcionario obj = new Funcionario();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCpf(rs.getString("cpf"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setSalario(rs.getDouble("salario"));
                obj.setRua(rs.getString("rua"));
                obj.setNumero(rs.getString("numero"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setAtivo(rs.getBoolean("ativo"));
                lista.add(obj);
            }
        } catch(SQLException ex) {
            System.out.println("SQLException " + ex.getMessage());
        }
        return lista;
    }
    
    public boolean salvar(Funcionario obj) {
        if(obj.getId() == 0) {
            return this.incluir(obj);
        }else {
            return this.alterar(obj);
        }
    }
    
    private boolean incluir(Funcionario obj) {
        String sql = "insert into funcionario(nome, cpf, telefone, salario, rua,"
                + "numero, bairro, cidade, ativo) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getCpf());
            pst.setString(3, obj.getTelefone());
            pst.setDouble(4, obj.getSalario());
            pst.setString(5, obj.getRua());
            pst.setString(6, obj.getNumero());
            pst.setString(7, obj.getBairro());
            pst.setString(8, obj.getCidade());
            pst.setBoolean(9, obj.isAtivo());
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Funcionario criado com sucesso");
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
    
    private boolean alterar(Funcionario obj) {
        String sql = "update funcionario set nome = ?, cpf = ?, telefone = ?,"
                + "salario = ?, rua = ?,numero = ?, bairro = ?, cidade = ?, ativo = ? where id = ?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getCpf());
            pst.setString(3, obj.getTelefone());
            pst.setDouble(4, obj.getSalario());
            pst.setString(5, obj.getRua());
            pst.setString(6, obj.getNumero());
            pst.setString(7, obj.getBairro());
            pst.setString(8, obj.getCidade());
            pst.setBoolean(9, obj.isAtivo());
            pst.setInt(10, obj.getId());
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Funcionario alterado com sucesso");
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
    
    public boolean deletar(Funcionario obj) {
        String sql = "delete from funcionario where id = ?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getId());
            if(pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Funcionario deletado com sucesso");
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
    
    public Funcionario localizar(int id) {
        String sql = "select * from funcionario where id = ?";
        Funcionario obj = new Funcionario();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCpf(rs.getString("cpf"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setSalario(rs.getDouble("salario"));
                obj.setRua(rs.getString("rua"));
                obj.setNumero(rs.getString("numero"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setAtivo(rs.getBoolean("ativo"));
                return obj;
            }
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Exception "+ ex.getMessage());
        }
        return null;
    }
}
