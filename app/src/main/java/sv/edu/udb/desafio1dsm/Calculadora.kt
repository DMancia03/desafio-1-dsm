package sv.edu.udb.desafio1dsm

import android.os.Bundle
import android.content.Intent
import android.widget.*
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow
import kotlin.math.sqrt


class Calculadora : AppCompatActivity(){

    lateinit var operand1EditText: EditText
    lateinit var operand2EditText: EditText
    lateinit var resultTextView: TextView
    lateinit var btnSuma: Button
    lateinit var btnResta: Button
    lateinit var btnMultiplicacion: Button
    lateinit var btnDivision: Button
    lateinit var btnExponente: Button
    lateinit var btnRaizCuadrada: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.calculadora)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.calculadora)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnPrincipal = findViewById<Button>(R.id.btnPrincipal)

        btnPrincipal.setOnClickListener {
            // Crear un Intent para regresar a la MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        operand1EditText = findViewById(R.id.operando1)
        operand2EditText = findViewById(R.id.operando2)
        resultTextView = findViewById(R.id.resultTextView)

        btnSuma = findViewById(R.id.btnSuma)
        btnResta = findViewById(R.id.btnResta)
        btnMultiplicacion = findViewById(R.id.btnMultiplicacion)
        btnDivision = findViewById(R.id.btnDivision)
        btnExponente = findViewById(R.id.btnExponente)
        btnRaizCuadrada = findViewById(R.id.btnRaizCuadrada)

        // Configuracion de los botones para realizar las operaciones
        btnSuma.setOnClickListener { calcular("Suma") }
        btnResta.setOnClickListener { calcular("Resta") }
        btnMultiplicacion.setOnClickListener { calcular("Multiplicación") }
        btnDivision.setOnClickListener { calcular("División") }
        btnExponente.setOnClickListener { calcular("Exponente") }
        btnRaizCuadrada.setOnClickListener { calcular("Raíz Cuadrada") }
    }

    private fun calcular(operacion: String) {
        val operando1 = operand1EditText.text.toString()
        val operando2 = operand2EditText.text.toString()

        // Validación de los operandos
        if (operando1.isEmpty()) {
            Toast.makeText(this, "Debe ingresar el primer operando", Toast.LENGTH_SHORT).show()
            return
        }

        if (operando2.isEmpty() && operacion != "Raíz Cuadrada" ) {
            Toast.makeText(this, "Debe ingresar el segundo operando", Toast.LENGTH_SHORT).show()
            return
        }

        val num1 = operando1.toDouble()
        val num2 = if (operando2.isNotEmpty()) operando2.toDouble() else 0.0

        // Realizar la operación seleccionada
        val resultado = when (operacion) {
            "Suma" -> num1 + num2
            "Resta" -> num1 - num2
            "Multiplicación" -> num1 * num2
            "División" -> if (num2 != 0.0) num1 / num2 else {
                Toast.makeText(this, "No se puede dividir entre 0", Toast.LENGTH_SHORT).show()
                return
            }
            "Exponente" -> num1.pow(num2)
            "Raíz Cuadrada" -> sqrt(num1)
            else -> 0.0
        }

        // Mostrar el resultado
        resultTextView.text = "Resultado: %.2f".format(resultado)
        operand1EditText.setText("")
        operand2EditText.setText("")

    }
}
