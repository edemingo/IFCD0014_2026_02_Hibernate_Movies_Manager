package es.edemingo.moviesmanager;

import es.edemingo.moviesmanager.model.Genero;
import es.edemingo.moviesmanager.model.Pelicula;
import es.edemingo.moviesmanager.persistence.GeneroDAO;
import es.edemingo.moviesmanager.persistence.PeliculaDAO;
import es.edemingo.moviesmanager.persistence.SessionManager;

import es.edemingo.moviesmanager.utils.utilities;

import java.util.Scanner;
import java.util.List;

import static es.edemingo.moviesmanager.utils.utilities.getScannerInt;
import static es.edemingo.moviesmanager.utils.utilities.getScannerString;

public class App {


   public static void main(String args[]) {

       Scanner scString = new Scanner(System.in);

        //1. Creación de géneros
        /*
        Genero fantastico = new Genero("Fantastico");
        GeneroDAO.create(fantastico);
        */

        //1.1 Insertamos un cerro de peliculas
       /*
        Genero generoAll = GeneroDAO.findById(4L);
        createMovies(generoAll);
        */


       //1.2 Recuperamos el genero con el id 1


        //2. Creación de película
        /*
        Pelicula myPelicula = new Pelicula("Star Wars: Empire strikes back", "George Lucas", 1977, genero);
        PeliculaDAO.create(myPelicula);
        */

        int anyoBuscado = 1975;

           System.out.print("selecciona Operacion: \n");
           System.out.print("1 - Peliculas del año " + anyoBuscado + "\n");
           System.out.print("2 - Peliculas anteriores a : " + anyoBuscado + "\n");
           System.out.print("3 - Peliculas posteriores a :" + anyoBuscado + "\n");
           System.out.print("4 - Peliculas por genero : \n");
           System.out.print("5 - Peliculas por director : \n");
           System.out.print("6 - Info de un genero : \n");

           int opcion = getScannerInt(scString);

           switch (opcion) {
               case 1:
                   /*-------Peliculas del año dado ------ */
                   ShowFilmFromYear(anyoBuscado);
                   break;

               case 2:
                   /*-------Peliculas anteriores a  año dado ------ */
                   showFilmByDateAnt(anyoBuscado);
                   break;

               case 3:
                   /*-------Peliculas posteriores a año dado ------ */
                   showFilmByDatePos(anyoBuscado);
                   break;

               case 4:
                   /*-------Peliculas por genero  ------*/
                   showFilmByGenre();
                   break;

               case 5:
                   ShowFilmFromDirectorName(scString);
                   break;

               case 6:
                   /*----- Recuparamos los datos de un Genero*/
                   Long idGenero = 3L;
                   Genero genero =  getGenreInfo(idGenero);
                   break;

               case 0:
                   System.out.println("Saliendo del programa...");
                   break;

               default:
                   System.out.println("Opción no válida. Intenta de nuevo.");



           }

       scString.close();
       SessionManager.finishSession();
   }



   public static void ShowFilmFromDirectorName(Scanner scString){

       System.out.print("Introduce el nombre del director: \n");
       String directorName = getScannerString(scString);


       System.out.println(" ");
       System.out.println("-------------------------------------------------------------------------------------------");
       System.out.println("-------Peliculas Por director " + directorName + "------");
       System.out.println("-------------------------------------------------------------------------------------------");

       List<Pelicula> peliculasList = PeliculaDAO.findPeliculadByDirector(directorName);

       for (Pelicula p : peliculasList) {
           System.out.println(rpad(limitarString(p.getTitulo(),35), 40, ' ') + " | " + rpad(limitarString(p.getDirector(),35), 40, ' ') + " | " +  rpad(limitarString(String.valueOf(p.getAnyo()),35), 40, ' ') + " | " + rpad(limitarString(p.getGenero().getName(),35), 40, ' ') );
           System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
       }

   }

    public static void ShowFilmFromYear(int anyoBuscado){
        System.out.println(" ");
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("-------Peliculas del año " + anyoBuscado + "------");
        System.out.println("-------------------------------------------------------------------------------------------");

        List<Pelicula> peliculasList = PeliculaDAO.findPeliculadByAnyo(anyoBuscado);

        for (Pelicula p : peliculasList) {
            System.out.println(rpad(limitarString(p.getTitulo(),35), 40, ' ') + " | " + rpad(limitarString(p.getDirector(),35), 40, ' ') + " | " +  rpad(limitarString(String.valueOf(p.getAnyo()),35), 40, ' ') + " | " + rpad(limitarString(p.getGenero().getName(),35), 40, ' ') );
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    public static void showFilmByDateAnt(int anyoBuscado){

        System.out.println(" ");
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("-------Peliculas anteriores a " + anyoBuscado + "------");
        System.out.println("-------------------------------------------------------------------------------------------");

        List<Pelicula> peliculasListAnt = PeliculaDAO.findPeliculadByAnyoAnt(anyoBuscado);

        for (Pelicula p : peliculasListAnt) {
            System.out.println(rpad(limitarString(p.getTitulo(),35), 40, ' ') + " | " + rpad(limitarString(p.getDirector(),35), 40, ' ') + " | " +  rpad(limitarString(String.valueOf(p.getAnyo()),35), 40, ' ') + " | " + rpad(limitarString(p.getGenero().getName(),35), 40, ' ') );
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
        }

    }

    public static void showFilmByDatePos(int anyoBuscado){

        System.out.println(" ");
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("-------Peliculas posteriores a " + anyoBuscado + "------");
        System.out.println("-------------------------------------------------------------------------------------------");

        List<Pelicula> peliculasListPos = PeliculaDAO.findPeliculadByAnyoPos(anyoBuscado);

        for (Pelicula p : peliculasListPos) {
            System.out.println(rpad(limitarString(p.getTitulo(),35), 40, ' ') + " | " + rpad(limitarString(p.getDirector(),35), 40, ' ') + " | " +  rpad(limitarString(String.valueOf(p.getAnyo()),35), 40, ' ') + " | " + rpad(limitarString(p.getGenero().getName(),35), 40, ' ') );
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }


    public static void showFilmByGenre(){

        List<Genero> generos = GeneroDAO.findAll();

        Scanner sc = new Scanner(System.in);
        System.out.print("selecciona el genero: \n");
        for (Genero g : generos) {
            System.out.println(g.getIdGenero()  + " - " + g.getName());
        }
        Long intgeneroBuscado = sc.nextLong();
        Genero generoBuscado = GeneroDAO.findById(intgeneroBuscado);
        sc.close();

        System.out.println(" ");
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("-------Peliculas por genero  " + generoBuscado.getName() + "------");
        System.out.println("-------------------------------------------------------------------------------------------");

        List<Pelicula> peliculasListByGenre = PeliculaDAO.findPeliculadByGenre(generoBuscado);

        for (Pelicula p : peliculasListByGenre) {
            System.out.println(rpad(limitarString(p.getTitulo(),35), 40, ' ') + " | " + rpad(limitarString(p.getDirector(),35), 40, ' ') + " | " +  rpad(limitarString(String.valueOf(p.getAnyo()),35), 40, ' ') + " | " + rpad(limitarString(p.getGenero().getName(),35), 40, ' ') );
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
        }

    }

    public static Genero getGenreInfo(Long idGenero){
        return GeneroDAO.findById(3L);
    }

    private static void createMovies(Genero genero){
        PeliculaDAO.create(new Pelicula("El Replandor", "Kubrick", 1978, genero));
        PeliculaDAO.create(new Pelicula("The Cabinet of Dr. Caligari", "Robert Wiene", 1920, genero));
        PeliculaDAO.create(new Pelicula("Metropolis", "Fritz Lang", 1927, genero));
        PeliculaDAO.create(new Pelicula("City Lights", "Charles Chaplin", 1931, genero));
        PeliculaDAO.create(new Pelicula("Gone with the Wind", "Victor Fleming", 1939, genero));
        PeliculaDAO.create(new Pelicula("Citizen Kane", "Orson Welles", 1941, genero));
        PeliculaDAO.create(new Pelicula("Casablanca", "Michael Curtiz", 1942, genero));
        PeliculaDAO.create(new Pelicula("Bicycle Thieves", "Vittorio De Sica", 1948, genero));
        PeliculaDAO.create(new Pelicula("Sunset Boulevard", "Billy Wilder", 1950, genero));
        PeliculaDAO.create(new Pelicula("Seven Samurai", "Akira Kurosawa", 1954, genero));
        PeliculaDAO.create(new Pelicula("The Searchers", "John Ford", 1956, genero));
        PeliculaDAO.create(new Pelicula("Psycho", "Alfred Hitchcock", 1960, genero));
        PeliculaDAO.create(new Pelicula("Lawrence of Arabia", "David Lean", 1962, genero));
        PeliculaDAO.create(new Pelicula("The Good, the Bad and the Ugly", "Sergio Leone", 1966, genero));
        PeliculaDAO.create(new Pelicula("2001: A Space Odyssey", "Stanley Kubrick", 1968, genero));
        PeliculaDAO.create(new Pelicula("The Godfather", "Francis Ford Coppola", 1972, genero));
        PeliculaDAO.create(new Pelicula("Jaws", "Steven Spielberg", 1975, genero));
        PeliculaDAO.create(new Pelicula("Star Wars", "George Lucas", 1977, genero));
        PeliculaDAO.create(new Pelicula("Apocalypse Now", "Francis Ford Coppola", 1979, genero));
        PeliculaDAO.create(new Pelicula("The Shining", "Stanley Kubrick", 1980, genero));
        PeliculaDAO.create(new Pelicula("Blade Runner", "Ridley Scott", 1982, genero));
        PeliculaDAO.create(new Pelicula("The Terminator", "James Cameron", 1984, genero));
        PeliculaDAO.create(new Pelicula("Back to the Future", "Robert Zemeckis", 1985, genero));
        PeliculaDAO.create(new Pelicula("Platoon", "Oliver Stone", 1986, genero));
        PeliculaDAO.create(new Pelicula("The Princess Bride", "Rob Reiner", 1987, genero));
        PeliculaDAO.create(new Pelicula("Die Hard", "John McTiernan", 1988, genero));
        PeliculaDAO.create(new Pelicula("Cinema Paradiso", "Giuseppe Tornatore", 1988, genero));
        PeliculaDAO.create(new Pelicula("Goodfellas", "Martin Scorsese", 1990, genero));
        PeliculaDAO.create(new Pelicula("The Silence of the Lambs", "Jonathan Demme", 1991, genero));
        PeliculaDAO.create(new Pelicula("Jurassic Park", "Steven Spielberg", 1993, genero));
        PeliculaDAO.create(new Pelicula("Pulp Fiction", "Quentin Tarantino", 1994, genero));
        PeliculaDAO.create(new Pelicula("The Shawshank Redemption", "Frank Darabont", 1994, genero));
        PeliculaDAO.create(new Pelicula("Se7en", "David Fincher", 1995, genero));
        PeliculaDAO.create(new Pelicula("Titanic", "James Cameron", 1997, genero));
        PeliculaDAO.create(new Pelicula("The Matrix", "Wachowskis", 1999, genero));
        PeliculaDAO.create(new Pelicula("Gladiator", "Ridley Scott", 2000, genero));
        PeliculaDAO.create(new Pelicula("The Lord of the Rings: The Fellowship of the Ring", "Peter Jackson", 2001, genero));
        PeliculaDAO.create(new Pelicula("City of God", "Fernando Meirelles", 2002, genero));
        PeliculaDAO.create(new Pelicula("Finding Nemo", "Andrew Stanton", 2003, genero));
        PeliculaDAO.create(new Pelicula("The Dark Knight", "Christopher Nolan", 2008, genero));
        PeliculaDAO.create(new Pelicula("Avatar", "James Cameron", 2009, genero));
        PeliculaDAO.create(new Pelicula("Inception", "Christopher Nolan", 2010, genero));
        PeliculaDAO.create(new Pelicula("The Social Network", "David Fincher", 2010, genero));
    }

    public static String rpad(String texto, int longitud, char relleno) {
        if (texto.length() >= longitud) return texto;
        return texto + String.valueOf(relleno).repeat(longitud - texto.length());
    }

    public static String limitarString(String texto, int limite) {
        if (texto == null) return null;
        return texto.length() <= limite ? texto : texto.substring(0, limite);
    }



}