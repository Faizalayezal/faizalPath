package com.example.faizalipathtask

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.faizalipathtask.helper.LocationEditText
import com.example.faizalipathtask.viewmodel.MapViewModel
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import kotlinx.coroutines.launch


@Composable
fun FirstScreen(
    viewModel: MapViewModel,
    showLoading: (Boolean) -> Unit,

    ) {
    val context = LocalContext.current
    val scope= rememberCoroutineScope()
    val intent =
        Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, viewModel.field).build(context)

    val address = remember {
        mutableStateOf("")
    }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            if (it.resultCode == RESULT_OK) {
                val data: Intent? = it.data

                val place = Autocomplete.getPlaceFromIntent(data)
                address.value = place.name
                scope.launch {
                    viewModel.sendEmailOtp(
                        place.latLng?.latitude.toString(),
                        place.latLng?.longitude.toString()
                    ) {
                        showLoading.invoke(true)

                    }

                }


            }

        }
    val items = viewModel.locationStateFlow.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 20.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {


        LocationEditText(
            "Enter Location",
            onDataChanged = { it ->
                address.value = it
            },
            onclick = {
                launcher.launch(intent)
            },
            address.value,

            )

        Spacer(modifier = Modifier.height(10.dp))
        if (items != null) {
            showLoading.invoke(false)

            Column() {

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.LightGray,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(10.dp)
                ) {


                    Row {
                        Box {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = "temperature_2m: ${items.current.temperature_2m} ${items.current_units.temperature_2m}",
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Center,
                                    color = Color.Black,
                                )
                                Spacer(modifier = Modifier.height(15.dp))

                                Text(
                                    text = "wind_speed_10m: ${items.current.wind_speed_10m} ${items.current_units.wind_speed_10m}",
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Center,
                                    color = Color.Black,
                                )

                            }

                        }

                        Spacer(modifier = Modifier.width(5.dp))

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .background(Color.Gray)
                                .fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = if (items.current.temperature_2m >= 25) R.drawable.sun else R.drawable.cloudy),
                                contentDescription = "", modifier = Modifier
                                    .size(40.dp)


                            )
                        }


                    }


                }


            }
        }


    }

}