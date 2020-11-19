package com.unip.pimzeh.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.unip.pimzeh.model.Usuario;

public class LoginUtil {

    private final static String LOGIN_KEY = "LOGIN_KEY";
    private final static String LOGIN_NOME = "LOGIN_NOME";
    private final static String LOGIN_EMAIL = "LOGIN_EMAIL";
    private final static String LOGIN_CPF = "LOGIN_CPF";
    private final static String LOGIN_SENHA = "LOGIN_SENHA";

    public static void salvarUsuario(Usuario usuario, Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences(LOGIN_KEY + usuario.getCpf(), 0);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();

        prefsEditor.putString(LOGIN_NOME, usuario.getNome());
        prefsEditor.putString(LOGIN_EMAIL, usuario.getEmail());
        prefsEditor.putString(LOGIN_CPF, usuario.getCpf());
        prefsEditor.putString(LOGIN_SENHA, usuario.getSenha());

        prefsEditor.apply();
    }

    public static String recuperarLogin(Usuario usuario, Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences(LOGIN_KEY + usuario.getCpf(), 0);
        return mPrefs.getString(LOGIN_SENHA, "-1");
    }


}
