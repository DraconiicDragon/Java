/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Dinalva
 */
public class Conexao {
    public static final String BANCO = "jdbc:mysql://localhost:3306/papelaria";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String USUARIO = "root";
    public static final String SENHA = "";
    
    private static Connection con = null;
    
    public Conexao() {
        
    }
    
    public static Connection getConexao() {
        if(con == null) {
            try {
                Class.forName(DRIVER);
                con = DriverManager.getConnection(BANCO, USUARIO, SENHA);
            } catch(ClassNotFoundException e) {
                System.out.println("Não Encontrou o Driver " + e.getMessage());
            } catch(SQLException ex) {
                System.out.println("SQLException " + ex.getMessage());
            }
        }
        return con;
    }
    
    public static PreparedStatement getPreparedStatement(String sql) {
        if(con == null) {
            con = getConexao();
        }
        try {
            return con.prepareStatement(sql);
        } catch(SQLException ex) {
            System.out.println("SQLException " + ex.getMessage());
        }
        return null;
    }
    
}
