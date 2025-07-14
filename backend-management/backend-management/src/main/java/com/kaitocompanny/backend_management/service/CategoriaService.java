package com.kaitocompanny.backend_management.service;

import com.kaitocompanny.backend_management.dto.CategoriaDTO;
import com.kaitocompanny.backend_management.dto.CreateCategoriaDTO;
import java.util.List;

public interface CategoriaService {
    List<CategoriaDTO> obterTodas();
    CategoriaDTO obterPorId(Long id);
    CategoriaDTO criar(CreateCategoriaDTO dto);
    CategoriaDTO atualizar(Long id, CreateCategoriaDTO dto);
    void deletar(Long id);
}
