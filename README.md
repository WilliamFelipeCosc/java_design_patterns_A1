# Design Patterns em Java - Atividades Práticas

**Aluno:** William Felipe Coscodai  
**RGM:** 30051291  
**Curso:** Ciência da Computação

---

## 📋 Sobre o Projeto

Este repositório contém implementações práticas de 5 Design Patterns fundamentais da Engenharia de Software, aplicados a cenários reais de desenvolvimento. Cada atividade demonstra um pattern específico com exemplos completos, documentação detalhada e casos de uso.

---

## 🎯 Atividades Implementadas

### **Atividade 1: Factory Method Pattern**
📁 `atividade1/`

**Cenário:** Sistema de Relatórios Operacionais para Empresa de Logística

**Problema:** Uma empresa de logística precisa gerar diferentes tipos de relatórios (diário, semanal, emergencial) com regras e formatos específicos, permitindo adicionar novos tipos sem modificar o código existente.

**Pattern:** Factory Method
- Delega a criação de relatórios para factories específicas
- Cada tipo de relatório tem sua própria classe e factory
- Extensível: novos relatórios sem modificar existentes


---

### **Atividade 2: Strategy Pattern**
📁 `atividade2/`

**Cenário:** Plataforma de Análise de Investimentos

**Problema:** Sistema precisa calcular perfil de risco de clientes usando diferentes modelos (agressivo, moderado, conservador) com fórmulas e critérios distintos, permitindo troca dinâmica do modelo em tempo de execução.

**Pattern:** Strategy
- Encapsula algoritmos de cálculo em estratégias separadas
- Troca de modelo em runtime sem alterar o código cliente
- Cada modelo tem pesos e critérios específicos


---

### **Atividade 3: Observer Pattern**
📁 `atividade3/`

**Cenário:** Sistema de Monitoramento Ambiental

**Problema:** Sensores distribuídos (temperatura, umidade, poluição) precisam notificar automaticamente múltiplos módulos (painel, alertas, histórico) quando atualizam valores, mantendo baixo acoplamento.

**Pattern:** Observer
- Sensores (subjects) notificam observers automaticamente
- Múltiplos módulos observam sem conhecer uns aos outros
- Adicionar/remover observers dinamicamente

---

### **Atividade 4: Chain of Responsibility Pattern**
📁 `atividade4/`

**Cenário:** Sistema Antifraude para Pagamentos Online

**Problema:** Transações precisam passar por cadeia de verificações (valor, geolocalização, histórico, dispositivo), sendo bloqueadas imediatamente ao falhar em qualquer etapa, com possibilidade de reorganizar ou estender a cadeia.

**Pattern:** Chain of Responsibility
- Verificações encadeadas sequencialmente
- Early exit: para ao primeiro erro
- Cadeia flexível e reorganizável

---

### **Atividade 5: Singleton Pattern**
📁 `atividade5/`

**Cenário:** Sistema Corporativo de Logging Centralizado

**Problema:** Sistema precisa de um componente único e global para registrar logs (erros, eventos, auditoria) em arquivo e servidor externo, garantindo thread-safety e prevenindo inconsistências de múltiplas instâncias.

**Pattern:** Singleton
- Instância única garantida em toda aplicação
- Acesso global thread-safe
- Double-Checked Locking para performance


---

## 🔍 Comparação dos Patterns

| Pattern | Atividade | Foco Principal | Relacionamento |
|---------|-----------|----------------|----------------|
| **Factory Method** | 1 | Criação de objetos | Herança |
| **Strategy** | 2 | Algoritmos intercambiáveis | Composição (1:1) |
| **Observer** | 3 | Notificação de mudanças | Composição (1:N) |
| **Chain of Responsibility** | 4 | Processamento sequencial | Cadeia linear |
| **Singleton** | 5 | Instância única | Controle global |

---


## ⚖️ Licença

Projeto acadêmico - Uso educacional

---

**Desenvolvido como atividade prática de Design Patterns**
