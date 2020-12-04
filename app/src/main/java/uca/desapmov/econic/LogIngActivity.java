package uca.desapmov.econic;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import uca.desapmov.econic.data.UserConfig;
import uca.desapmov.econic.models.UserModel;

public class LogIngActivity extends AppCompatActivity {

    TextInputLayout tilEmail, tilPwd;
    EditText etEmail, etPwd;

    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        setup();
    }

    private void setup() {
        tilEmail = findViewById(R.id.txtEmail);
        tilPwd = findViewById(R.id.txtPassword);
        etEmail = tilEmail.getEditText();
        etPwd = tilPwd.getEditText();

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

        String same = etEmail.getText().toString();
        userModel = new UserModel(same, etEmail.getText().toString(), etPwd.getText().toString());
        saveUser(userModel);
        navigateToMain(userModel);
    }

    private void saveUser(UserModel user) {
        UserConfig userConfig = new UserConfig(getApplicationContext());
        userConfig.setUser(user);
    }

    private void navigateToMain(UserModel user) {
        Intent intent = new Intent(this, MainActivity.class);
        //    la proxima activity ahora será la primera en el back stack
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(MainActivity.FULLNAME_KEY, user.getFullname());
        intent.putExtra(MainActivity.EMAIL_KEY, user.getEmail());
        intent.putExtra(MainActivity.PWD_KEY, user.getPassword());
        startActivity(intent);
    }

    private boolean validateFields() {

        if(etEmail.getText() == null || TextUtils.isEmpty(etEmail.getText().toString())) {
            tilEmail.setError(getString(R.string.email_error));
            return false;
        }
        if(etPwd.getText() == null || TextUtils.isEmpty(etPwd.getText().toString())) {
            tilPwd.setError(getString(R.string.password_error));
            return false;
        }
        /**if(etConPwd.getText() != etPwd.getText() || TextUtils.isEmpty(etConPwd.getText().toString()) != TextUtils.isEmpty(etPwd.getText().toString())) {
         tilConPwd.setError(getString(R.string.password_confirmacion));
         tilPwd.setError(getString(R.string.password_confirmacion));
         return false;
         }**/
        return true;
    }
}
