package ch.arc.tp_swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;


public class MyJFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Action openAction, closeAction;
	private JMenuBar menuBar;
	private JMenu fileMenu, aboutMenu;
	private JMenuItem openItem, closeItem, aboutItem;
	
	private JButton startButton;
	private JProgressBar progressBar;
	private JFileChooser openFileDialog;
	private JTextField srcText;
	private JButton openButton;
	private JLabel counterLabel;
	
	public MyJFrame()
	{
		this.setTitle("Tp_Swing");

        this.setSize(400, 200);

        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        CreateObject();

	}
	
	
	private void CreateObject()
	{
		
		menuBar = new JMenuBar();
		
		fileMenu = new JMenu("File");
		aboutMenu = new JMenu("About");
		menuBar.add(fileMenu);
		menuBar.add(aboutMenu);
		
		openItem = new JMenuItem("Open");
		openItem.addActionListener(l -> open());
		closeItem = new JMenuItem("Close");
		closeItem.addActionListener(l -> close());
		aboutItem = new JMenuItem("About");
		aboutItem.addActionListener(l -> about());
		
		fileMenu.add(openItem);
		fileMenu.add(closeItem);		
	    aboutMenu.add(aboutItem);
		this.setJMenuBar(menuBar);
		
		progressBar = new JProgressBar(0,100);
		startButton = new JButton("Start");
		startButton.addActionListener(l -> start());
		openFileDialog = new JFileChooser();
		openFileDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		JLabel chooseLabel = new JLabel("Choose dir :");
		srcText = new JTextField();
		openButton = new JButton("Open");
		openButton.addActionListener(l -> open());
		counterLabel = new JLabel();
		
		JPanel north = new JPanel();
		north.setLayout(new BorderLayout());
		
		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());
		
		this.setLayout(new BorderLayout());
		
		this.add(north, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
        this.add(progressBar, BorderLayout.SOUTH);
        
        north.add(chooseLabel, BorderLayout.WEST);
        north.add(srcText, BorderLayout.CENTER);
        north.add(openButton, BorderLayout.EAST);
        
        center.add(startButton, BorderLayout.SOUTH);
        center.add(counterLabel, BorderLayout.CENTER);
        
	}
	
	 public static void main(String[] args)
	    {
	        MyJFrame frame = new MyJFrame();
	        frame.setVisible(true);
	    }

	 
	 private void start()
	 {
		 Observer obs = new Observer(srcText.getText());
		 obs.addPropertyChangeListener(new PropertyChangeListener()
		    {
		        
		        @Override
		        public void propertyChange(PropertyChangeEvent evt)
		        {
		        	
		            if("progress".equals(evt.getPropertyName()))
		            {
		                counterLabel.setText("" + (Integer) evt.getNewValue());
		            }
		            
		        }
		    });
		 obs.execute();
	 }
	 
	 private void open()
	 {
		 openFileDialog.showOpenDialog(this);
		 srcText.setText(openFileDialog.getSelectedFile().getAbsolutePath());
	 }
	 
	 private void close()
	 {
		 System.exit(0);
	 }
	 
	 private void about()
	 {
		 JOptionPane.showMessageDialog(null, "1 et 1 ça fait 11 et ça c'est beau");
	 }
	 
	 
}

