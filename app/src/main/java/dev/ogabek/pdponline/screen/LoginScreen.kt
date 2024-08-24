package dev.ogabek.pdponline.screen

import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
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
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.ogabek.pdponline.R
import dev.ogabek.pdponline.manager.BiometricManager
import dev.ogabek.pdponline.model.BiometricResult
import dev.ogabek.pdponline.mvvm.LoginViewModel
import dev.ogabek.pdponline.ui.theme.MainColor
import dev.ogabek.pdponline.utils.addPasswordValue
import dev.ogabek.pdponline.view.ActionPadButton
import dev.ogabek.pdponline.view.NumberPadButton
import dev.ogabek.pdponline.view.PasswordDots

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    var password by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    val enrollLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {
            println("Activity result: $it")
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .width(200.dp),
                painter = painterResource(id = R.drawable.payme),
                contentDescription = "PayMe Logo"
            )

            Spacer(Modifier.height(32.dp))
            
            PasswordDots(
                total = 4,
                typed = password.length,
                defaultColor = Color.Gray,
                typedColor = MainColor
            )

            Spacer(Modifier.height(32.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NumberPadButton(number = 1) {
                    password = addPasswordValue(password, it.toString())
                }
                NumberPadButton(number = 2) {
                    password = addPasswordValue(password, it.toString())
                }
                NumberPadButton(number = 3) {
                    password = addPasswordValue(password, it.toString())
                }
            }

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NumberPadButton(number = 4) {
                    password = addPasswordValue(password, it.toString())
                }
                NumberPadButton(number = 5) {
                    password = addPasswordValue(password, it.toString())
                }
                NumberPadButton(number = 6) {
                    password = addPasswordValue(password, it.toString())
                }
            }

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NumberPadButton(number = 7) {
                    password = addPasswordValue(password, it.toString())
                }
                NumberPadButton(number = 8) {
                    password = addPasswordValue(password, it.toString())
                }
                NumberPadButton(number = 9) {
                    password = addPasswordValue(password, it.toString())
                }
            }

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ActionPadButton(image = R.drawable.fingerprint) {
                    viewModel.requireBiometricVerification(
                        onPasswordRequired = {
                            Toast.makeText(context, "Please set a password.", Toast.LENGTH_SHORT).show()
                            if(Build.VERSION.SDK_INT >= 30) {
                                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                                    putExtra(
                                        Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                                        BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                                    )
                                }
                                enrollLauncher.launch(enrollIntent)
                            }
                        },
                        onFailure = {
                            Toast.makeText(context, "Fingerprint failure, please try again!", Toast.LENGTH_SHORT).show()
                        },
                        onSuccess = {
                            Toast.makeText(context, "Fingerprint is success", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
                NumberPadButton(number = 0) {
                    password = addPasswordValue(password, it.toString())
                }
                ActionPadButton(image = R.drawable.backspace) {
                    password = password.dropLast(1)
                }
            }
        }
    }
}