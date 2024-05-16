#Teste de programação - VR Benefícios

# Mini autorizador

**Jaziel de Oliveira**

**Notas sobre o desenvolvimento**

Para esta aplicação, foi utilizada a organição mais clássica de aplicativos rest em Spring: foi criada a divisão física/lógica como segue: 
- pasta controller
- pasta dto (para troca de valor entre o cliente e o controller), com mapper para o model
- repository, para a conversa com a camada de dados
- service, para a lógica de negócio

Houve a tentativa de utilização dos princípios de SOLID, procurando-se baixo acoplamento, single-responsibility, inversão de controle fornecida pelo próprio spring entre outros.

Poderia ter sido implementado um modelo mais abstrato com interface entre os models e os dtos, por exemplo, mas para os critérios dessa avaliação e o tamanho da aplicação optou-se por não fazê-lo.

Foi utilizado lombok para facilitar o desenvolvimento de POJOs.

Foi utilizado o *pattern chain of responsibility* para implementação da validação do cartão, que cabia perfeitamente como boa prática nesse requisito.

Foi utilizado o conceito de Test Driven Design e apenas testes de integração foram utilizados no desenvolvimento.

Para testes foi segregado um banco de dados H2 exclusivo em memória.

Para rodar o sistema será necessário subir o banco via docker compose a parte. Foi cogitada a ideia de usar o spring para lidar com o docker compose, mas como isso não era um requisito do teste, essa ideia foi abandonada.

O docker compose está na raiz do projeto, com a parte do MongoDb comentada. Não se utilizou o script anexado à pasta do docker para mudar o usuário/senha do MySql, uma vez que era requisito não mexer no docker compose. Assim, o banco de dados é chamado com usuário root sem senha.

Com relação às regras de negócio:
- é possível utilizar um valor negativo nas transações e provocar aumento do saldo;
- o payload não está sendo validado; assim, se um campo inválido é passado o sistema não reclama e, se um campo necessário não é passado ou está ortograficamente errado, o sistema reclamará se ele for mandatório.
- não foi implementada validação de dados que nao estivesse definida nos requisitos. Assim, é possível, por exemplo, passar uma string no valor da transação, provocando BAD REQUEST. Não foram feitos testes de manipulação de dados inválidos.

Foi seguido a risca o CONTRATO de input e de output das apis.

Não foi gerado SWAGGER para a aplicação.

Para o requisito de concorrência foi implementado o conceito de Transação com a anotação @Trancsactional do spring. Todo o processo de withdraw */transacoes* é bloqueado e não é possível duas requisições executarem o processo ao mesmo tempo. Caso haja algum erro antes ou depois da gravação do saldo no banco, a operação é rolled back. Como este é um exercício, não houve preocupação em configurar timeouts ou manipular erros mais específicos, ou diminuir o escopo da trava, etc.

É isso. Foi muito divertido!