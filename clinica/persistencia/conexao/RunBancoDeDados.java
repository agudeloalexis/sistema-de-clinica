package persistencia.conexao;

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
        out("Para cadastrar pasciente digite 1 \nPara cadastrar médico digite 2\nPara vincular médico e especialidade digite 3\nPara buscar pasciente digite 4\nPara deletar um médico digite 5");
        Scanner scanner = new Scanner(System.in);
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                out("Cadastrando pasciente");
                cadastrarPasciente();
                break;
            case 2:
                out("Cadastrando medico");
                cadastrarMedico();
                break;
            case 3:
                out("Vincular medico e especialidade");
                vincularMedicoEspecialidade();
                break;
            case 4:
                out("Buscando pasciente");
                buscarPasciente();
                break;
            case 5:
                out("Deletando médico");
                deletarMedico();
                break;

            default:
                out("Opção inválida");
                break;
        }
    }

    private static void out(String s) {
        System.out.println(s);
    }

    public static void cadastrarPasciente() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        PersistenciaPasciente persistenciaPasciente = new PersistenciaPasciente();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Pasciente pasciente = new Pasciente();
        // Solicita ao usuário que digite uma entrada
        out("Digite o nome do pasciente:");
        pasciente.setNome(scanner.nextLine());
        out("Digite a data de nascimento do pasciente:");
        Date dataNascimento = sdf.parse(scanner.nextLine(), new java.text.ParsePosition(0));
        pasciente.setDataNascimento(dataNascimento);
        pasciente.setDataConsulta(Date.from(Instant.now()));
        out("Verifique os dados do pasciente:");
        out("Nome: " + pasciente.getNome());
        out("Data de nascimento: " + pasciente.getDataNascimento());
        out("Data de consulta: " + pasciente.getDataConsulta());
        out("Para confirmar digite S, para corrigir digite N, para voltar ao menu inicial digite V");
        String confirmacao = scanner.nextLine();
        switch (confirmacao) {
            case "S":
                persistenciaPasciente.Salvar(pasciente);
                break;
            case "N":
                out("Cadastro cancelado");
                break;
            default:
                out("Opção inválida");
                menuInicial();
                break;
        }
        menuInicial();
    }

    private static void buscarPasciente() throws SQLException {

        Scanner scanner = new Scanner(System.in);
        out("Para listar todos pascientes digite 1 \nPara buscar pelo nome digite 2");
        PersistenciaPasciente persistenciaPasciente = new PersistenciaPasciente();
        int opcao = scanner.nextInt();
        ArrayList<String> pascientes = null;
        switch (opcao) {
            case 1:
                out("Listando todos os pascientes");
                pascientes = persistenciaPasciente.buscarPasciente();
                break;
            case 2:
                scanner.reset();
                out("Digite o nome do pasciente:");
                String nome = scanner.nextLine();
                pascientes = persistenciaPasciente.buscarPasciente(nome);
                break;
            default:
                out("Opção inválida");
                break;
        }

        for (String pasciente : pascientes) {
            out(pasciente);
        }

        scanner.reset();
        out("Para editar pasciente dige e\nPara deletar pasciente digite d");

        String opcaoEditar = scanner.next();

        switch (opcaoEditar) {
            case "e":
                out("Digite o código do pasciente que deseja editar:");
                int codigo = scanner.nextInt();
                scanner.reset();
                out("Digite o novo nome do pasciente:");
                String novoNome = scanner.next();
                persistenciaPasciente.editarPasciente(codigo, novoNome);
                break;
            case "d":
                System.out.println("Digite o código do pasciente que deseja deletar:");
                int codigoDeletar = scanner.nextInt();
                persistenciaPasciente.deletarPasciente(codigoDeletar);
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }


    }

    private static void cadastrarMedico() throws SQLException {

        Medico medico = new Medico();
        PersistenciaMedico persistenciaMedico = new PersistenciaMedico();
        PersistenciaEspecialidade persistenciaMateria = new PersistenciaEspecialidade();
        ArrayList<String> materias = null;
        try {
            materias = persistenciaMateria.buscarEspecialidade();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Scanner scanner = new Scanner(System.in);

        out("Digite o nome do medico:");
        String nome = scanner.nextLine();
        medico.setNome(nome);
        for (String materia : materias) {
            out(materia);
        }
        out("Digite o código da especialidade do medico:");
        medico.setEspecialidade(scanner.nextInt());
        out("Verifique os dados do medico:");
        out("Nome: " + medico.getNome());
        out("Especialidade: " + medico.getEspecialidade());
        persistenciaMedico.Salvar(medico);


    }
    public static void vincularMedicoEspecialidade() throws SQLException {
        PersistenciaMedico persistenciaMedico = new PersistenciaMedico();
        PersistenciaEspecialidade persistenciaEspecialidade = new PersistenciaEspecialidade();
        ArrayList<String> especialidades = null;
        ArrayList<String> medicos = null;
        try {
            especialidades = persistenciaEspecialidade.buscarEspecialidade();
            medicos = persistenciaMedico.buscarMedico();
            Scanner scanner = new Scanner(System.in);
            out("Selecione o medico:");
            for (String medico : medicos) {
                out(medico);
            }
            int codigoMedico = scanner.nextInt();
            out("Selecione a especialidade:");
            for (String especialidade : especialidades) {
                out(especialidade);
            }
            int codigoMateria = scanner.nextInt();
            persistenciaMedico.vincularMedicoEspecialidade(codigoMedico, codigoMateria);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deletarMedico() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        PersistenciaMedico persistenciaMedico = new PersistenciaMedico();

        ArrayList<String> medicos = persistenciaMedico.buscarMedico();

        out("Lista de Médicos:");
        for (String medico : medicos) {
            out(medico);
        }

        out("Digite o código do médico que deseja deletar:");
        int codigoMedico = scanner.nextInt();

        persistenciaMedico.deletarMedico(codigoMedico);

        out("Médico deletado com sucesso!");
    }

}