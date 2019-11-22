# Stone Wallet

Este aplicativo é uma implementação do [desafio da stone][stone-desafio]

Stone Wallet é um simulador de carteira virtual que transaciona valores entre as moedas: Real, Brita e Bitcoin. 
Para começar, todo usuário recebe uma quantia de R$ 100.000,00 (cem mil reais).

## Como usar
A funcionalidade de cadastro não foi implementada, para poder testar o aplicativo use um dos usuários já cadastrados: 
`Wiliam`, `Leticia`, `Pedro`, `Joao` ou `Jorge`, todos com a senha `123456`(Os dados são falsos e armazenados localmente)

## Funções do aplicativo
* Login(com dados locais)
* Bonus de R$ 100.000,00 ao efetuar o primeiro login(com tela de instrução)
* Visualizador de saldo
* Tela de perfil(para efetuar logout)
* Tela para efetuar câmbio entre as moedas disponíveis 
* Extrato das transações com detalhamento de dia, valor de compra e valor de venda.

## Detalhes do desenvolvimento
* Arquitetura MVP
* Code style: [klint][klint]
* [layout][layout]
* Android Studio 3.5.1
* Minimum SDK version 21
* Compile SDK version 29
* Kotlin version 1.3.50
* 

## Dependências 
* [Coroutines][page-coroutine]
* [Koin][page-koin]
* [Retrofit][page-retrofit]
* [Gson][page-gson]
* [Room Database][page-room-database]
* [Junit][page-junit]
* [Mockito][page-mockito]
* [Mockito kotlin][page-mockito-kotlin]

## Integração contínua
O projeto esta integrado com o [circleCI][circle-ci]; 
executa várias rotinas automaticamente via scripts: [config][config] e [fastfile][fastfile]; 
e exibe os logs em uma [página pessoal][log-ci]

Segue as principais tarefas:

#### Branch feature/*
Quando há alguma alteração nas branches de feature:
* [Notifica início dos testes](https://trancosowiliam.herokuapp.com/api/logci/143)
* Roda os testes
* [Notifica status dos testes](https://trancosowiliam.herokuapp.com/api/logci/144)
* [Notifica sobre a nova branch atualizada](https://trancosowiliam.herokuapp.com/api/logci/145)
* [Recomenda fazer PR da branch para develop](https://trancosowiliam.herokuapp.com/api/logci/146)
* [Exibe no log o conteúdo do arquivo ReleaseNotes.txt](https://trancosowiliam.herokuapp.com/api/logci/147)

#### Branch develop
Quando há alguma alteração na branch develop:
* [Notifica início dos testes](https://trancosowiliam.herokuapp.com/api/logci/148)
* Roda os testes
* [Notifica status dos testes](https://trancosowiliam.herokuapp.com/api/logci/149)
* [Informa que esta gerando arquivo APK](https://trancosowiliam.herokuapp.com/api/logci/150)
* Gera um arquivo APK e salva no projeto
* [Faz commit e push das alterações](https://github.com/trancosowiliam/brita-wallet/commit/fa567f40147086464e6af08e9c2806ab116241d2)
* [Apk fica anexado ao projeto](https://github.com/trancosowiliam/brita-wallet/tree/master/beta)
* [Notifica sobre nova versão disponivel para teste(com link gerado)](https://trancosowiliam.herokuapp.com/api/logci/151)

#### Fluxo alternativo
Falha ao executar qualquer tarefa(teste de unidade, deploy, acesso ao servidor) é enviado para o log e pode ser acompanhado via console do circleCI.

Os logs mais recentes podem ser vistos [na minha página][log-ci]

## Melhorias futuras
* Adicionar servidor para controle de usuário e saldos
* Melhorar precisão das conversões
* Implementar perfil
* Aumentar detalhamento do extrato

## Créditos
Créditar a [Stone][stone] pela elaboração do desafio e por toda instrução bem documentada. Ao [Jefferson](https://www.linkedin.com/in/jefferson-leut%C3%A9rio-6579697a/) pelas dicas para entregar a melhor experiência para o usuário e por dar vida ao layout


[stone-desafio]: https://github.com/stone-payments/desafio-mobile/blob/master/wallet/README.md
[stone]: https://www.stone.com.br/

[klint]: https://github.com/pinterest/ktlint
[layout]: https://xd.adobe.com/view/b3d23077-53b9-4893-4740-87523264683f-c5e3/
[circle-ci]: https://circleci.com/
[log-ci]: https://trancosowiliam.herokuapp.com/logci

[page-coroutine]: https://github.com/Kotlin/kotlinx.coroutines
[page-koin]: https://insert-koin.io/
[page-retrofit]: https://square.github.io/retrofit/
[page-gson]: https://github.com/google/gson
[page-room-database]: https://developer.android.com/topic/libraries/architecture/room
[page-junit]: https://developer.android.com/training/testing/junit-rules
[page-mockito-kotlin]: https://github.com/nhaarman/mockito-kotlin
[page-mockito]: https://github.com/mockito/mockito

[fastfile]: https://github.com/trancosowiliam/brita-wallet/blob/master/fastlane/Fastfile
[config]: https://github.com/trancosowiliam/brita-wallet/blob/master/.circleci/config.yml