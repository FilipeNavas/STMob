package br.edu.ifsp.stmob;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by Rafael on 2015-11-13.
 */
public class MostraAtividadeActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade);

        Button btnChamaQrCode = (Button) findViewById(R.id.btnConfirmaPresenca);
        btnChamaQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                IntentIntegrator integrator = new IntentIntegrator(MostraAtividadeActivity.this);
                integrator.addExtra("SCAN_WIDTH", 460);
                integrator.addExtra("SCAN_HEIGHT", 500);
                integrator.addExtra("SCAN_MODE", "QR_CODE_MODE");
                //customize the prompt message before scanning
                //integrator.addExtra("PROMPT_MESSAGE", "Scanner Start!");
                integrator.initiateScan(IntentIntegrator.QR_CODE_TYPES);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (result != null) {
            String contents = result.getContents();
            if (contents != null) {
                Toast toast = Toast.makeText(getApplicationContext(), result.getContents().toString(), Toast.LENGTH_LONG);
                toast.show();
                //showDialog(R.string.result_succeeded, Bundle.EMPTY);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.result_failed + result.toString(), Toast.LENGTH_LONG);
                toast.show();
                //showDialog(R.string.result_failed, Bundle.EMPTY);
            }
        }
    }

}
