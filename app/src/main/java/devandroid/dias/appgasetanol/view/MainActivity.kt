package devandroid.dias.appgasetanol.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import devandroid.dias.appgasetanol.ui.theme.AppGasEtanolTheme
import devandroid.dias.appgasetanol.controller.CombustivelController

class MainActivity : ComponentActivity() {

    private lateinit var controller: CombustivelController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller = CombustivelController(this)

        enableEdgeToEdge()
        setContent {
            AppGasEtanolTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TelaPrincipal(
                        controller = controller,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
