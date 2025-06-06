package sophia09.com.github.alunos_rm99508_rm551442.data

import androidx.room.Database
import androidx.room.RoomDatabase
import sophia09.com.github.alunos_rm99508_rm551442.model.EventoModel

@Database(entities = [EventoModel::class], version = 1)
abstract class EventoDatabase : RoomDatabase() {
    abstract fun eventoDao(): EventoDao
}