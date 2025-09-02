package devandroid.dias.appgasetanol.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import devandroid.dias.appgasetanol.model.Combustivel

class DBHelper(context: Context) : SQLiteOpenHelper(context, "combustivel.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE combustivel (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                precoGasolina REAL,
                precoEtanol REAL,
                resultado TEXT,
                data TEXT
            )
        """
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS combustivel")
        onCreate(db)
    }

    fun inserir(item: Combustivel) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("precoGasolina", item.precoGasolina)
            put("precoEtanol", item.precoEtanol)
            put("resultado", item.resultado)
            put("data", item.data)
        }
        db.insert("combustivel", null, values)
    }

    fun deletar(id: Int) {
        val db = writableDatabase
        db.delete("combustivel", "id = ?", arrayOf(id.toString()))
        db.close()
    }


    fun listar(): List<Combustivel> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM combustivel ORDER BY id DESC", null)
        val lista = mutableListOf<Combustivel>()

        if (cursor.moveToFirst()) {
            do {
                val item = Combustivel(
                    id = cursor.getInt(0),
                    precoGasolina = cursor.getDouble(1),
                    precoEtanol = cursor.getDouble(2),
                    resultado = cursor.getString(3),
                    data = cursor.getString(4)
                )
                lista.add(item)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return lista
    }

    fun buscarPorId(id: Int): Combustivel? {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM combustivel WHERE id = ?", arrayOf(id.toString()))
        var item: Combustivel? = null
        if (cursor.moveToFirst()) {
            item = Combustivel(
                id = cursor.getInt(0),
                precoGasolina = cursor.getDouble(1),
                precoEtanol = cursor.getDouble(2),
                resultado = cursor.getString(3),
                data = cursor.getString(4)
            )
        }
        cursor.close()
        return item
    }
}
