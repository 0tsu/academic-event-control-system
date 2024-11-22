public abstract class Exceptions {
    protected String EventoNaoEncontratoException(){
        return "EventoNaoEncontratoException: Este Evento não foi encontrado na base de dados!";
    }

    protected String CapacidadeExcedidaException(){
        return "CapacidadeExcedidaException: Este evento esta com a capacidade execida!";
    }

    protected String UsuarioNaoEncontratoException(){
        return "UsuarioNaoEncontratoException: Este usuario não foi encontrado na base de dados!";
    }
}
