package com.example.cornavirusbrinformation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.cornavirusbrinformation.gateway.ApiCountryRestClient
import com.example.cornavirusbrinformation.model.Country
import com.example.cornavirusbrinformation.network.RetrofitEventListener
import com.facebook.shimmer.ShimmerFrameLayout
import retrofit2.Call
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity() {

    private val dateFormat = "dd/MM/yyyy"
    private val dateFormatter = DateTimeFormatter.ofPattern(dateFormat)

    private lateinit var shimmerFrameLayout: ShimmerFrameLayout;
    private lateinit var linerLayout: LinearLayout;
    private lateinit var carView: CardView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        shimmerFrameLayout = findViewById<ShimmerFrameLayout>(R.id.main_Shimmer)
        linerLayout = findViewById<LinearLayout>(R.id.linear_layout)
        carView = findViewById<CardView>(R.id.card_view)
        linerLayout.removeView(carView)

        callCountryApi(this.applicationContext)
    }

    internal fun callCountryApi(context: Context) {

        ApiCountryRestClient.instance.getCountryInformation( object : RetrofitEventListener {
            override  fun onSuccess(call: Call<*>, response: Any) {
               assignValueToTextViews(response)
            }

            override fun onError(call: Call<*>, t: Throwable) {
                shimmerFrameLayout.stopShimmer()
                Toast.makeText(context, "Erro ao baixar dados, tente novamente mais tarde", Toast.LENGTH_LONG).show();
            }
        })
    }

    private fun assignValueToTextViews(response: Any) {
        shimmerFrameLayout.stopShimmer()
        linerLayout.removeView(shimmerFrameLayout)
        linerLayout.addView(carView)
        if (response is Country) {
            Log.d("request success", "${response.countryData}"  )

            val decimalFormat = DecimalFormat("###,###,###.##")

            findViewById<TextView>(R.id.txtValPopulation)
                .text = "${decimalFormat.format(response.countryData?.population)}"

            findViewById<TextView>(R.id.txtValConfirmed)
                .text = "${decimalFormat.format(response.countryData?.latestCountryData?.confirmed)}"

            findViewById<TextView>(R.id.txtValRecoverd)
                .text = "${decimalFormat.format(response.countryData?.latestCountryData?.recovered)}"

            findViewById<TextView>(R.id.txtValCritical)
                .text = "${decimalFormat.format(response.countryData?.latestCountryData?.critical)}"

            findViewById<TextView>(R.id.txtValDeaths)
                .text = "${decimalFormat.format(response.countryData?.latestCountryData?.deaths)}"

            findViewById<TextView>(R.id.txtValLastUpdate)
                .text = "${response.countryData?.updatedAt}"

            findViewById<TextView>(R.id.txtDate)
                .text = dateFormatter.format(LocalDate.now())

        }
    }

}
