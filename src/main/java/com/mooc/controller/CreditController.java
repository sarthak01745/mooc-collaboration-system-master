package com.mooc.controller;

import com.mooc.service.AutomataEngine;
import com.mooc.service.RoutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/credits")
public class CreditController {

    @Autowired
    private AutomataEngine automataEngine;
    
    @Autowired
    private RoutingService routingService;

    @GetMapping("/validate/{sourceId}/{destId}")
    public ResponseEntity<Map<String, Boolean>> validateTransfer(
            @PathVariable Long sourceId, @PathVariable Long destId) {
        boolean isValid = automataEngine.validateCreditTransfer(sourceId, destId);
        return ResponseEntity.ok(Map.of("isValid", isValid));
    }
    
    @GetMapping("/routing/path/{sourceAs}/{destAs}")
    public ResponseEntity<Map<String, List<Integer>>> getBestPath(
            @PathVariable int sourceAs, @PathVariable int destAs) {
        List<Integer> path = routingService.getBestPath(sourceAs, destAs);
        return ResponseEntity.ok(Map.of("bestPath", path));
    }
}