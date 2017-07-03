package itmvu.litmus17;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    public static final MediaType FORM_DATA_TYPE
            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    //URL derived from form URL
    public static final String URL="https://docs.google.com/forms/d/1PY_Dp0J9Hc15oG40Yj75JO3HdqKLsHC7FCk3A7cc_4s/formResponse";
    //input element ids found from the live form page
    public static final String NAME_KEY="entry_415685491";
    public static final String CONTACT_KEY="entry_1224779713";
    public static final String GENDER_KEY="entry_1036532381";
    public static final String EMAIL_KEY="entry_1304946355";
    public static final String COLLEGE_KEY="entry_292520852";
    public static final String CITY_KEY="entry_1361276158";
    public static final String TECHNO_KEY="entry_494600264";
    public static final String CHAKRA_KEY="entry_1465986407";
    public static final String MAGNIF_KEY="entry_107405282";


    private EditText nameEditText;
    private EditText contactEditText;
    private EditText emailEditText;
    private EditText collegeEditText;
    private EditText cityEditText;
    public String dept;
    private CheckBox t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,c1,c2,c3,c4,c5,c6,c7,c8,c9,m1,m2,m3,m4,m5,m6;
    public String techno;
    public String chakra;
    public String mag= "  ";


    private Toolbar toolbar;
    private Context context;
    public  String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context =this;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Male");
        categories.add("Female");



        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        //Get references to UI elements in the layout
        Button sendButton = (Button)findViewById(R.id.submit);
        nameEditText = (EditText)findViewById(R.id.name);
        emailEditText = (EditText)findViewById(R.id.email);
        contactEditText = (EditText)findViewById(R.id.contactno);
        collegeEditText = (EditText)findViewById(R.id.college);
        cityEditText = (EditText)findViewById(R.id.city);

        t1 = (CheckBox)findViewById(R.id.cha1);
        t2 = (CheckBox)findViewById(R.id.cha2);
        t3 = (CheckBox)findViewById(R.id.cha3);
        t4 = (CheckBox)findViewById(R.id.cha4);
        t5 = (CheckBox)findViewById(R.id.cha5);
        t6 = (CheckBox)findViewById(R.id.cha6);
        t7 = (CheckBox)findViewById(R.id.cha7);
        t8 = (CheckBox)findViewById(R.id.cha8);
        t9 = (CheckBox)findViewById(R.id.cha9);
        t10 = (CheckBox)findViewById(R.id.cha10);
        t11 = (CheckBox)findViewById(R.id.cha11);
        t12 = (CheckBox)findViewById(R.id.cha12);
        t13 = (CheckBox)findViewById(R.id.cha13);

        c1 = (CheckBox)findViewById(R.id.chc1);
        c2 = (CheckBox)findViewById(R.id.chc2);
        c3 = (CheckBox)findViewById(R.id.chc3);
        c4 = (CheckBox)findViewById(R.id.chc4);
        c5 = (CheckBox)findViewById(R.id.chc5);
        c6 = (CheckBox)findViewById(R.id.chc6);
        c7 = (CheckBox)findViewById(R.id.chc7);
        c8 = (CheckBox)findViewById(R.id.chc8);
        c9 = (CheckBox)findViewById(R.id.chc9);

      /*
        m1 = (CheckBox)findViewById(R.id.chb1);
        m2 = (CheckBox)findViewById(R.id.chb2);
        m3 = (CheckBox)findViewById(R.id.chb3);
        m4 = (CheckBox)findViewById(R.id.chb4);
        m5 = (CheckBox)findViewById(R.id.chb5);
        m6 = (CheckBox)findViewById(R.id.chb6);
      */



        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Make sure all the fields are filled with values
                if (TextUtils.isEmpty(emailEditText.getText().toString()) ||
                        TextUtils.isEmpty(nameEditText.getText().toString()) ||
                        TextUtils.isEmpty(contactEditText.getText().toString()) ||
                        TextUtils.isEmpty(cityEditText.getText().toString()) ||
                        TextUtils.isEmpty(collegeEditText.getText().toString())) {
                    Toast.makeText(context, "All Fields are compalsary.", Toast.LENGTH_LONG).show();
                    return;
                }
                //Check if a valid email is entered
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText().toString()).matches()) {
                    Toast.makeText(context, "Please enter a valid email.", Toast.LENGTH_LONG).show();
                    return;
                }

                if((t1.isChecked()||t2.isChecked()||t3.isChecked()||t4.isChecked()||t5.isChecked()||t6.isChecked()||t7.isChecked()||t8.isChecked()||t9.isChecked()||t10.isChecked()||t11.isChecked()||t12.isChecked()||t13.isChecked()||c1.isChecked()||c2.isChecked()||c3.isChecked()||c4.isChecked()||c5.isChecked()||c6.isChecked()||c7.isChecked()||c8.isChecked()))//||m1.isChecked()||m2.isChecked()||m3.isChecked()||m4.isChecked()||m5.isChecked()||m6.isChecked()))
                {
                    techno = " ";
                    chakra = " ";
                    mag = " ";
                }
                else
                {
                    techno = " Not selected any thing ";
                    chakra = " Not selected any thing ";
                    mag = " Not selected any thing ";
                    Toast.makeText(context,"Please tick atleast one event",
                            Toast.LENGTH_SHORT).show();
                    return;

                }

                if(t1.isChecked())
                {
                    techno = techno + " Fury of Tracks;";
                }
                else
                {
                    techno = techno +"  ";
                }

                if(t2.isChecked())
                {
                    techno = techno + " Code to Win;  ";
                }
                else
                {
                    techno = techno + "  ";
                }

                if(t3.isChecked())
                {
                    techno = techno + " Fiery Fighters; ";
                }
                else
                {
                    techno = techno + "  ";
                }

                if(t4.isChecked())
                {
                    techno = techno + " Speeding Machines; ";
                }
                else
                {
                    techno = techno +"  ";
                }

                if(t5.isChecked())
                {
                    techno = techno + " Reclamation; ";
                }
                else
                {
                    techno = techno +"  ";
                }

                if(t6.isChecked())
                {
                    techno = techno + " Construo; ";
                }
                else
                {
                    techno = techno +"  ";
                }

                if(t7.isChecked())
                {
                    techno = techno + " Bridge The Gap;  ";
                }
                else
                {
                    techno = techno +"  ";
                }

                if(t8.isChecked())
                {
                    techno = techno + " Shift to Beat; ";
                }
                else
                {
                    techno = techno +"  ";
                }

                if(t9.isChecked())
                {
                    techno = techno + " Hit it to Win it; ";
                }
                else
                {
                    techno = techno + "  ";
                }


                if(t10.isChecked())
                {
                    techno = techno + " Folder Maze; ";
                }
                else
                {
                    techno = techno +"  ";
                }


                if(t11.isChecked())
                {
                    techno = techno + " Flying Warriors ; ";
                }
                else
                {
                    techno = techno +"  ";
                }


                if(t12.isChecked())
                {
                    techno = techno + " ARTrivia; ";
                }
                else
                {
                    techno = techno +"  ";
                }


                if(t13.isChecked())
                {
                    techno = techno + " Hydrolic Robo Race; ";
                }
                else
                {
                    techno = techno +"  ";
                }



                if(c1.isChecked())
                {
                    chakra=  chakra+" Girls_gully_cricket; ";
                }
                else
                {
                    chakra = chakra + " ";

                }

                if(c2.isChecked())
                {
                    chakra=  chakra+" Cricket; ";
                }
                else
                {
                    chakra = chakra + " ";

                }


                if(c3.isChecked())
                {
                    chakra=  chakra+" Volleyball; ";
                }
                else
                {
                    chakra = chakra + " ";

                }

                if(c4.isChecked())
                {
                    chakra=  chakra+" BasketBall; ";
                }
                else
                {
                    chakra = chakra + " ";

                }

                if(c5.isChecked())
                {
                    chakra=  chakra+" Football ";
                }
                else
                {
                    chakra = chakra + " ";

                }

                if(c6.isChecked())
                {
                    chakra=  chakra+" Athelites; ";
                }
                else
                {
                    chakra = chakra + " ";

                }
                if(c7.isChecked())
                {
                    chakra=  chakra+" Carrom; ";
                }
                else
                {
                    chakra = chakra + " ";

                }

                if(c8.isChecked())
                {
                    chakra=  chakra+" Chess; ";
                }
                else
                {
                    chakra = chakra + " ";

                }

                if(c9.isChecked())
                {
                    chakra=  chakra+" Table Tennis; ";
                }
                else
                {
                    chakra = chakra + " ";

                }



            /*    if(m1.isChecked())
                {
                    mag = mag + " Hare and Hounds; ";
                }
                else
                {
                    mag =  mag + " ";
                }

                if(m2.isChecked())
                {
                    mag = mag + " Wrist wrestling; ";
                }
                else
                {
                    mag =  mag + " ";
                }
                if(m3.isChecked())
                {
                    mag = mag + " 60_sec_fame; ";
                }
                else
                {
                    mag =  mag + " ";
                }
                if(m4.isChecked())
                {
                    mag = mag + " Documentry_film; ";
                }
                else
                {
                    mag =  mag + " ";
                }
                if(m5.isChecked())
                {
                    mag = mag + " photo_maina; ";
                }
                else
                {
                    mag =  mag + " ";
                }if(m6.isChecked())
                {
                    mag = mag + " Fan-ad-tastic; ";
                }
                else
                {
                    mag =  mag + " ";
                }


    */



                //Create an object for PostDataTask AsyncTask
                PostDataTask postDataTask = new PostDataTask();

                //execute asynctask
                postDataTask.execute(URL, nameEditText.getText().toString(),
                        contactEditText.getText().toString(),
                        gender,emailEditText.getText().toString(),
                        collegeEditText.getText().toString(),
                        cityEditText.getText().toString(),
                        techno,chakra,mag);
            }
        });

        /*nameEditText.setText("");
        contactEditText.setText("");
        emailEditText.setText("");
        collegeEditText.setText("");
        cityEditText.setText("");

        t1.setChecked(false);
        t2.setChecked(false);
        t3.setChecked(false);
        t4.setChecked(false);
        t5.setChecked(false);
        t6.setChecked(false);
        t7.setChecked(false);
        t8.setChecked(false);
        t9.setChecked(false);
        t10.setChecked(false);
        t11.setChecked(false);
        t12.setChecked(false);
        t13.setChecked(false);

        GirlsGullyCricket_magnif.setChecked(false);
        c2.setChecked(false);
        c3.setChecked(false);
        c4.setChecked(false);
        c5.setChecked(false);
        c6.setChecked(false);
        c7.setChecked(false);
        c8.setChecked(false); */



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

        gender = parent.getItemAtPosition(position).toString();

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
            String contact = contactData[2];
            String gender = contactData[3];
            String email = contactData[4];
            String college = contactData[5];
            String city1 = contactData[6];
            String technovision = contactData[7];
            String chakravyuh = contactData[8];
            String magnif = contactData[9];


            String postBody="";

            try {
                //all values must be URL encoded to make sure that special characters like & | ",etc.
                //do not cause problems
                postBody = NAME_KEY+"=" + URLEncoder.encode(name, "UTF-8") +
                        "&" + CONTACT_KEY+ "=" + URLEncoder.encode(contact,"UTF-8") +
                        "&" + GENDER_KEY+ "=" + URLEncoder.encode(gender,"UTF-8") +
                        "&" + EMAIL_KEY + "=" + URLEncoder.encode(email,"UTF-8")+
                        "&" + COLLEGE_KEY+ "=" + URLEncoder.encode(college,"UTF-8") +
                        "&" + CITY_KEY+ "=" + URLEncoder.encode(city1,"UTF-8")+
                        "&" + TECHNO_KEY+ "=" + URLEncoder.encode(technovision,"UTF-8")+
                        "&" + CHAKRA_KEY+ "=" + URLEncoder.encode(chakravyuh,"UTF-8")+
                        "&" + MAGNIF_KEY+ "=" + URLEncoder.encode(magnif,"UTF-8");
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


            Toast.makeText(context,result?"Registered successfully!":"Are you not connected to internet??",Toast.LENGTH_LONG).show();
        }

    }
}
