package dev.java10x.ViajaAI.service;

import dev.java10x.ViajaAI.model.PlaceItem;
import dev.java10x.ViajaAI.repository.PlaceItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceItemService {

    private PlaceItemRepository repository;

    public PlaceItemService(PlaceItemRepository repository) {
        this.repository = repository;
    }

    public PlaceItem criar(PlaceItem placeItem) {
        return repository.save(placeItem);
    }

    public List<PlaceItem> listar() {
        return repository.findAll();
    }

    public PlaceItem listarPorId(Long id) {
        Optional<PlaceItem> place = repository.findById(id);

        return place.orElse(null);
    }

    public PlaceItem editar(Long id, PlaceItem placeItem) {
        Optional<PlaceItem> place = repository.findById(id);

        if (place.isPresent()) {
            PlaceItem placeAtualizado = placeItem;
            placeAtualizado.setId(id);
            return repository.save(placeAtualizado);
        }

        return null;
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
