package rhynix.eliud.loginandregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtusername,edtName,edtAge;
    Button btnLogout;
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName= (EditText)findViewById(R.id.edtName);
        edtAge = (EditText)findViewById(R.id.edtAge);
        edtusername = (EditText)findViewById(R.id.edtusername);

        userLocalStore = new UserLocalStore(this);


        btnLogout = (Button)findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(this);









        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (authenticate()==true){
            displayUserDetails();
        }else{
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
    }

    private boolean authenticate(){
        return userLocalStore.getUserLoggedIn();
    }
    private void displayUserDetails(){

        User user = userLocalStore.getLoggedInUser();

        edtusername.setText(user.username);
        edtName.setText(user.name);
        edtAge.setText(user.age+"");

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnLogout:

                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);

                startActivity(new Intent(this,LoginActivity.class));
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
