package leite.sampaio.lucas.currencyconverterapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.gson.JsonObject
import leite.sampaio.lucas.currencyconverterapplication.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCurrencies()

        binding.btConvert.setOnClickListener {
            convertCurrency()
        }
    }

    private fun getCurrencies() {

        val retrofitClient = RetrofitClient.getInstance("https://cdn.jsdelivr.net/")

        val service = retrofitClient.create(CurrencyApiService::class.java)

        service.getCurrencies().enqueue(object : Callback<JsonObject> {

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                if (!response.isSuccessful) {
                    showToast("Erro HTTP: ${response.code()}")
                    return
                }

                val body = response.body()

                if (body == null) {
                    showToast("Sem resposta da API")
                    return
                }

                val data = body.keySet().toList()

                val adapter = ArrayAdapter(this@MainActivity,
                    android.R.layout.simple_spinner_item, data)

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                binding.spFrom.adapter = adapter
                binding.spTo.adapter = adapter

                val brlPosition = data.indexOf("brl")
                val usdPosition = data.indexOf("usd")

                if (brlPosition >= 0) {
                    binding.spFrom.setSelection(brlPosition)
                }

                if (usdPosition >= 0) {
                    binding.spTo.setSelection(usdPosition)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                showToast("Erro: ${t.message}")
            }
        })
    }
    private fun convertCurrency() {

        val from = binding.spFrom.selectedItem.toString()
        val to = binding.spTo.selectedItem.toString()

        val value = binding.etCashValue.text.toString().toDoubleOrNull()

        if (value == null) {
            showToast("Informe um valor válido")
            return
        }

        val retrofitClient = RetrofitClient.getInstance("https://cdn.jsdelivr.net/")

        val service = retrofitClient.create(CurrencyApiService::class.java)

        service.getCurrencyRate(from).enqueue(object : Callback<JsonObject> {

                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                    if (!response.isSuccessful) {
                        showToast("Erro HTTP: ${response.code()}")
                        return
                    }

                    val body = response.body()

                    if (body == null) {
                        showToast("Resposta vazia da API")
                        return
                    }

                    val currencyData = body.getAsJsonObject(from)

                    if (currencyData == null) {
                        showToast("Moeda de origem não encontrada")
                        return
                    }

                    val rate = currencyData.get(to)?.asDouble

                    if (rate == null) {
                        showToast("Taxa de conversão não encontrada")
                        return
                    }

                    val conversion = value * rate

                    val result = "${to.uppercase()} $conversion"

                    binding.tvResult.text = result
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    showToast("Erro: ${t.message}")
                }
            }
        )
    }

    fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}
