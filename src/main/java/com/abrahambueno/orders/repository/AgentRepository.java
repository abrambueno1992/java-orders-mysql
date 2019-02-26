package com.abrahambueno.orders.repository;

import com.abrahambueno.orders.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AgentRepository extends JpaRepository<Agent, Long> {

    @Query(value = "select  a.agentname, c.custname from agents a, customers c WHERE a.agentcode = c.agentcode ORDER BY a.agentname", nativeQuery = true )
    List<Object[]> getAgentsAndCustomers();

    @Query(value = "select  a.agentname, o.ordnum from agents a, orders o WHERE a.agentcode = o.agentcode ORDER BY a.agentname", nativeQuery = true)
    List<Object[]> getAgentsAndOrders();

}