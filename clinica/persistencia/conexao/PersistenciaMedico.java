package persistencia.conexao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PersistenciaMedico {
    Connection connection;
    public PersistenciaMedico() throws SQLException {
        this.connection = ConexaoBancoDeDados.getInstance().getConnection();
    }
    public void Salvar(Medico medico) {
        // Salva o medico no banco de dados
        String query = "INSERT INTO medico ( nome) VALUES ( '" + medico.getNome() + "');";
        try {
            Statement stmt = this.connection.createStatement();
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.next()) {
                int lastInsertId = resultSet.getInt(1);
                String query2 = "INSERT INTO medico_especialidade ( codigo_medico, codigo_especialidade) VALUES ( '" + lastInsertId + "', '" + medico.getEspecialidade() + "');";
                stmt.executeUpdate(query2);
            }


            System.out.println("Medico salvo com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> buscarMedico() throws SQLException {
        String query = "SELECT * FROM medico";
        ArrayList<String> resultado = new ArrayList<>();
        try {
            ResultSet linhas =  this.connection.createStatement().executeQuery(query);

            while (linhas.next()){
                int codigo = linhas.getInt("codigo");
                String especialidade = linhas.getString("nome");
                String linha = codigo + " - " + especialidade;
                resultado.add(linha);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }
    public void vincularMedicoEspecialidade(int codigoMedico, int codigoEspecialidade) throws SQLException {
        String query = "INSERT INTO medico_especialidade ( codigo_medico, codigo_especialidade) VALUES ( '" + codigoMedico + "', '" + codigoEspecialidade + "');";
        try {
            this.connection.createStatement().executeUpdate(query);
            System.out.println("Medico vinculado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletarMedico(int codigo) {
        String query = "DELETE FROM medico WHERE codigo = " + codigo;
        executaDelete(query);
    }

    private void executaDelete(String query) {
    }


}
