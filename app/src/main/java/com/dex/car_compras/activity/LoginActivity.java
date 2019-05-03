package com.dex.car_compras.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dex.car_compras.R;
import com.dex.car_compras.config.AuthConfig;
import com.dex.car_compras.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class LoginActivity extends AppCompatActivity {
    private EditText fieldEmail;
    private EditText fieldPass;
    private Button buttonLogin;
    private User user;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fieldEmail = findViewById(R.id.editEmail);
        fieldPass = findViewById(R.id.editPass);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectUser();
            }
        });

    }

    public void connectUser() {
        String textEmail = fieldEmail.getText().toString();
        String textPass = fieldPass.getText().toString();

        if (!textEmail.isEmpty()) {
            if (!textPass.isEmpty()) {
                user = new User();
                user.setEmail(textEmail);
                user.setPass(textPass);

                validateLogin();
            } else {
                Toast.makeText(LoginActivity.this, "Preencha o campo senha", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(LoginActivity.this, "Preencha o campo e-mail", Toast.LENGTH_SHORT).show();
        }
    }

    public void validateLogin() {
        auth = AuthConfig.getAuth();
        auth.signInWithEmailAndPassword(
                user.getEmail(),
                user.getPass()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this,
                            "Sucesso ao fazer login",
                            Toast.LENGTH_SHORT).show();
                    openHomeActivity();
                } else {
                    // EXCEPTION ERROS CONNECTUSER
                    exceptionsErro(task);
                }
            }
        });
    }

    public void exceptionsErro(@NonNull Task<AuthResult> task) {
        String exception = "";
        try {
            throw task.getException();
        } catch (FirebaseAuthInvalidUserException e) {
            exception = "Usuário não está cadastrado";
        } catch (FirebaseAuthInvalidCredentialsException e) {
            exception = "E-mail e senha inválidos";
        } catch (Exception e) {
            exception = "Erro ao conectar o usuário: " + e.getMessage();
            e.printStackTrace();
        }
        Toast.makeText(LoginActivity.this,
                exception,
                Toast.LENGTH_SHORT).show();
    }

    public void openHomeActivity() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}
