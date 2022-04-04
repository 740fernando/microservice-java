# Microsserviços

 O texto abaixo é a uma tradução do artigo escrito por Martin Fowler e James Lewis. Para acessar a versão original em inglês, <a href= "https://martinfowler.com/articles/microservices.html"> clique aqui </a > . Caso tenha sugestões para tornar a tradução melhor, compartilhe através da seção de "Issues"
 
 “Microsserviços” – um novo nome nas populosas ruas da arquitetura de software. Embora nossa tendência natural é ignorar estas novidades com um olhar de desprezo, essa nova terminologia descreve um estilo de sistema que achamos cada vez mais atraente. Temos visto muitos projetos usando este formato nos últimos anos e os resultados tem sido positivos, tanto que para muitos dos nossos colegas esta se tornou a forma padrão para desenvolver aplicações empresariais. Infelizmente, entretanto, não existe muita informação que descreva o que são microsserviços e como implementá-los.

Em resumo, microsserviço é uma abordagem <a href="#note_1">[1]</a> para desenvolver uma única aplicação como uma suíte de serviços, cada um rodando em seu próprio processo e se comunicando através de mecanismos leves, geralmente através de uma API HTTP. Estes serviços são construídos através de pequenas responsabilidades e publicados em produção de maneira independente através de processos de deploys automatizados. Existe um gerenciamento centralizado mínimo destes serviços, que podem serem escritos em diferentes linguagens e usarem diferentes tecnologias para armazenamento de dados.


Para começar explicando o que é o padrão de microsserviços, podemos compara-lo ao padrão monolítico: uma aplicação monolítica é feita como uma única unidade. Aplicações empresariais são geralmente construídas em três partes principais: uma interface para o cliente (tais como páginas HTML e Javascript rodando em um navegador no computador do usuário), um banco de dados (várias tabelas em um mesmo lugar, geralmente um sistema de banco de dados relacional) e uma aplicação server-side. A aplicação server-side irá manipular as requisições HTTP, executar toda lógica de domínio, receber e atualizar os dados da base de dados e por fim, selecionar e popular os blocos HTML para enviar ao navegador. Esta aplicação server-side é monolítica – uma única unidade lógica executável [2]. Qualquer mudança no sistema consiste em publicar uma nova versão da aplicação server-side.

###### 2: O termo “monolítico” tem sido usado pela comunidade Unix há algum tempo. Ele aparece no The Art of Unix Programming para descrever sistemas que ficam muito grandes.

Este tipo de servidor monolítico é uma forma natural de construir um sistema. Toda sua lógica para manipular uma requisição é executada em um único processo, permitindo que você use as características básicas da sua linguagem para separar sua aplicação em classes, funções e namespaces. Com algum cuidado, você pode rodar e testar a aplicação no computador do desenvolvedor e usar uma seqüência de integração para garantir que todas as mudanças sejam propriamente testadas e implantadas em produção. Você pode escalar uma aplicação monolítica horizontalmente rodando diversas instâncias através de um load-balancer.

Aplicações monolíticas podem ser bem-sucedidas, porém as pessoas começarão a ficar frustradas – especialmente quando muitas aplicações começarem a serem implantadas na nuvem. Ciclos de mudanças começam a ficarem amarrados – uma pequena alteração feita em uma parte pequena do software faz com que toda a aplicação monolítica necessite ser republicada. Com o passar do tempo ficará cada vez mais difícil manter uma estrutura modular, sendo difícil separar as mudanças que deveriam afetar somente um módulo. Para escalar é necessário escalar toda a aplicação, ao invés de escalar somente as partes que necessitem de maiores recursos.

![image](https://user-images.githubusercontent.com/32016610/161525011-771095a8-a0c7-408f-9ca6-a7a9ece82151.png)

Estas frustrações levaram ao padrão de microsserviços: aplicações como uma suíte de serviços. Além do fato de que os serviços são implantados e escalam de maneiras independentes, cada serviço também provê uma fronteira bem definida entre os módulos, permitindo até mesmo que diferentes serviços sejam escritos em diferentes linguagens de programação. Eles podem inclusive serem administrados por times diferentes.

Nós não tomamos o padrão de microsserviços como algo novo, suas raízes remetem no mínimo aos princípios de design do próprio Unix. Porém nós cremos que poucas pessoas consideram uma arquitetura de microsserviços e que o desenvolvimento de muitos softwares seriam melhores se adotassem esse padrão.

## Características de uma arquitetura em microsserviços

Não podemos dizer que existe uma definição formal para uma arquitetura em micro serviços, mas podemos tentar descrever o que temos visto como características comuns em arquiteturas que caibam no padrão. Como em qualquer definição que descreva características em comum, nem todas as arquiteturas em microsserviços tem todas as características, mas nós acreditamos que a maioria das arquiteturas em microsserviços exibam a maior parte das características a serem citadas. Como membros ativos na comunidade sobre este tema, temos tentado descrever o que temos visto em nosso próprio trabalho e através de esforços semelhantes em times que nós temos conhecimento.

## Componentização via serviços
Vemos na indústria de softwares um desejo de construir sistemas plugando componentes entre si, tal como vemos as coisas serem feitas no mundo físico. Durante as últimas décadas, percebemos progressos consideráveis através de várias bibliotecas que fazem parte da maior parte das plataformas de desenvolvimento.

Quando falamos sobre componentes, encontramos a dificuldade em definir o que é um componente. <Strong>Nossa definição é que um componente é uma unidade de software que é substituída ou atualizada de maneira independente.</Strong>

Arquiteturas em microsserviços usarão bibliotecas, mas buscam primeiramente organizar seu próprio software dividindo em serviços. Nós definimos bibliotecas como componentes que são usados em um programa através de chamadas de função diretamente em memória, enquanto serviços são componentes em processos diferentes que se comunicam através de mecanismos tais como requisições via web services ou chamadas de código remotas (este é um conceito diferente de objeto de serviço, encontrado em muitas linguagens orientadas a objeto [3]) .


<p>
<strong id="note_1">1</strong>: O termo “microsserviço” foi discutido em um workshop de arquitetos de software perto de Veneza, em Maio de 2011, para descrever o que os participantes viram como um padrão arquitetural que eles estiveram explorando recentemente. Em Maio de 2012, o mesmo grupo decidiu que “microsserviços” era o nome mais apropriado. James apresentou algumas destas idéias em seu caso de estudo no 33rd Degree na Cracóvia em Microsserviços – Java, the Unix Way foi outro caso apresentado pelo Fred George na mesma época. Adrian Cockcroft, na Netflix, descrevendo esta abordagem como “SOA bem feito”, foi um dos pioneiros deste padrão em uma escala Web, tal como muitos dos outros apresentados neste artigo – Joe Walnes, Dan North, Evan Botcher e Graham Tackley. 
</p>

