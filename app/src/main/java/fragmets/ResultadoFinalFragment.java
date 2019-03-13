package fragmets;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mediaescolarmvc.R;
import com.example.mediaescolarmvc.controller.MediaEscolarController;
import com.example.mediaescolarmvc.model.MediaEscolar;

import java.util.ArrayList;

import adapter.ResultadoFinalListAdapter;

public class ResultadoFinalFragment extends Fragment {
    ArrayList<MediaEscolar> dataSet;
    ListView listView;
    MediaEscolarController controller;

    View view;

    public ResultadoFinalFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_resultado_final, container, false);

        controller = new MediaEscolarController(getContext());

        listView = view.findViewById(R.id.listview);
        dataSet = controller.getResultadoFinal();

        final ResultadoFinalListAdapter adapter = new ResultadoFinalListAdapter(dataSet, getContext());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MediaEscolar mediaEscolar = dataSet.get(position);
                Snackbar.make(view, mediaEscolar.getMateria() + "\n" + mediaEscolar.getSituacao() + " Média Final: " + mediaEscolar.getMediaFinal(), Snackbar.LENGTH_LONG).setAction("No action", null).show();

                //Realiza a atualização dos dados na tela através do DataSetObserver
                dataSet = controller.getAllResultadoFinal();
                adapter.atualizarLista(dataSet);
            }
        });

        return view;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
