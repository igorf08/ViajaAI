package dev.java10x.ViajaAI.controller;
import dev.java10x.ViajaAI.service.OpenAiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class GuideController {
    private OpenAiService service;

    public GuideController(OpenAiService service) {
        this.service = service;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateGuide () {
        return service.generateGuide()
                .map(recipe -> ResponseEntity.ok(recipe))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}
