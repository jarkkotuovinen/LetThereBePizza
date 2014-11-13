import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
//import java.io.IOException;

public class LetThereBePizza
		extends 	JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private		JTabbedPane tabbedPane;
	private		JPanel		panel1;
	private		JPanel		panel2;
	private		JPanel		panel3;
	public JMenuBar menuBar;
	public JMenuItem menuItem;
	private JCheckBox QuattroStaggioni = new JCheckBox("Quattro staggioni (kinkku,ananas,aurajuusto) 7€");
	private JCheckBox KebabPizza = new JCheckBox("Kebabpizza (kebab,jalapeno) 7€");
	private JCheckBox SalamiPizza = new JCheckBox("Salamipizza (salami,jalapeno) 7€");
	private JCheckBox Pollo = new JCheckBox("Pollo (kana, aurajuusto, feta) 7€");
	private JCheckBox Pekoni = new JCheckBox("Pekonipizza (pekoni, kananmuna) 7€");
	private JCheckBox TuttiFrutti = new JCheckBox("Tutti Frutti (pekoni,salami,palvikinkku) 7€");
	
	JTextField textFieldSum = new JTextField(10);
	JTextArea pizzatSum = new JTextArea();
    JTextField nimi = new JTextField();
    JTextField osoite = new JTextField();
    String[] juomat = { "Cola", "Fanta", "Sprite", "Jaffa", "Pepsi" };
    JComboBox cb = new JComboBox(juomat);
	private int sum = 0;	// sum of pizzas
    private String quattropizza = "";
    private String kebabpizza = "";
    private String salamipizza = "";
    private String pollopizza = "";
    private String pekonipizza = "";
    private String tuttifruttipizza = "";
    
	public LetThereBePizza()
	{
		// Splash screen
		
		setTitle( "Let There Be Pizza" );
		setSize( 800, 600 );
		setBackground( Color.white );
		
		JWindow window = new JWindow();
		window.getContentPane().add(
		    new JLabel("", new ImageIcon("images/PizzaMaster.gif"), SwingConstants.CENTER));
		window.setSize(800, 600 );
		window.setBackground(Color.blue);
		window.setVisible(true);
		try {
		    Thread.sleep(5000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		window.setVisible(false);
		
		// Layout
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );

		// Create the tab pages
		createPage1();
		createPage2();
		createPage3();

	    // Create a menu bar and give it a bevel border.
	    menuBar = new JMenuBar();
	    menuBar.setBorder(new BevelBorder(BevelBorder.RAISED));

	    // Create a menu and add it to the menu bar.
	    JMenu menu = new JMenu("Menu");
	    menuBar.add(menu);
			    
	    // A group of JMenuItems
	    menuItem = new JMenuItem("Help");
	    menu.add(menuItem);
		
		// Create a tabbed pane
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab( "Liha Pizza", panel1 );
		tabbedPane.addTab( "Vege Pizza", panel2 );
		tabbedPane.addTab( "Oma Valinta", panel3 );
		topPanel.add( tabbedPane, BorderLayout.CENTER );
		
	}
	// Page 1
	public void createPage1()
	{
		// Layout
		
		panel1 = new JPanel();
		panel1.setLayout( new GridLayout( 0, 2 ) );

		// Contact info
		
		panel1.add( new JLabel( "Nimi:" ) );
		panel1.add( nimi );
		nimi.addActionListener(this);
	    nimi.getText();

		panel1.add( new JLabel( "Osoite:" ) );
		panel1.add( osoite);
		osoite.addActionListener(this);
	    osoite.getText();
		
		panel1.add( new JLabel( "Puhelinnumero:" ) );

	    // Check boxes
	    
		panel1.add( new JTextField() );
		panel1.add(QuattroStaggioni);
		panel1.add(KebabPizza);
		panel1.add(Pollo);
	    panel1.add(SalamiPizza);
	    panel1.add(Pekoni);
	    panel1.add(TuttiFrutti);
		panel1.add( new JLabel( "Juoma (kuuluu hintaan):" ) );
		panel1.add( cb);
		cb.addActionListener(this);
		cb.getSelectedItem(); 
		
		// Add action listener for the check boxes
		ActionListener actionListener = new ActionHandler(); 
		QuattroStaggioni.addActionListener(actionListener);
		KebabPizza.addActionListener(actionListener);
		SalamiPizza.addActionListener(actionListener);
		Pollo.addActionListener(actionListener);
		Pekoni.addActionListener(actionListener);
		TuttiFrutti.addActionListener(actionListener);
		
		// Pizza price sum
		final JLabel labelSum = new JLabel("Kokonaishinta (€): ");
		panel1.add(labelSum);
		textFieldSum.setEditable(false);
		panel1.add(textFieldSum);
		textFieldSum.getText();
		
		// Tilaa button
	      JButton TilaaButton = new JButton("Tilaa");          
	          TilaaButton.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	        	  
	        	  Object[] options = {"Kyllä",
                  "Ei"};
	              int result = JOptionPane.showOptionDialog(
	            		    (Component)e.getSource(),
	            		    "Arvon " + nimi.getText() + "\n" + "Haluatteko varmasti tilata seuraavat herkut?"+ "\n" + "Pizza(t):"+ pizzatSum.getText() + "\n" + "Juoma: "+ cb.getSelectedItem() + "\n" + "Osoitteeseen: " + osoite.getText() + "\n" + "Yhteishintaan: "+ textFieldSum.getText() + " €",
	            		    "Tilauksen varmistus",
	            		    JOptionPane.YES_NO_OPTION,
	            		    JOptionPane.QUESTION_MESSAGE,
	            		    null,
	              			options,
	              			options[0]);
	              if (result == JOptionPane.YES_OPTION) {
		        	  JOptionPane.showMessageDialog( (Component)e.getSource(), "Kiitos tilauksestanne! " + nimi.getText() + "\n" + "Pizzanne toimitetaan osoitteeseen: " + osoite.getText() ,"Tilausvahvistus", JOptionPane.PLAIN_MESSAGE);
		                System.exit(0);
	              } 
	          }          
	       });
	     
	    //Peruuta button
		      JButton PeruutaButton = new JButton("Poistu");
	          PeruutaButton.addActionListener(new ActionListener() {
		          public void actionPerformed(ActionEvent e) {

		        	  Object[] options = {"Kyllä",
	                    "Ei"};
		              int result = JOptionPane.showOptionDialog(
		            		    (Component)e.getSource(),
		            		    "Haluatteko varmasti poistua pizzantilauksesta?",
		            		    "Poistu pizzan tilauksesta",
		            		    JOptionPane.YES_NO_OPTION,
		            		    JOptionPane.QUESTION_MESSAGE,
		            		    null,
		              			options,
		              			options[0]);
		              if (result == JOptionPane.YES_OPTION) {
		                System.exit(0);
		              } 
		        }});

	    panel1.add(TilaaButton);
	    panel1.add(PeruutaButton);
	}
	
	// Page 2

	public void createPage2()
	{
		panel2 = new JPanel();
		panel2.setLayout( new GridLayout( 0, 2 ) );
		panel2.add( new JLabel( "Nimi:" ) );
		panel2.add( new TextArea() );
		panel2.add( new JLabel( "Osoite:" ) );
		panel2.add( new TextArea() );
		panel2.add( new JLabel( "Puhelinnumero:" ) );
		panel2.add( new TextArea() );
		panel2.add(new JCheckBox("Tomaatti"));
	    panel2.add(new JCheckBox("Juusto"));
	    panel2.add(new JCheckBox("Ananas"));
	    panel2.add(new JCheckBox("Aurajuusto"));
	    panel2.add(new JCheckBox("Ruohosipuli"));
	    panel2.add(new JCheckBox("Fetajuusto"));
	    panel2.add( new JButton("Tilaa"));
	    panel2.add( new JButton("Peruuta"));
	}
	
	// Page 3

	public void createPage3()
	{
		panel3 = new JPanel();
		panel3.setLayout( new GridLayout( 0, 2 ) );

		panel3.add( new JLabel( "Nimi:" ) );
		panel3.add( new TextArea() );
		panel3.add( new JLabel( "Osoite:" ) );
		panel3.add( new TextArea() );
		panel3.add( new JLabel( "Puhelinnumero:" ) );
		panel3.add( new TextArea() );
	    panel3.add(new JCheckBox("Tonnikala"));
	    panel3.add(new JCheckBox("Kebab"));
	    panel3.add(new JCheckBox("Kinkku"));
	    panel3.add(new JCheckBox("Salami"));
	    panel3.add(new JCheckBox("Pekoni"));
	    panel3.add(new JCheckBox("Kananmuna"));
	    panel3.add( new JButton("Tilaa"));
	    panel3.add( new JButton("Peruuta"));

	}

    // Main method 
	public static void main( String args[] )
	{
		// Create an instance of the test application
	       //Create and set up the content pane.
		LetThereBePizza mainFrame	= new LetThereBePizza();
	    // mainFrame.setJMenubar(mainFrame.createMenuBar());
	    mainFrame.setJMenuBar(mainFrame.menuBar);

		mainFrame.setVisible( true );
	}
	
	// Pizza calculator
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}
	class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			JCheckBox checkbox = (JCheckBox) event.getSource();
			if (checkbox.isSelected()) {
				if (checkbox == QuattroStaggioni) {
					sum += 7;
					quattropizza = "\n" + " Quattro staggioni (kinkku,ananas,aurajuusto) 7€";
				} else if (checkbox == KebabPizza) {
					sum += 7;
					kebabpizza = "\n" + " Kebabpizza (kebab,jalapeno) 7€";
				} else if (checkbox == SalamiPizza) {
					sum += 7;
					salamipizza = "\n" + " Salamipizza (salami,jalapeno) 7€";
				}
				else if (checkbox == Pollo) {
					sum += 7;
					pollopizza = "\n" + " Pollo (kana, aurajuusto, feta) 7€";
				}
				else if (checkbox == Pekoni) {
					sum += 7;
					pekonipizza = "\n" + " Pekonipizza (pekoni, kananmuna) 7€";
				}
				else if (checkbox == TuttiFrutti) {
					sum += 7;
					tuttifruttipizza = "\n" + " Tutti Frutti (pekoni,salami,palvikinkku) 7€";
				}
				
			} else {
				if (checkbox == QuattroStaggioni) {
					sum -= 7;
					quattropizza = "";
				} else if (checkbox == KebabPizza) {
					sum -= 7;
					kebabpizza = "";
				} else if (checkbox == SalamiPizza) {
					sum -= 7;
					salamipizza = "";
				}
				else if (checkbox == Pollo) {
					sum -= 7;
					pollopizza = "";
				}
				else if (checkbox == Pekoni) {
					sum -= 7;
					pekonipizza = "";
				}
				else if (checkbox == TuttiFrutti) {
					sum -= 7;
					tuttifruttipizza = "";
				}
			}
			textFieldSum.setText(String.valueOf(sum));
			pizzatSum.setText(quattropizza + kebabpizza + salamipizza + pollopizza + pekonipizza + tuttifruttipizza);
		}
	}	
	
}