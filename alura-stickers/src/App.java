import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
   
    public static void main(String[] args) throws Exception {
    
        // fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://gist.githubusercontent.com/lucasfugisawa/cbb0d10ee3901bd0541468e431c629b3/raw/1fe1f3340dfe5b5876a209c0e8226d090f6aef10/Top250Movies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        System.out.println();
        System.out.println(listaDeFilmes.size());
        System.out.println();
        System.out.println(listaDeFilmes.get(0));
        System.out.println();
        
        // exibir e manipular os dados 
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\uD83D\uDC99 💙️ Título: " + filme.get("title"));
            System.out.println("Poster: " + filme.get("image"));
            System.out.println("Classificação: " + filme.get("imDbRating"));
            System.out.println();
        }
    }
}
