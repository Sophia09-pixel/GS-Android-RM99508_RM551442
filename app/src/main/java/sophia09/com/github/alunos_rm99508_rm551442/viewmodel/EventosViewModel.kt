package sophia09.com.github.alunos_rm99508_rm551442.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sophia09.com.github.alunos_rm99508_rm551442.data.EventoDao
import sophia09.com.github.alunos_rm99508_rm551442.model.EventoModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sophia09.com.github.alunos_rm99508_rm551442.data.EventoDatabase


class EventosViewModel(application: Application) : AndroidViewModel(application) {

    private val eventoDao: EventoDao


    val eventoLiveData: LiveData<List<EventoModel>>

    init {
        val database = Room.databaseBuilder(
            getApplication(),
            EventoDatabase::class.java,
            "eventos_database"
        ).fallbackToDestructiveMigration()
            .build()

        eventoDao = database.eventoDao()

        eventoLiveData = eventoDao.getAll()
    }

    fun addEvent(nomeLocal: String, tipo: String, grau: String, data: String, numero: String ) {
        viewModelScope.launch(Dispatchers.IO) {
            val newEvent = EventoModel(
                nomeLocal = nomeLocal,
                tipo = tipo,
                grau = grau,
                data = data,
                numero = numero
            )
            eventoDao.insert(newEvent)
        }
    }

    fun removeEvent(evento: EventoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            eventoDao.delete(evento)
        }
    }
}