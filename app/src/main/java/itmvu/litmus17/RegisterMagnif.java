package itmvu.litmus17;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class RegisterMagnif extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Toolbar toolbar;
    private Context context;
//mgnif regestration

    private Toolbar mToolbar;
    public static final MediaType FORM_DATA_TYPE
            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    //URL derived from form URL

    public static final String URL="https://docs.google.com/forms/d/12AeTSwmWMAxqj_y9qDWhfqZS1FzIeytsC979Ab__HnA/formResponse";

    //input element ids found from the live form page

    public static final String NAME_KEY="entry_1298155033";
    public static final String CONTACT_KEY="entry_1917124163";
    public static final String EMAIL_KEY="entry_1296921257";
    public static final String COLLEGE_KEY="entry_490540615";
    public static final String GENDER_KEY="entry_1438524466";
    public static final String TECHNO_KEY="entry_316755037";


    private EditText nameEditText;
    private EditText contactEditText;
    private EditText emailEditText;
    private EditText collegeEditText;
    private EditText cityEditText;
    public String  techno;
    //public String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerm);

        toolbar =  (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context =this;

        // Spinner element
       // Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);

        // Spinner click listener
        //spinner.setOnItemSelectedListener(this);
        spinner1.setOnItemSelectedListener(this);

        //List<String> categories1 = new ArrayList<String>();
        //categories1.add("Male");
        //categories1.add("Female");


        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("PAINT YOUR IMAGINATION");
        categories.add("Documentary Film");
        categories.add("Treasure Hunt");
        categories.add("AD-Selfie");
        categories.add("BIZTECH");
        categories.add("Fun Race");
        categories.add("WRIST WRESTLING");




        // Creating adapter for spinner
        //ArrayAdapter<String> dataAdapter = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, categories1);
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        //dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
       // spinner.setAdapter(dataAdapter);
        spinner1.setAdapter(dataAdapter1);


        //Get references to UI elements in the layout
        Button sendButton = (Button)findViewById(R.id.submit);
        nameEditText = (EditText)findViewById(R.id.name);
        emailEditText =  (EditText)findViewById(R.id.email);
        contactEditText = (EditText)findViewById(R.id.contactno);
        collegeEditText = (EditText)findViewById(R.id.college);
        cityEditText = (EditText)findViewById(R.id.city);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Make sure all the fields are filled with values
                if (TextUtils.isEmpty(emailEditText.getText().toString()) ||
                        TextUtils.isEmpty(nameEditText.getText().toString()) ||
                        TextUtils.isEmpty(contactEditText.getText().toString()) ||
                        TextUtils.isEmpty(cityEditText.getText().toString()) ||
                        TextUtils.isEmpty(collegeEditText.getText().toString())) {
                    Toast.makeText(context, "All fields are mandatory.", Toast.LENGTH_LONG).show();
                    return;
                }
                //Check if a valid email is entered
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText().toString()).matches()) {
                    Toast.makeText(context, "Please enter a valid email.", Toast.LENGTH_LONG).show();
                    return;
                }



                //Create an object for PostDataTask AsyncTask
                PostDataTask postDataTask = new PostDataTask();

                //execute asynctask
                postDataTask.execute(URL, nameEditText.getText().toString(),
                        contactEditText.getText().toString(),
                        emailEditText.getText().toString(),
                        collegeEditText.getText().toString(),
                        cityEditText.getText().toString(),techno);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registerp, menu);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item

        switch (parent.getId())
        {
            //case R.id.spinner :  = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), gender, Toast.LENGTH_LONG).show();
              //  break;
            case R.id.spinner1 : techno = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), techno, Toast.LENGTH_LONG).show();
                break;
        }
        // Showing selected spinner item
        //  Toast.makeText(parent.getContext(), dept, Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }



    //AsyncTask to send data as a http POST request
    private class PostDataTask extends AsyncTask<String, Void, Boolean> {


        private ProgressDialog dialog = new ProgressDialog(RegisterMagnif.this);

        protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();
            dialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected Boolean doInBackground(String... contactData) {
            Boolean result = true;
            String url = contactData[0];
            String name = contactData[1];
            String contact = contactData[2];
            String email = contactData[3];
            String college = contactData[4];
            String gender = contactData[5];
            String techno = contactData[6];



            String postBody="";

            try {
                //all values must be URL encoded to make sure that special characters like & | ",etc.
                //do not cause problems
                postBody = NAME_KEY+"=" + URLEncoder.encode(name, "UTF-8") +
                        "&" + CONTACT_KEY+ "=" + URLEncoder.encode(contact,"UTF-8") +
                        "&" + EMAIL_KEY+ "=" + URLEncoder.encode(email,"UTF-8") +
                        "&" + COLLEGE_KEY + "=" + URLEncoder.encode(college,"UTF-8")+
                        "&" + GENDER_KEY+ "=" + URLEncoder.encode(gender,"UTF-8") +
                        "&" + TECHNO_KEY+ "=" + URLEncoder.encode(techno,"UTF-8");
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

            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            //Print Success or failure message accordingly
            Toast.makeText(context,result?"Message successfully sent!":"There was some error in sending message. Please try again after some time.",Toast.LENGTH_LONG).show();
        }

    }
}
