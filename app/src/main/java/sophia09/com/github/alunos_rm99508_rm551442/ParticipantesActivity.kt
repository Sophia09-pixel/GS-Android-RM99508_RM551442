package sophia09.com.github.alunos_rm99508_rm551442

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ParticipantesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_participantes)

        val textView = findViewById<TextView>(R.id.textParticipantes)
        textView.text = "Participantes:\n\n1. Livia - RM99508\n2. Sophia - RM551442"

        val buttonVoltar = findViewById<Button>(R.id.buttonVoltar)
        buttonVoltar.setOnClickListener {
            finish()
        }
    }
}