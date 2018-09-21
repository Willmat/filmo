package imdbapi;

import modele.TypeEnum;

public class Search {

    private String Title;
    private Integer Year;
    private String imdbID;
    private TypeEnum Type;
    private String Poster;

    Search() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Integer getYear() {
        return Year;
    }

    public void setYear(Integer year) {
        Year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public TypeEnum getType() {
        return Type;
    }

    public void setType(TypeEnum type) {
        Type = type;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }
}
