package Pessoa;

import Evento.ModelEvento;

public class ControllerPessoa {

    public String InscreverEvento(ModelEvento evento, ModelPessoa pessoa){
        if(pessoa.AddEvento(evento))
            return "Inscrição feita com sucesso";

        return "Erro na inscrção, tente novamente";
    }

}
