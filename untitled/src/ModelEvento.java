import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModelEvento extends Exceptions{
    private int identificador;
    private String nome;
    private Date inicioEvento;
    private Date terminoEvento;
    private List<ModelPessoa> estudantes = new ArrayList<ModelPessoa>();
    private ModelPessoa administrador;
    private TipoEvento tipoEvento;

    public String getNome() {
        return nome;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public ModelPessoa getAdministrador() {
        return administrador;
    }

    public List<ModelPessoa> getEstudantes() {
        return estudantes;
    }

    public int getEstudantesSize() {
        if(estudantes == null)
            return 0;
        return estudantes.size();
    }

    public boolean AddEstudante(ModelPessoa pessoa){
        if(estudantes.size() >= 10){
            System.out.println(CapacidadeExcedidaException());
            return false;
        }
        return estudantes.add(pessoa);
    }

    public boolean DeleteEstudante(ModelPessoa pessoa){
        for(ModelPessoa estudanteInscrito: estudantes){
            if(estudanteInscrito.getIdentificador() == pessoa.getIdentificador()){
                return estudantes.remove(pessoa);
            }
        }
        return false;
    }

    public Date getTerminoEvento() {
        return terminoEvento;
    }

    public Date getInicioEvento() {
        return inicioEvento;
    }

    public int getIdentificador() {
        return identificador;
    }

    public ModelEvento(int identificador,String nome, Date inicioEvento, Date terminoEvento, ModelPessoa administrador, TipoEvento tipoEvento) {
        this.identificador = identificador;
        this.nome = nome;
        this.inicioEvento = inicioEvento;
        this.terminoEvento = terminoEvento;
        this.administrador = administrador;
        this.tipoEvento = tipoEvento;
    }

    @Override
    public String toString() {
        return "ModelEvento{" +
                "identificador=" + identificador +
                ", \ninicioEvento=" + new SimpleDateFormat("yyyy-MM-dd").format(inicioEvento) +
                ", \nterminoEvento=" + new SimpleDateFormat("yyyy-MM-dd").format(terminoEvento) +
                ", \ntipo evento=" + tipoEvento +
                ", \nadministrador=" + administrador +
                //", \nestudantes=" + estudantes +
                '}';
    }
}
