package dev.ogabek.pdponline.utils

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun <T: Any?> ViewModel.changeStateValue(root: MutableState<T>, newValue: T, defaultValue: T) {
    viewModelScope.launch {
        withContext(Dispatchers.IO) {
            root.value = newValue
            delay(2000)
            root.value = defaultValue
        }
    }
}

fun addPasswordValue(password: String, value: String): String {
    var result = password
    if (password.length < 4) {
        result += value
    }
    return result
}