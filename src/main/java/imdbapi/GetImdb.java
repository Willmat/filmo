package imdbapi;

import com.google.gson.Gson;
import ihm.MainView;
import modele.Film;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class GetImdb {

    //http://www.omdbapi.com/?i=tt3896198&apikey=9a929d0d
    private static final String OMDBAPI = "http://www.omdbapi.com/?";
    private static final String APIKEY = "&apikey=9a929d0d";
    private static Gson gson = new Gson();

    public GetImdb() {
    }

    public static List<Film> getListFilmFromString(String titre) {
        StringBuilder url = new StringBuilder();
        url.append(OMDBAPI);
        url.append("s=").append(titre);
        url.append(APIKEY);
        RestTemplate restTemplate = new RestTemplate();
        MainView.LOGGER.info(url.toString());
        String json = restTemplate.getForObject(url.toString(), String.class);
        List<Film> listeFilms = new ArrayList<>();

        JsonToJava search = gson.fromJson(json, JsonToJava.class);
        MainView.LOGGER.info(search);
        for (Search filmjson : search.getSearches()) {
            Film film = new Film();
            film.setTitre(filmjson.getTitle());
            film.setAnnee(filmjson.getYear());
            listeFilms.add(film);
        }
        return listeFilms;
    }


}
