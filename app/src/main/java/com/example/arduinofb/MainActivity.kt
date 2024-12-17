package com.example.arduinofb

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    // Referencia a Firebase Realtime Database
    private lateinit var database: DatabaseReference

    // TextView para mostrar la acción del portón
    private lateinit var accionText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar el TextView
        accionText = findViewById(R.id.accionText) // Asegúrate de tener un TextView con este id en activity_main.xml

        // Inicializar Firebase Realtime Database
        database = FirebaseDatabase.getInstance().getReference("datos/Porton")

        // Escuchar cambios en tiempo real
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Verificar si el nodo tiene el campo "Accion"
                val accion = snapshot.child("Accion").getValue(String::class.java)
                if (accion != null) {
                    accionText.text = accion // Mostrar la acción en el TextView
                } else {
                    accionText.text = "Sin datos disponibles"
                    Log.w("FirebaseData", "El nodo 'Accion' está vacío o no existe.")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@MainActivity,
                    "Error al obtener datos: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("FirebaseError", "Error al leer datos: ${error.message}")
            }
        })
    }
}
