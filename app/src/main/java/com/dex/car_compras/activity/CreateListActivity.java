package com.dex.car_compras.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dex.car_compras.R;
import com.dex.car_compras.config.AuthConfig;
import com.dex.car_compras.helper.Base64Custom;
import com.dex.car_compras.helper.DateUtil;
import com.dex.car_compras.model.MovimentacaoT;
import com.dex.car_compras.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class CreateListActivity extends AppCompatActivity {
    private TextView fieldDate;
    private MovimentacaoT mov;
    private TextInputEditText campoData, campoCategoria, campoDescricao;
    private EditText campoValor;
    private DatabaseReference firebase = AuthConfig.getFirebase();
    private FirebaseAuth auth = AuthConfig.getAuth();
    private double despesaTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);

        campoValor = findViewById(R.id.editValue);
        campoData = findViewById(R.id.editDate);
        campoCategoria = findViewById(R.id.editCat);
        campoDescricao = findViewById(R.id.editDesc);

        campoData.setText(DateUtil.dateNow());

    }

    public void salvarDespesa(View view) {
        if (validarCampos()) {
            mov = new MovimentacaoT();
            String d = campoData.getText().toString();
            mov.setValue(Double.parseDouble(campoValor.getText().toString()));
            mov.setCategory(campoCategoria.getText().toString());
            mov.setDescription(campoDescricao.getText().toString());
            mov.setDate(campoData.getText().toString());
            mov.setType("d");

            mov.salvar(d);
        }

    }

    public Boolean validarCampos() {
        String textoValor = campoValor.getText().toString();
        String textoDescricao = campoDescricao.getText().toString();
        String textoCategoria = campoCategoria.getText().toString();
        String textoData = campoData.getText().toString();

        if (!textoValor.isEmpty()) {
            if (!textoData.isEmpty()) {
                if (!textoCategoria.isEmpty()) {
                    if (!textoDescricao.isEmpty()) {
                        return true;

                    } else {
                        Toast.makeText(CreateListActivity.this, "Descrição não foi preenchido", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                } else {
                    Toast.makeText(CreateListActivity.this, "Categoria não foi preenchido", Toast.LENGTH_SHORT).show();
                    ;
                    return false;
                }

            } else {
                Toast.makeText(CreateListActivity.this, "Data não foi preenchido", Toast.LENGTH_SHORT).show();
                ;
                return false;
            }
        } else {
            Toast.makeText(CreateListActivity.this, "Valor não foi preenchido", Toast.LENGTH_SHORT).show();
            ;
            return false;
        }
    }

    public void recuperarDespesaTotal(){
        DatabaseReference usuarioRef = firebase.child("users").child(Base64Custom.condifyBase64(auth.getCurrentUser().getEmail()));

        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                despesaTotal = user.getValorTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
