# ProjectOne

Este é um aplicativo de exemplo desenvolvido por dois programadores mobile que utiliza as seguintes bibliotecas do Android:

### Navigation: <br>
Biblioteca de navegação que ajuda a gerenciar a navegação em aplicativos do Android. <br>
### Retrofit: <br> 
Biblioteca para fazer chamadas de rede de forma fácil e elegante.
### Coroutines: <br>
Biblioteca para lidar com tarefas assíncronas de forma mais simples e concisa.
### Room:<br>
Biblioteca para armazenamento de dados no SQLite com menos código boilerplate.

# Arquitetura

Este aplicativo utiliza a arquitetura MVVM (Model-View-ViewModel), que é uma arquitetura recomendada pelo Google para desenvolvimento de aplicativos Android. Esta arquitetura separa as responsabilidades de cada componente em três camadas:

### Model:<br> 
É responsável por armazenar os dados e fornecer acesso a eles. Neste aplicativo, o modelo é implementado usando a biblioteca Room.
### View: <br> 
É responsável pela interface do usuário. Neste aplicativo, as visualizações são definidas em arquivos XML.
### ViewModel: <br> 
É responsável por intermediar a comunicação entre a View e o Model. Neste aplicativo, a ViewModel é implementada usando a biblioteca ViewModel.
Além disso, este aplicativo utiliza a biblioteca Coroutines para lidar com tarefas assíncronas de forma mais simples e concisa.
