package dev.ogabek.pdponline.mvvm

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ogabek.pdponline.MainActivity
import dev.ogabek.pdponline.manager.BiometricManager
import dev.ogabek.pdponline.manager.SharedPrefs
import dev.ogabek.pdponline.model.BiometricResult
import javax.inject.Inject

@HiltViewModel
@SuppressLint("StaticFieldLeak")
class LoginViewModel @Inject constructor(
    private val context: Context,
    private val sharedPrefs: SharedPrefs
): ViewModel() {

    private val biometricManager = BiometricManager(MainActivity.MainCompatActivity)

    val initPassword = mutableStateOf(false)

    init {
        if (sharedPrefs.getInt("password") == 0) {
            initPassword.value = true
        }
    }

    fun requireBiometricVerification(
        onPasswordRequired: () -> Unit,
        onFailure: (String) -> Unit,
        onSuccess: () -> Unit
    ) {
        biometricManager.showBiometricPrompt(
            title = "Fingerprint Verification",
            description = "Please verify your finger",
            onError = {
                when(it) {
                    is BiometricResult.AuthenticationNotSet -> {
                        onPasswordRequired()
                    }
                    is BiometricResult.AuthenticationError -> {
                        onFailure(it.error)
                    }
                    else -> {
                        onFailure(it.toString())
                    }
                }
            },
            onDone = {
                onSuccess()
            }
        )
    }

    fun checkPassword(
        password: String,
        onError: (String) -> Unit,
        onSuccess: () -> Unit
    ) {

        val appPassword = sharedPrefs.getInt("password")

        if (appPassword == password.toInt()) {
            onSuccess()
        } else {
            onError("Incorrect password")
        }
    }

    fun enrollPassword(
        password: String,
        onError: () -> Unit,
        onSuccess: () -> Unit
    ) {
        sharedPrefs.saveInt("password", password.toInt())
        initPassword.value = false
    }

}