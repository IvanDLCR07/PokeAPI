package com.example.pokeApi.service;

import java.util.List;

import com.example.pokeApi.persistance.model.Pokemon;

public interface PokemonService {

    List<Pokemon> getAllPokemons();
}
