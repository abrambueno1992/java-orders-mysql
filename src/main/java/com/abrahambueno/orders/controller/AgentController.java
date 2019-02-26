package com.abrahambueno.orders.controller;

import com.abrahambueno.orders.model.Agent;
import com.abrahambueno.orders.repository.AgentRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@Api(value = "Java Orders Application", description = "An Application that implements orders with CRUD functionality")
@RequestMapping(path = {}, produces = MediaType.APPLICATION_JSON_VALUE)
public class AgentController {
    @Autowired
    AgentRepository agentrepos;

    @GetMapping("/agents")
    public List<Object[]> getAgentCustomers() throws URISyntaxException {
        var agentsCustomers = agentrepos.getAgentsAndCustomers();
        if (agentsCustomers != null) {
            return agentsCustomers;
        } else {
            return null;
        }
    }

    @PostMapping("/agents")
    public Agent addAgent(@RequestBody Agent agent) {
        return agentrepos.save(agent);
    }

    @GetMapping("/agents/orders")
    public List<Object[]> getAgentOrders() {
        var agentOrders = agentrepos.getAgentsAndOrders();
        if (agentOrders != null) {
            return agentOrders;
        } else {
            return null;
        }

    }


    @PutMapping("/agents/agentcode/{agentcode}")
    public Agent updateAgent(@RequestBody Agent newagent, @PathVariable Long agentcode) throws URISyntaxException {
        Optional<Agent> updateAgent = agentrepos.findById(agentcode);
        if (updateAgent.isPresent()) {
            newagent.setAgentCode(agentcode);
            agentrepos.save(newagent);
            return newagent;
        } else {
            return null;
        }
    }

    // not sure if works
    @GetMapping("/agents/agentcode/{agentcode}")
    public void deleteAgentNotAssigned(@PathVariable Long agentcode) {

    }
}
