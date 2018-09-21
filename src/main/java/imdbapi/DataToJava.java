package imdbapi;

import modele.Film;

import java.util.List;

public class DataToJava {


    private List<Film> films;

    DataToJava() {

    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}
