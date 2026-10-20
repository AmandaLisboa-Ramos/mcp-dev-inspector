# 🔍 MCP Dev Inspector

Servidor **Model Context Protocol (MCP)** desenvolvido em **Java 21** com **Spring Boot** e **Spring AI**, projetado para expor ferramentas de diagnóstico, monitoramento de saúde do sistema e inspeção de logs para assistentes de IA (como Claude Desktop, Cursor, Antigravity, entre outros).

---

## 🚀 Funcionalidades

O servidor implementa o protocolo MCP sobre **STDIO** (`Standard Input/Output`), disponibilizando as seguintes ferramentas (*tools*) para o modelo de linguagem:

| Ferramenta | Descrição | Retorno |
| :--- | :--- | :--- |
| `get_system_health` | Retorna o status operacional e métricas de consumo de memória do servidor (total, livre e % em uso). | Texto formatado |
| `get_recent_logs` | Retorna os eventos e logs recentes do sistema para diagnóstico e solução de incidentes. | Lista de strings |

---

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 4.x** (starter webmvc & actuator)
- **Spring AI (2.0.x)** com:
  - `spring-ai-starter-mcp-server-webmvc` (transporte STDIO para MCP)
  - `spring-ai-starter-model-google-genai` (suporte a modelos Google Gemini)
- **Maven** (gerenciador de dependências e build)
- **MCP Inspector** (`@modelcontextprotocol/inspector` para testes interativos)

---

## 📋 Pré-requisitos

Antes de iniciar, certifique-se de ter instalado:

- [JDK 21](https://www.oracle.com/java/technologies/downloads/#java21) ou superior
- [Maven](https://maven.apache.org/) (opcional, o wrapper `mvnw` está incluído no projeto)
- [Node.js](https://nodejs.org/) (necessário se for utilizar o MCP Inspector oficial para testes)
- Chave de API do Google Gemini ([Google AI Studio](https://aistudio.google.com/app/apikey))

---

## ⚙️ Configuração

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/mcp-dev-inspector.git
   cd mcp-dev-inspector
   ```

2. **Configure as variáveis de ambiente:**
   Copie o arquivo de exemplo `.env.example` para `.env`:
   ```bash
   cp .env.example .env
   ```
   Abra o `.env` e adicione sua chave de API do Gemini:
   ```env
   GEMINI_API_KEY=sua_chave_gemini_aqui
   ```

---

## 📦 Compilação e Build

Para compilar o projeto e gerar o executável JAR:

**Linux / macOS:**
```bash
./mvnw clean package -DskipTests
```

**Windows (PowerShell ou CMD):**
```powershell
.\mvnw.cmd clean package -DskipTests
```

O arquivo compilado será gerado em: `target/mcp-dev-inspector-0.0.1-SNAPSHOT.jar`.

---

## 🧪 Testando com o MCP Inspector

A forma mais rápida e visual de testar as ferramentas é usando o **MCP Inspector** oficial:

```bash
npx @modelcontextprotocol/inspector java -jar target/mcp-dev-inspector-0.0.1-SNAPSHOT.jar
```

Ou no Windows com caminho absoluto (se necessário):
```powershell
npx @modelcontextprotocol/inspector java -jar "C:/caminho/para/mcp-dev-inspector/target/mcp-dev-inspector-0.0.1-SNAPSHOT.jar"
```

Uma interface web será aberta no navegador (geralmente em `http://localhost:5173` ou na porta indicada no terminal), onde você poderá listar as ferramentas e executá-las manualmente.

---

## 🔌 Integração com Clientes MCP

### Claude Desktop

Para utilizar o servidor no aplicativo desktop do Claude, adicione ao seu arquivo de configuração `claude_desktop_config.json`:

- **Windows:** `%APPDATA%\Claude\claude_desktop_config.json`
- **macOS:** `~/Library/Application Support/Claude/claude_desktop_config.json`

```json
{
  "mcpServers": {
    "mcp-dev-inspector": {
      "command": "java",
      "args": [
        "-jar",
        "C:/caminho/completo/para/target/mcp-dev-inspector-0.0.1-SNAPSHOT.jar"
      ],
      "env": {
        "GEMINI_API_KEY": "sua_chave_gemini_aqui"
      }
    }
  }
}
```

> **Nota:** No Windows, utilize barras normais (`/`) ou escape as barras invertidas (`\\`) nos caminhos.

---


## 💡 Observações Importantes

- **Modo STDIO:** A comunicação MCP ocorre através da entrada/saída padrão (`System.in`/`System.out`). Por esse motivo, o banner do Spring e os logs do Spring Boot estão desativados (`logging.level.root=OFF`) no [application.properties](file:///src/main/resources/application.properties) para evitar corromper as mensagens no formato JSON-RPC do protocolo.

---
