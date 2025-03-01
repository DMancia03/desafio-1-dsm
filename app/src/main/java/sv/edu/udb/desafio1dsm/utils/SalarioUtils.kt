package sv.edu.udb.desafio1dsm.utils

import java.math.BigDecimal
import java.math.RoundingMode

class SalarioUtils {
    companion object {
        // ------------------- AFP ----------------------------------------------------------
        fun CalcularDescuentoAfp(salarioBase: BigDecimal) : BigDecimal{
            return (salarioBase * 0.0725.toBigDecimal()).setScale(2, RoundingMode.CEILING)
        }

        // ------------------- ISSS ---------------------------------------------------------
        fun CalcularDescuentoIsss(salarioBase: BigDecimal) : BigDecimal{
            return (salarioBase * 0.03.toBigDecimal()).setScale(2, RoundingMode.CEILING)
        }

        // ------------------- RENTA --------------------------------------------------------
        // Rangos para tramos
        val rangoMinTramo1 : BigDecimal = 0.01.toBigDecimal()
        val rangoMaxTramo1 : BigDecimal = 472.0.toBigDecimal()

        val rangoMinTramo2 : BigDecimal = 472.01.toBigDecimal()
        val rangoMaxTramo2 : BigDecimal = 895.24.toBigDecimal()

        val rangoMinTramo3 : BigDecimal = 895.25.toBigDecimal()
        val rangoMaxTramo3 : BigDecimal = 2038.10.toBigDecimal()

        val rangoMinTramo4 : BigDecimal = 2038.11.toBigDecimal()

        // Obtencion de porcentaje a aplicar y cuota fija segun tabla regulada
        fun ObtenerRentaPorcentaje(salarioBase : BigDecimal) : BigDecimal{
            if(salarioBase >= rangoMinTramo1 && salarioBase <= rangoMaxTramo1){
                return 0.0.toBigDecimal()
            }
            else if(salarioBase >= rangoMinTramo2 && salarioBase <= rangoMaxTramo2){
                return 0.1.toBigDecimal()
            }
            else if(salarioBase >= rangoMinTramo3 && salarioBase <= rangoMaxTramo3){
                return 0.2.toBigDecimal()
            }
            else{
                return 0.3.toBigDecimal()
            }
        }

        fun ObtenerRentaCuota(salarioBase : BigDecimal) : BigDecimal{
            if(salarioBase >= rangoMinTramo1 && salarioBase <= rangoMaxTramo1){
                return 0.0.toBigDecimal()
            }
            else if(salarioBase >= rangoMinTramo2 && salarioBase <= rangoMaxTramo2){
                return 17.67.toBigDecimal()
            }
            else if(salarioBase >= rangoMinTramo3 && salarioBase <= rangoMaxTramo3){
                return 60.0.toBigDecimal()
            }
            else{
                return 288.57.toBigDecimal()
            }
        }

        fun ObtenerRangoExceso(salarioBase : BigDecimal) : BigDecimal{
            if(salarioBase >= rangoMinTramo1 && salarioBase <= rangoMaxTramo1){
                return 0.0.toBigDecimal()
            }
            else if(salarioBase >= rangoMinTramo2 && salarioBase <= rangoMaxTramo2){
                return rangoMaxTramo1
            }
            else if(salarioBase >= rangoMinTramo3 && salarioBase <= rangoMaxTramo3){
                return rangoMaxTramo2
            }
            else{
                return rangoMaxTramo3
            }
        }

        // Calculo del descuento de renta
        fun CalcularDescuentoRenta(salarioBase: BigDecimal, descuentoAfp : BigDecimal, descuentoIsss : BigDecimal) : BigDecimal{
            var neto = (salarioBase - descuentoAfp - descuentoIsss - ObtenerRangoExceso(salarioBase)) * ObtenerRentaPorcentaje(salarioBase) + ObtenerRentaCuota(salarioBase)
            return neto.setScale(2, RoundingMode.CEILING)
        }

        // ------------------- NETO ---------------------------------------------------------
        fun CalcularSalarioNeto(salarioBase: BigDecimal, descuentoAfp: BigDecimal, descuentoIsss: BigDecimal, descuentoRenta: BigDecimal) : BigDecimal{
            return (salarioBase - (descuentoRenta + descuentoAfp + descuentoIsss)).setScale(2, RoundingMode.CEILING)
        }
    }
}