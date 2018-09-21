package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Film {

    private String titre;
    private Integer annee;

    public Film() {
    }

    public Film(String titre, Integer annee) {
        this.titre = titre;
        this.annee = annee;
    }

    public String getTitre() {
        return titre;
    }

    public StringProperty getTitreTab() {
        return new SimpleStringProperty(titre);
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Integer getAnnee() {
        return annee;
    }

    public IntegerProperty getAnneeTab() {
        return new SimpleIntegerProperty(annee);
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    @Override
    public String toString() {
        return titre;
    }
}
