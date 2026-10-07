package com.example.pokeApi.controller;

import com.example.pokeApi.persistance.model.Pokemon;
import com.example.pokeApi.persistance.repository.PokemonRepository;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {

    private final PokemonRepository pokemonRepository;

    public PokemonController(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @GetMapping
    public List<Pokemon> getAllPokemons() {
        return pokemonRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable Long id) {
        return pokemonRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pokemon> createPokemon(@RequestBody Pokemon pokemon) {
        pokemon.setId(null);
        Pokemon savedPokemon = pokemonRepository.save(pokemon);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPokemon.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedPokemon);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> updatePokemon(
            @PathVariable Long id,
            @RequestBody Pokemon pokemon) {
        return pokemonRepository.findById(id)
                .map(existingPokemon -> {
                    existingPokemon.setName(pokemon.getName());
                    existingPokemon.setUrl(pokemon.getUrl());
                    existingPokemon.setType(pokemon.getType());
                    return ResponseEntity.ok(pokemonRepository.save(existingPokemon));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePokemon(@PathVariable Long id) {
        if (!pokemonRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pokemonRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

