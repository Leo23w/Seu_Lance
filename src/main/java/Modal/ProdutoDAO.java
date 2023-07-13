package Modal;

import com.example.javafx.ConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public List<Produto> getAll() throws SQLException {
        try (Statement statement = ConnectionSingleton.getConnection().createStatement();
             ResultSet rs = statement.executeQuery("select * from produtos;")) {
            List<Produto> produtos = new ArrayList<>();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.idproduto = rs.getInt("idproduto");
                produto.nome = rs.getString("nome");
                produto.valor = rs.getDouble("valor");
                produto.quantidade = rs.getInt("quantidade");
                produto.descricao = rs.getString("descricao");
                produto.lance = rs.getDouble("lances");
                produtos.add(produto);
            }
            return produtos;
        }
    }

    public void novoProduto(Produto novoProduto) throws SQLException {
        String sql = "insert into produtos (nome, valor, quantidade, descricao, lance) values (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = ConnectionSingleton.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, novoProduto.nome);
            preparedStatement.setDouble(2, novoProduto.valor);
            preparedStatement.setInt(3, novoProduto.quantidade);
            preparedStatement.setString(4, novoProduto.descricao);
            preparedStatement.setDouble(5,novoProduto.lance);

            preparedStatement.executeUpdate();

            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                if (rs.next()) {
                    novoProduto.idproduto = rs.getInt(1);
                }
            }
        }
    }

    public void editarProduto(Produto editaProduto) throws SQLException {
        String sql = "UPDATE produtos SET nome = ?, valor = ?, quantidade = ?, descricao = ?, lances = ? WHERE idproduto = ?";
        try (PreparedStatement preparedStatement = ConnectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, editaProduto.nome);
            preparedStatement.setDouble(2, editaProduto.valor);
            preparedStatement.setInt(3, editaProduto.quantidade);
            preparedStatement.setString(4, editaProduto.descricao);
            preparedStatement.setDouble(5,editaProduto.lance);
            preparedStatement.setInt(6, editaProduto.idproduto);

            preparedStatement.executeUpdate();

        }
    }

    public void excluirProduto(Produto excluiProduto) throws SQLException {
        String sql = "delete from produtos where idproduto=?;";
        try (PreparedStatement preparedStatement = ConnectionSingleton.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, excluiProduto.idproduto);
            preparedStatement.executeUpdate();
        }
    }

    public void fazerLance(Produto fazerlance) throws SQLException {
        String sql = "update produtos set lances = ? where idproduto = ?;";
        try (PreparedStatement preparedStatement = ConnectionSingleton.connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, fazerlance.lance);
            preparedStatement.setInt(2, fazerlance.idproduto);
            preparedStatement.executeUpdate();
        }
    }

    public void inserirLance(Produto lancar) throws SQLException{
        String sql = "insert into produtos (insert into produtos (lances) values (?);)";
        try (PreparedStatement preparedStatement = ConnectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setDouble(1,lancar.lance);
            preparedStatement.executeUpdate();
    }
    }
}


