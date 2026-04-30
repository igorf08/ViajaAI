package dev.java10x.ViajaAI.controller;

import dev.java10x.ViajaAI.model.PlaceItem;
import dev.java10x.ViajaAI.service.PlaceItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/place")
public class PlaceItemController {

    private PlaceItemService service;

    public PlaceItemController(PlaceItemService service) {
        this.service = service;
    }

    @PostMapping("/criar")
    public ResponseEntity<PlaceItem> CriarLocal(@RequestBody PlaceItem placeItem) {
        return ResponseEntity.ok(service.criar(placeItem));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PlaceItem>> listarLocais() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarLocal(@PathVariable Long id) {
        return service.listarPorId(id)
            .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarLocal(@PathVariable Long id, @RequestBody PlaceItem placeItem) {
        return service.listarPorId(id)
                .map(itemExistente -> {
                    placeItem.setId(id);
                    service.editar(id, placeItem);
                    return ResponseEntity.ok(placeItem);
                }).orElse(ResponseEntity.notFound().build());

        }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarLocal(@PathVariable Long id) {
        return service.listarPorId(id)
                .map(placeItem -> {
                    service.deletar(id);
                    return ResponseEntity.ok("Seu item foi excluído com sucesso.");
                }).orElse(ResponseEntity.notFound().build());
    }

}




