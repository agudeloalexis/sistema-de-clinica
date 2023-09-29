package persistencia.conexao;

import java.util.Date;

abstract class Pessoa {
    private String nome;
    private Long codigo;
    private Date dataNascimento;


    public Pessoa() {
    }

    public Pessoa(String nome, Long codigo, Date dataNascimento) {
        this.nome = nome;
        this.codigo = codigo;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}