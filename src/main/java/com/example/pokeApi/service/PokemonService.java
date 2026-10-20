package com.example.pokeApi.service;

import java.util.List;

import com.example.pokeApi.persistance.model.Pokemon;

public interface PokemonService {

    public List<Pokemon> getAllPokemons();
    public Pokemon getPokemonById(Long id);
    public Pokemon createPokemon(Pokemon pokemon);
    public Pokemon updatePokemon(Long id, Pokemon pokemon);
    public void deletePokemon(Long id);
}
