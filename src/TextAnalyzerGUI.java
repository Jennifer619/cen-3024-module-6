import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import javax.swing.JScrollPane;

public class TextAnalyzerGUI {
	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextAnalyzerGUI window = new TextAnalyzerGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				} // end of try catch
			} // end of run
		}); // end of event queue
	} // end of main
	/**
	 * Create the application.
	 */
	public TextAnalyzerGUI() {
		initialize();
	} // end of gui
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// window
		frame = new JFrame();
		frame.setBounds(100, 100, 810, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		// poem word frequency
		JButton btnNewButton_1 = new JButton("Top 20 Most Used Words in The Raven");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnNewButton_1.setBounds(265, 150, 265, 170);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					/** Reading file line by line */
					File file = new File("src\\TheRaven.txt");
					Scanner scan = null;
					try {
						scan = new Scanner(file);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					/**
					 * map to store key value pair
					 * key : word
					 * value: frequency of the word
					 */
					Map<String,Integer> map = new HashMap<String, Integer>(); 
					while (scan.hasNext()) {
						// reading line by line
						String val = scan.next(); 
						// if the string is not inserted in the map yet then insert by setting the frequency as 1
						if (map.containsKey(val) == false) 
							map.put(val,1);
						// otherwise remove the entry from map and again insert by adding 1 in the frequency
						else { 
							// finding the current frequency of the word
							int count = (int)(map.get(val)); 
							// removing the entry from the map
							map.remove(val);  
							// reinserting the word and increase frequency by 1
							map.put(val,count+1); 
						} // end of else
					} // end of while
					// retrieving the map contents
					Set<Map.Entry<String, Integer>> set = map.entrySet(); 
					// make an array list
					List<Map.Entry<String, Integer>> sortedList = new ArrayList<Map.Entry<String, Integer>>(set);  
					// sorting the array list
					Collections.sort(sortedList, new Comparator<Map.Entry<String, Integer>>() { 
						// comparator function for sorting
						public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) { 
							// for descending order
							return (b.getValue()).compareTo(a.getValue());   
						} // end of compare
					}); // end of sort
					// printing the list
					JOptionPane.showMessageDialog(null, sortedList);
			} // end of action event
		}); // end of action listener
		frame.getContentPane().add(btnNewButton_1);
		// header
		JLabel lblNewLabel = new JLabel("            Click to continue");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(265, 90, 265, 24);
		frame.getContentPane().add(lblNewLabel);
		

	} // end of initialize
} // end of class
