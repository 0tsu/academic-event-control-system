public abstract class Exceptions {
    protected String EventoNaoEncontratoException(){
        return "EventoNaoEncontratoException: Este Evento não foi encontrado na base de dados!";
    }

    protected String NaoContemEventosException(){
        return "NaoContemEventosException: Não Existe nem um evento registrado na base de dados!";
    }

    protected String ContemEventosInscritosException(){
        return "ContemEventosInscritosException: Operação não permitida! Este estudante tem evento(s) inscrito(s)";
    }

    protected String CapacidadeExcedidaException(){
        return "CapacidadeExcedidaException: Este evento esta com a capacidade execida!";
    }

    protected String UsuarioNaoEncontratoException(){
        return "UsuarioNaoEncontratoException: Este usuario não foi encontrado na base de dados!";
    }
}
