package GUI;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent;
import Entities.Food;
import FileManagement.FoodManager;
import javax.swing.table.DefaultTableModel;
public class FoodRegGUI extends JFrame implements ActionListener{
	
	JTextField txtId;
	JTextField txtName;
	JTextField txtPrice;
	JTextField txtQuantity;
	JTextField txtExpDate;
	JTextField txtSearch;
	
	JButton submit;
	JButton search;
	JButton update;
	JButton delete;
	
	DefaultTableModel tmodel;
	
	public FoodRegGUI(){
		setSize(1400,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String imagePath = "resources/background.jpg"; 
        BackgroundImagePanel bgPanel = new BackgroundImagePanel(imagePath);
        setContentPane(bgPanel);
		
		bgPanel.setLayout(new FlowLayout());
		
		setLayout(new FlowLayout());
		
		add(new JLabel("Search by Item No"));
		txtSearch = new JTextField(5);
		add(txtSearch);
		search = new JButton("Search");
		add(search);
		search.addActionListener(this);
		
		add(new JLabel("Item No"));
		txtId = new JTextField(6);
		add(txtId);
		add(new JLabel("Name"));
		txtName = new JTextField(25);
		add(txtName);
		add(new JLabel("Price"));
		txtPrice = new JTextField(6);
		add(txtPrice);
		add(new JLabel("Qauntity"));
		txtQuantity = new JTextField(6);
		add(txtQuantity);
		add(new JLabel("Expiry Date"));
		txtExpDate = new JTextField(6);
		add(txtExpDate);
		
		submit = new JButton("Register");
		add(submit);
		submit.addActionListener(this);
		
		update = new JButton("Update");
		add(update);
		update.addActionListener(this);
		
		delete = new JButton("Delete");
		add(delete);
		delete.addActionListener(this);
		
		//tables
		tmodel= new DefaultTableModel();
		tmodel.addColumn("Item No");
		tmodel.addColumn("Name");
		tmodel.addColumn("Qauntity");
		tmodel.addColumn("Price");
		tmodel.addColumn("Expiry Date");
		
		JTable table= new JTable(tmodel);
		JScrollPane jspTable = new JScrollPane(table);
		bgPanel.add(jspTable);
		
		
		loadData();
		
		setVisible(true);
		}
	
	public void actionPerformed(ActionEvent e){
        if(e.getSource() == submit){
            registerClicked();
        }
        else if(e.getSource() == search){
            searchClicked();
        }
        else if(e.getSource() == delete){
            deleteClicked();
        }
        else if(e.getSource() == update){
            updateClicked();
        }
	}
	
	private void loadData(){
        FoodManager sm = new FoodManager();
        Food[] foods = sm.getAllFoods();
        System.out.println(foods.length);
        for(int i=0;i<foods.length;i++){
            Food s = foods[i];
            tmodel.addRow(s.getTableRow());
        }
    }
	
	public void registerClicked(){
        String sl = txtId.getText();
        String name = txtName.getText();
        String price = txtPrice.getText();
        String quantity = txtQuantity.getText();
        String expDate = txtExpDate.getText();
		
        Object[] row = new Object[]{sl,name,quantity, price,expDate};
        tmodel.addRow(row);

        Food s = new Food(sl,name,quantity, price,expDate);
        FoodManager sm = new FoodManager();
        sm.writeFood(s,true);
        JOptionPane.showMessageDialog(null,"Food Inserted");
    }
	public void searchClicked(){
        String sl = txtSearch.getText();
        FoodManager sm = new FoodManager();
        Food s = sm.searchFood(Integer.parseInt(sl));
        if(s == null){
            JOptionPane.showMessageDialog(null,"Food not found");
        }
        else{
            txtId.setText(s.getSl()+"");
            txtName.setText(s.getName());
           txtQuantity.setText(s.getQuantity()+"");
			 txtPrice.setText(""+s.getPrice());
            txtExpDate.setText(s.getExpDate());
			
        }
       }
	    public void deleteClicked(){
        String sl = txtId.getText();
        FoodManager sm = new FoodManager();
        sm.deleteFood(Integer.parseInt(sl));
        refreshTable();
        JOptionPane.showMessageDialog(null,"Food Deleted");
    }
    public void updateClicked(){
        String sl = txtId.getText();
        String name = txtName.getText();
       String quantity = txtQuantity.getText();
	    String price = txtPrice.getText();
        String expDate = txtExpDate.getText();
		
        Food s = new Food(sl,name,quantity, price,expDate);
        FoodManager sm = new FoodManager();
        sm.updateFood(s);
        refreshTable();
        JOptionPane.showMessageDialog(null,"Food Updated");

    }
    public void refreshTable(){
        tmodel.getDataVector().removeAllElements();
        FoodManager sm = new FoodManager();
        Food[] foods = sm.getAllFoods();
        for(int i=0;i<foods.length;i++){
           Food s = foods[i];
            tmodel.addRow(s.getTableRow());
        }
    }
	class BackgroundImagePanel extends JPanel {
    private Image backgroundImage;

    public BackgroundImagePanel(String imagePath) {
        // Load the image
        backgroundImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the image to fill the entire panel
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
		
		
		
	}

