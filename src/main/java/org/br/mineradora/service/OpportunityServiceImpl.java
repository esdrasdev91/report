package org.br.mineradora.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.br.mineradora.dto.OpportunityDTO;
import org.br.mineradora.dto.ProposalDTO;
import org.br.mineradora.dto.QuotationDTO;

import java.util.List;

@ApplicationScoped
public class OpportunityServiceImpl  implements OpportunityService{

    @Override
    public void buildOpportunity(ProposalDTO proposalDTO) {

    }

    @Override
    public void saveQuotation(QuotationDTO quotationDTO) {

    }

    @Override
    public List<OpportunityDTO> generateOpportunityData() {
        return List.of();
    }
}
