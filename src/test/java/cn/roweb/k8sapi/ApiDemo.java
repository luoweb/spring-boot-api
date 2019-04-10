package cn.roweb.k8sapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.roweb.k8sapi.web.SSLSocketClient;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceBuilder;
import io.fabric8.kubernetes.api.model.Node;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiDemo {
	private final static Logger logger = LoggerFactory.getLogger(ApiDemo.class);
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String master_url = "http://localhost:8001/";
//		master_url = "https://localhost:6443/";
		Config config = new ConfigBuilder().withMasterUrl(master_url).build();
		@SuppressWarnings("resource")
		KubernetesClient client = new DefaultKubernetesClient(config);
		Namespace ns = new NamespaceBuilder().withNewMetadata().withName("thisisatest").addToLabels("this", "rocks").endMetadata().build();
//		logger.info("Create namespace:{}",client.namespaces().create(ns));
		logger.info("*************************************************************");
		logger.info("Get namespace:{}",client.namespaces().withName("thisisatest").get().getSpec());
		logger.info("*************************************************************");
		logger.info("Namespaces list:{}",client.namespaces().list().getItems());
		logger.info("*************************************************************");
		ArrayList arrList = new ArrayList();
		Iterator it = client.nodes().list().getItems().iterator();
		while (it.hasNext()) {
			Node node = (Node) it.next();
			String address = node.getStatus().getAddresses().get(0).getAddress().toString();
			arrList.add(address);
			System.out.println(address);
		}
		
		logger.info("Node address list:{}", arrList.toArray());
		logger.info("*************************************************************");
		Pod pod = new Pod();
		
		
		logger.info("Pod create:", client.pods().inNamespace("test").createOrReplace(pod));
		logger.info("*************************************************************");
		logger.info("CS Status:{}",client.componentstatuses());
		OkHttpClient httpClient = new OkHttpClient().newBuilder()
				.sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), SSLSocketClient.getX509TrustManager())
//				.sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
				.hostnameVerifier(SSLSocketClient.getHostnameVerifier()).build();
		String nodes_api_url = master_url + "/api/v1/nodes";
		Request request = new Request.Builder().url(master_url).get().build();
		KubernetesClient k8sClient = new DefaultKubernetesClient(httpClient, config);
		Call call = httpClient.newCall(request);
		call.enqueue(new Callback() {
			@Override
		    public void onFailure(Call call, IOException e) {
		        logger.info("onFailure: ",e );
		    }

		    public void onResponse(Call call, Response response) throws IOException {
//		    	JSONObject jso = JSON.parseObject(response.body().string());
//		    	JSONArray jsarr=jso.getJSONArray("items");
		        logger.info( "onResponse: " + response.body().string());
		    }
		});
		
	}

}
