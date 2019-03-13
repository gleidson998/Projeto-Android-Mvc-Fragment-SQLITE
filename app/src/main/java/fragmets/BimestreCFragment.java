package fragmets;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediaescolarmvc.R;
import com.example.mediaescolarmvc.controller.MediaEscolarController;
import com.example.mediaescolarmvc.model.MediaEscolar;
import com.example.mediaescolarmvc.view.MainActivity;

public class BimestreCFragment extends Fragment
{
    MediaEscolar mediaEscolar;
    MediaEscolarController controller;

    Button btnCalcular;
    EditText editMateria,editNotaProva,editNotaTrabalho;
    TextView txtResultado,txtSituacaoFinal;

    //Variaveis de controle
    double notaProva;
    double notaTrabalho;
    double media;

    boolean dadosValidados = true;
    Context context;
    View view;

    public BimestreCFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bimestre_c, container, false);

        //declara e uni as variaveis com seus respectivos campos
        editMateria = view.findViewById(R.id.editMateria);
        editNotaProva = view.findViewById(R.id.editNotaProva);
        editNotaTrabalho = view.findViewById(R.id.editNotaTrabalho);
        txtResultado = view.findViewById(R.id.txtResultado);
        txtSituacaoFinal= view.findViewById(R.id.txtSituacaoFinal);

        btnCalcular = view.findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    if (editNotaProva.getText().toString().length() > 0) {
                        notaProva = Double.parseDouble(editNotaProva.getText().toString());

                        if (notaProva > 10) {
                            dadosValidados = false;
                            Toast.makeText(context, "Nota inválida!", Toast.LENGTH_SHORT).show();
                            editNotaProva.requestFocus();

                        } else {
                            dadosValidados = true;
                        }

                    } else {
                        editNotaProva.setError("*");
                        editNotaProva.requestFocus();
                        dadosValidados = false;
                    }

                    if (editNotaTrabalho.getText().toString().length() > 0) {
                        notaTrabalho = Double.parseDouble(editNotaTrabalho.getText().toString());


                        if (notaTrabalho > 10) {
                            dadosValidados = false;
                            Toast.makeText(context, "Nota inválida!", Toast.LENGTH_SHORT).show();
                            editNotaTrabalho.requestFocus();

                        } else {
                            dadosValidados = true;
                        }

                    } else {
                        editNotaTrabalho.setError("*");
                        editNotaTrabalho.requestFocus();
                        dadosValidados = false;
                    }

                    if (editMateria.getText().toString().length() == 0) {
                        editMateria.setError("*");
                        editMateria.requestFocus();
                        dadosValidados = false;
                    }

                    // Após Validação
                    if (dadosValidados) {

                        mediaEscolar = new MediaEscolar();
                        controller = new MediaEscolarController(context);

                        mediaEscolar.setMateria(editMateria.getText().toString());
                        mediaEscolar.setNotaProva(Double.parseDouble(editNotaProva.getText().toString()));
                        mediaEscolar.setNotaMateria(Double.parseDouble(editNotaTrabalho.getText().toString()));
                        mediaEscolar.setBimestre("3º - Bimestre");

                        media = controller.calcularMedia(mediaEscolar);//(notaProva + notaTrabalho) / 2;

                        mediaEscolar.setMediaFinal(media);
                        mediaEscolar.setSituacao(controller.resultadoFinal(media));
                        txtResultado.setText(MainActivity.formatarValorDecimal(media));
                        txtSituacaoFinal.setText(mediaEscolar.getSituacao());

                        editNotaProva.setText(MainActivity.formatarValorDecimal(notaProva));
                        editNotaTrabalho.setText(MainActivity.formatarValorDecimal(notaTrabalho));

                        //salvarSharedPreferences();
                        if (controller.salvar(mediaEscolar)) {
                            Toast.makeText(context, "Dados salvos com Sucesso", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "Falha ao salvar os dados", Toast.LENGTH_LONG).show();
                        }

                    }

                } catch (Exception e) {

                    Toast.makeText(context, "Informe as notas...", Toast.LENGTH_LONG).show();

                }

            }
        });
        return view;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
