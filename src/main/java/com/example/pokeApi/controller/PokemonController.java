package com.example.pokeApi.controller;

import com.example.pokeApi.controller.converter.Converter;
import com.example.pokeApi.controller.dto.PokemonDto;
import com.example.pokeApi.persistance.model.Pokemon;
import com.example.pokeApi.service.PokemonService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
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
@RequestMapping("/api/v2/pokemons")
@CrossOrigin
public class PokemonController {

    private final PokemonService pokemonService;
    private final Converter converter;

    public PokemonController(PokemonService pokemonService, Converter converter) {
        this.pokemonService = pokemonService;
        this.converter = converter;
    }

    @GetMapping
    public ResponseEntity<List<PokemonDto>> getAllPokemons() {
        return ResponseEntity.status(HttpStatus.OK).body(pokemonService.getAllPokemons().stream().map(converter::convertToDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonDto> getPokemonById(@PathVariable Long id) {
        return Optional.ofNullable(pokemonService.getPokemonById(id)).map(converter::convertToDto).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody Pokemon pokemon) {
        Pokemon createdPokemon = pokemonService.createPokemon(pokemon);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdPokemon.getId()).toUri()).body(converter.convertToDto(createdPokemon));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PokemonDto> updatePokemon(@PathVariable Long id, @RequestBody Pokemon pokemon) {
        return ResponseEntity.status(HttpStatus.OK).body(converter.convertToDto(pokemonService.updatePokemon(id, pokemon)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePokemon(@PathVariable Long id) {
        pokemonService.deletePokemon(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
