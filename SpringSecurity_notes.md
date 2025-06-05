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
## Implementação

De modo geral, para usar as funcionalidades do Spring Security, existem algumas estruturas que podem
ser implementadas:
    
- ### Classe de configuração de segurança
    - É importante criar uma classe que vai conter as principais configurações de segurança. Essa classe deve
        ser anotada com `@Configuration`.
    - Dentro dessa classe vai um método `SecurityFilterChain` anotado como `@Bean` (componente do Spring). 

>#### SecurityFilterChain
> É uma cadeia de **filtros de segurança** no processamento de cada requisição HTTP. 
> 
> No SecurityFilterChain é possível definir: **filtros, autenticação, regras de acesso, proteções**.

Ex:
```Java
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
            .httpBasic();  // ou .formLogin();
        return http.build();
    }
}
```
---
- ### UserDetailService
    - O `UserDetailService` é uma **interface** do Spring Security que permite definir como carregar
        o usuário e suas permissões.
    - Éssa interface serve como ponto de integração entre o Spring Security e o repositório de usuários
      (banco de dados, memória, arquivos, etc).
    - É usada quando a **autenticação é baseada em <ins>usuário e senha**.

:arrow_right: Método principal da interface UserDetailService:
```Java
UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
```
- O Spring chama esse método passando o parâmetro (nome de usuário, podendo ser email, nick, etc);
- O método deve retorna um objeto que implementa a interface `UserDetails`.

> **UserDetails**
> É outra interface que representa um usuário autenticado.
> 
> Os métodos dessa interface são:
> - `getUsername()`: String
> - `getPassword()`: String
> - `getAuthorities()`: Collection < GrantedAuthority* >
> - `isAccountNonExpired()`: boolean
> - `isAccountNonLocked()`: boolean
> - `isCredentialsNonExpired()`: boolean
> - `isEnabled()`: boolean

> ***GrantedAuthority** é uma interface que vai representar um "papel" ou autorização que um usuário possui.
> ```Java
> public interface GrantedAuthority {
>    String getAuthority();
> }
> ```