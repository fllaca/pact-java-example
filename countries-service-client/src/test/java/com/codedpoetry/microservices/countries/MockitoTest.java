package com.codedpoetry.microservices.countries;

import static org.mockito.Mockito.*;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class MockitoTest {
	
	public void test(){
		
		String mockString = mock(String.class);
		
		when(mockString.substring(any(Integer.class), any(Integer.class))).then(new Answer() {

			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Object caller = invocation.getMock();
				Object[] arguments = invocation.getArguments();
				
				// Do stuff with arguments and calling object...
				return null;
			}
		});
	}
	
	

}
