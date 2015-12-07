package br.edu.ifsp.stmob;

import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import br.edu.ifsp.stmob.dao.AreaConhecimentoDAO;
import br.edu.ifsp.stmob.dao.AtividadeDAO;
import br.edu.ifsp.stmob.dao.InscricaoDAO;
import br.edu.ifsp.stmob.dao.PalestranteDAO;
import br.edu.ifsp.stmob.modelo.Atividade;
import br.edu.ifsp.stmob.modelo.GerenciadorSessao;
import br.edu.ifsp.stmob.modelo.Inscricao;
import br.edu.ifsp.stmob.modelo.Palestrante;
import br.edu.ifsp.stmob.modelo.Usuario;



/**
 * Created by cliente on 29/11/2015.
 */
public class DetalheAtividadeActivity extends Activity {


    private PalestranteDAO plsDao;
    private AreaConhecimentoDAO arcDao;
    private AtividadeDAO atvDao;
    private InscricaoDAO insDao;
    private Atividade atividade;
    private TextView atvTit;
    private TextView atvDscr;
    private TextView atvPls;
    private TextView atvLocal;
    private TextView atvData;
    private Button inscricao;
    private Button compartilhar;
    private Button presenca;
    private Button agenda;
    public com.google.api.services.calendar.Calendar mService = null;

    //Sessao
    private GerenciadorSessao sessao;

    //ATRIBUTOS UTILIZADOS NA INTEGRAÇÃO COM O GOOGLE AGENDA
    GoogleAccountCredential mCredential;
    private TextView mOutputText;
    ProgressDialog mProgress;

    static final int REQUEST_ACCOUNT_PICKER = 1000;
    static final int REQUEST_AUTHORIZATION = 1001;
    static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
    private static final String PREF_ACCOUNT_NAME = "accountName";
    private static final String[] SCOPES = { CalendarScopes.CALENDAR};


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_atividade);
        atvDao = new AtividadeDAO(getApplicationContext());
        plsDao = new PalestranteDAO(getApplicationContext());
        atvTit= (TextView) findViewById(R.id.atvNome);
        atvDscr= (TextView) findViewById(R.id.atvDescr);
        atvPls= (TextView) findViewById(R.id.atvPales);
        insDao = new InscricaoDAO(getApplicationContext());
        atvLocal= (TextView) findViewById(R.id.atvLocal);
        atvData= (TextView) findViewById(R.id.atvData);
        atividade= new Atividade();
        mOutputText = new TextView(this);

        // Initialize credentials and service object.
        SharedPreferences settings = getPreferences(Context.MODE_PRIVATE);
        mCredential = GoogleAccountCredential.usingOAuth2(
                getApplicationContext(), Arrays.asList(SCOPES))
                .setBackOff(new ExponentialBackOff())
                .setSelectedAccountName(settings.getString(PREF_ACCOUNT_NAME, null));

        atvPls.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), DetalhePalestranteActivity.class);
                it.putExtra("pls",Integer.toString(atividade.getAvtPalestrante().getPltCod()));

                startActivity(it);

            }
        });

        //botoes
        inscricao = (Button) findViewById(R.id.btnInscr);
        inscricao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Inscricao ins = new Inscricao();
                Usuario usu = new Usuario();

                //Pega a sessao
                sessao = new GerenciadorSessao(getApplicationContext());

                //pega os dados da sessao
                HashMap<String, String> user = sessao.pegarDadosUsuario();

                //pega o codigo do usuario da sessao
                int codigoUsuario = Integer.parseInt(user.get(GerenciadorSessao.CHAVE_CODIGO));

                //Pega o codigo do usuario
                usu.setUsuCod(codigoUsuario);

                ins.setInsAtividade(atividade);
                ins.setInsUsuario(usu);
                Inscricao isInscrit = insDao.isInscrito(atividade.getAtvCod(), usu.getUsuCod());

                if (isInscrit.getInsCod()!=0) {
                    insDao.deletar(isInscrit.getInsCod());
                    Toast.makeText(DetalheAtividadeActivity.this, "Inscricao cancelada", Toast.LENGTH_SHORT).show();
                }else{
                    insDao.inscricao(ins);
                    Toast.makeText(DetalheAtividadeActivity.this, "Inscrito com Sucesso", Toast.LENGTH_SHORT).show();
                }
            }
        });

        compartilhar = (Button) findViewById(R.id.btnShare);




        presenca = (Button) findViewById(R.id.btnPresenca);
        presenca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                IntentIntegrator integrator = new IntentIntegrator(DetalheAtividadeActivity.this);
                integrator.addExtra("SCAN_WIDTH", 460);
                integrator.addExtra("SCAN_HEIGHT", 500);
                integrator.addExtra("SCAN_MODE", "QR_CODE_MODE");
                //customize the prompt message before scanning
                //integrator.addExtra("PROMPT_MESSAGE", "Scanner Start!");
                integrator.initiateScan(IntentIntegrator.QR_CODE_TYPES);
            }
        });


        agenda = (Button) findViewById(R.id.btnAgenda);
        agenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                if (isGooglePlayServicesAvailable()) {
                    refreshResults();
                } else {
                    Log.i("Calendar", "Google Play Services required: " +
                            "after installing, close and relaunch this app.");
                }
            }
        });


    }


    protected void onStart() {
        super.onStart();
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            Log.d("extra", extras.getString("atvCod"));
           if (!extras.getString("atvCod").isEmpty()) {
                atividade= atvDao.buscarListaAtividadeEspecifica(Integer.parseInt(extras.getString("atvCod")));
               preencherAtividade(atividade);
            }

        }
    }

    private void preencherAtividade(Atividade atv){
        Palestrante p =plsDao.getByID(atv.getAvtPalestrante().getPltCod());
        atvTit.setText(atv.getAtvTitulo());
        atvDscr.setText(atv.getAtvDescricao());
        atvPls.setText(p.getPltNome());
        atvLocal.setText(atv.getAtvLocal());
        atvData.setText(atv.getAtvData() + " - " + atv.getAtvHorario().substring(0, 2) + ':' + atv.getAtvHorario().substring(2, 4));

    }


    //MÉTODO DE INTEGRAÇÃO DE QRCODE
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        switch(requestCode) {
            case REQUEST_GOOGLE_PLAY_SERVICES:
                if (resultCode != RESULT_OK) {
                    isGooglePlayServicesAvailable();
                }
                break;
            case REQUEST_ACCOUNT_PICKER:
                if (resultCode == RESULT_OK && intent != null &&
                        intent.getExtras() != null) {
                    String accountName =
                            intent.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                    if (accountName != null) {
                        mCredential.setSelectedAccountName(accountName);
                        SharedPreferences settings =
                                getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString(PREF_ACCOUNT_NAME, accountName);
                        editor.apply();
                    }
                } else if (resultCode == RESULT_CANCELED) {
                    Log.i("Calendar"," Account unspecified.");
                }
                break;
            case REQUEST_AUTHORIZATION:
                if (resultCode != RESULT_OK) {
                    chooseAccount();
                }
                break;
        }


        if (result != null) {
            String contents = result.getContents();
            if (contents != null) {

                if( result.getContents().toString().equals(String.valueOf( atividade.getAtvCod()))){
                    Toast toast = Toast.makeText(getApplicationContext(),  "PRESENÇA CONFIRMADA!", Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(),  "SUA PRESENÇA NÃO FOI CONFIRMADA!", Toast.LENGTH_LONG);
                    toast.show();
                }

            } else {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.result_failed + result.toString(), Toast.LENGTH_LONG);
                toast.show();
                //showDialog(R.string.result_failed, Bundle.EMPTY);
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }





    @Override
    protected void onResume() {
        super.onResume();

    }


    /**
     * Attempt to get a set of data from the Google Calendar API to display. If the
     * email address isn't known yet, then call chooseAccount() method so the
     * user can pick an account.
     */
    private void refreshResults() {
        if (mCredential.getSelectedAccountName() == null) {
            chooseAccount();
        } else {
            if (isDeviceOnline()) {
                new MakeRequestTask(mCredential).execute();
            } else {
                Log.i("CALENDAR","Conexão de rede indisponível no momento.");
            }
        }
    }

    /**
     * Starts an activity in Google Play Services so the user can pick an
     * account.
     */
    private void chooseAccount() {
        startActivityForResult(
                mCredential.newChooseAccountIntent(), REQUEST_ACCOUNT_PICKER);
    }

    /**
     * Checks whether the device currently has a network connection.
     * @return true if the device has a network connection, false otherwise.
     */
    private boolean isDeviceOnline() {
        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    /**
     * Check that Google Play services APK is installed and up to date. Will
     * launch an error dialog for the user to update Google Play Services if
     * possible.
     * @return true if Google Play Services is available and up to
     *     date on this device; false otherwise.
     */
    private boolean isGooglePlayServicesAvailable() {
        final int connectionStatusCode =
                GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (GooglePlayServicesUtil.isUserRecoverableError(connectionStatusCode)) {
            showGooglePlayServicesAvailabilityErrorDialog(connectionStatusCode);
            return false;
        } else if (connectionStatusCode != ConnectionResult.SUCCESS ) {
            return false;
        }
        return true;
    }

    /**
     * Display an error dialog showing that Google Play Services is missing
     * or out of date.
     * @param connectionStatusCode code describing the presence (or lack of)
     *     Google Play Services on this device.
     */
    void showGooglePlayServicesAvailabilityErrorDialog(
            final int connectionStatusCode) {
        Dialog dialog = GooglePlayServicesUtil.getErrorDialog(
                connectionStatusCode,
                DetalheAtividadeActivity.this,
                REQUEST_GOOGLE_PLAY_SERVICES);
        dialog.show();
    }

    private class MakeRequestTask extends AsyncTask<Void, Void, Boolean> {

        private Exception mLastError = null;

        public MakeRequestTask(GoogleAccountCredential credential) {
            HttpTransport transport = AndroidHttp.newCompatibleTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
            mService = new com.google.api.services.calendar.Calendar.Builder(
                    transport, jsonFactory, credential)
                    .setApplicationName("Compartilhamento de Evento - Google Calendar")
                    .build();
        }

        /**
         * Background task to call Google Calendar API.
         * @param params no parameters needed for this task.
         */
        @Override
        protected Boolean doInBackground(Void... params) {
            try
            {
                criarEventoAgenda();
                return true;
            }
            catch (IOException e) {
                mLastError = e;
                Log.e("CALENDAR","Ocorreu o seguinte erro:\n"
                        + e.getClass());
                //Toast.makeText(DetalheAtividadeActivity.this,"Erro: "+e.getMessage(),Toast.LENGTH_LONG).show();
                //cancel(true);
            }
            return false;
        }



        @Override
        protected void onPreExecute() {
            mProgress = new ProgressDialog(DetalheAtividadeActivity.this);
            mProgress.setMessage("Criando eventos no calendário");
            mProgress.show();

        }

        private void criarEventoAgenda() throws IOException {

            //DateTime now = new DateTime(System.currentTimeMillis());
            Event event = new Event()
                    .setSummary(atividade.getAtvTitulo())
                    .setLocation(atividade.getAtvLocal())
                    .setDescription(atividade.getAtvDescricao());


           //DateTime startDateTime = new DateTime("2015-12-06T21:01:00-02:00");
           //DateFormat dtf = new SimpleDateFormat("yyyy-mm-dd");
           //SimpleTimeLimiter tmf = new SimpleTimeLimiter("hh:mm:ss");

            //DateTime startDateTime = new DateTime(dtf.format(atividade.getAtvData())+"T"+tmf.toString(atividade.getAtvHorario())+"-02:00");

            //pegar data e hora
            String data = atividade.getAtvData(); //Pega a data

            String dataArray[] = data.split("/"); //divide a string pelas barras
            String dia = dataArray[0];
            String mes = dataArray[1];
            String ano = dataArray[2];
            data = ano + "-" + mes + "-" + dia; //junta a data no formato certo (yyyy-mm-dd)

            String horaHora = atividade.getAtvHorario().substring(0, 2);//Pega a hora
            String horaMinuto = atividade.getAtvHorario().substring(2,4);//Pega os minutos
            String hora = horaHora + ":" + horaMinuto;//junta a hora e minutos com dois pontos (pq vem do banco sem o smile emoticon

            String dataInicial = data + "T" + hora + ":00-02:00"; //cria a data inicial com os segundos (00) e a timezone
            String dataFinal = data + "T" + hora + ":00-03:00"; //cria a data final com os segundos (00) e adiciona 1 hora a timezone
            //System.out.println("DATA FINAL:" + dataFinal);

            DateTime startDateTime = new DateTime(dataInicial);
            EventDateTime start = new EventDateTime()
                    .setDateTime(startDateTime)
                    .setTimeZone("America/Sao_Paulo");

            event.setStart(start);


            DateTime endDateTime = new DateTime(dataFinal);
            EventDateTime end = new EventDateTime()
                    .setDateTime(endDateTime)
                    .setTimeZone("America/Sao_Paulo");
            event.setEnd(end);

            String calendarId = "primary";
            HttpTransport transport = AndroidHttp.newCompatibleTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

            try{
                event = mService.events().insert("primary", event).execute();
            } catch (UserRecoverableAuthIOException e) {
                startActivityForResult(e.getIntent(), REQUEST_AUTHORIZATION);
            }
            System.out.printf("Evento criado: %s\n", event.getHtmlLink());

        }


        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean)
            {
                Toast.makeText(DetalheAtividadeActivity.this,"Evento criado com sucesso",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(DetalheAtividadeActivity.this,"Erro ao criar eventos",Toast.LENGTH_LONG).show();
            }
            mProgress.hide();

        }

        @Override
        protected void onCancelled() {
            mProgress.hide();
            if (mLastError != null) {
                if (mLastError instanceof GooglePlayServicesAvailabilityIOException) {
                    showGooglePlayServicesAvailabilityErrorDialog(
                            ((GooglePlayServicesAvailabilityIOException) mLastError)
                                    .getConnectionStatusCode());
                } else if (mLastError instanceof UserRecoverableAuthIOException) {
                    startActivityForResult(
                            ((UserRecoverableAuthIOException) mLastError).getIntent(),
                            DetalheAtividadeActivity.REQUEST_AUTHORIZATION);
                } else {
                    Log.e("CALENDAR","Ocorreu o seguinte erro:\n"
                            + mLastError.getMessage());
                }
            } else {
                Log.e("CALENDAR","Solicitação cancelada.");
            }
        }


    }




}
