import java.util.Date;
import java.util.List;

public class ModelPessoa{

    private int identificador;
    private String nome;
    private Date DataNascimento;
    private TipoPessoa tipoPessoa;
    private List<ModelEvento> eventosInscritos;

    public int getIdentificador() {
        return identificador;
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
        for(ModelEvento eventoInscrito: eventosInscritos){
            if(eventoInscrito.getIdentificador() == evento.getIdentificador()){
                return eventosInscritos.remove(evento);
            }
        }
        return false;
    }

    public ModelPessoa(int identificador, String nome, Date dataNascimento, TipoPessoa tipoPessoa) {
        this.identificador = identificador;
        this.nome = nome;
        DataNascimento = dataNascimento;
        this.tipoPessoa = tipoPessoa;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "indentificador='" + identificador + '\'' +
                ", nome='" + nome + '\'' +
                ", DataNascimento=" + DataNascimento +
                ", tipoPessoa=" + tipoPessoa +
                ", eventosInscritos=" + eventosInscritos +
                '}';
    }
}
