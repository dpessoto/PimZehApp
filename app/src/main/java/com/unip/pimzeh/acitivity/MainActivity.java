package com.unip.pimzeh.acitivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.unip.pimzeh.R;

import static com.unip.pimzeh.util.LoginUtil.limparUsuarioLogado;
import static com.unip.pimzeh.util.LoginUtil.recuperarUsuarioLogado;
import static com.unip.pimzeh.util.LoginUtil.recuperarUsuarioNomeLogado;

public class MainActivity extends AppCompatActivity {

    private TextView txtNome, txtCliqueAqui;
    private ImageView imgLougout;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        inicializarComponentes();

        String cpf = recuperarUsuarioLogado(context);
        txtNome.setText(recuperarUsuarioNomeLogado(cpf, context));
        txtCliqueAqui.setText(Html.fromHtml("clique <b>AQUI</b>"));

        imgLougout.setOnClickListener(view -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setTitle("Atenção!")
                    .setIcon(R.mipmap.ic_launcher)
                    .setMessage("Deseja sair do app?")
                    .setPositiveButton("SIM", (dialogInterface, i) -> {
                        limparUsuarioLogado(context);
                        startActivity(new Intent(context, LoginActivity.class));
                        finish();
                    })
                    .setNegativeButton("NÃO", (dialogInterface, i) -> { });

            alert.create();
            alert.show();
        });

        txtCliqueAqui.setOnClickListener(view -> startActivity(new Intent(context, WebActivity.class)));

    }

    public void inicializarComponentes() {
        txtNome = findViewById(R.id.txtNome);
        txtCliqueAqui = findViewById(R.id.txtCliqueAqui);
        imgLougout = findViewById(R.id.imgLougout);
    }

    @Override
    public void onBackPressed() {

    }
}