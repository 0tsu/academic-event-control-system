import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Dados extends Exceptions{

    Scanner sc = new Scanner(System.in);
    SimpleDateFormat dateNascimanto = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat dateEvento = new SimpleDateFormat("dd/MM/yyyy");

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

    public TipoEvento SelecionaTipoEvento(int opc){
        switch (opc){
            case 1:
                return TipoEvento.COMPETICAO;
            case 2:
                return TipoEvento.MINICURSO;
            case 3:
                return TipoEvento.PALESTRA;
            case 4:
                return TipoEvento.WORKSHOP;
            case 5:
                return TipoEvento.SEMINARIO;
            default:
                return null;
        }
    }

    public ModelEvento GeraEvento(){
        Date inicioEvento;
        Date terminoEvento;

        System.out.println("Informe o nome do evento: ");
        nome = sc.next();

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
                System.out.println("Informe o termino do evento dd/MM/YYYY hh:mm: ");
                terminoEvento = dateEvento.parse(sc.next());
                break;
            } catch (Exception e) {
                System.out.println("Formato de data inválido. Por favor, tente novamente.");
            }
        }

        System.out.println("Informe o tipo do evento \n" +
                           "1-COMPETICAO\n" +
                           "2-MINICURSO\n" +
                           "3-PALESTRA\n" +
                           "4-WORKSHOP\n" +
                           "5-SEMINARIO");
        TipoEvento tipoEvento = SelecionaTipoEvento(sc.nextInt());

        return new ModelEvento(eventoId, nome, inicioEvento, terminoEvento, administrador, tipoEvento);
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

    public void MostrarEstudantesEvento(ModelEvento evento){
        for(ModelPessoa estudante: evento.getEstudantes()){
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

    public void MostrarEventosQuantidade(){
        eventos.sort(Comparator.comparing(e -> e.getEstudantes().size()));

        for(ModelEvento evento : eventos){
            System.out.println(evento.toString());
        }
    }

    public void MostrarEventosTipoEvento(){
        eventos.sort(Comparator.comparing(e -> e.getTipoEvento()));

        for(ModelEvento evento : eventos){
            System.out.println(evento.toString());
        }
    }

    public void MostrarEventosPeriodo(Date inicio, Date termino, int periodo){
        for(ModelEvento evento : eventos){
            if(periodo == 1){
                if(evento.getInicioEvento().after(inicio) && evento.getInicioEvento().before(termino)){
                    System.out.println(evento.toString());
                }
            }
            else if (periodo == 2){
                if(evento.getTerminoEvento().after(inicio) && evento.getTerminoEvento().before(termino)){
                    System.out.println(evento.toString());
                }
            }
        }
    }

    public void SelecionaOpcao(int opc){
        switch (opc){
            case 1:
                eventos.add(GeraEvento());
                eventoId++;
                System.out.println("Evento registrado com sucesso!\n");
                break;

            case 2:
                if(eventos.isEmpty()){
                    System.out.println(NaoContemEventosException());
                    break;
                }

                estudantes.add(GeraEstudante());
                pessoaId++;

                System.out.println("Estudante registrado com sucesso!\n");
                break;

            case 3:
                MostrarEstudantes();
                System.out.print("Informe o estudante que deseja excluir: ");
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
                System.out.print("Informe o estudante que deseja registrar em um evento: ");
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
                System.out.print("Informe o estudante que deseja desenscrever em um evento: ");
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
                System.out.println("1 - Mostrar os estudantes cadastrado em um evento especifico");
                System.out.println("2 - Mostrar os eventos classificado pela quantidade");
                System.out.println("3 - Mostrar os eventos classificado pelo tipo de evento");
                System.out.println("4 - Mostrar os eventos por periodo");
                System.out.println("5 - Voltar");
                System.out.print("Digite sua opção de consulta: ");
                SelecionaConsulta(sc.nextInt());
                break;

            case 7:
                isRun = false;
                break;

            default:
                System.out.println("Opção invalida! ");
                break;
        }
    }

    public void SelecionaConsulta(int opc){
        switch (opc){
            case 1:
                MostrarEventos();
                System.out.print("Informe qual evento que deseja ver: ");
                evento = getEvento(sc.nextInt());
                if(evento == null){
                    System.out.println(EventoNaoEncontratoException());
                    break;
                }
                MostrarEstudantesEvento(evento);
                break;

            case 2:
                MostrarEventosQuantidade();
                break;

            case 3:
                MostrarEventosTipoEvento();
                break;

            case 4:
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
                System.out.println("Informe o periodo referente \n" +
                        "1 - inicio evento\n" +
                        "2 - termino evento");
                MostrarEventosPeriodo(inicioEvento, terminoEvento, sc.nextInt());
                break;

            case 5:
                break;

            default:
                System.out.println("Opção invalida! ");
                break;
        }
    }

    public void Menu(){
        System.out.println("Sistema de controle de Eventos");

        System.out.println("Registre um administrador!");

        administrador = GeraAdministrador();
        pessoaId++;
        System.out.println("Administrador registrado com sucesso!\n");

        while(isRun){
            System.out.println("1 - Registrar um evento");
            System.out.println("2 - Registrar um estudante");
            System.out.println("3 - Excluir um estudante");
            System.out.println("4 - Inscrever um estudante");
            System.out.println("5 - Desenscrever um estudante");
            System.out.println("6 - Consultas");
            System.out.println("7 - Sair");
            System.out.println("Escolha sua opção: ");
            SelecionaOpcao(sc.nextInt());
        }
    }
}
