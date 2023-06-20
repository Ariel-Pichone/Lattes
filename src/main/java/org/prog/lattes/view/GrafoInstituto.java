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
public class GrafoInstituto {
    private Long instituto1;
    private Long instituto2;
    private String nomeProducao;
    private String tipoProducao;
}