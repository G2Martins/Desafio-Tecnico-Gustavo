package com.placeti.avaliacao.controller;

import com.placeti.avaliacao.dto.CidadeDTO;
import com.placeti.avaliacao.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//--------------------------------------------------
// Controller responsável por operações com cidades
//--------------------------------------------------
@RestController
@RequestMapping("/placeti/cidades")  
public class CidadeController {

    @Autowired
    private ProjetoService projetoService;

    //----------------------------------------------------------
    /** Retorna uma cidade pelo ID */
    //----------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<CidadeDTO> buscarPeloId(@PathVariable Long id) {
        CidadeDTO cidade = projetoService.pesquisarCidade(id);
        return cidade != null ? ResponseEntity.ok(cidade) : ResponseEntity.notFound().build();
    }

    //----------------------------------------------------------
    /** Retorna todas as cidades cadastradas */
    //----------------------------------------------------------
    @GetMapping
    public ResponseEntity<List<CidadeDTO>> pesquisarCidades() {
        List<CidadeDTO> cidades = projetoService.pesquisarCidades();
        return ResponseEntity.ok(cidades);
    }

    //----------------------------------------------------------
    /** Inclui uma nova cidade */
    //----------------------------------------------------------
    @PostMapping
    public ResponseEntity<Void> incluirCidade(@RequestBody CidadeDTO cidadeDto) {
        projetoService.incluirCidade(cidadeDto);
        return ResponseEntity.ok().build();
    }

    //----------------------------------------------------------
    /** Altera uma cidade */
    //----------------------------------------------------------
    @PutMapping
    public ResponseEntity<Void> alterarCidade(@RequestBody CidadeDTO cidadeDto) {
        projetoService.alterarCidade(cidadeDto);
        return ResponseEntity.ok().build();
    }

    //----------------------------------------------------------
    /** Exclui uma cidade */
    //----------------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCidade(@PathVariable Long id) {
        projetoService.excluirCidade(id);
        return ResponseEntity.ok().build();
    }
}
