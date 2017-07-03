package itmvu.litmus17;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

public class RegisterTechnovision extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Toolbar toolbar;
    private Context context;
    // register Technovision

    private Toolbar mToolbar;
    public static final MediaType FORM_DATA_TYPE
            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    //URL derived from form URL

    public static final String URL="https://docs.google.com/forms/d/1kLVJHFSs5g7omR3mypg4UO5dkBX_Ax-OIZem5hmi5ME/formResponse";

    //input element ids found from the live form page

    public static final String NAME_KEY="entry_1004369131";
    public static final String NAME_KEY1="entry_719297224";
    public static final String NAME_KEY2="entry_70347215";
    public static final String NAME_KEY3="entry_1861849072";
    public static final String NAME_KEY4="entry_517917920";
    public static final String CONTACT_KEY="entry_624515683";
    public static final String EMAIL_KEY="entry_34918404";
    public static final String COLLEGE_KEY="entry_1646565045";
    public static final String CITY_KEY="entry_606829223";
    public static final String TECHNO_KEY="entry_139681025";


    private EditText nameEditText;
    private EditText nameEditText1;
    private EditText nameEditText2;
    private EditText nameEditText3;
    private EditText nameEditText4;
    private EditText contactEditText;
    private EditText emailEditText;
    private EditText collegeEditText;
    private EditText cityEditText;
    public String  techno;
    public String  x1;
    public String  x2;
    public String  x3;
    public String  x4;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerp);

        toolbar =  (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context =this;


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Fury of Tracks");
        categories.add("Code To Win");
        categories.add("FIERY FIGHTERS");
        categories.add("SPEEDING MACHINES");
        categories.add("FLYING WARRIORS");
        categories.add("Construo");
        categories.add("Bridge The Gap");
        categories.add("Shift To Beat");
        categories.add("Hit it to Win it");
        categories.add("Folder Maze");
        categories.add("FLYING WARRIORS");
        categories.add("Reclamation");
        categories.add("ARTRIVIA");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        //Get references to UI elements in the layout
        Button sendButton = (Button)findViewById(R.id.submit);
        nameEditText = (EditText)findViewById(R.id.name);
        nameEditText1= (EditText)findViewById(R.id.name2);
        nameEditText2=(EditText)findViewById(R.id.name3);
        nameEditText3=(EditText)findViewById(R.id.name4);
        nameEditText4=(EditText)findViewById(R.id.name5);
        emailEditText = (EditText)findViewById(R.id.email);
        contactEditText = (EditText)findViewById(R.id.contactno);
        collegeEditText = (EditText)findViewById(R.id.college);
        cityEditText = (EditText)findViewById(R.id.city2);





        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Make sure all the fields are filled with values
                if (TextUtils.isEmpty(emailEditText.getText().toString()) ||
                        TextUtils.isEmpty(nameEditText.getText().toString()) ||
                        TextUtils.isEmpty(contactEditText.getText().toString()) ||
                        TextUtils.isEmpty(cityEditText.getText().toString()) ||
                        TextUtils.isEmpty(collegeEditText.getText().toString())) {
                    Toast.makeText(context, "Fill all the compalsary Fields..!!.", Toast.LENGTH_LONG).show();
                    return;
                }
                //Check if a valid email is entered
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText().toString()).matches()) {
                    Toast.makeText(context, "Please enter a valid email.", Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(nameEditText1.getText().toString()))
                {
                    x1 = " ";

                }

                else

                {
                    x1 = nameEditText1.getText().toString();
                }

                if(TextUtils.isEmpty(nameEditText2.getText().toString()))
                {
                    x2 = " ";

                }

                else

                {
                    x2 = nameEditText2.getText().toString();
                }

                if(TextUtils.isEmpty(nameEditText3.getText().toString()))
                {
                    x3 = " ";

                }

                else

                {
                    x3 = nameEditText3.getText().toString();
                }

                if(TextUtils.isEmpty(nameEditText4.getText().toString()))
                {
                    x4 = " ";

                }

                else

                {
                    x4 = nameEditText4.getText().toString();
                }


                //Create an object for PostDataTask AsyncTask
                PostDataTask postDataTask = new PostDataTask();

                //execute asynctask
                postDataTask.execute(URL, nameEditText.getText().toString(),
                        x1,
                        x2,
                        x3,
                        x4,
                        contactEditText.getText().toString(),
                        emailEditText.getText().toString(),
                        collegeEditText.getText().toString(),
                        cityEditText.getText().toString(),
                        techno);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Have a look at LITMUS'17, Annual fest of ITMVU. \n\n");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.frwd)));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item

        techno = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), dept, Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }


    //AsyncTask to send data as a http POST request
    private class PostDataTask extends AsyncTask<String, Void, Boolean> {


        private ProgressDialog dialog = new ProgressDialog(RegisterTechnovision.this);

        protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            dialog.setCanceledOnTouchOutside(false);
            this.dialog.show();

        }

        @Override
        protected Boolean doInBackground(String... contactData) {
            Boolean result = true;
            String url = contactData[0];
            String name = contactData[1];
            String name1 = contactData[2];
            String name2 = contactData[3];
            String name3 = contactData[4];
            String name4 = contactData[5];
            String contact = contactData[6];
            String email = contactData[7];
            String college = contactData[8];
            String city1 = contactData[9];
            String tech = contactData[10];


            String postBody="";

            try {
                //all values must be URL encoded to make sure that special characters like & | ",etc.
                //do not cause problems
                postBody = NAME_KEY+"=" + URLEncoder.encode(name, "UTF-8") +
                        "&" + NAME_KEY1+ "=" + URLEncoder.encode(name1,"UTF-8") +
                        "&" + NAME_KEY2+ "=" + URLEncoder.encode(name2,"UTF-8") +
                        "&" + NAME_KEY3+ "=" + URLEncoder.encode(name3,"UTF-8") +
                        "&" + NAME_KEY4+ "=" + URLEncoder.encode(name4,"UTF-8") +
                        "&" + CONTACT_KEY+ "=" + URLEncoder.encode(contact,"UTF-8") +
                        "&" + EMAIL_KEY + "=" + URLEncoder.encode(email,"UTF-8")+
                        "&" + COLLEGE_KEY+ "=" + URLEncoder.encode(college,"UTF-8") +
                        "&" + CITY_KEY+ "=" + URLEncoder.encode(city1,"UTF-8")+
                        "&" + TECHNO_KEY+ "=" + URLEncoder.encode(tech,"UTF-8");

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
            Toast.makeText(context,result?"Registered successfully !":"There was some error in sending message. Please try again after some time.",Toast.LENGTH_LONG).show();
        }

    }
}



