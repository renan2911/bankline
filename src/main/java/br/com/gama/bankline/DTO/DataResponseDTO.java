package br.com.gama.bankline.DTO;

import java.util.List;

import br.com.gama.bankline.model.PlanoConta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataResponseDTO {

    private Boolean success;
    private Integer count;
    private List<PlanoContaDTO> data;

    
}
