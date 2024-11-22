public class ControllerPessoa extends Exceptions{

    public String InscreverEvento(ModelEvento evento, ModelPessoa pessoa){
        if(pessoa.AddEvento(evento))
            return "Inscrição feita com sucesso";

        return EventoNaoEncontratoException();
    }
    public String DesinscreverEvento(ModelEvento evento, ModelPessoa pessoa){
        if(pessoa.AddEvento(evento))
            return "Desinscrição feita com sucesso";

        return EventoNaoEncontratoException();
    }
}
