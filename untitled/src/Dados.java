import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Dados extends Exceptions{

    Scanner sc = new Scanner(System.in);
    SimpleDateFormat dateNascimanto = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dateEvento = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    private List<ModelEvento> eventos = new ArrayList<ModelEvento>();
    private List<ModelPessoa> estudantes = new ArrayList<ModelPessoa>();
    ModelPessoa estudante;
    ModelEvento evento;

    private int pessoaId = 1;
    private int eventoId = 1;

    private Date nascimento = null;
    private String nome;

    private ModelPessoa administrador;

    private boolean isRun = true;

    public ModelPessoa GeraAdministrador(){
        System.out.print("Informe o nome do administrador: ");
        nome = sc.next();
        System.out.print("Informe a data de nascimento do administrador (dd/mm/yyyy): ");
        while(true) {
            try {
                nascimento = dateNascimanto.parse(sc.next());
                System.out.println("Data de nascimento armazenada: " + nascimento);
                break;
            } catch (Exception e) {
                System.out.println("Formato de data inválido. Por favor, tente novamente.");
            }
        }
        return new ModelPessoa(pessoaId, nome, nascimento, TipoPessoa.ADMINISTRADOR);
    }

    public ModelEvento GeraEvento(){
        Date inicioEvento;
        Date terminoEvento;
        while(true) {
            try {
                System.out.println("Informe o inicio do evento dd/MM/YYYY hh:mm: ");
                inicioEvento = dateEvento.parse(sc.next());
                break;
            } catch (Exception e) {
                System.out.println("Formato de data inválido. Por favor, tente novamente.");
            }
        }
        while(true) {
            try {
                System.out.println("Informe o inicio do evento dd/MM/YYYY hh:mm: ");
                terminoEvento = dateEvento.parse(sc.next());
                break;
            } catch (Exception e) {
                System.out.println("Formato de data inválido. Por favor, tente novamente.");
            }
        }

        return new ModelEvento(eventoId, inicioEvento, terminoEvento, administrador);
    }

    public ModelPessoa GeraEstudante(){
        System.out.print("Informe o nome do aluno: ");
        nome = sc.next();
        System.out.print("Informe a data de nascimento do aluno (dd/mm/yyyy): ");
        while(true) {
            try {
                nascimento = dateNascimanto.parse(sc.next());
                System.out.println("Data de nascimento armazenada: " + nascimento);
                break;
            } catch (Exception e) {
                System.out.println("Formato de data inválido. Por favor, tente novamente.");
            }
        }
        return new ModelPessoa(pessoaId, nome, nascimento, TipoPessoa.ESTUDANTE);
    }

    public void MostrarEventos(){
        for(ModelEvento evento: eventos){
            System.out.println(evento.toString() +"\n");
        }
    }

    public void MostrarEstudantes(){
        for(ModelPessoa estudante: estudantes){
            System.out.println(estudante.toString() + "\n");
        }
    }

    public void MostrarEventosInscritos(ModelPessoa estudante){
        for (ModelEvento eventosInscritos: estudante.getEventosInscritos()){
            System.out.println(eventosInscritos.toString() + "\n");
        }
    }

    public void InscreverEstudante(ModelEvento evento, ModelPessoa estudante){
        if(evento.AddEstudante(estudante)){
            estudante.AddEvento(evento);
        }
    }

    public void DesinscreverEstudante(ModelEvento evento, ModelPessoa estudante){
        if(estudante.DeleteEvento(evento)){
            evento.DeleteEstudante(estudante);
        }
    }

    public ModelPessoa getEstudante(int id){
        for(ModelPessoa estudante: estudantes){
            if(estudante.getIdentificador() == id){
                return estudante;
            }
        }
        return null;
    }

    public ModelEvento getEvento(int id){
        for(ModelEvento evento: eventos){
            if(evento.getIdentificador() == id){
                return evento;
            }
        }
        return null;
    }

    public void ExcluirEstudante(ModelPessoa estudante){
        estudantes.remove(estudante);
        System.out.println("Estudante removido com sucesso!");
    }

    public void Menu(){

        System.out.println("Sistema de controle de Eventos");

        System.out.println("Registre um administrador!");

        administrador = GeraAdministrador();
        pessoaId++;
        System.out.println("Administrador registrado com sucesso!");

        while(isRun){
            System.out.println("Escolha suas opções");
            System.out.println("1 - Registrar um evento");
            System.out.println("2 - Registrar um estudante");
            System.out.println("3 - Excluir um estudante");
            System.out.println("4 - Inscrever um estudante");
            System.out.println("5 - Desenscrever um estudante");
            System.out.println("6 - Consulta por eventos");
            System.out.println("7 - Consulta por estudante");
            System.out.println("8 - Sair");
            int opcoes = sc.nextInt();

            switch (opcoes){
                case 1:
                    eventos.add(GeraEvento());
                    eventoId++;
                    System.out.println("Evento registrado com sucesso!");
                    break;
                case 2:
                    if(eventos.isEmpty()){
                        System.out.println(NaoContemEventosException());
                        break;
                    }

                    estudantes.add(GeraEstudante());
                    pessoaId++;

                    System.out.println("Estudante registrado com sucesso!");
                    break;
                case 3:
                    MostrarEstudantes();
                    System.out.println("Informe o estudante que deseja excluir: ");
                    estudante = getEstudante(sc.nextInt());
                    if(estudante == null){
                        System.out.println(UsuarioNaoEncontratoException());
                        break;
                    }
                    if(!estudante.getEventosInscritos().isEmpty()){
                        System.out.println(ContemEventosInscritosException());
                        break;
                    }
                    ExcluirEstudante(estudante);
                    break;
                case 4:
                    MostrarEstudantes();
                    System.out.println("Informe o estudante que deseja registrar em um evento: ");
                    estudante = getEstudante(sc.nextInt());
                    if(estudante == null){
                        System.out.println(UsuarioNaoEncontratoException());
                        break;
                    }

                    MostrarEventos();
                    System.out.print("Informe qual evento que deseja inscrever o estudante digitando o numeros de identificação: ");
                    evento = getEvento(sc.nextInt());
                    if(evento == null){
                        System.out.println(EventoNaoEncontratoException());
                        break;
                    }

                    InscreverEstudante(evento, estudante);
                    break;

                case 5:
                    MostrarEstudantes();
                    System.out.println("Informe o estudante que deseja desenscrever em um evento: ");
                    estudante = getEstudante(sc.nextInt());
                    if(estudante == null){
                        System.out.println(UsuarioNaoEncontratoException());
                        break;
                    }

                    MostrarEventosInscritos(estudante);
                    System.out.print("Informe qual evento que deseja desenscrever o estudante digitando o numeros de identificação: ");
                    evento = getEvento(sc.nextInt());
                    if(evento == null){
                        System.out.println(EventoNaoEncontratoException());
                        break;
                    }

                    DesinscreverEstudante(evento, estudante);
                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:
                    isRun = false;
                    break;

                default:
                    System.out.println("Opção invalida! ");
            }
        }
    }
}
