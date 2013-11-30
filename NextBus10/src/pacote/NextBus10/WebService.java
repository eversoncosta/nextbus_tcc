package pacote.NextBus10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.util.Log;

public class WebService {
	private static final int TIMEOUT_CONEXAO = 20000; // 20 segundos
    private static final int TIMEOUT_SOCKET = 30000; // 30 segundos
    private static final int TAM_MAX_BUFFER = 10240; // 10Kbytes
    private String url;
    
    public WebService(String url) {
        this.url = "http://localhost:4848/Design_Time_Addresses/";
    }
    
    public String getEstados(){
        String parserbuilder = "";
        
        try{
            HttpParams httpParameters = new BasicHttpParams();
            
            // Configura o timeout da conex�o em milisegundos at� que a conex�o
            // seja estabelecida
            HttpConnectionParams.setConnectionTimeout(httpParameters, 
                    TIMEOUT_CONEXAO);
            
            // Configura o timeout do socket em milisegundos do tempo 
            // que ser� utilizado para aguardar os dados
            HttpConnectionParams.setSoTimeout(httpParameters, 
                    TIMEOUT_SOCKET);   
            
            HttpClient httpclient = new DefaultHttpClient(httpParameters);
            HttpPost httppost = new HttpPost(url + "/ListarPessoas/4");
    
            HttpResponse response = httpclient.execute(httppost);
            
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent(),
                            "UTF-8"), TAM_MAX_BUFFER);
            
            StringBuilder builder = new StringBuilder();
            
            for (String line = null ; (line = reader.readLine())!= null;) {
                builder.append(line).append("\n");
            }
            
            parserbuilder = builder.toString();
            
            // Retira a string <?xml version="1.0" encoding="utf-8" ?> 
            // <string xmlns="http://tempuri.org/"> e a tag </string> 
            // para obter o resultado em Json, j� que o webservice est�
            // retornando uma string
            Integer firstTagString = parserbuilder.indexOf("<string");
            Integer posXml = parserbuilder.indexOf(">", firstTagString);
            Integer posTagString = parserbuilder.indexOf("</string>");
            parserbuilder = parserbuilder.substring(posXml + 1, posTagString + 1);
        
        }catch(ClientProtocolException e){
            Log.e("WebService", e.toString());
        }
        catch(IOException e){
            Log.e("WebService", e.toString());
        }
        
        return parserbuilder;    
    }

}
