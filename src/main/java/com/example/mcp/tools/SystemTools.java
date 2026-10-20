package com.example.mcp.tools;

import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class SystemTools {
    @Tool(name = "get_system_health", description = "Retorna o status de saúde e métricas do servidor")
    public String getSystemHealt() {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        double memoryUsagePercentage = (double) freeMemory / totalMemory * 100;
        return String.format("Status: OPERACIONAL\n Total de memoria: %d\n Memoria livre: %d\n"
                + "Uso de memoria: %.2f%%", totalMemory, freeMemory, memoryUsagePercentage);
    }

    @Tool(name = "get_recent_logs", description = "Retorna os últimos logs do sistema para identificar possíveis problemas")
    public List<String> getRecentLogs() {
        return List.of(
                "[INFO] Servidor iniciado com sucesso na porta 8080",
                "[INFO] MCP server ativo",
                "[WARN] Uso de memoria acima de 80%",
                "[ERROR] NullPointerException ao processar o payload de integração");
    }
}
