package com.github.esthergoldberg.devrantclient;

import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URI;

class MyCookiePolicy implements CookiePolicy {
	public boolean shouldAccept(URI uri, HttpCookie cookie) {
//    String host = uri.getHost();
		//  return host.equals("localhost");
		return true;
	}
}