package leite.sampaio.lucas.currencyconverterapplication

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyApiService {
    @GET("npm/@fawazahmed0/currency-api@latest/v1/currencies.json")
    fun getCurrencies() : Call<JsonObject>

    @GET("npm/@fawazahmed0/currency-api@latest/v1/currencies/{from}.json")
    fun getCurrencyRate(
        @Path(value = "from", encoded = true) from : String
    ) : Call<JsonObject>
}


