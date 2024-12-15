# IMDCorp - Sistema de Gerenciamento de Funcionários

## Funcionalidades 
O sistema permite as seguintes operações:

1. *Adicionar Professor:* Permite inserir um novo professor no sistema com dados pessoais e profissionais.
2. *Adicionar Técnico Administrativo:* Permite inserir um novo técnico administrativo com suas informações.
3. *Listar Funcionários:* Lista todos os funcionários cadastrados com suas informações.
4. *Remover Funcionário:* Remove um funcionário do sistema com base na matrícula.
5. *Calcular Salário:* Calcula o salário de um funcionário com base na sua matrícula.
6. *Encerrar Sessão:* Salva os dados no banco de dados e finaliza o sistema.


## Estrutura do Projeto
O projeto está organizado em pacotes:

- *dao:* Contém a classe BancoDAO, responsável por carregar e salvar dados persistentes.
- *enums:* Define a formação, as enumerações para gênero e níveis hierárquicos.
- *main:* Implementa a interface principal do sistema, permitindo realizar operações como cadastro,   listagem, remoção, e cálculo de salário de professores e técnicos administrativos.
- *model:* Contém as classes de modelo com Endereco, Funcionário, Pessoa, Professor e TecnicoADM.
- *service:* Define o cadastro, listagem, busca, exclusão e cálculo de salário para professores e técnicos administrativos.

## Executar
1. Certifique-se de que o seu computador possui [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html) 

2. Compile o projeto com o comando:
   bash
   javac Main.java
   
3. Para executar:
    Run na Main.java

4. Siga as instruções no menu interativo para realizar as operações desejadas.

## Instruções de Uso
Ao iniciar o programa, o menu principal será exibido. Digite o número correspondente à operação que você deseja realizar e siga as instruções fornecidas pelo sistema.

### Cadastro de Funcionários
- O sistema solicita informações pessoais, como nome, CPF, data de nascimento, gênero, endereço e salário.
- Para cada tipo de funcionário, dados específicos são solicitados:
  - *Professor:* Nível, formação e disciplinas lecionadas.
  - *Técnico Administrativo:* Nível, formação, insalubridade e função gratificada.

### Busca e Deleção
- Ao buscar ou deletar um funcionário, é necessário informar a matrícula cadastrada.

### Cálculo de Salário
- Para o cálculo de salário ser fornecido, é preciso informar a matrícula cadastrada.

## Autora
Emilly Miller