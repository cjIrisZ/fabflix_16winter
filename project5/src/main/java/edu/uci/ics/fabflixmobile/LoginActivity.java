package edu.uci.ics.fabflixmobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public void connectToTomcat(final View view){

        //

        final Map<String, String> params = new HashMap<String, String>();


        // no user is logged in, so we must connect to the server
        RequestQueue queue = Volley.newRequestQueue(this);

        final Context context = this;
        String url = "http://54.193.91.219:80/fabflix/Mobile_login";
        final String email = ((EditText)findViewById(R.id.login_email)).getText().toString();
        final String password = ((EditText)findViewById(R.id.login_password)).getText().toString();

        System.out.println(email+" and "+password);

        if (email.equals("") || password.equals("")) {Toast.makeText(LoginActivity.this, "email or password cannot be empty", Toast.LENGTH_SHORT).show();}

        else {
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            //Log.d("response", response);
                            //((TextView)findViewById(R.id.http_response)).setText(response);
                            //System.out.println(email+" and "+password + "\n"+response);
                            if(response.trim().equals("ERROR"))
                            {
                                Toast.makeText(LoginActivity.this, "email or password is not correct", Toast.LENGTH_SHORT).show();

                            }
                            else goToSearch(view);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                            Log.d("security.error", error.toString());
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    //Map<String, String> params = new HashMap<String, String>();
                    params.put("email", email);
                    params.put("password", password);
                    return params;
                }
            };



            // Add the request to the RequestQueue.
            queue.add(postRequest);
        }

        return ;
    }

    public void goToSearch(View view){
        //String msg = ((EditText)findViewById(R.id.red_2_blue_message)).getText().toString();

        Intent goToIntent = new Intent(this, FullTextSearchActivity.class);

        goToIntent.putExtra("last_activity", "login");
        //goToIntent.putExtra("message", msg);

        startActivity(goToIntent);
    }


}
