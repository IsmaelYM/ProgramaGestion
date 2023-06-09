package es.studium.Programa_Gestion;

import java.io.IOException;

public class Ayuda {
	
public Ayuda() {
		
		
	}
	
	public void help ()
	{
		try
		{
			Runtime.getRuntime().exec("hh.exe AceiteSL.chm");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
