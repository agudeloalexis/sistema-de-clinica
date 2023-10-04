package persistencia.conexao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersistenciaEspecialidade extends ConexaoBancoDeDados{
    Connection connection;
    public PersistenciaEspecialidade() throws SQLException {
        this.connection = ConexaoBancoDeDados.getInstance().getConnection();
    }
    public ArrayList<String> buscarEspecialidade() throws SQLException {
        String query = "SELECT * FROM especialidade";
        ArrayList<String> resultado = new ArrayList<>();
        try {
            ResultSet linhas =  this.connection.createStatement().executeQuery(query);

            while (linhas.next()){
                int codigo = linhas.getInt("codigo");
                String especialidade = linhas.getString("especialidade");
                String linha = codigo + " - " + especialidade;
                resultado.add(linha);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }

}