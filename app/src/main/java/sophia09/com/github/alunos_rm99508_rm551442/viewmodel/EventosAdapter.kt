package sophia09.com.github.alunos_rm99508_rm551442.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sophia09.com.github.alunos_rm99508_rm551442.model.EventoModel
import sophia09.com.github.alunos_rm99508_rm551442.R


class EventosAdapter(private val onEventRemoved: (EventoModel) -> Unit) :
    RecyclerView.Adapter<EventosAdapter.EventoViewHolder>() {

    private var eventos = listOf<EventoModel>()

    inner class EventoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textView = view.findViewById<TextView>(R.id.textViewEvento)
        val button = view.findViewById<ImageButton>(R.id.imageButton)

        fun bind(evento: EventoModel) {
            textView.text = "${evento.nomeLocal}\n" +
                    "Tipo: ${evento.tipo}\n" +
                    "Grau Impacto: ${evento.grau}\n" +
                    "Data: ${evento.data}\n" +
                    "Pessoas afetadas: ${evento.numero}"
            button.setOnClickListener {
                onEventRemoved(evento)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.evento_layout, parent, false)
        return EventoViewHolder(view)
    }

    override fun getItemCount(): Int = eventos.size

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        val evento = eventos[position]
        holder.bind(evento)
    }

    fun updateEvents(newEvent: List<EventoModel>) {
        eventos = newEvent
        notifyDataSetChanged()
    }
}