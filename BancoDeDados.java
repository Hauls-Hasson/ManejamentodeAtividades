package activiteManagement;

import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BancoDeDados implements OperacoesCRUD<Tarefa> {

    private final String url;
    private final String usuario;
    private final String senha;

    public BancoDeDados(String url, String usuario, String senha) {
        this.url = "postgres://qbxnpisz:wy3xBTbG1KVo6o-Yw9yVEllxImAUIa_c@motty.db.elephantsql.com/qbxnpisz";
        this.usuario = "qbxnpisz";
        this.senha = "wy3xBTbG1KVo6o-Yw9yVEllxImAUIa_c";
    }

    @Override 
    public void adicionar(Tarefa tarefa) {
        
   	 String url = "postgres://qbxnpisz:wy3xBTbG1KVo6o-Yw9yVEllxImAUIa_c@motty.db.elephantsql.com/qbxnpisz";
        String usuario = "qbxnpisz";
        String senha = "wy3xBTbG1KVo6o-Yw9yVEllxImAUIa_c";

        String sql = "INSERT INTO tarefas (titulo, descricao, data_inicio, data_conclusao_prevista, status) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, tarefa.getTitulo());
            statement.setString(2, tarefa.getDescricao());
            statement.setString(3, tarefa.getDatainicio());
            statement.setString(4, tarefa.getDataconclusao());
            statement.setBoolean(5, tarefa.isStatus() ? 1:0);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void listar() {
        // Implementação para listar do banco de dados
        // ...
    }

    @Override
    public void editar(int indice, Tarefa tarefa) {
   	 
   	 String url = "postgres://qbxnpisz:wy3xBTbG1KVo6o-Yw9yVEllxImAUIa_c@motty.db.elephantsql.com/qbxnpisz";
        String usuario = "qbxnpisz";
        String senha = "wy3xBTbG1 KVo6o-Yw9yVEllxImAUIa_c";
        
        String sql = "UPDATE tarefas SET titulo=?, descricao=?, data_inicio=?, data_conclusao_prevista=?, status=? " +
                "WHERE id=?";

        try (Connection connection = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, tarefa.getTitulo());
            statement.setString(2, tarefa.getDescricao());
            statement.setString(3, tarefa.getDatainicio());
            statement.setString(4, tarefa.getDataconclusao());
            statement.setBoolean(5, tarefa.isStatus() ? 1:0);
            statement.setInt(6, indice + 1); // Índices no banco começam em 1

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void removerTarefa(int indice) {
    
    	    
    		 String url = "postgres://qbxnpisz:wy3xBTbG1KVo6o-Yw9yVEllxImAUIa_c@motty.db.elephantsql.com/qbxnpisz";
    	     String usuario = "qbxnpisz";
    	     String senha = "wy3xBTbG1KVo6o-Yw9yVEllxImAUIa_c";

    	     String sql = "DELETE FROM tarefas WHERE titulo=?";

    	     try (Connection connection = DriverManager.getConnection(url, usuario, senha);
    	          PreparedStatement statement = connection.prepareStatement(sql)) {

    	         statement.setString(1, tarefa.getTitulo());

    	         statement.executeUpdate();

    	     } catch (SQLException e) {
    	         e.printStackTrace();
    	     }
    	        
    }
    public void carregarTarefasDoBanco() {
        
   	 String url = "postgres://qbxnpisz:wy3xBTbG1KVo6o-Yw9yVEllxImAUIa_c@motty.db.elephantsql.com/qbxnpisz";
     String usuario = "qbxnpisz";
     String senha = "wy3xBTbG1KVo6o-Yw9yVEllxImAUIa_c";
      

        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "SELECT * FROM tarefas";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Tarefa tarefa = new Tarefa(
                            resultSet.getString("titulo"),
                            resultSet.getString("descricao"),
                            resultSet.getString("data_inicio"),
                            resultSet.getString("data_conclusao")
                    );
                    tarefa.setStatus(resultSet.getBoolean("status"));
                    listaTarefas.add(tarefa);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
