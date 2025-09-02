package devandroid.dias.appgasetanol.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import devandroid.dias.appgasetanol.controller.CombustivelController
import devandroid.dias.appgasetanol.model.Combustivel

@Composable
fun TelaPrincipal(controller: CombustivelController, modifier: Modifier = Modifier) {
    var precoGasolina by remember { mutableStateOf("") }
    var precoEtanol by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    var lista by remember { mutableStateOf(controller.listar()) }

    Column(modifier = modifier.padding(16.dp)) {
        // Título
        Text(
            text = "Gasolina ou Etanol?",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            color = MaterialTheme.colorScheme.primary
        )

        OutlinedTextField(
            value = precoGasolina,
            onValueChange = { precoGasolina = it },
            label = { Text("Preço da Gasolina") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = precoEtanol,
            onValueChange = { precoEtanol = it },
            label = { Text("Preço do Etanol") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {
                val gasolina = precoGasolina.toDoubleOrNull()
                val etanol = precoEtanol.toDoubleOrNull()

                if (gasolina != null && etanol != null) {
                    resultado = "Compensa usar: " + controller.calcularCombustivel(gasolina, etanol)
                    controller.salvar(gasolina, etanol)
                    lista = controller.listar()
                } else {
                    resultado = "Digite os dois valores!"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular")
        }

        Spacer(Modifier.height(8.dp))

        Text(text = resultado, style = MaterialTheme.typography.bodyLarge)

        Spacer(Modifier.height(16.dp))

        Text("Histórico", style = MaterialTheme.typography.titleMedium)

        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(lista) { item ->
                CombustivelItem(
                    item = item,
                    onClick = {
                        precoGasolina = it.precoGasolina.toString()
                        precoEtanol = it.precoEtanol.toString()
                        resultado = "Carregado: ${it.resultado}"
                    },
                    onRemove = {
                        controller.excluir(item)
                        lista = controller.listar()
                    }
                )
            }
        }

    }
}

@Composable
fun CombustivelItem(
    item: Combustivel,
    onClick: (Combustivel) -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        onClick = { onClick(item) }
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("${item.data} - ${item.resultado}")
            Text("Gasolina: ${item.precoGasolina} | Etanol: ${item.precoEtanol}")

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { onRemove() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Remover Histórico")
            }
        }
    }
}



