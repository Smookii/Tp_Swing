package ch.arc.tp_swing;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.List;

import javax.swing.SwingWorker;

public class Observer extends SwingWorker<Integer,Integer>{

	String src = "";
	int count = 0;
	
	public Observer(String _src)
	{
		src = _src;
	}
	
	@Override
	protected Integer doInBackground() throws Exception {
		getFile(src);
		return null;
	}
	
	private void getFile(String srcString) {
	    File f = new File(srcString);
	    File[] files = f.listFiles();

	    if (files != null)
	    for (int i = 0; i < files.length; i++) {
	    	count++;
	        File file = files[i];
	        
	        if (file.isDirectory()) {   
	             getFile(file.getAbsolutePath()); 
	        }
	    }
	} 
	
	//Problème à ce niveau
	@Override
	protected void process(List<Integer> chuncks)
	{
		int a = chuncks.get(chuncks.size() - 1);
		
	}
	
}
