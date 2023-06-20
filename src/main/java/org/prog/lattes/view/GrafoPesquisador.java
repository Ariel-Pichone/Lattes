package org.prog.lattes.view;

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
    private String pesquisador1;
    private String pesquisador2;
    private String nomeProducao;
    private String tipoProducao;
}