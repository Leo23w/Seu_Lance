package Modal;

import com.example.javafx.ConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    public boolean existe(Usuario usuario) throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuario WHERE usuario = ? AND senha = ?";
        try (PreparedStatement preparedStatement = ConnectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, usuario.usuario);
            preparedStatement.setString(2, usuario.senha);

            try (ResultSet resultado = preparedStatement.executeQuery()) {
                resultado.next();
                int quantidadeUsuarios = resultado.getInt(1);
                return quantidadeUsuarios > 0;
            }
        }
    }

    public List<Usuario> getAll() throws SQLException {
        String sql = "SELECT * FROM usuario";
        try (Statement statement = ConnectionSingleton.getConnection().createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            List<Usuario> usuarios = new ArrayList<>();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.codigo = rs.getInt("idUsuario");
                usuario.localizacao = rs.getString("localizacao");
                usuario.contaBancaria = rs.getDouble("contaBancaria");
                usuario.usuario = rs.getString("usuario");
                usuario.senha = rs.getString("senha");
                usuario.contato = rs.getString("contato");
                usuarios.add(usuario);
            }
            return usuarios;
        }
    }

    public void editarUsuario(Usuario editarUsuario) throws SQLException {
        String sql = "UPDATE usuario SET localizacao = ?, contaBancaria = ?, usuario = ?, senha = ?, contato = ? WHERE idUsuario = ?";
        try (PreparedStatement preparedStatement = ConnectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1,editarUsuario.localizacao);
            preparedStatement.setDouble(2,editarUsuario.contaBancaria);
            preparedStatement.setString(3, editarUsuario.usuario);
            preparedStatement.setString(4, editarUsuario.senha);
            preparedStatement.setString(5, editarUsuario.contato);
            preparedStatement.setInt(6,editarUsuario.codigo);
            preparedStatement.executeUpdate();
        }
    }

    public void criarUsuario(Usuario novoUsuario) throws SQLException {
        String sql = "INSERT INTO usuario (localizacao, contaBancaria, usuario, senha, contato) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = ConnectionSingleton.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, novoUsuario.localizacao);
            preparedStatement.setDouble(2, novoUsuario.contaBancaria);
            preparedStatement.setString(3, novoUsuario.usuario);
            preparedStatement.setString(4, novoUsuario.senha);
            preparedStatement.setString(5, novoUsuario.contato);
            preparedStatement.executeUpdate();

            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                if (rs.next()) {
                    novoUsuario.codigo = rs.getInt(1);
                }
            }
        }
    }
    public void excluirUsuario(Usuario excluirUsuario) throws SQLException{
        String sql = "delete from usuario where idUsuario=?;";
        try (PreparedStatement preparedStatement = ConnectionSingleton.getConnection().prepareStatement(sql)){
            preparedStatement.setInt(1, excluirUsuario.codigo);
            preparedStatement.executeUpdate();
        }

    }
}