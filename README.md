<div align="center">

# SafeBus

<img width="2172" height="724" alt="banner_safebus" src="https://github.com/user-attachments/assets/b2b41d89-31cc-4198-bea2-14d8cba48835" />

### Aplicativo Android de apoio à segurança no transporte

**Documentação técnica • Arquitetura • Desenvolvimento • Funcionalidades**

Aplicação Android nativa desenvolvida com Kotlin e Jetpack Compose, com foco no registro de alertas de segurança, autenticação de usuários e localização geográfica.

![Android](https://img.shields.io/badge/Android-Native-0D6EAF?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-Development-0D6EAF?style=for-the-badge&logo=kotlin&logoColor=white)
![Compose](https://img.shields.io/badge/Jetpack_Compose-UI-00A6A6?style=for-the-badge&logo=jetpackcompose&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-Backend-00A6A6?style=for-the-badge&logo=firebase&logoColor=white)

![Status](https://img.shields.io/badge/PROJECT_STATUS-IN_DEVELOPMENT-0D6EAF?style=flat-square)
![Platform](https://img.shields.io/badge/PLATFORM-ANDROID-64748B?style=flat-square)

</div>

---

## Sobre esta documentação

Este documento apresenta o SafeBus de maneira técnica e didática. A proposta não é apenas mostrar quais tecnologias foram utilizadas, mas explicar **o que cada uma faz, como funciona, por que foi escolhida e como contribui para o aplicativo**.
A documentação foi organizada para atender tanto quem está começando a estudar desenvolvimento Android quanto quem deseja conhecer as decisões técnicas do projeto.

### Conteúdo

1. [Apresentação do projeto](#01--apresentação-do-projeto)
2. [Origem e modernização](#02--origem-e-modernização)
3. [Arquitetura de software](#03--arquitetura-de-software)
4. [MVVM explicado](#04--mvvm-explicado)
5. [Clean Architecture e separação de responsabilidades](#05--clean-architecture-e-separação-de-responsabilidades)
6. [Kotlin e Jetpack Compose](#06--kotlin-e-jetpack-compose)
7. [Gerenciamento de estado](#07--gerenciamento-de-estado)
8. [Componentes reutilizáveis e Design System](#08--componentes-reutilizáveis-e-design-system)
9. [Autenticação com Firebase](#09--autenticação-com-firebase)
10. [Cloud Firestore e persistência de dados](#10--cloud-firestore-e-persistência-de-dados)
11. [Localização e GPS](#11--localização-e-gps)
12. [Mapa com OpenStreetMap](#12--mapa-com-openstreetmap)
13. [Registro e histórico de alertas](#13--registro-e-histórico-de-alertas)
14. [Navegação entre telas](#14--navegação-entre-telas)
15. [Permissões, privacidade e segurança](#15--permissões-privacidade-e-segurança)
16. [Testes e validação](#16--testes-e-validação)
17. [Organização e manutenção do código](#17--organização-e-manutenção-do-código)
18. [Estado atual e próximas etapas](#18--estado-atual-e-próximas-etapas)
19. [Execução do projeto](#19--execução-do-projeto)
20. [Considerações finais](#20--considerações-finais)

---

# 01 — Apresentação do projeto

## O que é o SafeBus?

O **SafeBus** é um aplicativo Android criado com a proposta de contribuir para a segurança de pessoas que utilizam transporte público. Seu objetivo é permitir que situações de risco, como assalto e assédio ou importunação, sejam registradas de maneira simples, associando o alerta à localização geográfica obtida pelo dispositivo.
A ideia é que o usuário encontre uma interface clara, com poucas etapas e informações fáceis de compreender.

### Qual problema queremos resolver?

Em situações de risco, uma pessoa pode ter dificuldade para informar sua localização ou descrever rapidamente o que está acontecendo. O SafeBus busca reduzir parte dessa dificuldade por meio de um fluxo de registro de alertas que utiliza os recursos de localização do Android.

### Como o aplicativo pretende ajudar?

O projeto trabalha com três necessidades principais:

| Necessidade | Solução proposta |
|---|---|
| Identificar a situação | Seleção do tipo de alerta |
| Identificar o local | Captura de coordenadas geográficas |
| Consultar informações anteriores | Histórico dos alertas registrados |

### Limites da versão atual

O SafeBus ainda está em desenvolvimento. O registro de um alerta no aplicativo **não significa que a polícia ou outro serviço de emergência tenha sido acionado**. A integração com serviços oficiais de atendimento é uma possibilidade futura, que dependeria de requisitos técnicos, legais e operacionais próprios.

---

# 02 — Origem e modernização

## Como surgiu o SafeBus?

O projeto nasceu durante o desenvolvimento de um Trabalho de Conclusão de Curso (TCC). Na primeira versão, o aplicativo foi desenvolvido utilizando **Java e XML**, tecnologias amplamente utilizadas no desenvolvimento Android tradicional. Com a evolução do ecossistema Android, surgiu a proposta de reconstruir o aplicativo, mantendo seu objetivo original e modernizando sua implementação.

### Antes e agora

| Aspecto | Projeto original | Versão atual |
|---|---|---|
| Linguagem | Java | Kotlin |
| Interface | XML | Jetpack Compose |
| Organização | Estrutura original do TCC | Organização orientada a responsabilidades |
| Componentes visuais | Views tradicionais | Componentes Composable |
| Arquitetura | Implementação original | MVVM e princípios de Clean Architecture |
| Autenticação | Solução original | Firebase Authentication |
| Mapa | Solução original | OpenStreetMap com osmdroid |

## Por que modernizar?

Modernizar um aplicativo não significa apenas substituir uma linguagem por outra. É uma oportunidade para melhorar:

- A organização do código.
- A separação entre interface e lógica.
- A reutilização de componentes.
- A manutenção e a evolução das funcionalidades.
- A experiência de quem utiliza o aplicativo.
- A compreensão das decisões técnicas.

**O objetivo não é adicionar complexidade. É construir uma solução mais organizada e sustentável.**

---

# 03 — Arquitetura de software

## O que é arquitetura de software?

Arquitetura de software é a maneira como organizamos as diferentes partes de um sistema e definimos como elas se relacionam. Imagine um aplicativo em que uma única tela seja responsável por:

- Desenhar botões e campos.
- Validar informações.
- Acessar o Firebase.
- Obter a localização.
- Salvar dados.
- Controlar mensagens de erro.

Embora seja possível escrever um aplicativo dessa maneira, ele tende a se tornar difícil de entender e manter. A arquitetura ajuda a distribuir essas responsabilidades.

## Qual abordagem utilizamos?

O SafeBus utiliza **MVVM**, acompanhado de princípios de **Clean Architecture**, buscando separar a apresentação, a lógica e o acesso aos dados.

### Visão conceitual

```text
┌─────────────────────────────────────┐
│            APRESENTAÇÃO             │
│                                     │
│   Screens e componentes Compose     │
│                                     │
│   Exibem informações e recebem      │
│   interações do usuário             │
└─────────────────┬───────────────────┘
                  │
                  ▼
┌─────────────────────────────────────┐
│              VIEWMODEL              │
│                                     │
│   Controla estados e coordena       │
│   ações da funcionalidade           │
└─────────────────┬───────────────────┘
                  │
                  ▼
┌─────────────────────────────────────┐
│          REGRAS E DADOS             │
│                                     │
│   Responsabilidades de negócio,     │
│   persistência e serviços externos  │
└─────────────────┬───────────────────┘
                  │
                  ▼
┌─────────────────────────────────────┐
│         SERVIÇOS EXTERNOS           │
│                                     │
│   Firebase, localização e outros    │
│   recursos utilizados pelo app      │
└─────────────────────────────────────┘
```

**Importante:** esse diagrama representa a organização conceitual. Nem todas as funcionalidades precisam possuir todas essas camadas ou classes. O objetivo é criar separações quando elas realmente ajudam o projeto, evitando arquivos e abstrações desnecessárias.

---

# 04 — MVVM explicado

## O que significa MVVM?

MVVM é a sigla para:

**Model – View – ViewModel**

É um padrão arquitetural que ajuda a separar a interface da lógica responsável por preparar e controlar seus dados. Vamos entender cada parte.

## Model

O **Model** representa os dados e as regras relacionadas à funcionalidade. Em um aplicativo como o SafeBus, podemos trabalhar com informações como:

- Tipo de alerta.
- Latitude.
- Longitude.
- Precisão da localização.
- Data e hora do registro.

Essas informações não precisam depender diretamente de um botão ou de uma tela específica.

### Exemplo conceitual

```kotlin
data class Alert(
    val type: String,
    val latitude: Double,
    val longitude: Double,
    val accuracyMeters: Float?
)
```

Esse exemplo mostra como dados podem ser representados em Kotlin. Não significa que o projeto necessariamente possua uma classe com esse nome ou essa estrutura.

## View

A **View** é a parte visual do aplicativo. No SafeBus, ela é construída principalmente com Jetpack Compose.

Sua responsabilidade é:

- Mostrar textos, botões, imagens e campos.
- Apresentar informações ao usuário.
- Receber interações.
- Exibir estados como carregamento, sucesso ou erro.

Uma View não deve concentrar toda a lógica de negócio.

## ViewModel

O **ViewModel** faz a ligação entre a interface e as operações necessárias à funcionalidade. Ele pode:

- Controlar o estado da tela.
- Iniciar operações.
- Processar resultados.
- Informar à interface quando os dados estiverem disponíveis.
- Representar situações de erro.

### Exemplo real do SafeBus

Na funcionalidade de localização de alertas, utilizamos o `AlertLocationViewModel`.Ele é responsável por coordenar a obtenção da localização e atualizar o estado apresentado pela tela. O fluxo pode ser entendido assim:

```text
Usuário acessa a tela de localização
                  │
                  ▼
       AlertLocationScreen
                  │
                  ▼
       AlertLocationViewModel
                  │
                  ▼
      Verificação de permissão
                  │
                  ▼
      Solicitação de localização
                  │
                  ▼
       Resultado da operação
                  │
                  ▼
        Atualização do estado
                  │
                  ▼
      Interface apresenta o resultado
```

## Por que utilizar MVVM?

Porque ele ajuda a evitar que a interface seja responsável por tudo. Isso oferece vantagens importantes:

| Vantagem | Explicação |
|---|---|
| Organização | Cada parte possui uma responsabilidade mais clara |
| Manutenção | Uma mudança visual não precisa alterar toda a lógica |
| Testabilidade | Regras podem ser verificadas sem depender de uma tela completa |
| Legibilidade | Fica mais fácil localizar onde determinado comportamento acontece |
| Evolução | Novas funcionalidades podem ser adicionadas com menor acoplamento |

### Uma observação importante

MVVM não significa criar um ViewModel para cada botão ou componente visual. Componentes simples podem receber dados e callbacks diretamente. A decisão deve considerar a responsabilidade e a complexidade de cada funcionalidade.

---

# 05 — Clean Architecture e separação de responsabilidades

## O que é Clean Architecture?

Clean Architecture é uma abordagem que propõe organizar o software de maneira que suas regras mais importantes não dependam diretamente de detalhes externos. Em outras palavras: **O funcionamento central de uma aplicação não deveria ficar preso a uma biblioteca, banco de dados ou tecnologia de interface específica.** Isso facilita mudanças futuras.

## As três áreas mais comuns

### Presentation — Apresentação

É a parte que interage diretamente com o usuário.

Exemplos:

- Telas.
- Componentes visuais.
- Estados de interface.
- ViewModels.

### Domain — Domínio

Representa as regras e operações relacionadas ao problema que o aplicativo procura resolver. Em uma arquitetura mais completa, essa área pode conter:

- Modelos de domínio.
- Casos de uso.
- Contratos de repositórios.

Um **caso de uso**, ou *Use Case*, representa uma ação importante do sistema. Por exemplo, em termos conceituais:

```text
Registrar um alerta
Consultar histórico
Obter detalhes de um registro
```

Isso não significa que seja obrigatório criar uma classe separada para cada uma dessas ações.

### Data — Dados

É a parte que trabalha com fontes de dados e serviços externos. Exemplos:

- Firebase Authentication.
- Cloud Firestore.
- APIs.
- Armazenamento local, quando necessário.

## O que é um Repository?

Um **Repository** é um componente que organiza o acesso a dados. Ele pode oferecer operações como:

```text
salvarAlerta()
consultarAlertas()
buscarDetalhes()
```

A ideia é que outras partes do aplicativo não precisem conhecer todos os detalhes da fonte utilizada.

### Exemplo conceitual

```text
ViewModel
    │
    ▼
Repository
    │
    ▼
Cloud Firestore
```

Se uma fonte de dados mudar no futuro, uma separação adequada pode reduzir o impacto dessa alteração.

## O que é uma interface ou contrato?

Em Kotlin, uma `interface` define operações que uma implementação deve oferecer. Por exemplo:

```kotlin
interface AlertRepository {
    suspend fun saveAlert(alert: Alert)
}
```

A interface define **o que deve ser feito**, mas não necessariamente **como será feito**. Uma implementação pode utilizar Firebase; outra, em um contexto diferente, poderia utilizar outra fonte.

### Por que isso importa?

Porque diminui o acoplamento entre as partes do sistema. **Acoplamento** é o grau de dependência entre componentes. Quanto mais uma classe conhece detalhes internos de outra, maior tende a ser o esforço necessário para modificá-las.

## Como aplicamos esses princípios no SafeBus?

Buscamos:

- Manter responsabilidades identificáveis.
- Evitar lógica de negócio espalhada pela interface.
- Criar componentes reutilizáveis quando necessário.
- Separar acesso a serviços externos quando isso traz benefício.
- Preservar a simplicidade do projeto.

**Não estamos criando camadas apenas para seguir um modelo teórico.**

A arquitetura precisa ajudar o desenvolvimento, e não dificultá-lo.

---

# 06 — Kotlin e Jetpack Compose

## Kotlin

Kotlin é a linguagem utilizada no desenvolvimento da versão atual do SafeBus. Ela possui suporte oficial no ecossistema Android e oferece recursos que ajudam a escrever código mais conciso e seguro.

### Alguns conceitos importantes

**Null Safety**

Kotlin diferencia tipos que podem conter `null` daqueles que não podem.

```kotlin
var address: String? = null
```

O símbolo `?` indica que a variável pode não possuir um valor. Isso é útil, por exemplo, quando ainda não foi possível obter o endereço do usuário.

**Data Class**

Uma `data class` é utilizada para representar dados.

```kotlin
data class Coordinates(
    val latitude: Double,
    val longitude: Double
)
```

**Coroutines**

Coroutines permitem trabalhar com operações assíncronas sem bloquear desnecessariamente a execução da interface. São úteis para operações como:

- Buscar dados.
- Consultar serviços.
- Processar resultados.
- Executar tarefas que podem demorar.

## Jetpack Compose

Jetpack Compose é o toolkit moderno de interface declarativa do Android.

### O que significa interface declarativa?

Na abordagem declarativa, descrevemos **como a interface deve aparecer de acordo com o estado atual**. Em vez de modificar manualmente cada elemento visual, o Compose atualiza as partes necessárias quando o estado muda.

### Exemplo simples

```kotlin
@Composable
fun WelcomeMessage(name: String) {
    Text(text = "Olá, $name")
}
```

Esse componente recebe um nome e apresenta uma mensagem. Se o valor recebido mudar, o Compose poderá atualizar a interface.

## Por que escolhemos Compose?

- Facilita a construção de componentes reutilizáveis.
- Trabalha bem com gerenciamento de estado.
- Possui integração com Material 3.
- Permite criar previews no Android Studio.
- Ajuda a manter interfaces consistentes.

No SafeBus, as telas e componentes visuais estão sendo modernizados com Compose.

---

# 07 — Gerenciamento de estado

## O que é estado?

Estado é a informação que determina o que a interface deve apresentar em determinado momento. Considere uma tela que precisa obter a localização. Ela pode estar:

1. Aguardando a localização.
2. Solicitando permissão.
3. Apresentando uma localização encontrada.
4. Informando que não foi possível obter a localização.

Cada situação representa um estado diferente.

## Exemplo do SafeBus

Na funcionalidade de localização, trabalhamos com estados como:

```text
Loading
PermissionRequired
PermissionDenied
Success
LocationUnavailable
```

Eles permitem que a interface saiba o que apresentar.

### Por que isso é melhor?

Sem uma organização clara, seria comum utilizar várias variáveis booleanas:

```text
isLoading
hasError
hasPermission
isFinished
```

Dependendo da combinação dessas variáveis, a tela poderia acabar representando situações contraditórias. Com estados bem definidos, fica mais simples entender o que está acontecendo.

## StateFlow

`StateFlow` é um recurso de Kotlin Coroutines utilizado para disponibilizar um valor de estado que pode ser observado. Um ViewModel pode atualizar esse valor, e a interface pode reagir às mudanças.

### Fluxo simplificado

```text
ViewModel altera o estado
           │
           ▼
StateFlow disponibiliza o novo valor
           │
           ▼
A interface observa a alteração
           │
           ▼
Compose atualiza a apresentação
```

## O que é recomposição?

**Recomposição** é o processo pelo qual o Compose reexecuta partes da interface quando informações observadas mudam. Isso evita que o desenvolvedor precise atualizar manualmente cada elemento visual.

### Um cuidado importante

Nem todo dado precisa ficar em um ViewModel. Estados puramente visuais e temporários podem ser mantidos no próprio componente, quando isso fizer sentido. O importante é definir quem deve controlar cada informação.

---

# 08 — Componentes reutilizáveis e Design System

## O que é um componente reutilizável?

É um elemento visual que pode ser utilizado em diferentes partes do aplicativo. Exemplos:

- Botões.
- Campos de texto.
- Cards.
- Indicadores de carregamento.

No SafeBus, estamos trabalhando com componentes como:

```text
PrimaryButton
SecondaryButton
StandardCard
StandardTextField
```

## Por que criar componentes?

Imagine que dez telas utilizem botões com a mesma aparência. Se cada botão for implementado separadamente, uma alteração de altura, formato ou comportamento precisará ser repetida. Com um componente reutilizável, a alteração pode ser centralizada.

### Exemplo

```kotlin
@Composable
fun ExampleContent() {
    PrimaryButton(
        text = "Continuar",
        onClick = { }
    )
}
```

*Exemplo ilustrativo de utilização; os parâmetros exatos dependem da assinatura do componente no projeto.*

## O que é Design System?

Design System é um conjunto organizado de padrões visuais e comportamentais utilizados para manter a consistência de uma interface. Ele pode definir:

- Cores.
- Tipografia.
- Espaçamentos.
- Formatos.
- Botões.
- Campos.
- Cards.
- Comportamentos de interação.

## Material 3

O SafeBus utiliza Material 3 como base para seus componentes e estilos. Isso permite trabalhar com recursos como:

```kotlin
MaterialTheme.colorScheme.primary
MaterialTheme.colorScheme.surface
MaterialTheme.typography.titleMedium
```

### Por que não definir cores diretamente em todas as telas?

Porque isso dificulta a manutenção e o suporte a temas. Quando os componentes utilizam `MaterialTheme`, podem se adaptar aos padrões visuais definidos pelo aplicativo.

## Tema claro e escuro

O SafeBus possui suporte visual a dois temas. A interface utiliza cores semânticas, que representam funções, e não apenas valores fixos.

Por exemplo:

```text
primary          → Cor principal
onPrimary        → Conteúdo sobre a cor principal
surface          → Superfícies
onSurface        → Conteúdo sobre superfícies
error            → Indicação de erro
```

## Previews

Os previews permitem visualizar componentes e telas diretamente no Android Studio. No projeto, buscamos manter visualizações nos temas claro e escuro.

```kotlin
@Preview(
    name = "Tema Claro",
    showSystemUi = true
)
@Composable
private fun ExampleLightPreview() {
    SafeBusTheme(darkTheme = false) {
        ExampleContent()
    }
}
```

### Por que isso é útil?

Porque facilita verificar:

- Legibilidade.
- Espaçamento.
- Alinhamento.
- Comportamento visual.
- Consistência entre temas.

Alguns componentes que dependem de recursos nativos, como o mapa, não podem ser renderizados integralmente no Preview. Nesses casos, utilizamos uma representação visual adequada.

---

# 09 — Autenticação com Firebase

## O que é autenticação?

Autenticação é o processo de verificar a identidade de quem tenta acessar um sistema. No SafeBus, ela é importante porque o aplicativo trabalha com informações relacionadas aos usuários e seus registros.

## O que é Firebase Authentication?

Firebase Authentication é um serviço do Google que oferece recursos para autenticação de usuários. Ele permite implementar diferentes métodos de entrada, dependendo da configuração do projeto. No SafeBus, trabalhamos com autenticação utilizando Firebase e conta Google.

## Como funciona, de forma simplificada?

```text
Usuário inicia o login
          │
          ▼
Seleciona ou informa suas credenciais
          │
          ▼
Firebase Authentication valida a entrada
          │
          ├── Falha
          │     └── Aplicativo apresenta o erro
          │
          └── Sucesso
                └── Usuário acessa o aplicativo
```

## Por que utilizar Firebase Authentication?

Porque o serviço já oferece uma infraestrutura especializada para autenticação. Isso evita a necessidade de desenvolver do zero toda a estrutura de validação de identidade. Entretanto, utilizar Firebase não elimina a responsabilidade de configurar corretamente as permissões e proteger os dados do aplicativo.

---

# 10 — Cloud Firestore e persistência de dados

## O que significa persistência?

Persistência é a capacidade de manter informações armazenadas para que elas possam ser recuperadas posteriormente. Sem persistência, os dados poderiam desaparecer quando o aplicativo fosse fechado.

## O que é Cloud Firestore?

Cloud Firestore é um banco de dados NoSQL disponibilizado pelo Firebase. Diferentemente de um banco relacional tradicional, ele organiza dados principalmente em:

- **Collections:** coleções de documentos.
- **Documents:** documentos que contêm campos e valores.
- **Fields:** informações armazenadas em cada documento.

### Estrutura conceitual

```text
Coleção de alertas
        │
        ├── Documento A
        │     ├── Tipo
        │     ├── Latitude
        │     ├── Longitude
        │     └── Data e hora
        │
        └── Documento B
              ├── Tipo
              ├── Latitude
              ├── Longitude
              └── Data e hora
```

Essa representação é ilustrativa e não pretende reproduzir os nomes exatos das coleções e campos utilizados no banco.

## Como utilizamos no SafeBus?

O Firestore é utilizado para armazenar os registros de alerta, permitindo a consulta posterior no histórico.

### Fluxo conceitual

```text
Usuário registra um alerta
              │
              ▼
Aplicativo reúne as informações
              │
              ▼
Operação de gravação no Firestore
              │
              ▼
Resultado da operação
              │
              ▼
Registro disponível para consulta
```

## Por que utilizar Firestore?

- Integração com Firebase.
- Armazenamento de documentos.
- Suporte a operações assíncronas.
- Possibilidade de consulta posterior.
- Infraestrutura gerenciada.

## Cuidados necessários

O Firestore precisa de regras de segurança adequadas. Não basta esconder informações na interface: o acesso aos dados deve ser controlado também no backend. Antes de disponibilizar o aplicativo publicamente, é necessário revisar as regras de acesso, os dados armazenados e as políticas de privacidade.

---

# 11 — Localização e GPS

## Por que a localização é importante?

Uma das propostas centrais do SafeBus é associar um alerta ao local em que ele foi registrado. Para isso, utilizamos os recursos de localização disponibilizados pelo Android.

## O que são latitude e longitude?

São coordenadas geográficas utilizadas para representar posições na superfície terrestre.

- **Latitude:** indica a posição em relação ao Equador.
- **Longitude:** indica a posição em relação ao meridiano de Greenwich.

Juntas, elas identificam um ponto geográfico.

## Como o Android obtém a localização?

O dispositivo pode utilizar diferentes fontes, como:

- Sinais de satélite.
- Redes Wi-Fi.
- Redes móveis.
- Informações disponibilizadas pelos serviços de localização.

O Android combina essas fontes conforme a disponibilidade e as condições do dispositivo.

## Fused Location Provider

O SafeBus utiliza o **Fused Location Provider**, disponibilizado pelo Google Play Services. Esse serviço oferece uma API para solicitar a localização do dispositivo.

### Exemplo utilizado na tela do mapa

```kotlin
val locationClient =
    LocationServices.getFusedLocationProviderClient(context)
```

Essa instrução cria um cliente para solicitar informações de localização. Em seguida, podemos realizar uma solicitação:

```kotlin
locationClient.getCurrentLocation(
    Priority.PRIORITY_HIGH_ACCURACY,
    cancellationTokenSource.token
)
```

### O que significa HIGH_ACCURACY?

É uma prioridade que solicita ao serviço de localização uma posição com foco em maior precisão. Isso **não garante** que a posição obtida será exata. A qualidade depende das condições do dispositivo e das fontes disponíveis.

## Permissões

O Android exige autorização do usuário para acessar sua localização. As principais permissões envolvidas são:

```text
ACCESS_FINE_LOCATION
ACCESS_COARSE_LOCATION
```

### Qual a diferença?

**Fine Location:** permite solicitar localização precisa, quando disponível e autorizada. **Coarse Location:** permite obter localização aproximada. O aplicativo deve respeitar a escolha do usuário.

## Precisão estimada

A localização pode trazer uma informação chamada `accuracy`. Por exemplo:

```text
Precisão estimada: 15 m
```

Esse número indica uma estimativa de precisão horizontal fornecida pelo Android. Não significa que o endereço ou o número do imóvel esteja necessariamente correto.

## Geocoder

O `Geocoder` permite tentar converter coordenadas geográficas em informações de endereço. Esse processo é chamado de **geocodificação reversa**.

```text
Latitude + Longitude
          │
          ▼
       Geocoder
          │
          ▼
Endereço aproximado
```

### Por que o endereço pode estar errado?

Porque coordenadas e endereços são informações diferentes. O serviço pode identificar uma rua próxima ou apresentar um número de imóvel que não corresponde exatamente à posição. Por isso, é importante preservar as coordenadas originais.

## Testes no emulador

O emulador Android utiliza localizações simuladas. Ele não identifica automaticamente a posição física do computador. Durante o desenvolvimento, observamos que o emulador podia apresentar uma localização diferente daquela em que o teste estava sendo realizado.

## Testes no celular físico

Foram realizados testes no dispositivo físico, com resultados corretos para os cenários verificados. Isso é importante, mas não substitui testes adicionais em condições de sinal fraco, localização indisponível ou permissões limitadas.

---

# 12 — Mapa com OpenStreetMap

## O que é OpenStreetMap?

OpenStreetMap é um projeto colaborativo de dados geográficos abertos. Seus dados podem ser utilizados para construir soluções que envolvem mapas, respeitando as condições de uso e atribuição.

## O que é osmdroid?

`osmdroid` é uma biblioteca Android que permite exibir mapas baseados em OpenStreetMap. Ela oferece recursos como:

- Movimentação do mapa.
- Zoom.
- Centralização em coordenadas.
- Sobreposição de elementos gráficos.
- Interação com o mapa.

## Por que escolhemos essa solução?

O objetivo foi implementar um mapa funcional sem depender da configuração de faturamento da Google Maps Platform. Essa escolha também permite estudar uma alternativa de mapas baseada em dados abertos. **Observação:** utilizar OpenStreetMap não significa que qualquer servidor de mapas ofereça uso ilimitado ou sem condições. É necessário respeitar as políticas do serviço de tiles utilizado.

## Como funciona a tela do mapa?

A implementação atual utiliza `MapScreen`, Jetpack Compose e `MapView`, do osmdroid.

### Fluxo

```text
MapScreen
    │
    ├── Verifica permissão
    │
    ├── Solicita localização
    │
    ├── Recebe coordenadas
    │
    ├── Centraliza o MapView
    │
    ├── Desenha o marcador azul
    │
    ├── Consulta endereço aproximado
    │
    └── Apresenta informações e controles
```

## AndroidView

`AndroidView` é um componente do Jetpack Compose que permite utilizar Views tradicionais do Android dentro de uma interface Compose. Isso é necessário porque o `MapView` do osmdroid não é um componente Composable nativo.

### Exemplo do projeto

```kotlin
AndroidView(
    factory = { mapView },
    modifier = Modifier.fillMaxSize()
)
```

## Marcador de localização

O SafeBus utiliza uma implementação personalizada chamada `UserLocationOverlay`. Ela desenha o marcador azul sobre o mapa.

### O que é Overlay?

Um `Overlay` é um elemento desenhado sobre a visualização do mapa. Pode representar, por exemplo:

- Um marcador.
- Uma linha.
- Uma área.
- Um elemento gráfico personalizado.

No SafeBus, o overlay representa a localização obtida do dispositivo.

## Botão Minha localização

O botão permite retornar ao ponto azul depois que o usuário movimenta o mapa. Ele utiliza as coordenadas disponíveis na tela para centralizar novamente a visualização.

**Importante:** o botão não solicita, por si só, uma nova leitura do GPS na implementação atual.

## Card de localização

O card apresenta:

- Endereço aproximado.
- Precisão estimada, quando disponível.

Seu objetivo é complementar a informação visual do mapa. O mapa continua sendo o elemento principal da tela.

---

# 13 — Registro e histórico de alertas

## Registro de alertas

O fluxo de alertas permite selecionar o tipo de situação e associar a ocorrência à localização disponibilizada pelo dispositivo. Entre os tipos trabalhados estão:

- Assalto.
- Assédio ou importunação.

## Por que registrar a localização?

Porque a localização oferece contexto geográfico ao alerta. No entanto, uma coordenada não deve ser interpretada como garantia de que o usuário estava exatamente naquele ponto.

## Histórico

O histórico permite consultar registros realizados anteriormente. Isso é útil para recuperar informações sem depender da tela em que o alerta foi criado.

## Detalhes de um alerta

A visualização de detalhes pode apresentar informações como:

- Tipo da ocorrência.
- Data e hora.
- Coordenadas geográficas.
- Precisão disponível.
- Endereço aproximado.

O fluxo também oferece uma opção para abrir a localização em um aplicativo de mapas externo.

## Por que não depender apenas do endereço?

Porque o endereço é obtido a partir de uma interpretação das coordenadas. Guardar latitude e longitude permite preservar a informação geográfica original recebida pelo aplicativo.

---

# 14 — Navegação entre telas

## O que é navegação?

Navegação é o mecanismo que permite ao usuário passar de uma tela para outra. Em aplicativos Compose, podemos utilizar o **Navigation Compose** para organizar destinos e fluxos de navegação.

## Por que centralizar as rotas?

Porque isso facilita compreender quais telas existem e como elas se conectam. No SafeBus, o fluxo de navegação é organizado no `SafeBusNavHost`.

### Exemplo conceitual

```text
Aplicativo
    │
    ├── Autenticação
    │
    └── Área principal
          │
          ├── Início
          ├── Mapa
          ├── Perfil
          └── Mais
```

## O que é NavHost?

`NavHost` é o componente responsável por hospedar os destinos de navegação. Ele determina qual conteúdo deve ser exibido conforme a rota atual.

## O que é NavController?

`NavController` controla operações de navegação, como avançar para um destino ou retornar.

### Por que isso é importante?

Porque a navegação é uma responsabilidade diferente da apresentação visual de cada tela. Uma tela não precisa conhecer todos os detalhes das outras para solicitar uma navegação.

---

# 15 — Permissões, privacidade e segurança

## Permissões Android

Algumas funcionalidades precisam acessar recursos protegidos do dispositivo. A localização é um exemplo.

O aplicativo deve:

1. Verificar se a permissão foi concedida.
2. Solicitar autorização quando necessário.
3. Respeitar a resposta do usuário.
4. Informar quando uma funcionalidade não puder continuar.

## Privacidade

Informações de localização podem ser sensíveis. Por isso, é importante definir:

- Quais dados são coletados.
- Por que são necessários.
- Onde são armazenados.
- Quem pode acessá-los.
- Por quanto tempo serão mantidos.
- Como poderão ser removidos, quando aplicável.

## Segurança no Firebase

O Firebase oferece mecanismos de autenticação e regras de acesso, mas sua configuração é responsabilidade do projeto. Antes de uma publicação, será necessário validar:

- Regras de segurança do Firestore.
- Acesso aos registros por usuário.
- Tratamento de falhas.
- Proteção das configurações sensíveis.
- Política de privacidade.

## Segurança funcional

Como o SafeBus trata de situações de risco, também é necessário evitar mensagens que possam induzir o usuário a acreditar que um serviço de emergência foi acionado quando isso não aconteceu. A interface deve comunicar claramente o resultado de cada operação.

---

# 16 — Testes e validação

## Por que testar?

Porque uma funcionalidade pode compilar corretamente e ainda apresentar comportamentos inesperados. Testar significa verificar se o aplicativo se comporta conforme o esperado.

## Testes unitários

São testes voltados a pequenas unidades de comportamento, como uma regra ou operação. Exemplo:

```text
Dado um resultado de localização válido,
o estado deve representar sucesso.
```

Eles ajudam a detectar regressões em regras que não dependem diretamente da interface.

## Testes de interface

Verificam o comportamento visual e as interações do usuário. Exemplos:

- Um botão responde ao toque?
- Uma mensagem aparece corretamente?
- Um estado de carregamento é exibido?
- A navegação leva ao destino esperado?

## Previews não substituem testes

Os previews ajudam a verificar a aparência de uma interface. Mas eles não garantem que a navegação, o Firebase ou a localização funcionarão corretamente durante a execução.

## Validações realizadas

Durante o desenvolvimento, foram verificados:

- Componentes visuais.
- Temas claro e escuro.
- Fluxos de navegação.
- Registro de alertas.
- Consulta ao histórico.
- Exibição do mapa.
- Localização em emulador.
- Localização em celular físico.

A cobertura de testes automatizados e os cenários de falha ainda precisam ser ampliados.

---

# 17 — Organização e manutenção do código

## Organização por funcionalidade

O projeto utiliza uma organização que busca agrupar elementos relacionados. Um exemplo real é o pacote da tela do mapa:

```text
br.com.denisecastro.safebus
    │
    └── feature
          │
          └── map
                │
                └── screens
                      │
                      └── MapScreen.kt
```

Essa organização ajuda a localizar o código relacionado a uma funcionalidade.

## Componentes compartilhados

Elementos que possuem comportamento e aparência reutilizáveis ficam organizados em componentes. Isso evita duplicação desnecessária.

## Recursos de texto

Os textos apresentados ao usuário são centralizados em arquivos de recursos, como:

```text
res/values/strings.xml
```

Exemplo:

```xml
<string name="map_my_location">
    Minha localização
</string>
```

Na interface:

```kotlin
stringResource(R.string.map_my_location)
```

### Por que utilizar recursos?

- Facilita manutenção.
- Evita textos espalhados pelo código.
- Ajuda na futura internacionalização.
- Mantém consistência.

## Evolução incremental

O SafeBus está sendo desenvolvido em pequenas etapas. O processo utilizado é:

```text
Identificar uma necessidade
           │
           ▼
Analisar a implementação existente
           │
           ▼
Realizar a alteração necessária
           │
           ▼
Testar o comportamento
           │
           ▼
Validar o resultado
           │
           ▼
Seguir para a próxima etapa
```

A proposta é preservar o que já funciona e evitar complexidade desnecessária.

---

# 18 — Estado atual e próximas etapas

## Funcionalidades trabalhadas

| Área | Situação |
|---|---|
| Interface com Kotlin e Compose | Implementada e em evolução |
| Identidade visual e temas | Implementados |
| Componentes reutilizáveis | Implementados |
| Autenticação com Firebase | Implementada no fluxo desenvolvido |
| Registro de alertas | Implementado |
| Histórico e detalhes | Implementados |
| Captura de localização | Implementada e testada |
| Mapa com OpenStreetMap | Implementado |
| Marcador azul | Implementado |
| Botão Minha localização | Implementado |
| Card de endereço e precisão | Implementado |
| Opções adicionais de Perfil | Pendentes |
| Funcionalidades de Conversas e Contatos | Pendentes |
| Ampliação de testes | Pendente |
| Integração oficial com serviços de emergência | Não implementada |

## Próximas etapas

O desenvolvimento continuará com as funcionalidades pendentes da área de Perfil e demais opções de navegação. Também estão previstas melhorias relacionadas à validação de erros, privacidade, segurança e testes. As prioridades poderão ser ajustadas conforme a evolução do aplicativo.

---

# 19 — Execução do projeto

## Requisitos

Para trabalhar com o projeto, é necessário:

- Android Studio.
- Android SDK compatível.
- Suporte a Kotlin e Jetpack Compose.
- Emulador ou dispositivo Android.
- Configuração adequada do Firebase.
- Acesso à internet para os serviços necessários.

## Obter o código

```bash
git clone https://github.com/DeniseLeandroDeCastro/safe_bus
```

## Abrir no Android Studio

1. Abra o Android Studio.
2. Selecione a opção para abrir um projeto existente.
3. Escolha a pasta do SafeBus.
4. Aguarde a sincronização do Gradle.
5. Configure o Firebase conforme os requisitos do projeto.
6. Selecione um emulador ou celular físico.
7. Execute o aplicativo.

## Configuração do Firebase

A execução das funcionalidades que dependem do Firebase exige uma configuração válida do projeto. Essa configuração pode envolver o registro do aplicativo no console do Firebase, a habilitação dos métodos de autenticação utilizados e a configuração do Firestore. As regras de segurança devem ser revisadas antes de disponibilizar o aplicativo a outras pessoas.

## Testar localização

Para testar a localização:

- Conceda as permissões solicitadas pelo Android.
- Ative os serviços de localização do dispositivo.
- Utilize um celular físico para validar a captura real.
- Lembre-se de que o emulador pode utilizar posições simuladas.

---

# 20 — Considerações finais

O SafeBus representa a evolução de um projeto acadêmico para uma aplicação Android desenvolvida com ferramentas e práticas atuais. 
Mais do que modernizar uma interface, o trabalho envolve compreender como organizar responsabilidades, gerenciar estados, integrar serviços externos e construir funcionalidades que sejam fáceis de utilizar e manter. 
A escolha por Kotlin, Jetpack Compose, MVVM, princípios de Clean Architecture, Firebase e OpenStreetMap está relacionada a esses objetivos. 
O desenvolvimento continuará de maneira incremental, buscando soluções simples, justificadas e compatíveis com as necessidades reais do projeto.

---

<div align="center">

### SafeBus

**Desenvolvimento Android com foco em organização, usabilidade e propósito.**

Projeto em desenvolvimento.

</div>
