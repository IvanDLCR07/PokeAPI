package com.example.pokeApi.persistance.repository;

import com.example.pokeApi.persistance.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
