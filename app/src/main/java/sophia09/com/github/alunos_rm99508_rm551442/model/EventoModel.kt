package sophia09.com.github.alunos_rm99508_rm551442.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EventoModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nomeLocal: String,
    val tipo: String,
    val grau: String,
    val data: String,
    val numero: String
)
