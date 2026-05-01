package dev.java10x.ViajaAI.controller;
import dev.java10x.ViajaAI.model.PlaceItem;
import dev.java10x.ViajaAI.service.OpenAiService;
import dev.java10x.ViajaAI.service.PlaceItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class GuideController {
    private OpenAiService service;
    private PlaceItemService placeService;

    public GuideController(OpenAiService service, PlaceItemService placeService) {
        this.service = service;
        this.placeService = placeService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateGuide () {
        List<PlaceItem> placeitem = placeService.listar();
        return service.generateGuide(placeitem)
                .map(recipe -> ResponseEntity.ok(recipe))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}
