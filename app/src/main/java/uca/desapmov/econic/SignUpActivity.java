package uca.desapmov.econic;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import uca.desapmov.econic.models.*;
import uca.desapmov.econic.R;
import uca.desapmov.econic.data.*;

public class SignUpActivity extends AppCompatActivity {

    TextInputLayout tilFullname, tilEmail, tilPwd, tilConPwd;
    EditText etFullname, etEmail, etPwd, etConPwd;

    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        setup();
    }

    private void setup() {
        tilFullname = findViewById(R.id.txtNombre);
        tilEmail = findViewById(R.id.txtEmail);
        tilPwd = findViewById(R.id.txtPassword);
        tilConPwd = findViewById(R.id.txtPassword2);

        etFullname = tilFullname.getEditText();
        etEmail = tilEmail.getEditText();
        etPwd = tilPwd.getEditText();
        etConPwd = tilConPwd.getEditText();

        Button btnSignUp = findViewById(R.id.btnLogin);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        //loadDefaultDataIfDebug();
    }


    private void signUp() {
        if(!validateFields()) {
            return;
        }

        userModel = new UserModel(etFullname.getText().toString(), etEmail.getText().toString(), etPwd.getText().toString());
        saveUser(userModel);
        navigateToMain(userModel);
    }

    private void saveUser(UserModel user) {
        UserConfig userConfig = new UserConfig(getApplicationContext());
        userConfig.setUser(user);
    }

    private void navigateToMain(UserModel user) {
        Intent intent = new Intent(this, MainActivity.class);
        //la proxima activity ahora será la primera en el back stack
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(MainActivity.FULLNAME_KEY, user.getFullname());
        intent.putExtra(MainActivity.EMAIL_KEY, user.getEmail());
        intent.putExtra(MainActivity.PWD_KEY, user.getPassword());
        startActivity(intent);
    }

    private boolean validateFields() {
        if(etFullname.getText() == null || TextUtils.isEmpty(etFullname.getText().toString())) {
            tilFullname.setError(getString(R.string.fullname_error));
            return false;
        }
        if(etEmail.getText() == null || TextUtils.isEmpty(etEmail.getText().toString())) {
            tilEmail.setError(getString(R.string.email_error));
            return false;
        }
        if(etPwd.getText() == null || TextUtils.isEmpty(etPwd.getText().toString())) {
            tilPwd.setError(getString(R.string.password_error));
            return false;
        }
        if(etConPwd.getText() == null || TextUtils.isEmpty(etConPwd.getText().toString())) {
            tilConPwd.setError(getString(R.string.password_error));
            return false;
        }
        /**if(etConPwd.getText() != etPwd.getText() || TextUtils.isEmpty(etConPwd.getText().toString()) != TextUtils.isEmpty(etPwd.getText().toString())) {
            tilConPwd.setError(getString(R.string.password_confirmacion));
            tilPwd.setError(getString(R.string.password_confirmacion));
            return false;
        }**/

        showMessage("Todo bien, todo correcto, gracias!");
        return true;
    }

    private void showMessage(String message) {
        Toast.makeText(
                this,
                message,
                Toast.LENGTH_LONG
        ).show();
    }

}
