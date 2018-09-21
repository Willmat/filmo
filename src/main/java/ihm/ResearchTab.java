package ihm;

import javafx.collections.FXCollections;
import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import modele.Film;

import java.util.ArrayList;
import java.util.Calendar;

public class ResearchTab extends TabImpl {

    private final PseudoClass errorClass = PseudoClass.getPseudoClass("error");

    private TextField annee;


    ResearchTab() {
        ArrayList<Film> listeTitre = new ArrayList<>();
        for (Film film : FilmsTab.data
                ) {
            listeTitre.add(film);
        }
        ComboBox<Film> filmComboBox = new ComboBox<>(FXCollections.observableArrayList(listeTitre));
        filmComboBox.setEditable(true);
        ComboBoxAutoComplete.autoCompleteComboBoxPlus(filmComboBox, (typedText, itemToCompare) -> itemToCompare.getTitre().toLowerCase().contains(typedText.toLowerCase()) || itemToCompare.getTitre().equals(typedText));
        newSection("filmName", "Nom du film:", filmComboBox);

        // Init du champ année
        annee = new TextField();
        annee.textProperty().addListener((observable, oldValue, newValue) -> validateAnnee(annee));
        newSection("year", "Année:", annee);

        // Init du bouton de recherche
        Button research = new Button("Rechercher");
        newSection("btnResearch", "", research);
        // Init du cercle de progression
        ProgressIndicator saved = new ProgressIndicator();
        saved.setVisible(false);
        newSection("saved", "", saved);
        getSection("saved").getBox().setAlignment(Pos.CENTER);
        // Avant la recherche check si les champs ne sont pas vides
        research.setOnAction(e -> {
            if (checkBeforeResearch()) {
                saved.setVisible(true);
            } else {
                saved.setVisible(false);
            }
        });


        TableView<Film> resultat = new TableView<>();

        TableColumn<Film, String> nameCol;

        // Colonne TITRE
        nameCol = new TableColumn<>();
        nameCol.setText("Titre");
        nameCol.setCellValueFactory(p -> p.getValue().getTitreTab());
        resultat.getColumns().add(nameCol);
        filmComboBox.itemsProperty().addListener((observable, oldValue, newValue) -> resultat.setItems(filmComboBox.getItems()));
        newSection("resultat", "", resultat);


    }

    private boolean checkBeforeResearch() {
        boolean result = true;
        for (PseudoClass error : annee.getPseudoClassStates()) {
            if (errorClass.equals(error)) {
                result = false;
                break;
            }
        }
        return result;
    }


    private void validate(TextField tf) {
        if (tf.getText().trim().length() == 0) {
            tf.pseudoClassStateChanged(errorClass, true);
        } else {
            tf.pseudoClassStateChanged(errorClass, false);
        }

    }

    private void validateAnnee(TextField tf) {
        Integer annneeValid = null;
        try {
            annneeValid = Integer.parseInt(tf.getText());
        } catch (NumberFormatException ignored) {

        }
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        if (annneeValid != null && annneeValid > 1900 && annneeValid < year + 2) {
            tf.pseudoClassStateChanged(errorClass, false);
        } else {
            tf.pseudoClassStateChanged(errorClass, true);
        }

    }

}
