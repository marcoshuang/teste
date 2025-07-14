package com.kaitocompanny.backend_management.service;

import com.kaitocompanny.backend_management.dto.LugarDTO;
import com.kaitocompanny.backend_management.dto.CreateLugarDTO;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LugarService {
    List<LugarDTO> obterTodos(String nome, Long categoriaId);
    
    Page<LugarDTO> obterTodos(String nome, Long categoriaId, Pageable pageable);
    LugarDTO obterPorId(Long id);
    LugarDTO criar(CreateLugarDTO dto);
    LugarDTO atualizar(Long id, CreateLugarDTO dto);
    void deletar(Long id);

	byte[] buscarImagem(Long id);
}
