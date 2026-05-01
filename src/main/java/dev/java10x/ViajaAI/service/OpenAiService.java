package dev.java10x.ViajaAI.service;

import dev.java10x.ViajaAI.model.PlaceItem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OpenAiService {

    private final WebClient webClient;
    private String apiKey = System.getenv("API_KEY");

    public OpenAiService(WebClient webClient) {
        this.webClient = webClient;
    }



    public Mono<String> generateGuide(List<PlaceItem> placeitems){

        String pontosDeInteresse = placeitems.stream()
                .map(item -> String.format("%s - (%s)", item.getNome(), item.getCategoria()))
                .collect(Collectors.joining("\n"));


        String prompt = "Agora você é um guia turístico e vai gerar um guia de viagem com base meu banco de dados que pode conter locais, gostos, interesses, ok?" + pontosDeInteresse;

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-4o-mini",
                "messages", List.of(
                        Map.of("role", "system", "content", "Você é um guia de turismo."),
                        Map.of("role", "user", "content", prompt)
                )
        );
        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map( response -> {
                    var choices = (List<Map<String, Object>>) response.get("choices");
                    if (choices != null && !choices.isEmpty()) {
                        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                        return message.get("content").toString();
                    }
                    return "Nenhum guia foi gerado";
                });
    }

}
