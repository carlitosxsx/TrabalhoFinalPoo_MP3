import java.util.ArrayList;
import java.util.Scanner;

public class MusicPlayer implements IMusicPlayer {

    private boolean statusReproducao;
    private boolean running;
    private Playlist playlist;
    private Scanner scanner;
    private ArrayList<Music> musicasAvancadas;

    public MusicPlayer(){

        this.setStatusReproducao(false);
        playlist = new Playlist(this);
        scanner = new Scanner(System.in);
        musicasAvancadas = new ArrayList<>();

    }

    public boolean isRunning() {
        return running;
    }
    public void setRunning(boolean running) {
        this.running = running;
    }
    public boolean isStatusReproducao() {
        return statusReproducao;
    }
    public void setStatusReproducao(boolean statusReproducao) {
        this.statusReproducao = statusReproducao;
    }
    public ArrayList<Music> getMusicasAvancadas() {
        return musicasAvancadas;
    }
    public void setMusicasAvancadas(ArrayList<Music> musicasAvancadas) {
        this.musicasAvancadas = musicasAvancadas;
    }

    @Override
    public void apresentarMP3() {

        this.setRunning(true);
        do {

            System.out.println("========================");
            System.out.println("|      TOCADOR MP3     |");
            System.out.println("========================");
            System.out.println("|                      |");
            System.out.println("| 1 - Play             |");
            System.out.println("| 2 - Pause            |");
            System.out.println("| 3 - Avançar Faixa    |");
            System.out.println("| 4 - Retornar Faixa   |");
            System.out.println("| 5 - Acessar Playlist |");
            System.out.println("| 6 - Fechar           |");
            System.out.println("|                      |");
            System.out.println("========================");

            System.out.println("Qual ação você deseja realizar? Digite o valor correspondende:");
            int valor = scanner.nextInt();

            switch (valor) {
                case 1 -> this.play();
                case 2 -> this.pause();
                case 3 -> this.avancarFaixa();
                case 4 -> this.retornarFaixa();
                case 5 -> this.acessarPlaylist();
                case 6 -> {
                    running = false;
                    System.out.println(ConsoleColors.RED_BOLD + "Saindo..." + ConsoleColors.RESET);
                }
                default -> System.out.println(ConsoleColors.RED_BACKGROUND + "Insira o valor de uma ação válida" + ConsoleColors.RESET);
            }

        } while (this.isRunning());

        scanner.close();

    }

    @Override
    public void play() {

        if (this.isStatusReproducao()){
            System.out.println(ConsoleColors.YELLOW_BOLD + "MP3 já está tocando!" + ConsoleColors.RESET);

        } else if (playlist.getListaReproducao().isEmpty()){
            System.out.println(ConsoleColors.RED_BOLD + "Sua playlist está vazia!" + ConsoleColors.RESET);

        } else {
            this.setStatusReproducao(true);
            System.out.println(ConsoleColors.GREEN_BOLD + playlist.getListaReproducao().getFirst().getMusica() + " de " + playlist.getListaReproducao().getFirst().getArtista() + " está tocando agora!" + ConsoleColors.RESET);
        }

    }

    @Override
    public void pause() {

        if (this.isStatusReproducao()){
            this.setStatusReproducao(false);
            System.out.println(ConsoleColors.GREEN_BOLD + playlist.getListaReproducao().getFirst().getMusica() + " de " + playlist.getListaReproducao().getFirst().getArtista() + " foi pausada!" + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.YELLOW_BOLD + "O MP3 já está pausado!" + ConsoleColors.RESET);
        }

    }

    @Override
    public void avancarFaixa() {

        if (playlist.getListaReproducao().isEmpty()) {
            System.out.println(ConsoleColors.RED_BOLD + "Sua playlist está vazia!" + ConsoleColors.RESET);

        } else {
            this.getMusicasAvancadas().add(playlist.getListaReproducao().getFirst());
            playlist.getListaReproducao().remove(0);

            if (playlist.getListaReproducao().isEmpty()){
                this.setStatusReproducao(false);
                System.out.println(ConsoleColors.YELLOW_BOLD + "Essa era a última musica da playlist, o tocador foi pausado!" + ConsoleColors.RESET);

            } else if (this.isStatusReproducao()){
                System.out.println(ConsoleColors.GREEN_BOLD + "Avançou Para: " + playlist.getListaReproducao().getFirst().getMusica() + " de " + playlist.getListaReproducao().getFirst().getArtista() + " está tocando agora!" + ConsoleColors.RESET);

            } else {
                this.play();
            }
        }

    }

    @Override
    public void retornarFaixa() {

        if (this.getMusicasAvancadas().isEmpty()) {
            System.out.println(ConsoleColors.RED_BOLD + "Você ainda não avançou nenhuma música!" + ConsoleColors.RESET);

        } else {
            playlist.getListaReproducao().addFirst(this.getMusicasAvancadas().getLast());
            this.getMusicasAvancadas().removeLast();

            if (this.isStatusReproducao()) {
                System.out.println(ConsoleColors.GREEN_BOLD + "Retornou Para: " + playlist.getListaReproducao().getFirst().getMusica() + " de " + playlist.getListaReproducao().getFirst().getArtista() + " está tocando agora!" + ConsoleColors.RESET);

            } else {
                this.play();
            }
        }

    }

    @Override
    public void acessarPlaylist() {
        playlist.apresentarPlaylist();
    }
}
