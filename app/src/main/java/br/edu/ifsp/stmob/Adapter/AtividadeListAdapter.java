package br.edu.ifsp.stmob.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.ifsp.stmob.dao.PalestranteDAO;
import br.edu.ifsp.stmob.R;
import br.edu.ifsp.stmob.modelo.Atividade;
import br.edu.ifsp.stmob.modelo.Palestrante;

/**
 * Created by cliente on 20/11/2015.
 */


public class AtividadeListAdapter extends BaseAdapter {

    private Context context;
    private List<Atividade> lista;
    private PalestranteDAO plsDao;

    public AtividadeListAdapter(Context context, List<Atividade> lista) {
        this.context = context;
        this.lista = lista;
    }

    public int getCount() {
        return lista.size();
    }

    public Object getItem(int position) {
        return lista.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        plsDao = new PalestranteDAO(context);
        Atividade a = lista.get(position);
        Palestrante pls= plsDao.getByID(a.getAvtPalestrante().getPltCod());

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.atividade_view, null);

        TextView titulo = (TextView) view.findViewById(R.id.txtAtvTitulo);
        titulo.setText("Titulo: "+a.getAtvTitulo());

        TextView palestrante = (TextView) view.findViewById(R.id.txtAtvPalestrante);
        palestrante.setText(String.valueOf(pls.getPltNome()));

        TextView datahora = (TextView) view.findViewById(R.id.txtAtvDataHora);
        datahora.setText("Data: "+a.getAtvData()+" - "+  a.getAtvHorario().substring(0,2)+':'+a.getAtvHorario().substring(2,4));

        return view;
    }
}
