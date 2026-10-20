package com.example.mcp;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.mcp.tools.SystemTools;

@Configuration
public class McpConfig {

    @Bean
    public ToolCallbackProvider systemToolsCallback(SystemTools systemTools) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(systemTools)
                .build();
    }

}
