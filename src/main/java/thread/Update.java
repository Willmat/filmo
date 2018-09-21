package thread;

import com.abercap.mediainfo.api.MediaInfo;
import com.google.gson.Gson;
import ihm.MainView;
import modele.Film;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Update extends Thread {

    private static Gson gson = new Gson();

    Update() {
        start();
    }

    public void run() {
        try {
            List<File> filesInFolder = Files.walk(Paths.get("H:\\Films"))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
            List<Film> liste = new ArrayList<>();
            MediaInfo mediaInfo = new MediaInfo();
            int compteur = 0;
            int nbFiles = filesInFolder.size();
            for (File file : filesInFolder) {
                liste.add(new Film(file.getName(), 2010));
                //mediaInfo.open(file);
                //mediaInfo.inform();
                //mediaInfo.close();
                if (compteur++ % 10 == 0) {
                    MainView.LOGGER.info(compteur + "/" + nbFiles);
                }
            }
            String json = gson.toJson(liste);
            //Ecrire dans data
            List<String> lines = Arrays.asList(json);
            Path file = Paths.get("H:\\data");
            Files.write(file, lines, Charset.forName("UTF-8"));


            Film[] listefromjson = gson.fromJson(json, Film[].class);
            liste = Arrays.asList(listefromjson);
            MainView.LOGGER.info(liste.size());
        } catch (IOException e) {
            MainView.LOGGER.error(e);
        }
    }


}
