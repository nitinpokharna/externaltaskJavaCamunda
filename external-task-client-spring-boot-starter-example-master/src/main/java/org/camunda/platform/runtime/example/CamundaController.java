package org.camunda.platform.runtime.example;

import org.apache.http.entity.BasicHttpEntity;
import org.camunda.bpm.client.impl.ExternalTaskClientBuilderImpl;
import org.camunda.bpm.client.task.impl.ExternalTaskImpl;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Base64;

@RestController
@RequestMapping("/")
public class CamundaController {
	
	
	@PostMapping("create")
	public void create() {
		RestTemplate template = new RestTemplate();
		String base64Credentials = new String(Base64.getEncoder().encode("demo:demo".getBytes()));
		String authorizationHeader = "Basic " + base64Credentials;
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization",authorizationHeader);
	    JSONObject obj = new JSONObject();
		HttpEntity<String> request = new HttpEntity<String>(obj.toString(),headers);
		String str = template.postForObject("http://robs-camunda.centralus.azurecontainer.io:8080/engine-rest/process-definition/key/loan_process/start", request, String.class);
	}

}

