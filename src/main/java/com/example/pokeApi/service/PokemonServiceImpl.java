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
}
