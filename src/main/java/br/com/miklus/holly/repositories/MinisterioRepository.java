package br.com.miklus.holly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.miklus.holly.domain.Ministerio;

@Repository
public interface MinisterioRepository extends JpaRepository<Ministerio, Integer>{

}
