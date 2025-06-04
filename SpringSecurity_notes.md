# Spring Boot Security
O Spring Security é um framework do ecossistema Spring que oferece soluções de segurança.

O Spring Security oferece recursos para **autenticação** (provar quem é o usuário) e **autorização** (definir
quem pode acessar/modificar qual recurso), além de integração com **Oauth2, JWT, LDAP, etc**. 
Também conta com recursos de proteção de ataques comuns, como: CSRF, XSS, Clickjacking.

##### Conceitos básicos:
>**Autenticação**
>
> É o ato de identificar quem está enviando uma request. No Security a autenticação é tratada por:
> + **AuthenticationManager**: gerencia a autenticação;
> + **UserDetailService**: busca as informações do usuário no banco de dados;
> + **PasswordEncoder**: valida e codifica senhas.

>**Autorização**
> 
> Depois de autenticar o usuário, o sistema define o que o usuário pode ou não acessar ou modificar. É possível
> definir regras de autorização através de:
> + **Roles**: papéis atribuídos a cada tipo de usuário da aplicação;
> + **Authority**: permissão mais granular de acesso;
> + **Anotações**: `@PreAuthorize`, `@Secured`, `@RolesAllowed`.

---
### SecurityFilterChain
O Spring Security protege uma aplicação ao adicionar uma cadeia de **filtros de segurança** no processamento
de cada requisição HTTP. 

Todas as requisições são interceptadas antes que cheguem na camada Controller (Spring MVC). Nessa cadeia são
aplicadas a autenticação, autorização e proteções adicionais.