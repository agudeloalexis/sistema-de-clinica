package persistencia.conexao;


import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class PersistenciaPasciente extends ConexaoBancoDeDados {
    Connection connection;

    public PersistenciaPasciente() throws SQLException {
        this.connection = ConexaoBancoDeDados.getInstance().getConnection();
    }

    public void Salvar(Pasciente pasciente) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dataNascimento = sdf.format(pasciente.getDataNascimento());
        String dataMatricula = sdf.format(pasciente.getDataConsulta());

        String query = "INSERT INTO pasciente ( nome, data_nascimento, data_matricula) VALUES ( '" + pasciente.getNome() + "', '" + dataNascimento + "');";
        try {
            this.connection.createStatement().executeUpdate(query);
            System.out.println("Pasciente salvo com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}