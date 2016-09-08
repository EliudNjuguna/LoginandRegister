package rhynix.eliud.loginandregister;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    EditText edtusername,edtpassword,edtName,edtAge;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtName= (EditText)findViewById(R.id.edtName);
        edtAge = (EditText)findViewById(R.id.edtAge);
        edtusername = (EditText)findViewById(R.id.edtusername);
        edtpassword = (EditText)findViewById(R.id.edtpassword);
        btnRegister = (Button)findViewById(R.id.btnRegister);

            btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnRegister:

                String name = edtName.getText().toString();
                int age = Integer.parseInt(edtAge.getText().toString());
                String username = edtusername.getText().toString();
                String password = edtpassword.getText().toString();

                User user = new User(name,age,username,password);
                registerUser(user);
                break;
        }
    }
    public void registerUser(User user){
        final ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

    }
}
