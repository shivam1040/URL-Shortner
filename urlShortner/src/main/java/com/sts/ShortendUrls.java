package com.sts;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ShortendUrls {
	Map<Integer, String> urls;

	public ShortendUrls() {
		super();
		urls=new HashMap<Integer, String>();
		// TODO Auto-generated constructor stub
	}
	
}
