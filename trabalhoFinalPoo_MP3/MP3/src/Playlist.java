import java.util.ArrayList;
import java.util.Scanner;

public class Playlist {

    private MusicPlayer tocador;
    private Music musica;
    private ArrayList<Music> listaReproducao = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public Playlist(MusicPlayer tocador){
        this.tocador = tocador;
    }

    public MusicPlayer getTocador() {
        return tocador;
    }
    public void setTocador(MusicPlayer tocador) {
        this.tocador = tocador;
    }
    public Music getMusica() {
        return musica;
    }
    public void setMusica(Music musica) {
        this.musica = musica;
    }
    public ArrayList<Music> getListaReproducao() {
        return listaReproducao;
    }
    public void setListaReproducao(ArrayList<Music> listaReproducao) {
        this.listaReproducao = listaReproducao;
    }

    public void apresentarPlaylist() {

        do {

            System.out.println("========================");
            System.out.println("|       PLAYLIST       |");
            System.out.println("========================");
            System.out.println("|                      |");
            System.out.println("| 1 - Adicionar faixa  |");
            System.out.println("| 2 - Remover faixa    |");
            System.out.println("| 3 - Consultar fila   |");
            System.out.println("| 4 - Voltar ao MP3    |");
            System.out.println("|                      |");
            System.out.println("========================");

            System.out.println("Qual ação você deseja realizar? Digite o valor correspondende:");
            int valor = scanner.nextInt();
            scanner.nextLine();

            switch (valor) {
                case 1 -> this.adicionarFaixa();
                case 2 -> this.removerFaixa();
                case 3 -> this.consultarFila();
                case 4 -> this.voltarMp3();
                default -> System.out.println(ConsoleColors.RED_BACKGROUND + "Insira o valor de uma ação válida" + ConsoleColors.RESET);
            }

        } while (getTocador().isRunning());

    }

    public void adicionarFaixa(){

        System.out.println("Qual o nome da música?");
        String nmMusica = scanner.nextLine().toUpperCase();

        System.out.println("Qual o artista responsável?");
        String nmArtista = scanner.nextLine().toUpperCase();

        musica = new Music(nmMusica, nmArtista);

        listaReproducao.add(musica);
        System.out.println(ConsoleColors.GREEN_BOLD + "Música " + musica.getMusica() + " de " + musica.getArtista() + " adicionada com sucesso!" + ConsoleColors.RESET);

    }

    public void removerFaixa(){

        if (listaReproducao.isEmpty()) {
            System.out.println(ConsoleColors.RED_BOLD + "A fila de reprodução está vazia :(" + ConsoleColors.RESET);

        } else {
            System.out.println("Qual a posição faixa que você quer remover?");
            int numeroFaixa = scanner.nextInt()-1;

            if (numeroFaixa < listaReproducao.size()) {
                System.out.println(ConsoleColors.GREEN_BOLD + "Música: [" + (numeroFaixa + 1) + "] " + listaReproducao.get(numeroFaixa).getMusica() + " - " + listaReproducao.get(numeroFaixa).getArtista() + " removida com sucesso!" + ConsoleColors.RESET);
                listaReproducao.remove(numeroFaixa);

                if (tocador.isStatusReproducao() && listaReproducao.isEmpty()) {
                    tocador.pause();
                    System.out.println(ConsoleColors.YELLOW_BOLD + "Essa era a última musica da playlist, o tocador foi pausado!" + ConsoleColors.RESET);
                }
            } else {
                System.out.println(ConsoleColors.YELLOW_BOLD + "Não existe nenhuma faixa nessa posição, consulte a fila de reprodução!" + ConsoleColors.RESET);
            }

        }

    }

    public void consultarFila(){

        if (listaReproducao.isEmpty()) {
            System.out.println(ConsoleColors.RED_BOLD + "A fila de reprodução está vazia :(" + ConsoleColors.RESET);

        } else {
            System.out.println(ConsoleColors.CYAN_BOLD); // Dar espaçamento
            System.out.println("----------------------------------");
            System.out.println("|              FILA              |");
            System.out.println("----------------------------------");

            for (int j = 0; j < listaReproducao.size(); j++) {
                System.out.println("[" + (j+1) + "] " + listaReproducao.get(j).getMusica() + " - " + listaReproducao.get(j).getArtista());
            }
            System.out.println(ConsoleColors.RESET); //Dar Espaçamento
        }

    }

    public void voltarMp3(){
        tocador.apresentarMP3();
    }

}
