

import java.io.*;
import java.util.*;

import org.apache.log4j.*;

public class HelloWorld {

	private static Logger logger = Logger.getLogger(HelloWorld.class); 
	private static int i = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		while(true){
			logger.debug(i);
			i++;
			try{
				Thread.sleep(5000);
			} catch (Exception e){
				logger.error("error");
			}
			
		}
		
	}

}
