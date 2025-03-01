package sv.edu.udb.desafio1dsm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

lateinit var btnCalculoSalario: Button
lateinit var btnCalculoPromedio: Button
lateinit var btnCalculadora: Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar Botones
        btnCalculoSalario = findViewById(R.id.btnCalculoSalario)
        btnCalculoPromedio = findViewById(R.id.btnCalculoPromedio)
        btnCalculadora = findViewById(R.id.btnCalculadora)

        //Indicando acciones para Botones
        btnCalculoSalario.setOnClickListener {
            val intent = Intent(this, CalculoSalario::class.java)
            startActivity(intent)
        }

        btnCalculoPromedio.setOnClickListener {
            val intent = Intent(this, CalculoPromedio::class.java)
            startActivity(intent)
        }

        btnCalculadora.setOnClickListener {
            val intent = Intent(this, Calculadora::class.java)
            startActivity(intent)
        }
    }
}