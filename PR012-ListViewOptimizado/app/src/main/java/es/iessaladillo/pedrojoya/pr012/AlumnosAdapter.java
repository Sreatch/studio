package es.iessaladillo.pedrojoya.pr012;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class AlumnosAdapter extends ArrayAdapter<Alumno> {

    // Variables miembro.
    private final ArrayList<Alumno> alumnos;
    private final LayoutInflater inflador;

    // Constructor.
    public AlumnosAdapter(Context contexto, ArrayList<Alumno> alumnos) {
        super(contexto, R.layout.activity_main_item, alumnos);
        this.alumnos = alumnos;
        // Se obtiene el objeto inflador de layouts.
        inflador = LayoutInflater.from(contexto);
    }

    // Retorna la vista que se debe "dibujar" para un determinado elemento.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        // Si no se puede reciclar.
        if (convertView == null) {
            // Se obtiene la vista-fila inflando el layout.
            convertView = inflador.inflate(R.layout.activity_main_item, parent, false);
            // Se crea el contenedor de vistas para la vista-fila.
            holder = new ViewHolder(convertView);
            // Se almacena el contenedor en la vista.
            convertView.setTag(holder);
        }
        // Si se puede reciclar.
        else {
            // Se obtiene el contenedor de vistas desde la vista reciclada.
            holder = (ViewHolder) convertView.getTag();
        }
        // Se escriben los datos en las vistas del contenedor de vistas.
        onBindViewHolder(holder, position);
        // Se retorna la vista que representa el elemento.
        return convertView;
    }

    // Cuando se deben escribir los datos en la vista del elemento.
    private void onBindViewHolder(ViewHolder holder, int position) {
        // Se obtiene el alumno que debe mostrar el elemento.
        Alumno alumno = alumnos.get(position);
        // Se escriben los datos del alumno en las vistas.
        holder.bind(alumno);
    }

    // Contenedor de vistas para la vista-fila.
    static class ViewHolder {

        // El contenedor de vistas para un elemento de la lista debe contener...
        private final ImageView imgFoto;
        private final TextView lblNombre;
        private final TextView lblCurso;
        private final TextView lblEdad;
        private final TextView lblRepetidor;
        private final Button btnLlamar;
        private final Button btnNotas;

        // El constructor recibe la vista-fila.
        public ViewHolder(View itemView) {
            // Se obtienen las vistas de la vista-fila.
            imgFoto = (ImageView) itemView
                    .findViewById(R.id.imgFoto);
            lblNombre = (TextView) itemView
                    .findViewById(R.id.lblNombre);
            lblCurso = (TextView) itemView
                    .findViewById(R.id.lblCurso);
            lblEdad = (TextView) itemView
                    .findViewById(R.id.lblEdad);
            lblRepetidor = (TextView) itemView
                    .findViewById(R.id.lblRepetidor);
            btnLlamar = (Button) itemView.findViewById(R.id.btnLlamar);
            btnNotas = (Button) itemView.findViewById(R.id.btnNotas);
        }

        // Escribe los datos del alumno en las vistas.
        public void bind(final Alumno alumno) {
            imgFoto.setImageResource(alumno.getFoto());
            lblNombre.setText(alumno.getNombre());
            lblCurso.setText(lblCurso.getContext().getString(
                    R.string.cursociclo, alumno.getCurso(), alumno.getCiclo()));
            lblEdad.setText(lblEdad.getContext().getResources()
                    .getQuantityString(R.plurals.anios, alumno.getEdad(), alumno.getEdad()));
            // El fondo del TextView con la edad es diferente si es menor de
            // edad.
            if (alumno.getEdad() < 18) {
                lblEdad.setTextColor(ContextCompat.getColor(lblEdad.getContext(), R.color.accent));
            } else {
                lblEdad.setTextColor(
                        ContextCompat.getColor(lblEdad.getContext(), R.color.primary_text));
            }
            // Si el alumno es repetidor se muestra el TextView correspondiente.
            if (alumno.isRepetidor()) {
                lblRepetidor.setVisibility(View.VISIBLE);
            } else {
                lblRepetidor.setVisibility(View.INVISIBLE);
            }
            btnLlamar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),
                            view.getContext().getString(R.string.llamar_a, alumno.getNombre()), Toast.LENGTH_SHORT).show();
                }
            });
            btnNotas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),
                            view.getContext().getString(R.string.ver_notas_de, alumno.getNombre()), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}