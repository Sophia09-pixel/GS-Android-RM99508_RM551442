package sophia09.com.github.alunos_rm99508_rm551442.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class EventosViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EventosViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}