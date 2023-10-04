package persistencia.conexao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PersistenciaPasciente extends ConexaoBancoDeDados {
    Connection connection;

    public PersistenciaPasciente() throws SQLException {
        this.connection = ConexaoBancoDeDados.getInstance().getConnection();
    }

    public void Salvar(Pasciente pasciente) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dataNascimento = sdf.format(pasciente.getDataNascimento());
        String dataConsulta = sdf.format(pasciente.getDataConsulta());

        String query = "INSERT INTO pasciente ( nome, data_nascimento, data_consulta) VALUES ( '" + pasciente.getNome() + "', '" + dataNascimento + "', '" + dataConsulta +"');";
        try {
            this.connection.createStatement().executeUpdate(query);
            System.out.println("Pasciente salvo com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> buscarPasciente() {
        String consultaTodos = "SELECT * FROM pasciente ORDER BY nome ASC";
        return executaConsulta(consultaTodos);
    }
    public ArrayList<String> buscarPasciente(int codigo) {
        String consultaPorCodigo = "SELECT * FROM pasciente WHERE codigo = " + codigo + " ORDER BY nome ASC";
        return executaConsulta(consultaPorCodigo);
    }
    public ArrayList<String> buscarPasciente(String nome) {
        String consultaPorNome = "SELECT * FROM pasciente WHERE nome LIKE '%" + nome + "%' ORDER BY nome ASC";
        return executaConsulta(consultaPorNome);
    }
    public ArrayList<String> executaConsulta(String consulta){
        ArrayList<String> resultado = new ArrayList<>();
        try {
            ResultSet linhas =  this.connection.createStatement().executeQuery(consulta);

            while (linhas.next()){
                int codigo = linhas.getInt("codigo");
                String materia = linhas.getString("nome");
                String linha = codigo + " - " + materia;
                resultado.add(linha);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }

    public void deletarPasciente(int codigo) {
        String query = "DELETE FROM pasciente WHERE codigo = " + codigo;
        executaDelete(query);
    }
    public void deletarPasciente(String nome) {
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

    public void editarPasciente(int codigo, String novoNome) {
        String query = "UPDATE pasciente SET nome = '" + novoNome + "' WHERE codigo = " + codigo;
        try {
            System.out.println("pasciente editado com sucesso! "+query);
            this.connection.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}