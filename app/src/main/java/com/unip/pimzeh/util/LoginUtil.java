package com.unip.pimzeh.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.unip.pimzeh.model.Usuario;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoginUtil {

    private final static String LOGIN_KEY = "LOGIN_KEY";
    private final static String LOGIN_NOME = "LOGIN_NOME";
    private final static String LOGIN_EMAIL = "LOGIN_EMAIL";
    private final static String LOGIN_CPF = "LOGIN_CPF";
    private final static String LOGIN_SENHA = "LOGIN_SENHA";
    private final static String VERIFICA_EMAIL_KEY = "VERIFICA_EMAIL_KEY";
    private final static String VERIFICA_EMAIL = "VERIFICA_EMAIL";


    public static void salvarListaEmail(Usuario usuario, Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences(VERIFICA_EMAIL_KEY, 0);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        List<String> listaEmail = new ArrayList<>();

        if (!mPrefs.getString(VERIFICA_EMAIL, "-1").equals("-1")) {
            try {
                JSONArray array = new JSONArray(mPrefs.getString(VERIFICA_EMAIL, "-1"));

                for (int i = 0; array.length() > i; i++) {
                    JSONObject obj = array.getJSONObject(i);
                    listaEmail.add(obj.getString("email"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        listaEmail.add(usuario.getEmail());

        JSONArray array = new JSONArray();
        JSONObject obj;

        try {
            for (int i = 0; listaEmail.size() > i; i++) {
                obj = new JSONObject();
                obj.put("email", listaEmail.get(i));
                array.put(obj);
            }

            prefsEditor.putString(VERIFICA_EMAIL, array.toString());
            prefsEditor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void salvarUsuario(Usuario usuario, Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences(LOGIN_KEY + usuario.getCpf(), 0);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();

        prefsEditor.putString(LOGIN_NOME, usuario.getNome());
        prefsEditor.putString(LOGIN_EMAIL, usuario.getEmail());
        prefsEditor.putString(LOGIN_CPF, usuario.getCpf());
        prefsEditor.putString(LOGIN_SENHA, usuario.getSenha());

        prefsEditor.apply();
    }

    public static Boolean verificarCpf(Usuario usuario, Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences(LOGIN_KEY + usuario.getCpf(), 0);
        String cpf = mPrefs.getString(LOGIN_CPF, "-1");

        return !cpf.equals("-1");
    }

    public static Boolean verificarEmail(Usuario usuario, Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences(VERIFICA_EMAIL_KEY, 0);
        String email = mPrefs.getString(VERIFICA_EMAIL, "-1");

        List<String> listaEmail = new ArrayList<>();

        if (!email.equals("-1")) {
            try {
                JSONArray array = new JSONArray(mPrefs.getString(VERIFICA_EMAIL, "-1"));

                for (int i = 0; array.length() > i; i++) {
                    JSONObject obj = array.getJSONObject(i);
                    listaEmail.add(obj.getString("email"));
                }

                for (int i = 0; listaEmail.size() > i; i++) {
                    if (listaEmail.get(i).equals(usuario.getEmail())) {
                        return true;
                    }
                }
                return false;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return !email.equals("-1");
    }

    public static Boolean recuperarLogin(Usuario usuario, Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences(LOGIN_KEY + usuario.getCpf(), 0);

        return mPrefs.getString(LOGIN_SENHA, "-1").equals(usuario.getSenha());
    }


}
