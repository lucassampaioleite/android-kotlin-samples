package leite.sampaio.lucas.currencyconverterwithretrofit

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.gson.JsonObject
import leite.sampaio.lucas.currencyconverterwithretrofit.api.Endpoint
import leite.sampaio.lucas.currencyconverterwithretrofit.databinding.ActivityMainBinding
import leite.sampaio.lucas.currencyconverterwithretrofit.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Response

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCurrencies()

        binding.btnConvert.setOnClickListener {
            convertCurrency()
        }
    }

    private fun getCurrencies() {
        val retrofitClient = NetworkUtils.getRetrofitInstance(
            "https://cdn.jsdelivr.net/")
        val endpoint = retrofitClient.create(Endpoint::class.java)

        endpoint.getCurrencies().enqueue(object: retrofit2.Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val data = mutableListOf<String>()
                response.body()?.keySet()?.iterator()?.forEach {
                    data.add(it)
                }
                val adapter = ArrayAdapter(baseContext,
                    android.R.layout.simple_spinner_dropdown_item, data)
                binding.spnFrom.adapter = adapter
                binding.spnTo.adapter = adapter
                binding.spnFrom.setSelection(data.indexOf("brl"))
                binding.spnTo.setSelection(data.indexOf("usd"))
            }
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                showToast("error ${t.message}")
            }
        })
    }

    private fun convertCurrency(){
        val retrofitClient = NetworkUtils.getRetrofitInstance(
            "https://cdn.jsdelivr.net/")
        val endpoint = retrofitClient.create(Endpoint::class.java)

        endpoint.getCurrencyRate(binding.spnFrom.selectedItem.toString()).enqueue(object :
            retrofit2.Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val data = response.body()?.getAsJsonObject(binding.spnFrom.selectedItem.toString())
                val rate = data?.get(binding.spnTo.selectedItem.toString())?.asDouble
                val conversion = binding.edtCashValue.text.toString().toDouble() * rate!!
                val result = "${binding.spnTo.selectedItem.toString().uppercase()} $conversion"
                binding.txtResult.text = result
            }
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                showToast("error ${t.message}")
            }
        })
    }

    fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}

