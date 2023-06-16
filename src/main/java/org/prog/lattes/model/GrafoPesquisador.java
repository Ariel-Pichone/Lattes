package org.prog.lattes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GrafoPesquisador {
    private Long idPesquisador1;
    private Long idPesquisador2;
    private String nomeProducao;
}
