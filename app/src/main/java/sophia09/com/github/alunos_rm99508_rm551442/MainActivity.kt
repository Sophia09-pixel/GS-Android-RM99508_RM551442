package sophia09.com.github.alunos_rm99508_rm551442

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import sophia09.com.github.alunos_rm99508_rm551442.ui.theme.Alunos_RM99508_RM551442Theme
import sophia09.com.github.alunos_rm99508_rm551442.viewmodel.EventosAdapter
import sophia09.com.github.alunos_rm99508_rm551442.viewmodel.EventosViewModel
import sophia09.com.github.alunos_rm99508_rm551442.viewmodel.EventosViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: EventosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Registro de Eventos Extremos"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val eventsAdapter = EventosAdapter { evento ->
            viewModel.removeEvent(evento)
        }
        recyclerView.adapter = eventsAdapter

        val button = findViewById<Button>(R.id.button)
        val editName = findViewById<EditText>(R.id.editName)
        val editTipo = findViewById<EditText>(R.id.editTipo)
        val editImpacto = findViewById<EditText>(R.id.editImpacto)
        val editData = findViewById<EditText>(R.id.editData)
        val editPessoasAfetadas = findViewById<EditText>(R.id.editPessoasAfetadas)

        button.setOnClickListener {
            var isValid = true

            if (editName.text.isEmpty()) {
                editName.error = "Nome do local é obrigatório"
                isValid = false
            }

            if (editTipo.text.isEmpty()) {
                editTipo.error = "Tipo do evento é obrigatório"
                isValid = false
            }

            if (editImpacto.text.isEmpty()) {
                editImpacto.error = "Grau de impacto é obrigatório"
                isValid = false
            }

            if (editData.text.isEmpty()) {
                editData.error = "Data do evento é obrigatória"
                isValid = false
            }

            val numeroPessoasAfetadas = editPessoasAfetadas.text.toString().toIntOrNull()
            if (editPessoasAfetadas.text.isEmpty()) {
                editPessoasAfetadas.error = "Número de pessoas afetadas é obrigatório"
                isValid = false
            } else if (numeroPessoasAfetadas == null || numeroPessoasAfetadas <= 0) {
                editPessoasAfetadas.error = "Informe um número válido maior que 0"
                isValid = false
            }

            if (!isValid) return@setOnClickListener

            viewModel.addEvent(
                editName.text.toString(),
                editTipo.text.toString(),
                editImpacto.text.toString(),
                editData.text.toString(),
                numeroPessoasAfetadas.toString()
            )

            editName.text.clear()
            editTipo.text.clear()
            editImpacto.text.clear()
            editData.text.clear()
            editPessoasAfetadas.text.clear()
        }


        val viewModelFactory = EventosViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(EventosViewModel::class.java)

        viewModel.eventoLiveData.observe(this) { eventos ->
            eventsAdapter.updateEvents(eventos)
        }

        val buttonParticipantes = findViewById<Button>(R.id.buttonParticipantes)
        buttonParticipantes.setOnClickListener {
            val intent = Intent(this, ParticipantesActivity::class.java)
            startActivity(intent)
        }
    }
}