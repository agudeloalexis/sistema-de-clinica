package persistencia.conexao;

import java.util.Date;

public class Pasciente extends Pessoa{

    Date dataConsulta;
    public Pasciente(){

    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }
}