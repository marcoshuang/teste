package com.kaitocompanny.backend_management.repository;

import com.kaitocompanny.backend_management.model.Lugar;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LugarRepository extends JpaRepository<Lugar, Long> {
 
	List<Lugar> findByCategoriaId(Long categoriaId);
    List<Lugar> findByNomeContainingIgnoreCase(String nome);
    List<Lugar> findByNomeContainingIgnoreCaseAndCategoriaId(String nome, Long categoriaId);
    
    Page<Lugar> findByCategoriaId(Long categoriaId, Pageable pageable);
    Page<Lugar> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    Page<Lugar> findByNomeContainingIgnoreCaseAndCategoriaId(
        String nome, Long categoriaId, Pageable pageable
    );
    
    @Query("SELECT l FROM Lugar l " +
    	       "WHERE (:nome IS NULL OR LOWER(l.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) " +
    	       "AND (:categoriaId IS NULL OR l.categoria.id = :categoriaId)")
    	List<Lugar> filtrar(@Param("nome") String nome, @Param("categoriaId") Long categoriaId);
}