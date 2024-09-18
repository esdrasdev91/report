package org.br.mineradora.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.br.mineradora.dto.OpportunityDTO;
import org.br.mineradora.service.OpportunityService;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.List;

@Path("/api/opportunity")
public class OpportunityController {

    @Inject
    JsonWebToken token;

    @Inject
    OpportunityService opportunityService;

    @GET
    @Path("/data")
    @RolesAllowed({"user", "manager"})
    public List<OpportunityDTO> generateReport() {
        return opportunityService.generateOpportunityData();
    }

}