package com.ifugle.test;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;

public class EjbTestContext implements InitialContextFactory{

	@Override
	public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException {
		// TODO Auto-generated method stub
		return null;
	}
	
}

