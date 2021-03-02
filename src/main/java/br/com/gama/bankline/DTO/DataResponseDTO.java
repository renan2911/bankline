package br.com.gama.bankline.DTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataResponseDTO {

    private Boolean success;
    private Integer count;
    private List<PlanoContaDTO> data;

}
