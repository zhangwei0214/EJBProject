package me.ben.ejb.test;

import javax.ejb.Remote;

@Remote
public interface HelloWorldRemote {
	public String sayHello(String username);
}
