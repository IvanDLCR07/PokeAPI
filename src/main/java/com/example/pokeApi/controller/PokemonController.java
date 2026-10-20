package com.example.pokeApi.controller;

import com.example.pokeApi.persistance.model.Pokemon;
import com.example.pokeApi.service.PokemonService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pokemons")
@CrossOrigin
@AllArgsConstructor 
public class PokemonController {

    PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<List<Pokemon>> getAllPokemons() {
        return ResponseEntity.status(HttpStatus.OK).body(pokemonService.getAllPokemons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(pokemonService.getPokemonById(id));
        }

    @PostMapping
    public ResponseEntity<Pokemon> createPokemon(@RequestBody Pokemon pokemon) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemonService.createPokemon(pokemon));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> updatePokemon(@PathVariable Long id, @RequestBody Pokemon pokemon) {
        return ResponseEntity.status(HttpStatus.OK).body(pokemonService.updatePokemon(id, pokemon));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePokemon(@PathVariable Long id) {
        pokemonService.deletePokemon(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
