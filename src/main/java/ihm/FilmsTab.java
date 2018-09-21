package ihm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import modele.Film;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class FilmsTab extends VBox {

    static ObservableList<Film> data = FXCollections.observableArrayList();


    FilmsTab() {
        try {
            List<File> filesInFolder = Files.walk(Paths.get("H:\\Films"))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
            List<Film> liste = new ArrayList<>();
            for (File file : filesInFolder
                    ) {
                liste.add(new Film(file.getName(), 2010));
            }


            data.addAll(liste);
        } catch (IOException e) {
            MainView.LOGGER.error(e);
        }


        TableColumn<Film, String> nameCol;
        TableColumn<Film, Number> anneeCol;

        // Colonne TITRE
        nameCol = new TableColumn<>();
        nameCol.setText("Titre");
        nameCol.setCellValueFactory(p -> p.getValue().getTitreTab());

        // Colonne ANNEE
        anneeCol = new TableColumn<>();
        anneeCol.setText("Année de sortie");
        anneeCol.setCellValueFactory(p -> p.getValue().getAnneeTab());


        // Le tableau
        TableView<Film> table = new TableView<>();
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table.getSelectionModel().setCellSelectionEnabled(false);
        // Ajout des colonnes
        table.getColumns().addAll(nameCol, anneeCol);

        table.setItems(data);
        setVgrow(table, Priority.ALWAYS);
        getChildren().add(table);

    }


}
