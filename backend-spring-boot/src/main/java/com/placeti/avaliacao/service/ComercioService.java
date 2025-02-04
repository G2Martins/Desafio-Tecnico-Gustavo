package com.placeti.avaliacao.service;

import com.placeti.avaliacao.model.Comercio;
import com.placeti.avaliacao.repository.ComercioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class ComercioService {
     @Autowired
    private ComercioRepository comercioRepository;

    public List<Comercio> listarTodos() {
        return comercioRepository.findAll();
    }

    public Optional<Comercio> buscarPorId(Long id) {
        return comercioRepository.findById(id);
    }

    public Comercio salvar(Comercio comercio) {
        return comercioRepository.save(comercio);
    }

    public void deletar(Long id) {
        comercioRepository.deleteById(id);
    }
}
