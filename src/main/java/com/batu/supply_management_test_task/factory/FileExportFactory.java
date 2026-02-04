package com.batu.supply_management_test_task.factory;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.batu.supply_management_test_task.strategy.FileExportStrategy;

@Component
public class FileExportFactory {

    private final Map<String, FileExportStrategy> strategies;

    public FileExportFactory(Map<String, FileExportStrategy> strategies) {
        this.strategies = strategies;
    }

    public FileExportStrategy getStrategy(String name){
        return strategies.getOrDefault(name, strategies.get("CSV"));
    }
    
}
