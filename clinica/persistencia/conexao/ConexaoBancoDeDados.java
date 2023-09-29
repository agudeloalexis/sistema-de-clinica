package persistencia.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBancoDeDados {
    static String host = "localhost";
    static String database = "aula";
    static String user = "root";
    static String password = "123456";

    static int port = 3306;
    private static ConexaoBancoDeDados instance;
    private static Connection connection;
    // Conecta ao banco de dados MySQL

    private void conecta() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        if(this.connection == null ){
            this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?useTimezone=true&serverTimezone=UTC", this.user, this.password);
        }else if(this.connection.isClosed()){
            this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?useTimezone=true&serverTimezone=UTC", this.user, this.password);
        }
    }
    public Connection getConnection() {
        return connection;
    }
    public static ConexaoBancoDeDados getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConexaoBancoDeDados();
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useTimezone=true&serverTimezone=UTC", user, password);

        }
        return instance;
    }

    public void closeConnection() throws SQLException {
        this.connection.close();
    }

    public void deletarPasciente(int codigo) {
        String query = "DELETE FROM pasciente WHERE codigo = " + codigo;
        executaDelete(query);
    }
    public void deletarAluno(String nome) {
        String query = "DELETE FROM pasciente WHERE nome LIKE '%" + nome + "%'";
        executaDelete(query);
    }
    public void executaDelete(String consultaDeDelete){
        try {
            this.connection.createStatement().executeUpdate(consultaDeDelete);
            System.out.println("Pasciente deletado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
