package com.placeti.avaliacao.controller;

import com.placeti.avaliacao.model.Comercio;
import com.placeti.avaliacao.service.ComercioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/placeti/comercios")
public class ComercioController {
    @Autowired
    private ComercioService comercioService;

    @GetMapping
    public List<Comercio> listarTodos() {
        return comercioService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Comercio> buscarPorId(@PathVariable Long id) {
        return comercioService.buscarPorId(id);
    }

    @PostMapping
    public Comercio salvar(@RequestBody Comercio comercio) {
        return comercioService.salvar(comercio);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        comercioService.deletar(id);
    }
}
