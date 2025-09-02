# ⛽ Gasolina ou Etanol?

Aplicativo Android desenvolvido em **Kotlin** com **Jetpack Compose** que ajuda o usuário a decidir se é mais vantajoso abastecer com **Gasolina** ou **Etanol**, com base nos preços informados.

## 📱 Funcionalidades

- Entrada de preços da **Gasolina** e do **Etanol**
- Cálculo automático da melhor opção de combustível (regra dos 70%)
- Histórico de cálculos armazenados em **SQLite**
- Listagem de todos os registros salvos
- Possibilidade de **recarregar os valores** de um item do histórico
- Opção de **remover registros** do histórico

## 🏗️ Arquitetura

O projeto segue o padrão **MVC** com a seguinte estrutura:

```
app/
 ├─ model/        # Classe de dados (Combustivel)
 ├─ view/         # Telas com Jetpack Compose (MainActivity, etc.)
 ├─ controller/   # Lógica de negócio (CombustivelController)
 └─ data/         # Classe DBHelper para SQLite
```

## 🛠️ Tecnologias utilizadas

- [Kotlin](https://kotlinlang.org/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [SQLite](https://developer.android.com/training/data-storage/sqlite)
- Padrão arquitetural **MVC**

## 🚀 Como executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/AppGasEtanol.git
   ```
2. Abra o projeto no **Android Studio**.
3. Sincronize as dependências do Gradle.
4. Rode o aplicativo em um emulador ou dispositivo físico Android.

## 📸 Demonstração

*(adicione aqui prints da tela do app depois que rodar no emulador!)*

## 📄 Licença

Este projeto está sob a licença **MIT** - veja o arquivo [LICENSE](LICENSE) para mais detalhes.
