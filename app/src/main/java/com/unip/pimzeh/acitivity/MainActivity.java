package com.unip.pimzeh.acitivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.unip.pimzeh.R;

import static com.unip.pimzeh.util.LoginUtil.recuperarUsuarioLogado;
import static com.unip.pimzeh.util.LoginUtil.recuperarUsuarioNomeLogado;

public class MainActivity extends AppCompatActivity {

    private TextView txtNome, txtCliqueAqui;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        inicializarComponentes();

        String cpf = recuperarUsuarioLogado(context);
        txtNome.setText(recuperarUsuarioNomeLogado(cpf, context));

    }

    public void inicializarComponentes() {
        txtNome = findViewById(R.id.txtNome);
        txtCliqueAqui = findViewById(R.id.txtCliqueAqui);
    }

    @Override
    public void onBackPressed() {

    }
}