import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class Client {
	private HttpClient client;
	private final String BASEURL="https://sandbox.platfr.io/";
	private final static String AuthSchema = "S2S";
	public Client ()
	{
		client=HttpClientBuilder.create().build();
	}
	public double Balance(long userID) {
		//creo la url con l'accountId
		String api="api/gbs/banking/v2/accounts/userId/balance";
		api=api.replace("userId",""+userID);
		String url= BASEURL+""+api;
		//System.out.println("url: "+url);
		//creo la request alle API
		HttpGet request= new HttpGet(url);
		request.setHeader("Auth-Schema", AuthSchema);
		request.setHeader("accept","application/json");
		try {
		HttpResponse response = client.execute(request);
		//parsifico la response in JSON
		String json = EntityUtils.toString(response.getEntity());
		JSONObject obj= new JSONObject(json);
		JSONArray payload =(JSONArray) obj.get("payload");
		obj=payload.getJSONObject(0);	
		double balance = obj.getDouble("balance");
		return balance;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return -1;
		}
		//double x= Double.parseDouble((String) balance.get(1));
		
	}
	public int Bonifico (long userID )
	{
		//creo la url con l'accountId
		String api="api/gbs/banking/v2.1/accounts/accountId/payments/sct/orders";
		api=api.replace("accountId",""+userID);
		String url= BASEURL+""+api;
		//setto la httpPost
		/*List<NameValuePair> bonifico= new ArrayList<NameValuePair>();
		//bonifico.add(new BasicNameValuePair("accountId","14930637"));
		bonifico.add(new BasicNameValuePair("receiverName",""));
		bonifico.add(new BasicNameValuePair("description",""));
		bonifico.add(new BasicNameValuePair("currency",""));
		bonifico.add(new BasicNameValuePair("amount",""));
		bonifico.add(new BasicNameValuePair("executionDate",""));*/
		HttpPost request= new HttpPost(url);
		request.setHeader("Auth-Schema", AuthSchema);
		try {
			//request.setEntity(new UrlEncodedFormEntity(bonifico));
			HttpResponse response = client.execute(request);
			return response.getStatusLine().getStatusCode();
			}
		
		catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}
}
