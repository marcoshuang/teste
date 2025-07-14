package com.kaitocompanny.backend_management.service.impl;

import com.kaitocompanny.backend_management.dto.LugarDTO;
import com.kaitocompanny.backend_management.exception.ResourceNotFoundException;
import com.kaitocompanny.backend_management.dto.CreateLugarDTO;
import com.kaitocompanny.backend_management.dto.CategoriaDTO;
import com.kaitocompanny.backend_management.model.Lugar;
import com.kaitocompanny.backend_management.model.Categoria;
import com.kaitocompanny.backend_management.repository.LugarRepository;
import com.kaitocompanny.backend_management.repository.CategoriaRepository;
import com.kaitocompanny.backend_management.service.LugarService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class LugarServiceImpl implements LugarService {

    private final LugarRepository lugarRepo;
    private final CategoriaRepository categoriaRepo;

    public LugarServiceImpl(LugarRepository lugarRepo,
                            CategoriaRepository categoriaRepo) {
        this.lugarRepo = lugarRepo;
        this.categoriaRepo = categoriaRepo;
    }

    @Override
    public List<LugarDTO> obterTodos(String nome, Long categoriaId) {
        List<Lugar> lista;
        if (nome != null && categoriaId != null) {
            lista = lugarRepo.findByNomeContainingIgnoreCaseAndCategoriaId(nome, categoriaId);
        } else if (nome != null) {
            lista = lugarRepo.findByNomeContainingIgnoreCase(nome);
        } else if (categoriaId != null) {
            lista = lugarRepo.findByCategoriaId(categoriaId);
        } else {
            lista = lugarRepo.findAll();
        }	
        return lista.stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
    }

    @Override
    public LugarDTO obterPorId(Long id) {
    	Lugar lugar = lugarRepo.findById(id)
    		    .orElseThrow(() -> new ResourceNotFoundException("Lugar não encontrado com id " + id));
        return toDTO(lugar);
    }

    @Override
    public LugarDTO criar(CreateLugarDTO dto) {
        Categoria cat = categoriaRepo.findById(dto.getCategoriaId())
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        Lugar lugar = new Lugar();
        lugar.setNome(dto.getNome());
        lugar.setLocalizacao(dto.getLocalizacao());
        lugar.setUrlFoto(dto.getUrlFoto());
        lugar.setAvaliacao(dto.getAvaliacao());
        lugar.setCategoria(cat);
        return toDTO(lugarRepo.save(lugar));
    }

    @Override
    public LugarDTO atualizar(Long id, CreateLugarDTO dto) {
    	Lugar lugar = lugarRepo.findById(id)
    		    .orElseThrow(() -> new ResourceNotFoundException("Não foi possível atualizar. Lugar não encontrado com id " + id));

        Categoria cat = categoriaRepo.findById(dto.getCategoriaId())
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        lugar.setNome(dto.getNome());
        lugar.setLocalizacao(dto.getLocalizacao());
        lugar.setUrlFoto(dto.getUrlFoto());
        lugar.setAvaliacao(dto.getAvaliacao());
        lugar.setCategoria(cat);
        return toDTO(lugarRepo.save(lugar));
    }

    @Override
    public void deletar(Long id) {
    	if (!lugarRepo.existsById(id)) {
    	    throw new ResourceNotFoundException("Não foi possível deletar. Lugar não encontrado com id " + id);
    	}
        lugarRepo.deleteById(id);
    }

    private LugarDTO toDTO(Lugar lugar) {
        Categoria c = lugar.getCategoria();
        CategoriaDTO catDto = new CategoriaDTO(c.getId(), c.getNome(), c.getDescricao());
        LugarDTO dto = new LugarDTO();
        dto.setId(lugar.getId());
        dto.setNome(lugar.getNome());
        dto.setLocalizacao(lugar.getLocalizacao());
        dto.setUrlFoto(lugar.getUrlFoto());
        dto.setAvaliacao(lugar.getAvaliacao());
        dto.setCategoria(catDto);
        return dto;
    }
    
    public byte[] buscarImagem(Long id) {
        Path path = Paths.get("/imagens/" + id + ".jpg");
        try {
			return Files.readAllBytes(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    @Override
    public Page<LugarDTO> obterTodos(String nome, Long categoriaId, Pageable pageable) {
        Page<Lugar> page;
        if (nome != null && categoriaId != null) {
            page = lugarRepo.findByNomeContainingIgnoreCaseAndCategoriaId(nome, categoriaId, pageable);
        } else if (nome != null) {
            page = lugarRepo.findByNomeContainingIgnoreCase(nome, pageable);
        } else if (categoriaId != null) {
            page = lugarRepo.findByCategoriaId(categoriaId, pageable);
        } else {
            page = lugarRepo.findAll(pageable);
        }
        return page.map(this::toDTO);
    }


}
