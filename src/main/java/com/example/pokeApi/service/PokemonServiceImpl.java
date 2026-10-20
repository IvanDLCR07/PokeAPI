package com.example.pokeApi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pokeApi.persistance.model.Pokemon;
import com.example.pokeApi.persistance.repository.PokemonRepository;

@Service
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRepository pokemonRepository;

    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public List<Pokemon> getAllPokemons() {
        return pokemonRepository.findAll();
    }

    @Override
    public Pokemon getPokemonById(Long id) {
        return pokemonRepository.findById(id).orElse(null);
    }

    @Override
    public Pokemon createPokemon(Pokemon pokemon) {
        pokemon.setId(null);
        return pokemonRepository.save(pokemon);
    }

    @Override
    public Pokemon updatePokemon(Long id, Pokemon pokemon) {
        Pokemon existingPokemon = pokemonRepository.findById(id).orElse(null);
        if (existingPokemon != null) {
            existingPokemon.setName(pokemon.getName());
            existingPokemon.setUrl(pokemon.getUrl());
            existingPokemon.setType(pokemon.getType());
            return pokemonRepository.save(existingPokemon);
        }
        return null;
    }

    @Override
    public void deletePokemon(Long id) {
        pokemonRepository.deleteById(id);
    }

}
