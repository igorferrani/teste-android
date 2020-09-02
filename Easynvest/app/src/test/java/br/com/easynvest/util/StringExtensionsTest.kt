package br.com.easynvest.util

import org.junit.Assert
import org.junit.Test
import java.math.BigDecimal

class StringExtensionsTest {
    @Test
    fun `convertDate - ao chamar o metodo em formato americano, deve retornar a data no formarto brasileiro`() {
        val value = "2019-01-01"
        Assert.assertEquals(
            value.convertDate(AppConstants.AMERICAN_DATE_FORMAT, AppConstants.BRAZILIAN_DATE_FORMAT),
            "01/01/2019"
        )
    }

    @Test
    fun `convertDate - ao chamar o metodo passando data em formato brasileiro, deve retornar a data no formarto americano`() {
        val value = "01/01/2019"
        Assert.assertEquals(
            value.convertDate(AppConstants.BRAZILIAN_DATE_FORMAT, AppConstants.AMERICAN_DATE_FORMAT),
            "2019-01-01"
        )
    }

    @Test
    fun `convertDate - ao chamar o metodo passando valor vazio, deve retornar vazio`() {
        val value = ""
        Assert.assertEquals(
            value.convertDate(AppConstants.BRAZILIAN_DATE_FORMAT, AppConstants.AMERICAN_DATE_FORMAT),
            ""
        )
    }

    @Test
    fun `convertMonetaryToBigDecimal - ao chamar o metodo passando valor vazio, deve retornar BigDecimal zero`() {
        val value = ""
        Assert.assertEquals(
            value.convertMonetaryToBigDecimal(),
            BigDecimal(0)
        )
    }

    @Test
    fun `convertMonetaryToBigDecimal - ao chamar o metodo passando valor R$ 100,00, deve retornar BigDecimal 100 com 2 casas decimais`() {
        val value = "R$ 100,00"
        Assert.assertEquals(
            value.convertMonetaryToBigDecimal(),
            BigDecimal(100.00).setScale(
                2, BigDecimal.ROUND_FLOOR
            )
        )
    }

    @Test
    fun `convertMonetaryToBigDecimal - ao chamar o metodo passando valor 100, deve retornar BigDecimal 1 com 2 casas decimais`() {
        val value = "100"
        Assert.assertEquals(
            value.convertMonetaryToBigDecimal(),
            BigDecimal(1.00).setScale(
                2, BigDecimal.ROUND_FLOOR
            )
        )
    }

    @Test
    fun `convertToMoney - ao chamar o metodo passando valor 12 ponto 32, deve retornar R$ 12,32`() {
        val value = 12.32
        Assert.assertEquals(
            value.convertToMoney(),
            "R$ 12,32"
        )
    }

    @Test
    fun `convertToMoney - ao chamar o metodo passando valor 0, deve retornar R$ 0,00`() {
        val value = 0.0
        Assert.assertEquals(
            value.convertToMoney(),
            "R$ 0,00"
        )
    }

    @Test
    fun `convertToPercent - ao chamar o metodo passando valor 12, deve retornar 12%`() {
        val value = 12.0
        Assert.assertEquals(
            value.convertToPercent(),
            "12%"
        )
    }

    @Test
    fun `convertToPercent - ao chamar o metodo passando valor 123456, deve retornar 123 ponto 456%`() {
        val value = 123456.0
        Assert.assertEquals(
            value.convertToPercent(),
            "123.456%"
        )
    }

    @Test
    fun `convertToPercent - ao chamar o metodo passando valor 123456789 ponto 13, deve retornar 123 ponto 456 ponto 789,13%`() {
        val value = 123456789.13
        Assert.assertEquals(
            value.convertToPercent(),
            "123.456.789,13%"
        )
    }

    @Test
    fun `convertToPercent - ao chamar o metodo passando valor 0, deve retornar 0%`() {
        val value = 0.0
        Assert.assertEquals(
            value.convertToPercent(),
            "0%"
        )
    }

    @Test
    fun `removeSpecialCharacter - ao chamar o metodo passando valor R$ 123,98, deve retornar 12398`() {
        val value = "R$ 123,98"
        Assert.assertEquals(
            value.removeSpecialCharacterInNumber(),
            "12398"
        )
    }
}
