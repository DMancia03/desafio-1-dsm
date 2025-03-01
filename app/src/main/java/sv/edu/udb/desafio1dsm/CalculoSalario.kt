package sv.edu.udb.desafio1dsm

import android.os.Bundle
import android.text.Editable
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CalculoSalario : AppCompatActivity() {
    private lateinit var txtNombre : EditText
    private lateinit var txtSalarioBase : EditText
    private lateinit var txtRenta : EditText
    private lateinit var txtAFP : EditText
    private lateinit var txtISSS : EditText
    private lateinit var txtSalarioNeto : EditText

    private lateinit var btnCalcular : Button
    private lateinit var btnBorrar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.calculo_salario)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.calculo_salario)) { v, insets ->
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

        // Inicializar
        txtNombre = findViewById(R.id.txtNombre)
        txtSalarioBase = findViewById(R.id.txtSalBase)
        txtRenta = findViewById(R.id.txtRenta)
        txtAFP = findViewById(R.id.txtAFP)
        txtISSS = findViewById(R.id.txtISSS)
        txtSalarioNeto = findViewById(R.id.txtSalarioNeto)

        btnCalcular = findViewById(R.id.btnCalcular)
        btnBorrar = findViewById(R.id.btnBorrar)

        btnCalcular.setOnClickListener { Calcular() }
        btnBorrar.setOnClickListener { borrar() }
    }

    fun Calcular(){
        val nombre : String = txtNombre.text.toString()
        val stringSalarioBase : String = txtSalarioBase.text.toString()

        if(nombre.isEmpty() || nombre.isBlank()) {
            Toast.makeText(this, "Debe ingresar su nombre...", Toast.LENGTH_SHORT).show()
            return
        }

        if(stringSalarioBase.isEmpty() || stringSalarioBase.isBlank()) {
            Toast.makeText(this, "Debe ingresar su salario base...", Toast.LENGTH_SHORT).show()
            return
        }

        var salarioBase : Double = stringSalarioBase.toDoubleOrNull() ?: 0.0
        var renta : Double = 0.0
        var afp : Double = 0.0
        var isss : Double = 0.0
        var salarioNeto : Double = 0.0

        txtRenta.setText("$ " + renta.toString())
        txtAFP.setText("$ " + afp.toString())
        txtISSS.setText("$ " + isss.toString())
        txtSalarioNeto.setText("$ " + salarioNeto.toString())
    }

    fun borrar(){
        txtNombre.setText("")
        txtSalarioBase.setText("")
        txtRenta.setText("")
        txtAFP.setText("")
        txtISSS.setText("")
        txtSalarioNeto.setText("")
    }

}