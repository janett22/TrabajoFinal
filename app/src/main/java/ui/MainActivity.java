package ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.trabajofinal.R;

public class MainActivity extends AppCompatActivity {
    private EditText etUsuario, etContrasenia;
    private Button btLogin;
    private MainActivityViewModel vm;


    //login no funciona

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        inicializarVista();
        vm.getResultadoMutable().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String mensaje) {
                new AlertDialog.Builder(getApplicationContext())
                        .setTitle("Advertencia!")
                        .setMessage(mensaje)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {

                            }
                        });

            }
        });

        vm.getOkMutable().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Intent intent = new Intent(getApplicationContext(),MenuNavegation.class);
                startActivity(intent);
            }
        });

    }
    private void inicializarVista(){
        etUsuario=findViewById(R.id.etUsuario);
        etContrasenia=findViewById(R.id.etContrasenia);
        btLogin=findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                vm.verificarDatos(etUsuario.getText().toString(),etContrasenia.getText().toString());
            }
        });
    }
}
