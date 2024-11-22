import java.util.Date;
import java.util.List;

public class ModelPessoa{

    private String indentificador;
    private String nome;
    private Date DataNascimento;
    private TipoPessoa tipoPessoa;
    private List<ModelEvento> eventosInscritos;

    public String getIndentificador() {
        return indentificador;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataNascimento() {
        return DataNascimento;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public List<ModelEvento> getEventosInscritos() {
        return eventosInscritos;
    }

    public boolean AddEvento(ModelEvento evento){
        return eventosInscritos.add(evento);
    }

    public boolean DeleteEvento(ModelEvento evento){
        for(ModelEvento eventoIncrito: eventosInscritos){
            if(eventoIncrito.getIdentificador().equals(evento.getIdentificador())){
                return eventosInscritos.remove(evento);
            }
        }
        return false;
    }

    public ModelPessoa(String indentificador, String nome, Date dataNascimento, TipoPessoa tipoPessoa) {
        this.indentificador = indentificador;
        this.nome = nome;
        DataNascimento = dataNascimento;
        this.tipoPessoa = tipoPessoa;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "indentificador='" + indentificador + '\'' +
                ", nome='" + nome + '\'' +
                ", DataNascimento=" + DataNascimento +
                ", tipoPessoa=" + tipoPessoa +
                ", eventosInscritos=" + eventosInscritos +
                '}';
    }
}
