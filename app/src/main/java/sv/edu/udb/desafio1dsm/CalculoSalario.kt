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
import sv.edu.udb.desafio1dsm.utils.SalarioUtils
import java.math.BigDecimal

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
        btnBorrar.setOnClickListener { Borrar() }
    }

    fun Calcular(){
        val nombre : String = txtNombre.text.toString()
        val stringSalarioBase : String = txtSalarioBase.text.toString()

        if(nombre.isEmpty() || nombre.isBlank()) {
            Toast.makeText(this, "Debes ingresar tu nombre", Toast.LENGTH_SHORT).show()
            return
        }

        if(stringSalarioBase.isEmpty() || stringSalarioBase.isBlank()) {
            Toast.makeText(this, "Debes ingresar tu salario base", Toast.LENGTH_SHORT).show()
            return
        }

        if (stringSalarioBase.toDouble() < 0){
            Toast.makeText(this, "Debe ingresar un salario con valor mayor a 0", Toast.LENGTH_SHORT).show()
            return
        }

        // Salario base
        val salarioBase : BigDecimal = stringSalarioBase.toBigDecimalOrNull() ?: 0.0.toBigDecimal()

        // Descuentos
        val afp : BigDecimal = SalarioUtils.CalcularDescuentoAfp(salarioBase)
        val isss : BigDecimal = SalarioUtils.CalcularDescuentoIsss(salarioBase)
        val renta : BigDecimal = SalarioUtils.CalcularDescuentoRenta(salarioBase, afp, isss)

        // Salario neto
        val salarioNeto : BigDecimal = SalarioUtils.CalcularSalarioNeto(salarioBase, afp, isss, renta)

        txtRenta.setText("$ " + renta.toString())
        txtAFP.setText("$ " + afp.toString())
        txtISSS.setText("$ " + isss.toString())
        txtSalarioNeto.setText("$ " + salarioNeto.toString())
    }

    fun Borrar(){
        txtNombre.setText("")
        txtSalarioBase.setText("")
        txtRenta.setText("")
        txtAFP.setText("")
        txtISSS.setText("")
        txtSalarioNeto.setText("")
    }
}