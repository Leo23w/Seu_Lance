package com.example.javafx;

import java.sql.*;

public class UsuarioDAO {
    public boolean existe(Usuario usuario) throws SQLException {
        Connection connection = DriverManager.getConnection(//
                "jdbc:mysql://localhost:3306/mydb", //
                "root", //
                "");
        String sql = ("select count(*) from usuario " //
                + "where usuario = '" + usuario.usuario + "' " //
                + "AND senha = '" + usuario.senha + "'");


        ResultSet resultado = connection.createStatement().executeQuery(sql);
        resultado.next();
        int quantidadeUsuarios = resultado.getInt(1);
        if (quantidadeUsuarios > 0) {
            return true;
        } else {
            return false;
        }
    }
    public void editarUsuario(){
String sql = "update usuario set nome = ?, idUsuario = ?;";
        

        

}
