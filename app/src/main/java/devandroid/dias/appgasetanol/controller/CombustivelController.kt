package devandroid.dias.appgasetanol.controller

import android.content.Context
import devandroid.dias.appgasetanol.data.DBHelper
import devandroid.dias.appgasetanol.model.Combustivel
import java.text.SimpleDateFormat
import java.util.*

class CombustivelController(context: Context) {
    private val db = DBHelper(context)

    fun calcularCombustivel(precoGasolina: Double, precoEtanol: Double): String {
        return if (precoEtanol / precoGasolina < 0.7) "Etanol" else "Gasolina"
    }

    fun salvar(precoGasolina: Double, precoEtanol: Double) {
        val resultado = calcularCombustivel(precoGasolina, precoEtanol)
        val dataAtual = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
        val item = Combustivel(
            precoGasolina = precoGasolina,
            precoEtanol = precoEtanol,
            resultado = resultado,
            data = dataAtual
        )
        db.inserir(item)
    }

    fun excluir(item: Combustivel) {
        db.deletar(item.id)
    }


    fun listar(): List<Combustivel> = db.listar()

    fun buscarPorId(id: Int): Combustivel? = db.buscarPorId(id)
}
