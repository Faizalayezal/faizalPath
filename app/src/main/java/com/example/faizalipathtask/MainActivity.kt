package com.example.faizalipathtask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.faizalipathtask.ui.theme.FaizalIPathTaskTheme
import com.example.faizalipathtask.viewmodel.MapViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.faizalipathtask.ProgressDesign as ProgressDesign

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FaizalIPathTaskTheme {
                setContent {
                    App(this@MainActivity)

                }

            }
        }
    }
}

@Composable
fun App(mainActivity: MainActivity) {
    val navController= rememberNavController()
     val _isLoading = MutableStateFlow(false)
     val isLoading = _isLoading.asStateFlow()
    val progressState = isLoading.collectAsState().value

    val itemViewModel: MapViewModel = viewModel()

    NavHost(navController =navController , startDestination ="screen1" ){
        composable(route="screen1"){
            FirstScreen(
                itemViewModel,
            ){

                _isLoading.value = it
            }
            if (progressState) {
                ProgressDesign()
            }

        }



    }



}



@Preview
@Composable
private fun ProgressDesign() {
    Box(modifier = Modifier
        .clickable {}
        .focusable(true)
        .fillMaxSize()
        .background(color = colorResource(id = R.color.graytra)), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}