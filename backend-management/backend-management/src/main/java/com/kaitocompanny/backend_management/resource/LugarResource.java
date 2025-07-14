package com.kaitocompanny.backend_management.resource;

import com.kaitocompanny.backend_management.dto.LugarDTO;
import com.kaitocompanny.backend_management.dto.CreateLugarDTO;
import com.kaitocompanny.backend_management.service.LugarService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
//import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/lugares")
public class LugarResource {

    private final LugarService service;

    public LugarResource(LugarService service) {
        this.service = service;
    }

    /*
    @GetMapping
    public ResponseEntity<List<LugarDTO>> listar(
            @RequestParam(required = false) String nome,
            @RequestParam(name = "categoriaId", required = false) Long categoriaId) {
        return ResponseEntity.ok(service.obterTodos(nome, categoriaId));
    
*/
    @GetMapping("/{id}")
    public ResponseEntity<LugarDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.obterPorId(id));
    }

    @PostMapping
    public void criar(
        @Valid @RequestBody CreateLugarDTO dto,
        HttpServletResponse response
    ) {
        LugarDTO criado = service.criar(dto);
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.setHeader("Location", "/api/lugares/" + criado.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LugarDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody CreateLugarDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}/imagem")
    public void baixarImagem(
        @PathVariable Long id,
        HttpServletResponse response
    ) throws IOException {
        byte[] dados = service.buscarImagem(id);
        response.setContentType("image/jpeg");
        response.setContentLength(dados.length);
        try (ServletOutputStream 	out = response.getOutputStream()) {
            out.write(dados);
            out.flush();
        }
    }
    
    @GetMapping
    public ResponseEntity<Page<LugarDTO>> listar(
            @RequestParam(required = false) String nome,
            @RequestParam(name = "categoriaId", required = false) Long categoriaId,
            Pageable pageable
    ) {
        Page<LugarDTO> page = service.obterTodos(nome, categoriaId, pageable);
        return ResponseEntity.ok(page);
    }


}
