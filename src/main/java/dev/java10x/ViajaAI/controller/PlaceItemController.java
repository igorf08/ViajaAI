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
        PlaceItem salvo = service.criar(placeItem);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PlaceItem>> listarLocais() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarLocal(@PathVariable Long id) {
        PlaceItem placeItem = service.listarPorId(id);

        if (placeItem != null) {
            return ResponseEntity.ok(placeItem);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum local com ID " + id);
        }

    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarLocal(@PathVariable Long id, @RequestBody PlaceItem placeItem) {
        PlaceItem place = service.listarPorId(id);

        if (place != null) {
            service.editar(id, placeItem);
            return ResponseEntity.ok(placeItem);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum local com ID " + id);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarLocal(@PathVariable Long id) {
        PlaceItem place = service.listarPorId(id);

        if (place != null) {
            service.deletar(id);
            return ResponseEntity.ok("Seu local foi deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum local com ID " + id);
        }
    }

}
