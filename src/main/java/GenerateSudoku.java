import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GenerateSudoku {

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    public Board getSudoku(String diffictulity) throws Exception {

        String Url ="https://sugoku.herokuapp.com/board?difficulty=" + diffictulity;
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(Url))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if( response.statusCode() == 200 ) {
            ObjectMapper mapper = new ObjectMapper(); // JSON to Board.java
            String jsonString = response.body();
            Board board = mapper.readValue( jsonString, Board.class);
            return board;
        }
        return null;


    }

}
