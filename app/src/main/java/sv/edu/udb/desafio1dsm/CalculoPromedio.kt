package sv.edu.udb.desafio1dsm

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CalculoPromedio : AppCompatActivity() {
    private lateinit var txtNombre : EditText
    private lateinit var txtNota1 : EditText
    private lateinit var txtNota2 : EditText
    private lateinit var txtNota3 : EditText
    private lateinit var txtNota4 : EditText
    private lateinit var txtNota5 : EditText
    private lateinit var txtPromedio : EditText
    private lateinit var txtNotaFinal : EditText
    private lateinit var txtEstApro : EditText

    private lateinit var btnCalcular : Button
    private lateinit var btnBorrar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.calculo_promedio)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.calculo_promedio)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Boton de menu principal
        val btnPrincipal = findViewById<Button>(R.id.btnPrincipal)

        btnPrincipal.setOnClickListener {
            // Crear un Intent para regresar a la MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Inicializamos los componentes
        txtNombre = findViewById(R.id.txtNombre)
        txtNota1 = findViewById(R.id.txtNota1)
        txtNota2 = findViewById(R.id.txtNota2)
        txtNota3 = findViewById(R.id.txtNota3)
        txtNota4 = findViewById(R.id.txtNota4)
        txtNota5 = findViewById(R.id.txtNota5)
        txtPromedio = findViewById(R.id.txtPromedio)
        txtNotaFinal = findViewById(R.id.txtNotaFinal)
        txtEstApro = findViewById(R.id.txtEstApro)

        btnCalcular = findViewById(R.id.btnCalcular)
        btnBorrar = findViewById(R.id.btnBorrar)

        btnCalcular.setOnClickListener { Calcular() }
        btnBorrar.setOnClickListener { Borrar() }
    }

    fun Calcular(){
        val nombre = txtNombre.text.toString()
        val stringNota1 = txtNota1.text.toString()
        val stringNota2 = txtNota2.text.toString()
        val stringNota3 = txtNota3.text.toString()
        val stringNota4 = txtNota4.text.toString()
        val stringNota5 = txtNota5.text.toString()

        val notasString : Array<String> = arrayOf(stringNota1, stringNota2, stringNota3, stringNota4, stringNota5)

        var notas : Array<Double> = arrayOf(0.0, 0.0, 0.0, 0.0, 0.0)
        var notasPonderadas : Array<Double> = arrayOf(0.0, 0.0, 0.0, 0.0, 0.0)

        val notasPorcentaje : Array<Double> = arrayOf(0.15, 0.15, 0.2, 0.25, 0.25)

        val notaMaxima : Double = 10.0
        val notaMinima : Double = 0.0
        val notaMinimaAprobar : Double = 6.0

        if(nombre.isEmpty() || nombre.isBlank()){
            Toast.makeText(this, "Debes ingresar tu nombre", Toast.LENGTH_SHORT).show()
            return
        }


        for(index in notasString.indices){
            val nota : String = notasString[index]

            if(nota.isEmpty() || nota.isBlank()){ // No ha ingresado nota
                Toast.makeText(this, "Debes ingresar tu nota ${(index+1).toString()}", Toast.LENGTH_SHORT).show()
                return
            }
            else if(nota.toDouble() < notaMinima || nota.toDouble() > notaMaxima){ // La nota esta fuera de los limites establecidos
                Toast.makeText(this, "Debes ingresar una nota ${(index+1).toString()} con valor entre 0 y 10", Toast.LENGTH_SHORT).show()
                return
            }
            else{ // Guardar nota valida
                notas[index] = nota.toDouble()
                notasPonderadas[index] = nota.toDouble() * notasPorcentaje[index]
            }
        }

        txtPromedio.setText(notas.average().toString())
        txtNotaFinal.setText(notasPonderadas.sum().toString())
        txtEstApro.setText( if (notasPonderadas.sum() >= notaMinimaAprobar) "¡Felicidades! ${nombre} aprobaste el ciclo" else "Lo lamento ${nombre} reprobaste el ciclo" )
    }

    fun  Borrar(){
        txtNombre.setText("")
        txtNota1.setText("")
        txtNota2.setText("")
        txtNota3.setText("")
        txtNota4.setText("")
        txtNota5.setText("")
        txtPromedio.setText("")
        txtNotaFinal.setText("")
        txtEstApro.setText("")
    }
}