package com.dex.car_compras.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dex.car_compras.config.AuthConfig;
import com.dex.car_compras.R;
import com.dex.car_compras.helper.Base64Custom;
import com.dex.car_compras.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class RegisterActivity extends AppCompatActivity {
    private EditText fieldName;
    private EditText fieldEmail;
    private EditText fieldPass;
    private Button buttonRegister;
    private FirebaseAuth auth;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fieldName = findViewById(R.id.editName);
        fieldEmail = findViewById(R.id.editEmail);
        fieldPass = findViewById(R.id.editPass);
        buttonRegister = findViewById(R.id.buttonCadastrar);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newUser();
            }
        });
    }

    // CONNECT USER FOR FIREBASE
    public void newUser() {
        String textName = fieldName.getText().toString();
        String textEmail = fieldEmail.getText().toString();
        String textPass = fieldPass.getText().toString();

        if (!textName.isEmpty()) {
            if (!textEmail.isEmpty()) {
                if (!textPass.isEmpty()) {
                    user = new User();
                    user.setName(textName);
                    user.setEmail(textEmail);
                    user.setPass(textPass);

                    validateNewUser();
                } else {
                    Toast.makeText(RegisterActivity.this,
                            "Preencha o campo senha",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(RegisterActivity.this,
                        "Preencha o campo e-mail",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(RegisterActivity.this,
                    "Preencha o campo nome",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void validateNewUser() {
        auth = AuthConfig.getAuth();
        auth.createUserWithEmailAndPassword(
                user.getEmail(), user.getPass()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this,
                            "Usuário cadastrado com sucesso!",
                            Toast.LENGTH_SHORT).show();
                    String idUser = Base64Custom.condifyBase64(user.getEmail());
                    user.setIdUser(idUser);
                    user.save();
                    finish();
                } else {
                    // EXCEPTION ERROS NEWUSER
                    exceptionsErro(task);
                }
            }
        });
    }

    public void exceptionsErro(@NonNull Task<AuthResult> task) {
        String exception = "";
        try {
            throw task.getException();
        } catch (FirebaseAuthWeakPasswordException e) {
            exception = "Digite uma senha mais forte";
        } catch (FirebaseAuthInvalidCredentialsException e) {
            exception = "Digite um e-mail válido";
        } catch (FirebaseAuthUserCollisionException e) {
            exception = "Conta já cadastrado, tente cadastrar outra conta";
        } catch (Exception e) {
            exception = "Erro ao cadastrar o usuário: " + e.getMessage();
            e.printStackTrace();
        }
        Toast.makeText(RegisterActivity.this,
                exception,
                Toast.LENGTH_SHORT).show();
    }

}
