package com.kaitocompanny.backend_management.service.impl;

import com.kaitocompanny.backend_management.dto.CategoriaDTO;
import com.kaitocompanny.backend_management.dto.CreateCategoriaDTO;
import com.kaitocompanny.backend_management.exception.ResourceNotFoundException;
import com.kaitocompanny.backend_management.model.Categoria;
import com.kaitocompanny.backend_management.repository.CategoriaRepository;
import com.kaitocompanny.backend_management.service.CategoriaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepo;

    public CategoriaServiceImpl(CategoriaRepository repository) {
        this.categoriaRepo = repository;
    }

    @Override
    public List<CategoriaDTO> obterTodas() {
        return categoriaRepo.findAll()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO obterPorId(Long id) {
        Categoria categoria = categoriaRepo.findById(id)
            .orElseThrow(() -> 
                new ResourceNotFoundException("Categoria não encontrada com id " + id)
            );
        return toDTO(categoria);
    }

    @Override
    public CategoriaDTO criar(CreateCategoriaDTO dto) {
        Categoria categoria = new Categoria(dto.getNome(), dto.getDescricao());
        return toDTO(categoriaRepo.save(categoria));
    }

    @Override
    public CategoriaDTO atualizar(Long id, CreateCategoriaDTO dto) {
        // verificar existência
        Categoria existente = categoriaRepo.findById(id)
            .orElseThrow(() -> 
                new ResourceNotFoundException("Não foi possível atualizar. Categoria não encontrada com id " + id)
            );
        existente.setNome(dto.getNome());
        existente.setDescricao(dto.getDescricao());
        return toDTO(categoriaRepo.save(existente));
    }
    @Override
    public void deletar(Long id) {
        if (!categoriaRepo.existsById(id)) {
            throw new ResourceNotFoundException("Não foi possível deletar. Categoria não encontrada com id " + id);
        }
        categoriaRepo.deleteById(id);
    }
    
    private CategoriaDTO toDTO(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
        dto.setNome(categoria.getNome());
        dto.setDescricao(categoria.getDescricao());
        return dto;
    }
}
