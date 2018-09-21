package imdbapi;

import java.util.List;

public class JsonToJava {

    private List<Search> Search;
    private Integer totalResults;
    private String Response;

    JsonToJava() {
    }

    public List<Search> getSearches() {
        return Search;
    }

    public void setSearches(List<Search> searches) {
        this.Search = searches;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        this.Response = response;
    }
}
