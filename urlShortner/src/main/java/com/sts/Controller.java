package com.sts;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

@org.springframework.stereotype.Controller
public class Controller {
	
	@Autowired
	ShortendUrls shortendUrls;
	
	@GetMapping("/")
	@ResponseBody
	public String home() {
		String baseUrl = 
				ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
		String temp="REST API for url shortner is "+baseUrl+"/url/URLtoBeShortend";
		String temp2="Example: "+baseUrl+"/url/google.com";
		baseUrl=temp+"<br>"+temp2;
		return baseUrl;
	}
	
	@GetMapping("/url/**")
	@ResponseBody
	public String urlShortner() {
		String baseUrl = 
				ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
		String request=ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
		request=request.substring(baseUrl.length()+5);
		
		int hash=request.hashCode()/10_000;
		shortendUrls.urls.put(Math.abs(hash), request);
		return "Shortend url is: "+baseUrl+"/shortend/"+Math.abs(hash);
	}
	
	@GetMapping("/shortend/{hash}")
	public ResponseEntity<Void> visitShortend(@PathVariable int hash) {
		String url="https://"+shortendUrls.urls.get(hash);
		System.out.println(url);
		RedirectView redirectView=new RedirectView(url);
		//return redirectView;
		return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url)).build();
	}
	
}