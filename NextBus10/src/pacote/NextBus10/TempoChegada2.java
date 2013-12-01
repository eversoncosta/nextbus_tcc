package pacote.NextBus10;

import java.io.InputStream;
import java.net.URI;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TempoChegada2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_tempo_chegada2);
		
		Intent intent = getIntent();
		  
		  Bundle params = intent.getExtras();  
		   
		  if(params!=null)
		  {   
		   String mostraTexto = params.getString("mensagem");
		   
		   String endereco = "Avenida Duque de Caxias";
		   DefaultHttpClient httpClient = new DefaultHttpClient();    
	        URI uri;    
	        InputStream data = null;    
	        try {    
	        	   uri = new URI("http://10.0.2.2:8080/Next_Bus/WebGetTempo?IdLinha="+mostraTexto);    
	               HttpGet method = new HttpGet(uri);    
	               HttpResponse response = httpClient.execute(method);
	               HttpEntity httpEntity = response.getEntity();
	               String output = EntityUtils.toString(httpEntity);
	               
	               Gson gson = new Gson();
	               OnibusModel onibus = (OnibusModel) gson.fromJson(output,new TypeToken<OnibusModel>(){}.getType());
	               
	      	        EditText edittext  = (EditText) findViewById(R.id.editText1);
	      	        edittext.setText(onibus.onibus_endereco_atual);
	      	        
	      	      EditText tempoChegada  = (EditText) findViewById(R.id.texto_sua_posicao);
	      	      tempoChegada.setText(onibus.tempo_chegada);
	        }
	        catch (Exception ex)
	        {
	        	
	        }
		   
		  }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tempo_chegada2, menu);
		return true;
	}

}
