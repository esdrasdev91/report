package org.br.mineradora.service;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.br.mineradora.dto.OpportunityDTO;
import org.br.mineradora.dto.ProposalDTO;
import org.br.mineradora.dto.QuotationDTO;
import org.br.mineradora.entity.OpportunityEntity;
import org.br.mineradora.entity.QuotationEntity;
import org.br.mineradora.repository.OpportunityRepository;
import org.br.mineradora.repository.QuotationRepository;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class OpportunityServiceImpl  implements OpportunityService{

    @Inject
    QuotationRepository quotationRepository;

    @Inject
    OpportunityRepository opportunityRepository;

    @Override
    @WithSpan
    public void buildOpportunity(ProposalDTO proposalDTO) {

        List<QuotationEntity> quotationEntities = quotationRepository.findAll().list();
        Collections.reverse(quotationEntities);

        OpportunityEntity opportunity = new OpportunityEntity();
        opportunity.setDate(new Date());
        opportunity.setProposalId(proposalDTO.getProposalId());
        opportunity.setCustomer(proposalDTO.getCustomer());
        opportunity.setPriceTonne(proposalDTO.getPriceTonne());
        opportunity.setLastDollarQuotation(quotationEntities.get(0).getCurrencyPrice());

        opportunityRepository.persist(opportunity);
    }

    @Override
    @WithSpan
    @Transactional
    public void saveQuotation(QuotationDTO quotationDTO) {

        QuotationEntity createdQuotation = new QuotationEntity();
        createdQuotation.setDate(new Date());
        createdQuotation.setCurrencyPrice(quotationDTO.getCurrencyPrice());

        quotationRepository.persist(createdQuotation);
    }

    @Override
    @WithSpan
    public List<OpportunityDTO> generateOpportunityData() {

        List<OpportunityDTO> opportunities = new ArrayList<>();
        opportunityRepository.findAll()
                .stream()
                .forEach(item -> opportunities.add(
                                OpportunityDTO.builder()
                                .proposalId(item.getProposalId())
                                .customer(item.getCustomer())
                                .priceTonne(item.getPriceTonne())
                                .lastDollarQuotation(item.getLastDollarQuotation())
                                .build()
                ));

        return opportunities;
    }

}
