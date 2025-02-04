package com.placeti.avaliacao.service;

import com.placeti.avaliacao.dto.CidadeDTO;
import com.placeti.avaliacao.model.Cidade;
import com.placeti.avaliacao.repository.CidadeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//------------------------------------------------------------------
// Service usado para acessar os repositórios da aplicação
//------------------------------------------------------------------
@Service
public class ProjetoService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CidadeRepository cidadeRepository;

    //---------------------------------------------------------
    /** Busca uma cidade pelo seu ID */
    //---------------------------------------------------------
    public CidadeDTO pesquisarCidade(Long id) {
        Optional<Cidade> cidade = cidadeRepository.findById(id);
        return cidade.map(CidadeDTO::toDTO).orElse(null);
    }

    //---------------------------------------------------------
    /** Retorna todas as cidades cadastradas */
    //---------------------------------------------------------
    public List<CidadeDTO> pesquisarCidades() {
        List<Cidade> cidades = cidadeRepository.findAll();
        return cidades.stream().map(CidadeDTO::toDTO).collect(Collectors.toList());
    }

    //----------------------------------------------------------
    /** Inclui uma nova cidade */
    //----------------------------------------------------------
    public void incluirCidade(CidadeDTO dto) {
        Cidade cidade = new Cidade();
        cidade.setNome(dto.getNome());
        cidade.setUf(dto.getUf());
        cidade.setCapital(dto.getCapital());

        cidadeRepository.save(cidade);
        logger.info("Cidade incluída com sucesso: {}", dto.getNome());
    }

    //----------------------------------------------------------
    /** Altera uma cidade */
    //----------------------------------------------------------
    public void alterarCidade(CidadeDTO dto) {
        if (cidadeRepository.existsById(dto.getId())) {
            Cidade cidade = new Cidade();
            cidade.setId(dto.getId());
            cidade.setNome(dto.getNome());
            cidade.setUf(dto.getUf());
            cidade.setCapital(dto.getCapital());

            cidadeRepository.save(cidade);
            logger.info("Cidade alterada com sucesso: {}", dto.getNome());
        } else {
            logger.warn("Tentativa de alterar uma cidade inexistente. ID: {}", dto.getId());
        }
    }

    //----------------------------------------------------------
    /** Exclui uma cidade */
    //----------------------------------------------------------
    public void excluirCidade(Long idCidade) {
        if (cidadeRepository.existsById(idCidade)) {
            cidadeRepository.deleteById(idCidade);
            logger.info("Cidade excluída com sucesso. ID: {}", idCidade);
        } else {
            logger.warn("Tentativa de excluir uma cidade inexistente. ID: {}", idCidade);
        }
    }
}
