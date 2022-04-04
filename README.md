# Microsserviços

 O texto abaixo é a uma tradução do artigo escrito por Martin Fowler e James Lewis. Para acessar a versão original em inglês, <a href= "https://martinfowler.com/articles/microservices.html"> clique aqui </a > . Caso tenha sugestões para tornar a tradução melhor, compartilhe através da seção de "Issues"
 
 “Microsserviços” – um novo nome nas populosas ruas da arquitetura de software. Embora nossa tendência natural é ignorar estas novidades com um olhar de desprezo, essa nova terminologia descreve um estilo de sistema que achamos cada vez mais atraente. Temos visto muitos projetos usando este formato nos últimos anos e os resultados tem sido positivos, tanto que para muitos dos nossos colegas esta se tornou a forma padrão para desenvolver aplicações empresariais. Infelizmente, entretanto, não existe muita informação que descreva o que são microsserviços e como implementá-los.

Em resumo, microsserviço é uma abordagem <a href="#note_1">[1]</a> para desenvolver uma única aplicação como uma suíte de serviços, cada um rodando em seu próprio processo e se comunicando através de mecanismos leves, geralmente através de uma API HTTP. Estes serviços são construídos através de pequenas responsabilidades e publicados em produção de maneira independente através de processos de deploys automatizados. Existe um gerenciamento centralizado mínimo destes serviços, que podem serem escritos em diferentes linguagens e usarem diferentes tecnologias para armazenamento de dados.


Para começar explicando o que é o padrão de microsserviços, podemos compara-lo ao padrão monolítico: uma aplicação monolítica é feita como uma única unidade. Aplicações empresariais são geralmente construídas em três partes principais: uma interface para o cliente (tais como páginas HTML e Javascript rodando em um navegador no computador do usuário), um banco de dados (várias tabelas em um mesmo lugar, geralmente um sistema de banco de dados relacional) e uma aplicação server-side. A aplicação server-side irá manipular as requisições HTTP, executar toda lógica de domínio, receber e atualizar os dados da base de dados e por fim, selecionar e popular os blocos HTML para enviar ao navegador. Esta aplicação server-side é monolítica – uma única unidade lógica executável <a href="#note_2">[2]</a>. Qualquer mudança no sistema consiste em publicar uma nova versão da aplicação server-side.



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

Arquiteturas em microsserviços usarão bibliotecas, mas buscam primeiramente organizar seu próprio software dividindo em serviços. Nós definimos bibliotecas como componentes que são usados em um programa através de chamadas de função diretamente em memória, enquanto serviços são componentes em processos diferentes que se comunicam através de mecanismos tais como requisições via web services ou chamadas de código remotas (este é um conceito diferente de objeto de serviço, encontrado em muitas linguagens orientadas a objeto <a href="#note_3">[3]</a>) .


Uma das principais razões para usar serviços como componentes (ao invés de bibliotecas) é que serviços são publicados de maneira independente. Se você tem uma aplicação <a href="#note_4">[4]</a> que consiste em diversas bibliotecas em um único processo, uma mudança em qualquer componente resulta em ter que republicar toda sua aplicação. Mas se esta aplicação é divida em múltiplos serviços, você pode esperar que diversas mudanças em um único serviço exijam uma republicação somente no serviço alterado. Isso não é algo absoluto, pois algumas mudanças criam alterações nas interfaces entre os serviços, mas o dever de uma boa arquitetura em microsserviços é minimizar este impacto, criando interfaces coesas e mecanismos para evolução entre os serviços.

Outra conseqüência em usar serviços como componentes é ter uma interface mais explícita. A maioria das linguagens não tem uma boa forma de definir explicitamente uma interface do tipo Published Interface. Freqüentemente só conseguimos impedir uma violação no encapsulamento de um componente através da documentação e muita disciplina, o que leva a um alto acoplamento entre os componentes. Serviços ajudam a evitar esse problema, usando mecanismos de chamadas remotas.

Usar serviços desta forma tem alguns efeitos colaterais. Chamadas remotas são mais custosas que chamadas dentro do mesmo processo e APIs remotas precisam ser granulares, o que torna ainda mais complicado para usar. Se você precisa mudar as responsabilidades entre os componentes, tais mudanças de comportamento são mais difíceis de fazer do que quando você consegue ultrapassar as fronteiras entre os processos.

Em um primeiro momento, nós podemos ver quais serviços apontam para processos em execução, mas isso é só uma primeira impressão. Um serviço pode consistir em diversos processos que serão sempre desenvolvidos e publicados juntos, como uma única aplicação e um banco de dados que é usado por cada serviço.

<h4>Organizado através das áreas do negócio</h4>

Quando procuramos dividir uma grande aplicação em partes, o foco geralmente é na camada de tecnologia, levando os times a serem divididos entre aqueles que cuidam da interface, da lógica server-side e do banco de dados. Quando times são divididos desta forma, até mesmo mudanças simples podem exigir bastante tempo e aprovação financeira em projetos que envolvam diversos times. Ao fugir destes problemas, o time pode acabar trazendo a lógica para as aplicações que eles têm acesso. Em outras palavras, lógica em todos os lugares. Este é um exemplo da Lei de Conway <strong id="note_5">5</strong>: em ação.


<blockquote><p>"Qualquer organização que desenha um sistema (definido de forma ampla) irá produzir um <em>design</em> cuja estrutura é uma cópia da estrutura de comunicação da própria organização."</p>
<p>— Melvyn Conway, 1967</p></blockquote>

 <p style="text-align: center;"><img loading="lazy" class="aligncenter size-full wp-image-622" src="https://codigo35.com/wp-content/uploads/2015/12/blog_f2.png" alt="blog_f2" width="586" height="463" srcset="https://codigo35.com/wp-content/uploads/2015/12/blog_f2.png 586w, https://codigo35.com/wp-content/uploads/2015/12/blog_f2-300x237.png 300w" sizes="(max-width: 586px) 100vw, 586px">
</p><p style="text-align: center">Figura 2: Lei de Conway em ação</p>
 
 <p>A abordagem proposta pelos micros-serviços para esta divisão é diferente, ao organizar os times ao redor das <strong>áreas do negócio</strong>. Assim os serviços possuirão uma implementação satisfatória para uma determinada área do negócio, incluindo uma interface com usuário, armazenamento de dados persistente e qualquer outra necessidade externa. Conseqüentemente, os times se tornam multifuncionais, levando toda bagagem necessária para o desenvolvimento: interface com o usuário, banco de dados e o gerenciamento do projeto.</p>
 
 <p>
 <img loading="lazy" class="aligncenter size-full wp-image-677" src="https://codigo35.com/wp-content/uploads/2016/01/blog_f3.png" alt="blog_f3" width="748" height="403" srcset="https://codigo35.com/wp-content/uploads/2016/01/blog_f3.png 748w, https://codigo35.com/wp-content/uploads/2016/01/blog_f3-300x162.png 300w" sizes="(max-width: 748px) 100vw, 748px">
</p><p style="text-align: center">Figura 3: Limites dos serviços reforçados pelos limites dos times.</p>

Uma empresa organizada desta forma é a www.comparethemarket.com. Times multifuncionais ficam responsáveis por construir e operar cada produto e cada produto é quebrado em serviços individuais se comunicando via um barramento de mensagens.

<h3>Notas:</h3>

<p>
<strong id="note_1">1</strong>: O termo “microsserviço” foi discutido em um workshop de arquitetos de software perto de Veneza, em Maio de 2011, para descrever o que os participantes viram como um padrão arquitetural que eles estiveram explorando recentemente. Em Maio de 2012, o mesmo grupo decidiu que “microsserviços” era o nome mais apropriado. James apresentou algumas destas idéias em seu caso de estudo no 33rd Degree na Cracóvia em Microsserviços – Java, the Unix Way foi outro caso apresentado pelo Fred George na mesma época. Adrian Cockcroft, na Netflix, descrevendo esta abordagem como “SOA bem feito”, foi um dos pioneiros deste padrão em uma escala Web, tal como muitos dos outros apresentados neste artigo – Joe Walnes, Dan North, Evan Botcher e Graham Tackley. 
</p>

<p>
<strong id="note_2">2</strong>: O termo “monolítico” tem sido usado pela comunidade Unix há algum tempo. Ele aparece no The Art of Unix Programming para descrever sistemas que ficam muito grandes.
</p>

<p>
<strong id="note_3">3</strong>: Muitos dos desenvolvedores em orientação a objetos, incluíndo nós mesmos, usamos o termo “objeto de serviço” seguindo o sentido do Domain-Driven Design para um objeto que carrega uma parte significante do processo e que não esta preso a nenhuma entidade. Este é um conceito diferente do que estamos usando como “serviço” neste artigo. Infelizmente o termo serviço possuí ambos os sentidos e nós temos que viver com essa polivalência.
</p>

<p>
<strong id="note_4">4</strong>: Consideramos uma aplicação como uma construção que une uma base de código, grupo de funcionalidades e um fundamento para tudo isso.
</p>

<p>
<strong id="note_5">5</strong>: O paper original pode ser encontrados no site do Melvyn Conway, <a href="http://www.melconway.com/Home/Committees_Paper.html" target="_blank" rel="noopener">clicando aqui</a>
 </p>
 
