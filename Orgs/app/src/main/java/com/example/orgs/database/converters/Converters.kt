package com.example.orgs.database.converters

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {

    @TypeConverter
    fun deDoubleParaBigDecimal(valor: Double?): BigDecimal {
        return valor?.let {
            BigDecimal(valor.toString())
        } ?: BigDecimal.ZERO
    }

    @TypeConverter
    fun deBigDecimalParaDouble(valor: BigDecimal?): Double?{
        return valor?.let {
            valor.toDouble()
        }
    }
}