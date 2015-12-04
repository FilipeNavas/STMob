package br.edu.ifsp.stmob.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.ifsp.stmob.R;
import br.edu.ifsp.stmob.dao.AtividadeDAO;
import br.edu.ifsp.stmob.modelo.Atividade;
import br.edu.ifsp.stmob.modelo.AvisoExtraordinario;

/**
 * Created by Fernando on 02/12/2015.
 */
public class AvisoListAdapter extends BaseAdapter {

    private Context context;
    private List<AvisoExtraordinario> lista;
    private AtividadeDAO dao;
    private Atividade atividade;

    public AvisoListAdapter (Context context, List<AvisoExtraordinario> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        AvisoExtraordinario aviso = lista.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.avisos, null);

        TextView txtTitulo = (TextView) view.findViewById(R.id.txtTitulo);
        txtTitulo.setText(aviso.getAviTitulo());

        dao = new AtividadeDAO(context);
        atividade = dao.buscarListaAtividadeEspecifica(aviso.getAviAtividade().getAtvCod());

        TextView txtAtividade = (TextView) view.findViewById(R.id.txtAtividade);
        txtAtividade.setText(atividade.toString());

        TextView txtDescricao = (TextView) view.findViewById(R.id.txtDescricao);
        txtDescricao.setText(aviso.getAviDescricao());

        return view;
    }
}
