package adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mediaescolarmvc.R;
import com.example.mediaescolarmvc.controller.MediaEscolarController;
import com.example.mediaescolarmvc.model.MediaEscolar;

import java.util.ArrayList;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.view.LayoutInflater;

public class ResultadoFinalListAdapter extends ArrayAdapter<MediaEscolar> implements View.OnClickListener {
    Context context;
    private int ultimaPosicao = -1;
    AlertDialog.Builder builder;
    AlertDialog alert;
    MediaEscolar mediaEscolar;
    MediaEscolarController escolarController;
    ArrayList<MediaEscolar> dados;
    ViewHolder linha;

    private static class ViewHolder {

        TextView txtBimestre;
        TextView txtSituacao;
        TextView txtMateria;

        ImageView imgLogo;
        ImageView imgConsultar;
        ImageView imgEditar;
        ImageView imgDeletar;
        ImageView imgSalvar;
    }

    public ResultadoFinalListAdapter(ArrayList<MediaEscolar> dataSet, Context context) {
        super(context, R.layout.lisview_resultado_final, dataSet);

        this.dados = dataSet;

        this.context = context;
    }

    public void atualizarLista(ArrayList<MediaEscolar> objs) {
        this.dados.clear();
        this.dados.addAll(objs);

        notifyDataSetChanged();
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        super.registerDataSetObserver(observer);
    }

    @Override
    public void onClick(View v) {
        int posicao = (Integer) v.getTag();

        Object object = getItem(posicao);

        mediaEscolar = (MediaEscolar) object;
        escolarController = new MediaEscolarController(getContext());

        switch (v.getId()) {
            case R.id.imgLogo:
                // Aprensentar os dados detalhados no Snackbar
                Snackbar.make(v, "Nota da Prova " + mediaEscolar.getNotaProva(), Snackbar.LENGTH_LONG).setAction("No action", null).show();
                break;
            case R.id.imgDeletar:
                builder = new AlertDialog.Builder(getContext());
                builder.setTitle("ALERTA");
                builder.setMessage("Deseja DELETAR este registro?");
                builder.setCancelable(true);
                builder.setIcon(R.mipmap.ic_launcher);

                builder.setPositiveButton("SIM", new Dialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Chama o método deletar do meu controller
                        escolarController.deletar(mediaEscolar);
                        //linha.imgDeletar.setEnabled(false);
                        notifyDataSetChanged();//gatilho para avisar o observable que algo foi alterado.
                    }
                });

                builder.setNegativeButton("CANCELAR", new Dialog.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });

                alert = builder.create();
                alert.show();
                break;
        }
    }

    @NonNull
    @Override
    public View getView(int position, View dataSet, @NonNull ViewGroup parent) {
        MediaEscolar mediaEscolar = getItem(position);

        ViewHolder linha;

        if (dataSet == null) {

            linha = new ViewHolder();
            LayoutInflater layoutResultadoFinalList = LayoutInflater.from(getContext());
            dataSet = layoutResultadoFinalList.inflate(R.layout.lisview_resultado_final, parent, false);

            linha.txtMateria = dataSet.findViewById(R.id.txtMateria);
            linha.txtBimestre = dataSet.findViewById(R.id.txtBimestre);
            linha.txtSituacao = dataSet.findViewById(R.id.txtResultado);
            linha.imgLogo = dataSet.findViewById(R.id.imgLogo);
            linha.imgConsultar = dataSet.findViewById(R.id.imgConsultar);
            linha.imgDeletar = dataSet.findViewById(R.id.imgDeletar);
            linha.imgEditar = dataSet.findViewById(R.id.imgEditar);
            linha.imgSalvar = dataSet.findViewById(R.id.imgSalvar);

            dataSet.setTag(linha);

        } else {
            linha = (ViewHolder) dataSet.getTag();
        }

        linha.txtMateria.setText(mediaEscolar.getMateria());
        linha.txtBimestre.setText(mediaEscolar.getBimestre());
        linha.txtSituacao.setText(mediaEscolar.getSituacao());

        //Botão com o logo
        linha.imgLogo.setOnClickListener(this);
        linha.imgLogo.setTag(position);

        //Botão Excluir
        linha.imgDeletar.setOnClickListener(this);
        linha.imgDeletar.setTag(position);

        //botão Salvar
        linha.imgSalvar.setOnClickListener(this);
        linha.imgSalvar.setTag(position);

        //Botão Editar
        linha.imgEditar.setOnClickListener(this);
        linha.imgEditar.setTag(position);

        return dataSet;
    }

}
