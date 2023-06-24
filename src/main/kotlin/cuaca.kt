import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

fun main() {
    val apiKey = "7a273834e66afff27b7f1e12a908792c"
    val city = "Jakarta"

    val client = OkHttpClient()

    val request = Request.Builder()
        .url("http://api.openweathermap.org/data/2.5/forecast?q=$city&appid=$apiKey&units=metric")
        .build()

    val response = client.newCall(request).execute()
    val jsonData = response.body?.string()

    val json = JSONObject(jsonData)
    val forecastList = json.getJSONArray("list")

    val dateFormat = SimpleDateFormat("EEE, dd MMM yyyy", Locale.ENGLISH)

    val weatherMap: MutableMap<String, Double> = mutableMapOf()

    for (i in 0 until forecastList.length()) {
        val forecast = forecastList.getJSONObject(i)
        val dateTimeStamp = forecast.getLong("dt") * 1000
        val dateTime = Date(dateTimeStamp)
        val date = dateFormat.format(dateTime)
        val temperature = forecast.getJSONObject("main").getDouble("temp")


        if (!weatherMap.containsKey(date)) {
            weatherMap[date] = temperature
        }
    }

    println("Weather Forecast:")
    for ((date, temperature) in weatherMap) {
        println("$date: $temperature°C")
        println()
    }
}
