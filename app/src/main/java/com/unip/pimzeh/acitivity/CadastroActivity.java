package com.unip.pimzeh.acitivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.unip.pimzeh.R;
import com.unip.pimzeh.model.Usuario;

import static com.unip.pimzeh.util.LoginUtil.salvarUsuario;
import static com.unip.pimzeh.util.LoginUtil.verificarCpf;
import static com.unip.pimzeh.util.LoginUtil.verificarEmail;

public class CadastroActivity extends AppCompatActivity {
    private EditText editNome, editEmail, editCpf, editSenha;
    private Button btnCadastrar;
    private ProgressBar loginProgressBar;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        setTitle("Cadastro");

        context = CadastroActivity.this;

        inicializarComponentes();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCadastro();
            }
        });

    }

    private void validarCadastro() {
        String nome = editNome.getText().toString();
        String email = editEmail.getText().toString();
        String cpf = editCpf.getText().toString();
        String senha = editSenha.getText().toString();

        if (nome.length() == 0) {
            Toast.makeText(context, "Preencha o nome!", Toast.LENGTH_SHORT).show();
        } else if (email.length() == 0 || !email.contains("@")) {
            Toast.makeText(context, "Preencha o email!", Toast.LENGTH_SHORT).show();
        } else if (cpf.length() < 10) {
            Toast.makeText(context, "Preencha o CPF!", Toast.LENGTH_SHORT).show();
        } else if (senha.length() < 7) {
            Toast.makeText(context, "A senha deve ter no mínimo 8 caracteres", Toast.LENGTH_SHORT).show();
        } else {
            Usuario usuario = new Usuario(cpf, nome, email, senha);

            if (verificarCpf(usuario, context)) {
                Toast.makeText(context, "CPF já cadastrado!", Toast.LENGTH_LONG).show();
            } else if (verificarEmail(usuario, context)) {
                Toast.makeText(context, "Email já cadastrado!", Toast.LENGTH_LONG).show();
            } else {
                salvarUsuario(usuario, context);

                startActivity(new Intent(context, MainActivity.class));
                finish();
            }
        }
    }

    public void inicializarComponentes() {
        editNome = findViewById(R.id.editCadastroNome);
        editEmail = findViewById(R.id.editCadastroEmail);
        editCpf = findViewById(R.id.editCadastroCpf);
        editSenha = findViewById(R.id.editCadastroSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        loginProgressBar = findViewById(R.id.loginProgressBar);
    }
}