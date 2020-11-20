package com.unip.pimzeh.acitivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.unip.pimzeh.R;
import com.unip.pimzeh.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEditCpf, loginEditSenha;
    private Button loginBtnLogin;
    private ProgressBar loginProgressBar;
    private Context context;
    private Usuario usuario;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = LoginActivity.this;

        verificaConexao();
        inicializarComponentes();

        loginBtnLogin.setOnClickListener(view -> {
            dadosLogin();
        });

    }

    private void dadosLogin() {
        String txtCpf = loginEditCpf.getText().toString();
        String txtSenha = loginEditSenha.getText().toString();

        if (!txtCpf.isEmpty()) {
            if (!txtSenha.isEmpty()) {
                usuario = new Usuario(txtCpf, txtSenha);
                validarLogin(usuario);
            } else {
                Toast.makeText(context,
                        "Preencha a senha!",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context,
                    "Preencha o CPF!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void validarLogin(Usuario usuario) {
        loginProgressBar.setVisibility(View.VISIBLE);

        if (usuario.getCpf().equals("43271447896") && usuario.getSenha().equals("1234")) {
            loginProgressBar.setVisibility(View.GONE);
            Toast.makeText(context, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            loginProgressBar.setVisibility(View.GONE);
            Toast.makeText(context, "Erro ao fazer login!", Toast.LENGTH_SHORT).show();
        }
    }

    public void inicializarComponentes() {
        loginEditCpf = findViewById(R.id.loginEditCPF);
        loginEditSenha = findViewById(R.id.loginEditSenha);
        loginBtnLogin = findViewById(R.id.loginBtnLogin);
        loginProgressBar = findViewById(R.id.loginProgressBar);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void verificaConexao() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = cm.getActiveNetworkInfo();

        if (net != null && net.isConnectedOrConnecting()) {

        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Sem conexão com a internet")
                    .setIcon(R.mipmap.ic_launcher)
                    .setMessage("Precisamos de conexão com a internet. Por favor, ative e tente novamente")
                    .setCancelable(false)
                    .setPositiveButton("TENTAR NOVAMENTE", (dialogInterface, i) -> verificaConexao())
                    .setNegativeButton("FECHAR", (dialogInterface, i) -> {
                        Toast.makeText(getApplicationContext(),
                                "Tente mais tarde",
                                Toast.LENGTH_LONG).show();
                        finishAffinity();
                    });

            alert.create();
            alert.show();
        }
    }

    public void abrirCadastro(View view) {
        Intent intent = new Intent(context, CadastroActivity.class);
        startActivity(new Intent(context, CadastroActivity.class));
    }
}