package com.dex.car_compras.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dex.car_compras.config.AuthConfig;
import com.dex.car_compras.R;
import com.dex.car_compras.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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
                connectUser();
            }
        });
    }


    public void connectUser(){
        String textName = fieldName.getText().toString();
        String textEmail = fieldEmail.getText().toString();
        String textPass = fieldPass.getText().toString();

        if(!textEmail.isEmpty()){
            if(!textEmail.isEmpty()){
                if (!textPass.isEmpty()){
                    user = new User();
                    user.setName(textName);
                    user.setEmail(textEmail);
                    user.setPass(textPass);
                    newUser();
                } else {
                    Toast.makeText(RegisterActivity.this, "Preencha a senha!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(RegisterActivity.this, "Preencha o email!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(RegisterActivity.this, "Preencha o nome!", Toast.LENGTH_SHORT).show();
        }
    }

    public void newUser(){
        auth = AuthConfig.getAuth();
        auth.createUserWithEmailAndPassword(
                user.getEmail(), user.getPass()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(RegisterActivity.this, "Erro ao cadastrar usuário", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
