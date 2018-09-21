package ihm;

import imdbapi.GetImdb;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.Film;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thread.MainThread;
import thread.ThreadEnum;

import java.util.List;

public class MainView extends Application {

    public static final Logger LOGGER = LogManager.getLogger(MainView.class);
    private final VBox filmsTab = new FilmsTab();
    private final ScrollPane researchTab = new ScrollPane(new ResearchTab());

    @Override
    public void start(Stage primaryStage) {
        LOGGER.info("Lancement de l'application");
        primaryStage.setTitle("Filmographie");
        TabPane contentTabs = new TabPane();
        Tab tab1 = new Tab("Films");
        tab1.setContent(filmsTab);
        Tab tab2 = new Tab("Recherche");
        tab2.setContent(researchTab);
        contentTabs.getTabs().addAll(tab1, tab2);
        Scene scene = new Scene(contentTabs, 1024, 854);
        scene.setRoot(contentTabs);
        try {
            scene.getStylesheets().add(getClass().getClassLoader().getResource("text-field-red-border.css").toExternalForm());
        } catch (NullPointerException e) {
            LOGGER.error("Le fichier text-field-red-border.css n'a pas été trouvé ", e);
        }
        primaryStage.setScene(scene);
        primaryStage.show();
        MainThread mainThread = new MainThread();
        mainThread.start(ThreadEnum.UPDATE);
        LOGGER.info("L'application est en route");

        List<Film> list = GetImdb.getListFilmFromString("Narnia");
        LOGGER.info(list.size());

    }

    public static void main(String[] args) {
        launch(args);
    }
}
