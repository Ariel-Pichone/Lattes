package org.prog.lattes.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.prog.lattes.model.GrafoPesquisador;
import org.prog.lattes.model.Producao;
import org.prog.lattes.model.TipoProducao;
import org.prog.lattes.model.TotalProducoesAno;
import org.prog.lattes.model.TotalProducoesTipo;
import org.prog.lattes.repository.ProducaoRepository;
import org.prog.lattes.view.ProducaoView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
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

    // public Page<ProducaoView> pageProducaoPeloAno(Integer ano, Pageable pageable) {
    //     //Usado para ordenar a pagina pelo nome da produção de forma crescente
    //     PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("nome"));
        
    //     return producaoRepository.findByAno(ano, pageRequest);
    // }

    public Specification<Producao> querySpecification(Integer anoInicio, Integer anoFim, String instituto, String pesquisador, String tipoProducao, Integer ano){
        Specification<Producao> spec = Specification.where(null);
        if (anoInicio != null) {
            spec = spec.and(ProducaoRepository.filtrarPorAnoInicio(anoInicio));
        }
        if (anoFim != null) {
            spec = spec.and(ProducaoRepository.filtrarPorAnoFim(anoFim));
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
        if (ano != null) {
            spec = spec.and(ProducaoRepository.filtrarPorAno(ano));
        }
        return spec;
    }

    public Specification<Producao> querySpecification(Integer anoInicio, Integer anoFim, String instituto, String pesquisador, String tipoProducao){
        Specification<Producao> spec = Specification.where(null);
        if (anoInicio != null) {
            spec = spec.and(ProducaoRepository.filtrarPorAnoInicio(anoInicio));
        }
        if (anoFim != null) {
            spec = spec.and(ProducaoRepository.filtrarPorAnoFim(anoFim));
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
        return spec;
    }

    public Page<ProducaoView> buscarComFiltroDinamico(Integer anoInicio, Integer anoFim, String instituto, String pesquisador, String tipoProducao, Integer ano, Pageable pageable) {
        //Usado para ordenar a pagina pelo nome da produção de forma crescente
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("nome"));

        Specification<Producao> spec = querySpecification(anoInicio, anoFim, instituto, pesquisador, tipoProducao, ano);

        Page<Producao> producoesPage = producaoRepository.findAll(spec, pageRequest);

        List<ProducaoView> producoesView = producoesPage
                .stream()
                .map(this::convertToProducaoView)
                .collect(Collectors.toList());

        return new PageImpl<>(producoesView, pageRequest, producoesPage.getTotalElements());
    }

    private ProducaoView convertToProducaoView(Producao producao) {
        ProducaoView producaoView = new ProducaoView();
        producaoView.setId(producao.getId());
        producaoView.setNome(producao.getNome());
        producaoView.setAno(producao.getAno());
        producaoView.setTipoProducao(producao.getTipoProducao());
        producaoView.setPesquisador(producao.getPesquisador().getNome());
        return producaoView;
    }

    public List<TotalProducoesAno> countTotalProducoesPorAno(Integer anoInicio, Integer anoFim, String instituto, String pesquisador, String tipoProducao) {
        Specification<Producao> spec = querySpecification(anoInicio, anoFim, instituto, pesquisador, tipoProducao);

        List<Producao> producoesFiltradas = producaoRepository.findAll(spec);
        
        List<TotalProducoesAno> totalPorAno = new ArrayList<>();

        Map<Integer, Map<String, Long>> producoesAgrupadas = producoesFiltradas.stream()
            .collect(Collectors.groupingBy(Producao::getAno,
                Collectors.groupingBy(Producao::getTipoProducao,
                Collectors.counting())));

        for (Map.Entry<Integer, Map<String, Long>> entry : producoesAgrupadas.entrySet()) {
            Integer anoProducao = entry.getKey();
            Map<String, Long> producoesPorTipo = entry.getValue();

            TotalProducoesAno totalProducoesAno = new TotalProducoesAno();
            totalProducoesAno.setAnoProducao(anoProducao);

            // Obtenha os totais para cada tipo de produção e defina-os no objeto TotalProducoesAno
            totalProducoesAno.setArtigo(producoesPorTipo.getOrDefault(TipoProducao.ARTIGO.getNome(), 0L));
            totalProducoesAno.setCapituloLivro(producoesPorTipo.getOrDefault(TipoProducao.CAPITULO_LIVRO.getNome(), 0L));
            totalProducoesAno.setLivro(producoesPorTipo.getOrDefault(TipoProducao.LIVRO.getNome(), 0L));
            totalProducoesAno.setOrientacaoMestrado(producoesPorTipo.getOrDefault(TipoProducao.ORIENTACOES_MESTRADO.getNome(), 0L));
            totalProducoesAno.setOrientacaoTCC(producoesPorTipo.getOrDefault(TipoProducao.ORIENTACOES_TCC.getNome(), 0L));
            totalProducoesAno.setTrabalhoEvento(producoesPorTipo.getOrDefault(TipoProducao.TRABALHO_EVENTO.getNome(), 0L));

            // Calcule o total de produção para o ano e defina-o no objeto TotalProducoesAno
            long totalProducao = totalProducoesAno.getArtigo() + totalProducoesAno.getCapituloLivro()
                    + totalProducoesAno.getLivro() + totalProducoesAno.getOrientacaoMestrado()
                    + totalProducoesAno.getOrientacaoTCC() + totalProducoesAno.getTrabalhoEvento();
            totalProducoesAno.setTotalProducao(totalProducao);

            // Adicione o objeto TotalProducoesAno à lista de resultados
            totalPorAno.add(totalProducoesAno);
        }

        // Ordenar a lista totalPorAno pelo atributo anoProducao
        Collections.sort(totalPorAno, Comparator.comparingInt(TotalProducoesAno::getAnoProducao));

        return totalPorAno;
    }

    public long countProducao() {
        return producaoRepository.count();
    }
    
    public List<TotalProducoesTipo> countTotalProducoesPorTipo() {
        return producaoRepository.countTotalProducoesPorTipo();
    }

    public void saveAll(List<Producao> producaoList){
        producaoRepository.saveAll(producaoList);
    }

    public List<GrafoPesquisador> grafoPesquisador(){
        return producaoRepository.grafoPesquisador();
    }
}