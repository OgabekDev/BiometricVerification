package dev.ogabek.pdponline

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.ogabek.pdponline.manager.BiometricManager
import dev.ogabek.pdponline.screen.HomeScreen
import dev.ogabek.pdponline.screen.LoginScreen
import dev.ogabek.pdponline.ui.theme.PDPOnlineTheme
import dev.ogabek.pdponline.utils.HomePage
import dev.ogabek.pdponline.utils.LoginPage

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        MainCompatActivity = this

        val biometricManager by lazy {
            BiometricManager(this)
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PDPOnlineTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = LoginPage
                ) {
                    composable<HomePage> {
                        HomeScreen(navController = navController)
                    }

                    composable<LoginPage> {
                        LoginScreen(navController = navController)
                    }
                }
            }
        }
    }

    companion object {
        lateinit var MainCompatActivity: AppCompatActivity
    }

}