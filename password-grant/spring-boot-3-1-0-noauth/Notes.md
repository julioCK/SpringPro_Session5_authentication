## Controle de acesso
A implementação do controle de acesso nessa aplicação será feita por ***perfis de usuário*** (roles).

Para isso, primeiramente é necessário implementar um modelo de dados para armazenar esses perfis de usuário. A
imagem abaixo ilustra esse modelo:

![modelo de dados](user-role.png)
Cada usuário pode ter vários perfis de acesso, e cada perfil pode ser de vários usuários.
