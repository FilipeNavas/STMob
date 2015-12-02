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
import br.edu.ifsp.stmob.dao.InscricaoDAO;
import br.edu.ifsp.stmob.dao.PalestranteDAO;
import br.edu.ifsp.stmob.modelo.Atividade;
import br.edu.ifsp.stmob.modelo.Inscricao;
import br.edu.ifsp.stmob.modelo.Palestrante;

/**
 * Created by Fernando on 02/12/2015.
 */
public class InscricaoListAdapter extends BaseAdapter {

    private Context context;
    private List<Inscricao> lista;
    private InscricaoDAO inscricaoDAO;
    private PalestranteDAO palestranteDAO;
    private AtividadeDAO atividadeDAO;
    private Palestrante palestrante;
    private Atividade atividade;
    private Inscricao inscricao;

    public InscricaoListAdapter(Context context, List<Inscricao> lista) {
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

        Inscricao inscricao = lista.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.inscricoes, null);

        inscricaoDAO = new InscricaoDAO(context);
        palestranteDAO = new PalestranteDAO(context);
        atividadeDAO = new AtividadeDAO(context);

        inscricao = inscricaoDAO.getByCodInscricao(inscricao.getInsCod());
        atividade = atividadeDAO.buscarListaAtividadeEspecifica(inscricao.getInsAtividade().getAtvCod());
        palestrante = palestranteDAO.getByID(atividade.getAvtPalestrante().getPltCod());
        
        
        TextView txtTitulo = (TextView) view.findViewById(R.id.txtTitulo);
        txtTitulo.setText(atividade.getAtvTitulo());

        TextView txtPalestrante = (TextView) view.findViewById(R.id.txtPalestrante);
        txtPalestrante.setText(palestrante.getPltNome());

        TextView txtData = (TextView) view.findViewById(R.id.txtData);
        txtData.setText(atividade.getAtvData().toString() + " " + atividade.getAtvHorario().toString());

        return view;
    }
}
