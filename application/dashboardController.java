package application;

import java.io.File;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.chart.XYChart;

public class dashboardController implements Initializable {

	@FXML
    private Button availableBooks_addBtn;
	   @FXML
	    private Button purchase_tableview_clr;

    @FXML
    private TextField availableBooks_author;

    @FXML
    private TextField availableBooks_bookID;

    @FXML
    private TextField availableBooks_bookTitle;

    @FXML
    private Button availableBooks_btn;

    @FXML
    private TableColumn<bookData, String> availableBooks_col_author;

    @FXML
    private TableColumn<bookData, String> availableBooks_col_bookID;

    @FXML
    private TableColumn<bookData, String> availableBooks_col_bookTitle;

    @FXML
    private TableColumn<bookData, String> availableBooks_col_date;

    @FXML
    private TableColumn<bookData, String> availableBooks_col_genre;

    @FXML
    private TableColumn<bookData, String> availableBooks_col_price;

    @FXML
    private DatePicker availableBooks_date;

    @FXML
    private Button availableBooks_deleteBtn;

    @FXML
    private AnchorPane availableBooks_form;

    @FXML
    private TextField availableBooks_genre;

    @FXML
    private ImageView availableBooks_imageView;

    @FXML
    private Button availableBooks_importBtn;

    @FXML
    private TextField availableBooks_price;

    @FXML
    private TextField availableBooks_search;

    @FXML
    private TableView<bookData> availableBooks_tableView;

    @FXML
    private Button availableBooks_updateBtn;

    @FXML
    private Button close;

    @FXML
    private Label dashboard_AB;

    @FXML
    private Label dashboard_TC;

    @FXML
    private Label dashboard_TI;

    @FXML
    private Button dashboard_btn;

    @FXML
    private BarChart<?, ?> dashboard_customerChart;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private AreaChart<?, ?> dashboard_incomeChart;

    @FXML
    private Button logout;

    @FXML
    private Pane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Button purchase_addBtn;

    @FXML
    private ComboBox<?> purchase_bookID;

    @FXML
    private ComboBox<?> purchase_bookTitle;

    @FXML
    private Button purchase_btn;

    @FXML
    private TableColumn<customerData, String> purchase_col_author;

    @FXML
    private TableColumn<customerData, String> purchase_col_bookID;

    @FXML
    private TableColumn<customerData, String> purchase_col_bookTitle;

    @FXML
    private TableColumn<customerData, String> purchase_col_genre;

    @FXML
    private TableColumn<customerData, String> purchase_col_price;

    @FXML
    private TableColumn<customerData, String> purchase_col_quantity;

    @FXML
    private AnchorPane purchase_form;

    @FXML
    private Label purchase_info_author;

    @FXML
    private Label purchase_info_bookID;

    @FXML
    private Label purchase_info_bookTitle;

    @FXML
    private Label purchase_info_date;

    @FXML
    private Label purchase_info_genre;

    @FXML
    private Button purchase_payBtn;

    @FXML
    private TableView<customerData> purchase_tableView;
    
    @FXML
    private Spinner<Integer> purchase_quantity;

    @FXML
    private Label purchase_total;

    @FXML
    private Label username;
    
  private Connection connect;
  private PreparedStatement prepare;
  private Statement statement;
  private ResultSet result;
  
  private Image image;
  
 public void dashboardAB() {
	 String sql = "SELECT COUNT(book_id) FROM book";
	 
	 connect = database.ConnectDb();
	 int countAB = 0;
	 try {
		 
		 prepare = connect.prepareStatement(sql);
		 result = prepare.executeQuery();
		 
		 if(result.next()) {
			 countAB =result.getInt("COUNT(book_id)");
		 }
		 
		 dashboard_AB.setText(String.valueOf(countAB));
		 
	 }catch(Exception e) {e.printStackTrace();}
	 
 }
  
 
 public void dashboardTI() {
	 String sql = "SELECT SUM(total) FROM customer_info";
	 
	 connect = database.ConnectDb();
	 
	 double sumTotal=0;
	 try {
		 prepare = connect.prepareStatement(sql);
		 result = prepare.executeQuery();
		 
		 if(result.next()) {
			 sumTotal = result.getDouble("SUM(total)");
		 }
		 
		 dashboard_TI.setText("Rs. "+String.valueOf(sumTotal));
		 
	 }catch(Exception e) {e.printStackTrace();}
	 
 }
 

 
 public void dashboardTC() {
	 String sql="SELECT COUNT(customer_id) FROM customer_info";
	 
	 connect = database.ConnectDb();
	 int countTC=0;
	 try {
		 
		 prepare = connect.prepareStatement(sql);
		 result = prepare.executeQuery();
		 
		 if(result.next()) {
			 countTC = result.getInt("COUNT(customer_id)");
		 }
		 
		 dashboard_TC.setText(String.valueOf(countTC));
		 
	 }catch(Exception e) {e.printStackTrace();}
	 
 }

 public void dashboardIncomeChart() {
	 dashboard_incomeChart.getData().clear();
	 String sql="SELECT date, SUM(total) FROM customer_info GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 6";
	 
	 try {
		 XYChart.Series chart = new XYChart.Series();
		 
		prepare = connect.prepareStatement(sql);
		result = prepare.executeQuery();
		
		if(result.next()) {
			chart.getData().add(new XYChart.Data(result.getString(1),result.getInt(2))); 
		}
		
		 dashboard_incomeChart.getData().add(chart);
		 
	 }catch(Exception e) {e.printStackTrace();}
	 
 }
 
 public void dashboardCustomerChart() {
	 dashboard_customerChart.getData().clear();
	 String sql = "SELECT date, COUNT(customer_id) FROM customer_info GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 4";
	 
	 connect = database.ConnectDb();
	 try {
		 XYChart.Series chart = new XYChart.Series();
		 
		 prepare = connect.prepareStatement(sql);
		 result = prepare.executeQuery();
		 
		 if(result.next()) {
			
	chart.getData().add(new XYChart.Data(result.getString(1),result.getInt(2)));	 
		 }
		 
		 dashboard_customerChart.getData().add(chart);
		 
	 }catch(Exception e) {e.printStackTrace();}
	 
 }
 
 
public void availableBooksAdd()
{
 String sql = "INSERT INTO book (book_id,title,author,genre,pub_date,price,image)"
		         +"VALUES(?,?,?,?,?,?,?)";
 
 connect = database.ConnectDb();
 try {
	
	 Alert alert;
	 
	 if(availableBooks_bookID.getText().isEmpty() || availableBooks_bookTitle.getText().isEmpty() || availableBooks_author.getText().isEmpty()
		|| availableBooks_genre.getText().isEmpty() || availableBooks_date.getValue()==null ||availableBooks_price.getText().isEmpty() 
		|| getData.path==null || getData.path == "")
	 {
		 alert = new Alert(AlertType.ERROR);
		 alert.setTitle("Error Messege");
		 alert.setHeaderText(null);
		 alert.setContentText("please fill all blank fields");
		 alert.showAndWait();
	 }
	 else {	
		// check if book id is already exist
		 String checkData = "SELECT book_id FROM book WHERE book_id='"+availableBooks_bookID.getText()+"'";
		 
		 statement = connect.createStatement();
		 result = statement.executeQuery(checkData);
		 
		 if(result.next())
		 {
			 alert = new Alert(AlertType.ERROR);
			 alert.setTitle("Error Messege");
			 alert.setHeaderText(null);
			 alert.setContentText("Book ID : "+availableBooks_bookID.getText()+" was already exist !");
			 alert.showAndWait();
		 }
		 else {
		 
	 prepare = connect.prepareStatement(sql);
	 prepare.setString(1,availableBooks_bookID.getText());
	 prepare.setString(2,availableBooks_bookTitle.getText());
	 prepare.setString(3,availableBooks_author.getText());
	 prepare.setString(4,availableBooks_genre.getText());
	 prepare.setString(5,String.valueOf(availableBooks_date.getValue()));
	 prepare.setString(6,availableBooks_price.getText());
	 
	 
	 
String uri = getData.path;
uri = uri.replace("\\", "\\\\");

prepare.setString(7, uri); 
prepare.executeUpdate();

alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Information Messege");
alert.setHeaderText(null);
alert.setContentText("Succesfully Added!");
alert.showAndWait();

// to be update the table view
availableBooksShowListData();
// clear fields
availableBooksClear();

		 }
	 }
	 
 }catch(Exception e) {e.printStackTrace();}
}


public void availableBooksUpdate() {
    String uri = getData.path.replace("\\", "\\\\");

    String sql = "UPDATE book SET title = ?, author = ?, genre = ?, pub_date = ?, price = ?, image = ? WHERE book_id = ?";
    
    if (availableBooks_bookID.getText().isEmpty() || availableBooks_bookTitle.getText().isEmpty() ||
        availableBooks_author.getText().isEmpty() || availableBooks_genre.getText().isEmpty() ||
        availableBooks_date.getValue() == null || availableBooks_price.getText().isEmpty() ||
        getData.path.isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all blank fields");
        alert.showAndWait();
        return;
    }

    Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
    confirmationAlert.setTitle("Confirmation Message");
    confirmationAlert.setHeaderText(null);
    confirmationAlert.setContentText("Are you sure you want to update Book ID: " + availableBooks_bookID.getText() + "?");
    Optional<ButtonType> option = confirmationAlert.showAndWait();

    if (option.isPresent() && option.get() == ButtonType.OK) {
        try {
            connect = database.ConnectDb();
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, availableBooks_bookTitle.getText());
            preparedStatement.setString(2, availableBooks_author.getText());
            preparedStatement.setString(3, availableBooks_genre.getText());
            preparedStatement.setDate(4, java.sql.Date.valueOf(availableBooks_date.getValue()));
            preparedStatement.setString(5, availableBooks_price.getText());
            preparedStatement.setString(6, uri);
            preparedStatement.setString(7, availableBooks_bookID.getText());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                Alert successAlert = new Alert(AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Successfully Updated!");
                successAlert.showAndWait();

                // Update the table view
                availableBooksShowListData();
                // Clear fields
                availableBooksClear();
            } else {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle("Error Message");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Failed to update book. Please try again.");
                errorAlert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert sqlErrorAlert = new Alert(AlertType.ERROR);
            sqlErrorAlert.setTitle("SQL Error");
            sqlErrorAlert.setHeaderText(null);
            sqlErrorAlert.setContentText("An error occurred while updating the book. Please try again.");
            sqlErrorAlert.showAndWait();
        }
    }
}


public void availableBooksDelete() {
    if (availableBooks_bookID.getText().isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please select the Book ID to delete.");
        alert.showAndWait();
        return;
    }

    Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
    confirmationAlert.setTitle("Confirmation Message");
    confirmationAlert.setHeaderText(null);
    confirmationAlert.setContentText("Are you sure you want to delete Book ID: " + availableBooks_bookID.getText() + "?");
    Optional<ButtonType> option = confirmationAlert.showAndWait();

    if (option.isPresent() && option.get() == ButtonType.OK) {
        try {
            connect = database.ConnectDb();
            String sql = "DELETE FROM book WHERE book_id = ?";
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, availableBooks_bookID.getText());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                Alert successAlert = new Alert(AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Successfully Deleted!");
                successAlert.showAndWait();

                // Update the table view
                availableBooksShowListData();
                // Clear fields
                availableBooksClear();
            } else {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle("Error Message");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Book ID not found or could not be deleted. Please try again.");
                errorAlert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert sqlErrorAlert = new Alert(AlertType.ERROR);
            sqlErrorAlert.setTitle("SQL Error");
            sqlErrorAlert.setHeaderText(null);
            sqlErrorAlert.setContentText("An error occurred while deleting the book. Please try again.");
            sqlErrorAlert.showAndWait();
        }
    }
}



public void availableBooksClear()
{
	availableBooks_bookID.setText("");
	availableBooks_bookTitle.setText("");
	availableBooks_author.setText("");
	availableBooks_genre.setText("");
	availableBooks_date.setValue(null);
	availableBooks_price.setText("");
	
	getData.path="";
	
	availableBooks_imageView.setImage(null);
	
}

public void avaialableBooksInsertImage()
{
	FileChooser open = new FileChooser();
	open.setTitle("open image file");
	open.getExtensionFilters().add(new ExtensionFilter("File Image","*jpg","*png"));   
	
File file = open.showOpenDialog(main_form.getScene().getWindow());

if(file!=null)
{
	getData.path = file.getAbsolutePath();
	
	image = new Image(file.toURI().toString(),230,71,false,true);
	availableBooks_imageView.setImage(image);	
	
}
	
	
}

    
  public ObservableList<bookData> availableBooksListData()
  {
	  ObservableList<bookData> listData =FXCollections.observableArrayList(); 
	  String sql = "SELECT * FROM book";
	  
	  connect = database.ConnectDb();
	   try {
		  
	prepare = connect.prepareStatement(sql);
	result = prepare.executeQuery();
	bookData bookD;
	
	while(result.next())
	{
	bookD = new bookData(result.getInt("book_id"),result.getString("title"),result.getString("author"),result.getString("genre"),
			result.getDate("pub_date"),result.getDouble("price"),result.getString("image"));  
	
	listData.add(bookD);
	
	}
		   
	   }catch(Exception e) { e.printStackTrace();}
	 return listData; 
  }
    
 private ObservableList<bookData> availableBooksList;
 public void availableBooksShowListData()
 {
	 availableBooksList = availableBooksListData();
availableBooks_col_bookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
availableBooks_col_bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
availableBooks_col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
availableBooks_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
availableBooks_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
availableBooks_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
	
availableBooks_tableView.setItems(availableBooksList);


	 
 }
  
public void availableBooksSelect()
{
	bookData bookD = availableBooks_tableView.getSelectionModel().getSelectedItem();
	int num = availableBooks_tableView.getSelectionModel().getSelectedIndex();

if((num-1)<-1) {return;}	
availableBooks_bookID.setText(String.valueOf(bookD.getBookId()));
availableBooks_bookTitle.setText(bookD.getTitle());
availableBooks_author.setText(bookD.getAuthor());
availableBooks_genre.setText(bookD.getGenre());
availableBooks_date.setValue(LocalDate.parse(String.valueOf(bookD.getDate())));
availableBooks_price.setText(String.valueOf(bookD.getPrice()));

getData.path= bookD.getImage();

String uri = "file:"+bookD.getImage();

image = new Image(uri,230,71,false,true);
availableBooks_imageView.setImage(image);


}
 
public void availableBooksSeach() {
    // Filtered list for search functionality
    FilteredList<bookData> filteredList = new FilteredList<>(availableBooksList);

    // Listener for text changes in the search field
    availableBooks_search.textProperty().addListener((observable, oldValue, newValue) -> {
        // Update the predicate of the filtered list based on the search text
        filteredList.setPredicate(bookData -> {
            if (newValue == null || newValue.isEmpty()) {
                // If the search field is empty, do not display any book
                return false;
            }

            String searchKey = newValue.toLowerCase();

            // Check if any of the fields contain the search key
            return bookData.getBookId().toString().toLowerCase().contains(searchKey) ||
                    bookData.getTitle().toLowerCase().contains(searchKey) ||
                    bookData.getAuthor().toLowerCase().contains(searchKey) ||
                    bookData.getGenre().toLowerCase().contains(searchKey) ||
                    bookData.getDate().toString().toLowerCase().contains(searchKey) ||
                    bookData.getPrice().toString().toLowerCase().contains(searchKey);
        });
        
        // Create a sorted list and bind it to the table view
        SortedList<bookData> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(availableBooks_tableView.comparatorProperty());

        // Set items to the TableView based on whether there's a search or not
        availableBooks_tableView.setItems(newValue.isEmpty() ? availableBooksList : sortedList);
    });
}


private double totalp;
public void purchaseAdd() {
	purchasecustomerId();
	
	String sql ="INSERT INTO customer (customer_id, book_id,title,author,genre,quantity,price,date)"
			+ "VALUES(?,?,?,?,?,?,?,?)";
	
	connect=database.ConnectDb();
	
	try {
		Alert alert;
		
		if(purchase_bookTitle.getSelectionModel().getSelectedItem()==null 
				|| purchase_bookID.getSelectionModel().getSelectedItem()==null ) {
			
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Message");
			alert.setContentText("Please choose book first");
			alert.showAndWait();
		}else {
			
		
		prepare = connect.prepareStatement(sql);
		prepare.setString(1, String.valueOf(customerId));
		prepare.setString(2,purchase_info_bookID.getText());
		prepare.setString(3,purchase_info_bookTitle.getText());
		prepare.setString(4,purchase_info_author.getText());
		prepare.setString(5,purchase_info_genre.getText());
		prepare.setString(6,String.valueOf(qty));
		
		String checkData = "SELECT title, price FROM book WHERE title = '"+purchase_bookTitle.getSelectionModel().getSelectedItem()+"'";
		
		double priceD = 0;
		
		statement = connect.createStatement();
		result = statement.executeQuery(checkData);
		
		if(result.next()) {
			priceD = result.getDouble("price");
		}
		
		totalp = (qty*priceD);
		
		prepare.setString(7, String.valueOf(totalp));
		
		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());  
		
		prepare.setString(8, String.valueOf(sqlDate));
		prepare.executeUpdate();
		
		purchaseDisplayTotal();
		purchaseShowCustomerListData();
		}// ending else
		
	}catch(Exception e) {e.printStackTrace();}
	
}


public void purchasePay() {

    String sql = "INSERT INTO customer_info (customer_id, total, date) " +
                 "VALUES (?, ?, ?)";

    connect = database.ConnectDb();

    try {
        Alert alert;
        if (displayTotal == 0) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Invalid :3");
            alert.showAndWait();
        } else {

            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setContentText("Are you sure ?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.isPresent() && option.get().equals(ButtonType.OK)) {

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, String.valueOf(customerId));
               prepare.setDouble(2, displayTotal);


                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                prepare.setDate(3, sqlDate);

                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setContentText("Successful !");
                alert.showAndWait();

            }
            purchaseCustomerList.clear();

        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


private double displayTotal;
public void purchaseDisplayTotal() {
	
	purchasecustomerId();
	String sql="SELECT SUM(price) FROM customer WHERE customer_id = '"+customerId+"'";
	
	connect = database.ConnectDb();
	try {
	
		prepare = connect.prepareStatement(sql);
		result = prepare.executeQuery();
		
		if(result.next()) {
			displayTotal = result.getDouble("SUM(price)"); 
			
		}
		
		purchase_total.setText("Rs."+String.valueOf(displayTotal));
		
	}catch(Exception e) {e.printStackTrace();}
	
}


public void purchaseBookId()
{
	String sql = "SELECT book_id FROM book";
	connect = database.ConnectDb();
	try {
		
		prepare = connect.prepareStatement(sql);
		result = prepare.executeQuery();
		
		ObservableList listData = FXCollections.observableArrayList();
		
		while(result.next()) {
			listData.add(result.getString("book_id"));
		}
		
		purchase_bookID.setItems(listData);
		purchaseBookTitle();
		
	}catch(Exception e) {e.printStackTrace();}
}


public void purchaseBookTitle()
{
	String sql = "SELECT book_id, title FROM book WHERE book_id='"+purchase_bookID.getSelectionModel().getSelectedItem()+"'";   
	
	connect = database.ConnectDb();
	try {
		

		prepare = connect.prepareStatement(sql);
		result = prepare.executeQuery();
		
		ObservableList listData = FXCollections.observableArrayList();
		
		while(result.next()) {
			listData.add(result.getString("title"));
		}
		
		purchase_bookTitle.setItems(listData);
		
		purchaseBookInfo();
		
		
	}catch(Exception e){e.printStackTrace();}
	
}

public void purchaseBookInfo()
{
	String sql ="SELECT * FROM book WHERE title ='"+purchase_bookTitle.getSelectionModel().getSelectedItem()+"'";     
	
	connect = database.ConnectDb();
	
	String bookId = "";
	String title = "";
	String author = "";
	String genre = "";
	String date = "";
	
	try {
		
		prepare = connect.prepareStatement(sql);
		result = prepare.executeQuery();
		
		if(result.next()) {
			
			bookId = result.getString("book_id");
			title = result.getString("title");
			author = result.getString("author");
			genre = result.getString("genre");
			date = result.getString("pub_date");
			
		}
		
		purchase_info_bookID.setText(bookId);
		purchase_info_bookTitle.setText(title);
		purchase_info_author.setText(author);
		purchase_info_genre.setText(genre);
		purchase_info_date.setText(date);
		
		
		
	}catch(Exception e) {e.printStackTrace();}
	
	
}

public ObservableList<customerData> purchaseListData(){
	
	purchasecustomerId();
	String sql = "SELECT * FROM customer WHERE customer_id = '"+customerId+"'";  
	
	ObservableList<customerData> listData = FXCollections.observableArrayList();     
	
	connect=database.ConnectDb();
	
	try {
		
		prepare = connect.prepareStatement(sql);
		result = prepare.executeQuery();
		
		customerData customerD;
		while(result.next()) {
			customerD = new customerData(result.getInt("customer_id")
					,result.getInt("book_id")
					,result.getString("title")
					,result.getString("author")
					,result.getString("genre")
					,result.getInt("quantity")
					,result.getDouble("price")
					,result.getDate("date"));
			
			listData.add(customerD);
		}
		
		
		
	}catch(Exception e) {e.printStackTrace();}
	
	return listData;
}

private ObservableList<customerData> purchaseCustomerList;
public void purchaseShowCustomerListData() {
	
	purchaseCustomerList = purchaseListData();
	
	purchase_col_bookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));    
	purchase_col_bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
	purchase_col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
	purchase_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
	purchase_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
	purchase_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
	
	purchase_tableView.setItems(purchaseCustomerList);
	
}

private SpinnerValueFactory<Integer> spinner;

public void purchaseDisplayQTY() {
	
	spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
	purchase_quantity.setValueFactory(spinner);
	
}


private int qty;
public void purchaseQty() {
 qty = purchase_quantity.getValue();
}


private int customerId;
public void purchasecustomerId() {
	
	String sql = "SELECT MAX(customer_id) FROM customer";
	int checkCID = 0;
	connect = database.ConnectDb();
	
	try {
		prepare = connect.prepareStatement(sql);
		result = prepare.executeQuery();
		
		if(result.next()) {
			customerId = result.getInt("MAX(customer_id)");
		}
		
		String checkData = "SELECT MAX(customer_id) FROM customer_info";
		
		prepare = connect.prepareStatement(checkData);
		result = prepare.executeQuery();
		
		if(result.next()) {
			checkCID = result.getInt("MAX(customer_id)");
		}
		
		if(customerId == 0) {
			customerId+=1;
		}else if(checkCID == customerId) {
			
			customerId = checkCID +1;
		}
		
	}catch(Exception e) {e.printStackTrace();}
	
}



public void displayUsername()
{
	String user =getData.username;
	user = user.substring(0,1).toUpperCase()+user.substring(1);
	username.setText(user);
	
}
    
    
    
    

    

public void switchForm(ActionEvent event)
{
	if(event.getSource()==dashboard_btn)
	{
		dashboard_form.setVisible(true);
		availableBooks_form.setVisible(false);
		purchase_form.setVisible(false);
		
		 dashboardAB();
		 dashboardTI();
		 dashboardTC();
		 
		 dashboardCustomerChart();
		 dashboardIncomeChart();
		
	}
	else if(event.getSource()==availableBooks_btn)
	{
		dashboard_form.setVisible(false);
		availableBooks_form.setVisible(true);
		purchase_form.setVisible(false);
		
		availableBooksShowListData();
		availableBooksSeach();
		
	}
	else if(event.getSource()==purchase_btn)
	{
		dashboard_form.setVisible(false);
		availableBooks_form.setVisible(false);
		purchase_form.setVisible(true);
		
		purchaseBookTitle();
		purchaseBookId();
		purchaseShowCustomerListData();
		purchaseDisplayQTY();
		purchaseDisplayTotal();
		
	}
	
}
    
    
    
    
    
// logout
private double x=0;
private double y=0;

public void logout()
{
	try {
Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Comfirmation Message");
alert.setHeaderText(null);
alert.setContentText("Are You Sure Want to Logout ?");

Optional<ButtonType> option =alert.showAndWait();

if(option.get().equals(ButtonType.OK))
{ // hide dashboard form
	logout.getScene().getWindow().hide();
	
	
// link our login form
Parent root=FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
Scene scene = new Scene(root);
Stage stage = new Stage();

root.setOnMousePressed((MouseEvent event) -> {
	x=event.getSceneX();
	y=event.getSceneY();
});

root.setOnMouseDragged((MouseEvent event) ->{
	stage.setX(event.getSceneX()-x);
	stage.setY(event.getSceneY()-y);
	
	stage.setOpacity(0.8);
	
});

root.setOnMouseReleased((MouseEvent event) -> {
	stage.setOpacity(1);
});


stage.initStyle(StageStyle.TRANSPARENT);
stage.setScene(scene);
stage.show();


}


	}catch(Exception e) { e.printStackTrace(); }
	
}
    
	public void close()
	{
		System.exit(0);
		
	}
	public void minimize()
	{
		Stage stage=(Stage)main_form.getScene().getWindow();  
		stage.setIconified(true);
	}
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		displayUsername();
		
		 dashboardAB();
		 dashboardTI();
		 dashboardTC();
		 
		 dashboardCustomerChart();
		 dashboardIncomeChart();
		
// to show the data on table wiew (avaialble books)		
		availableBooksShowListData();	
		
		purchaseBookId();
		purchaseBookTitle();
		purchaseShowCustomerListData();
		purchaseDisplayQTY();
		purchaseDisplayTotal();
		
	}

}
