package Evento;

import Pessoa.ModelPessoa;

import java.util.Date;
import java.util.List;

public class ModelEvento {
    private String identificador;
    private Date inicioEvento;
    private Date terminoEvento;
    private List<ModelPessoa> Estudantes;
    private ModelPessoa administrador;

    public ModelPessoa getAdministrador() {
        return Administrador;
    }

    public List<ModelPessoa> getEstudantes() {
        return Estudantes;
    }

    public Date getTerminoEvento() {
        return terminoEvento;
    }

    public Date getInicioEvento() {
        return inicioEvento;
    }

    public String getIdentificador() {
        return identificador;
    }

    public ModelEvento(String identificador, Date inicioEvento, Date terminoEvento, ModelPessoa administrador) {
        this.identificador = identificador;
        this.inicioEvento = inicioEvento;
        this.terminoEvento = terminoEvento;
        this.administrador = administrador;
    }
}
