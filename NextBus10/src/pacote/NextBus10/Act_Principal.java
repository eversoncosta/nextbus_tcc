package pacote.NextBus10;

import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.layout;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;




public class Act_Principal extends Activity implements LocationListener  {

	private LocationManager locationManager;
	List<OnibusModel> onibusColecao = new ArrayList<OnibusModel>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_principal);
		
			    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				3000,   // 3 sec
				10, this);
		
		String endereco = "";
		//ALTERAÇÂO MINIMA
		
		DefaultHttpClient httpClient = new DefaultHttpClient();    
        URI uri;    
        InputStream data = null;    
        try {    
        	   uri = new URI("http://10.0.2.2:8080/Next_Bus/WebGetOnibus?endereco="+endereco);    
               HttpGet method = new HttpGet(uri);    
               HttpResponse response = httpClient.execute(method);
               HttpEntity httpEntity = response.getEntity();
               String output = EntityUtils.toString(httpEntity);
               
               Gson gson = new Gson();
               List<OnibusModel> onibus = (List<OnibusModel>) gson.fromJson(output,new TypeToken<List<OnibusModel>>(){}.getType());
               
               onibusColecao = onibus;
               
               final RadioButton[] rb = new RadioButton[50]; 
   	        RadioGroup rg = (RadioGroup) findViewById(R.id.radiogroup);//not this RadioGroup rg = new RadioGroup(this);
   	        rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
   	           for(int i=0; i<onibus.size(); i++)
   	           {
   	        	   rb[i]  = new RadioButton(this);
   	               rg.addView(rb[i]);
   	               rb[i].setText(onibus.get(i).linha_descricao);
   	               rb[i].setId(i);
   	           }
   	           
   	        Button butao  = (Button) findViewById(R.id.button1);
   			butao.setOnClickListener(new OnClickListener() {
   									
   				@Override
   				public void onClick(View v) {

   					Intent inten1 = new Intent(Act_Principal.this,TempoChegada2.class);
   					Bundle params = new Bundle();
   					
   				 RadioGroup rg = (RadioGroup) findViewById(R.id.radiogroup);
   				 
   				//String selectedRadioValue = ((RadioButton)findViewById(rg.getCheckedRadioButtonId() )).getText().toString();
   				int selectedRadioValue = ((RadioButton)findViewById(rg.getCheckedRadioButtonId() )).getId();
   					
   					//String resposta = selectedRadioValue;
   				String resposta = onibusColecao.get(selectedRadioValue).linha_id;
   	                params.putString("mensagem", resposta);
   	                inten1.putExtras(params);
   					startActivity(inten1);
   				}
   			 });
   			
   			
   			

   		
   		
               
        } catch (Exception e) {    
        	e.printStackTrace();    
        }    
		
	
	}
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void MultiplicarRadioButton()
	{
		
	}

	@Override
	public void onLocationChanged(Location location) {

		 String str = "Latitude: "+location.getLatitude()+" \nLongitude: "+location.getLongitude();
		 //Geocoder coder = new Geocoder(getApplicationContext());
		 Geocoder coder = new Geocoder(this, Locale.getDefault());
		 try
		 {
		 List<Address> addresses = null;
		    //addresses = coder.getFromLocation(location.getLatitude(), location.getLongitude(),1);
		 addresses = coder.getFromLocation(37.25942, -121.99837,1);
		    String teste = "etdf";
		    String outr = teste;
		 }
		 catch (Exception ex)
		 {
			 Toast.makeText(getBaseContext(), ex.toString(), Toast.LENGTH_LONG).show();
		 }
		    
		Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

}