package itmvu.litmus17;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class first extends ActionBarActivity  implements OnItemSelectedListener{

    private Toolbar mToolbar;
    public static final MediaType FORM_DATA_TYPE
            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    //URL derived from form URL

    public static final String URL="https://docs.google.com/forms/d/1P7d0WUf-ku_PqkfAg7pz74zoX1srOHzzdz2WaBHSa70/formResponse";
    //input element ids found from the live form page
    public static final String NAME_KEY="entry_1851426611";
    public static final String EMAIL_KEY="entry_1268364751";
    public static final String CONTACT_KEY="entry_517635401";
    public static final String ENROLL_KEY="entry_662148348";
    public static final String DEPT_KEY="entry_320346382";
    public static final String TECHNO_KEY="entry_1123308359";
    public static final String CHAKR_KEY="entry_275558133";
    public static final String KALAK_KEY="entry_1276040439";



    private  Context context;
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText contactEditText;
    private EditText enrollEditText;
    public String dept;
    private CheckBox tech,chak,kala;
    public String techno;
    public String chakra;
    public String kalak ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        context =this;


        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("B. Tech");
        categories.add("Integrated B.Tech");
        categories.add("MBA Programs");
        categories.add("BBA Programs");
        categories.add("Health care");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        //Get references to UI elements in the layout
        Button sendButton = (Button)findViewById(R.id.submit);
        nameEditText = (EditText)findViewById(R.id.name);
        emailEditText =  (EditText)findViewById(R.id.email);
        contactEditText = (EditText)findViewById(R.id.contactno);
        enrollEditText = (EditText)findViewById(R.id.enroll);
        tech = (CheckBox)findViewById(R.id.cha1);
        chak = (CheckBox)findViewById(R.id.cha2);
        kala = (CheckBox)findViewById(R.id.cha3);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Make sure all the fields are filled with values
                if (TextUtils.isEmpty(emailEditText.getText().toString()) ||
                        TextUtils.isEmpty(nameEditText.getText().toString()) ||
                        TextUtils.isEmpty(contactEditText.getText().toString()) ||
                        TextUtils.isEmpty(enrollEditText.getText().toString())) {
                    Toast.makeText(context, "All fields are mandatory.", Toast.LENGTH_LONG).show();
                    return;
                }
                //Check if a valid email is entered
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText().toString()).matches()) {
                    Toast.makeText(context, "Please enter a valid email.", Toast.LENGTH_LONG).show();
                    return;
                }

                if((tech.isChecked()||chak.isChecked()||kala.isChecked()))
                {
                    techno = " ";
                }
                else
                {
                    techno = " Not selected any thing ";
                    Toast.makeText(context,"Please tick atleast one event",
                            Toast.LENGTH_SHORT).show();
                    return;

                }

                if(tech.isChecked())
                {
                    techno = " Technovision ";
                }
                else
                {
                    techno = "  ";
                }

                if(chak.isChecked())
                {
                    techno =  techno+" Chakravyuh ";
                }
                else
                {
                    techno = techno + " ";

                }

                if(kala.isChecked())
                {
                    techno = techno + " Kalakriti ";
                }
                else
                {
                    techno = techno + " ";
                }

                //Create an object for PostDataTask AsyncTask
                PostDataTask postDataTask = new PostDataTask();

                //execute asynctask
                postDataTask.execute(URL, nameEditText.getText().toString(),
                        emailEditText.getText().toString(),
                        contactEditText.getText().toString(),
                        enrollEditText.getText().toString(),
                        dept,techno);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.share) {


            String shareBody = getResources().getString(R.string.link);
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Have a look at LITMUS'16, Annual fest of ITMVU. \n\n");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.frwd)));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        dept = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), dept, Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }


    //AsyncTask to send data as a http POST request
    private class PostDataTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... contactData) {
            Boolean result = true;
            String url = contactData[0];
            String name = contactData[1];
            String email = contactData[2];
            String contact = contactData[3];
            String enroll = contactData[4];
            String dept = contactData[5];
            String technovision = contactData[6];


            String postBody="";

            try {
                //all values must be URL encoded to make sure that special characters like & | ",etc.
                //do not cause problems
                postBody = NAME_KEY+"=" + URLEncoder.encode(name, "UTF-8") +
                        "&" + EMAIL_KEY+ "=" + URLEncoder.encode(email,"UTF-8") +
                        "&" + CONTACT_KEY+ "=" + URLEncoder.encode(contact,"UTF-8") +
                        "&" + ENROLL_KEY + "=" + URLEncoder.encode(enroll,"UTF-8")+
                        "&" + DEPT_KEY+ "=" + URLEncoder.encode(dept,"UTF-8") +
                         "&" + TECHNO_KEY+ "=" + URLEncoder.encode(technovision,"UTF-8");
            } catch (UnsupportedEncodingException ex) {
                result=false;
            }

            /*
            //If you want to use HttpRequest class from http://stackoverflow.com/a/2253280/1261816
            try {
			HttpRequest httpRequest = new HttpRequest();
			httpRequest.sendPost(url, postBody);
		}catch (Exception exception){
			result = false;
		}
            */

            try{
                //Create OkHttpClient for sending request
                OkHttpClient client = new OkHttpClient();
                //Create the request body with the help of Media Type
                RequestBody body = RequestBody.create(FORM_DATA_TYPE, postBody);
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                //Send the request
                Response response = client.newCall(request).execute();
            }catch (IOException exception){
                result=false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result){
            //Print Success or failure message accordingly
            Toast.makeText(context,result?"Message successfully sent!":"There was some error in sending message. Please try again after some time.",Toast.LENGTH_LONG).show();
        }

    }
}