package org.br.mineradora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Builder
@Data
@AllArgsConstructor
@Jacksonized
public class ProposalDTO {

    private Long proposalId;

    private String customer;

    private String priceTonne;

}
