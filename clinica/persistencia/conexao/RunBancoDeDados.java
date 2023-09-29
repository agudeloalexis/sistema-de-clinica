package persistencia.conexao;

import persistencia.conexao.PersistenciaPasciente;
import persistencia.conexao.Pasciente;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Scanner;

public class RunBancoDeDados {
    public static void main(String[] args) {

        try {
            menuInicial();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void menuInicial() throws SQLException {
        out("Para cadastrar pasciente digite 1 \nPara cadastrar médico digite 2\nPara vincular médico e especialidade digite 3\nPara buscar pasciente digite 4");
        Scanner scanner = new Scanner(System.in);
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                out("Cadastrando pasciente");
                cadastrarPasciente();
                break;
            case 2:
                out("Cadastrando médico");
                cadastrarProfessor();
                break;
            case 3:
                out("Vincular médico e especialidade");
                vincularProfessorMateria();
                break;
            case 4:
                out("Buscando pasciente");
                buscarAluno();
                break;
            default:
                out("Opção inválida");
                break;
        }
    }


}