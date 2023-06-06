package org.prog.lattes.service;

import java.util.List;
import java.util.stream.Collectors;
import org.prog.lattes.model.Producao;
import org.prog.lattes.model.Tipo;
import org.prog.lattes.model.TotalProducoesAno;
import org.prog.lattes.model.TotalProducoesTipo;
import org.prog.lattes.repository.ProducaoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ProducaoService {
    
    public ProducaoRepository producaoRepository;
    
    public ProducaoService(ProducaoRepository producaoRepository){
        this.producaoRepository = producaoRepository;
    }

    public List<Producao> listProducao(){
        return producaoRepository.findAll();
    }

    public Page<Producao> pageProducao(Pageable pageable){
        return this.producaoRepository.findAll(pageable);
    }

    public List<Producao> listProducaoPeloAno(Integer ano) {
        return producaoRepository.findByAno(ano);
    }

    public Page<Producao> buscarComFiltroDinamico(Integer dataInicio, Integer dataFim, String instituto, String pesquisador, String tipoProducao, Pageable pageable) {
        
        Specification<Producao> spec = Specification.where(null);

        if (dataInicio != null) {
            spec = spec.and(ProducaoRepository.filtrarPorDataInicio(dataInicio));
        }
        
        if (dataFim != null) {
            spec = spec.and(ProducaoRepository.filtrarPorDataFim(dataFim));
        }

        if (instituto != null) {
            spec = spec.and(ProducaoRepository.filtrarPorInstituto(instituto));
        }

        if (pesquisador != null) {
            spec = spec.and(ProducaoRepository.filtrarPorPesquisador(pesquisador));
        }

        if (tipoProducao != null) {
            spec = spec.and(ProducaoRepository.filtrarPorTipoProducao(tipoProducao));
        }

        return producaoRepository.findAll(spec, pageable);
    }

    public long countProducao() {
        return producaoRepository.count();
    }

    public long countProducaoPorAno(int ano) {
        return producaoRepository.countProducaoPorAno(ano);
    }

    public List<TotalProducoesAno> countTotalProducoesPorAno(Integer dataInicio, Integer dataFim, String instituto, String pesquisador, String tipoProducao, Pageable pageable) {
        
        Page<Producao> page = buscarComFiltroDinamico(dataInicio, dataFim, instituto, pesquisador, tipoProducao, pageable);
        List<Producao> producoesFiltradas = page.getContent();
        
        List<TotalProducoesAno> totalPorAno = producoesFiltradas.stream()
        .collect(Collectors.groupingBy(
            Producao::getAno,
            Collectors.groupingBy(
                Producao::getTipoProducao,
                Collectors.counting()
            )
        ))
        .entrySet()
        .stream()
        .map(entry -> {
            TotalProducoesAno totalProducoesAno = new TotalProducoesAno();
            totalProducoesAno.setAnoProducao(entry.getKey());
            
            Long totalArtigos = entry.getValue().getOrDefault(Tipo.ARTIGO, 0L);
            Long totalLivros = entry.getValue().getOrDefault(Tipo.LIVRO, 0L);
            
            totalProducoesAno.setArtigo(totalArtigos.intValue());
            totalProducoesAno.setLivro(totalLivros.intValue());
            totalProducoesAno.setTotalProducao(totalArtigos + totalLivros);
            
            return totalProducoesAno;
        })
        .collect(Collectors.toList());

        return totalPorAno;
    }

    public List<TotalProducoesTipo> countTotalProducoesPorTipo() {
        return producaoRepository.countTotalProducoesPorTipo();
    }

    public long countArtigo() {
        return producaoRepository.countArtigo();
    }

    public long countLivro() {
        return producaoRepository.countLivro();
    }
    
    public void saveAll(List<Producao> producaoList){
        producaoRepository.saveAll(producaoList);
    }
}
