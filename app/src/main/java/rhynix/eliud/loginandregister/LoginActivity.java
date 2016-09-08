package rhynix.eliud.loginandregister;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    EditText edtUsername,edtPassword;
    Button btnLogin;
    TextView txtRegisterHere;
    UserLocalStore userLocalStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        txtRegisterHere = (TextView)findViewById(R.id.txtRegisterHere);
        userLocalStore = new UserLocalStore(this);

       btnLogin.setOnClickListener(this);
        txtRegisterHere.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnLogin:
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                User user =  new User(username,password);

                authenticate(user);



                break;

            case R.id.txtRegisterHere:
                startActivity(new Intent(this,RegisterActivity.class));
        }
    }
    public void authenticate(User user){

        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.fetchUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                if (returnedUser == null){
                    showErrorMessage();
                }else{
                    loggedUserIn(returnedUser);
                }
            }
        });

    }
    public void showErrorMessage(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginActivity.this);
        dialogBuilder.setMessage("Incorrect Username or Password");
        dialogBuilder.setPositiveButton("OK",null);
        dialogBuilder.show();
    }
     public void loggedUserIn(User returnedUser){

         userLocalStore.setUserLoggedIn(true);
         userLocalStore.storeUserData(returnedUser);

         startActivity(new Intent(this,MainActivity.class));
     }
}
