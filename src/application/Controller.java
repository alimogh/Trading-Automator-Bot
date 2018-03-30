package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.json.JSONObject;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.event.Event;
import javafx.scene.control.TreeItem;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.collections.ObservableList;

public class Controller {
	QuickBuy quickbuy = new QuickBuy();
	AverageTrading averageTrading = new AverageTrading();
    private Main application;
    private Scene scene;
    public Stage primaryStage;
     @FXML private Label name;
	 @FXML private Label slogan;
	 @FXML private PasswordField Pass;
	 @FXML private Label EmailLabel;
	 @FXML private TextField Email;
	 @FXML private Label PassLabel;
	 @FXML private JFXButton LoggedIn;
	 @FXML private JFXCheckBox RememberMe;
	 @FXML private Label First;
	 @FXML private JFXButton SignUp;
	 @FXML private BorderPane mainView;
	 
	 
	 @FXML private JFXButton arbitrage;
	 @FXML private JFXButton mmake;
	 @FXML private JFXButton backtest;
	 @FXML private JFXButton lives;
	 
	 @FXML private JFXButton home;
	 
	 @FXML private JFXButton avtrad;
	 @FXML private JFXButton qbuy;
	 @FXML private JFXButton porder;
	 @FXML private JFXButton tstop;
	 @FXML private JFXButton fobook;
	 @FXML private JFXButton settings;
	 @FXML private JFXRadioButton BuyFO;
	 @FXML private JFXRadioButton SellFO;
	 @FXML private Separator sepVertical;
	 @FXML private ToggleGroup toggleGroupFO;
	 
	 //Average Trading
	 	@FXML private Label BPAv;
	    @FXML private TextField BPAvU;
	    @FXML private Label APAv;
	    @FXML private TextField APAvU;
	    @FXML private Label LTAv;
	    @FXML private TextField LPAvU;
	    @FXML private JFXRadioButton SAv;
	    @FXML private ToggleGroup toggleBAAv;
	    @FXML private ToggleGroup toggleBSAv;
	    @FXML private JFXRadioButton BAv;
	    @FXML private JFXRadioButton AskAv;
	    @FXML private JFXRadioButton BidAv;
	    @FXML private Label ExLabelAv;
	    @FXML private JFXComboBox<String> ExAv;
	    @FXML private JFXButton StartAv;
	    @FXML private Label MBAv;
	    @FXML private TextField VpOAV;
	    @FXML private JFXTextField MABAv;
	    
	//Pending Order
	    @FXML private Label POBP;
	    @FXML private TextField POBPU;
	    @FXML private Label APPO;
	    @FXML private TextField APPOU;
	    @FXML private Label PPO;
	    @FXML private TextField PPOU;
	    @FXML private Label BVPO;
	    @FXML private TextField BVPOU;
	    @FXML private Label ExPO;
	    @FXML private JFXComboBox<?> ExPOU;
	    @FXML private Label POWPO;
	    @FXML private TextField POVPOU;
	    @FXML private JFXButton RunPO;
	    @FXML private JFXRadioButton SellPO;
	    @FXML private ToggleGroup toggleGroup;
	    @FXML private JFXRadioButton BuyPO;
	    @FXML private ToggleGroup toggleGroupPO;
	    	    
	//QUICK BUY
	    @FXML private TextField qBase;
	    @FXML private TextField qAlt;
	    @FXML private TextField qVolume;
	    @FXML private TextField qBAA;
	    @FXML private JFXButton RunQBuy;
	    @FXML private JFXComboBox<?> qEx;	
	    
	    @FXML private JFXTreeTableView<Person> table;
	    
	    @FXML private JFXButton tablee;
	    
	@FXML
    private void handleChangeView(ActionEvent event) {
    	System.out.println("Changing");
        try {
            String menuItemID = ((JFXButton) event.getSource()).getId();
            System.out.println(menuItemID);
            //if (menuItemID.toString() == "avtrad") {
            	System.out.println("Adding ComboBox");
            	final JFXComboBox<String> ExAv = new JFXComboBox<String>();
                ExAv.getItems().addAll(
                		"Highest",
                        "High",
                        "Normal",
                        "Low",
                        "Lowest" 
                    );   
            //}
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/" + menuItemID + ".fxml"));
            loader.setController(this);
            
            System.out.println(loader);
            mainView.setCenter(loader.load()); 
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	@FXML
    private void DashBoard(ActionEvent event) throws IOException
    {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));
		mainView.setCenter(loader.load());
		System.out.println("HEREEE");
    }
	
	@FXML
	private void tableEnable(ActionEvent event) {
		JFXTreeTableColumn<Person,String> OrderType = new JFXTreeTableColumn<Person,String>("Order Type");
		OrderType.setPrefWidth(100);
	
		OrderType.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
	          
			@Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Person, String> param) {
                return param.getValue().getValue().OrderType;
            }
        });
		
		JFXTreeTableColumn<Person,String> BasePair = new JFXTreeTableColumn<Person,String>("Base Pair");
		BasePair.setPrefWidth(100);
	
		BasePair.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
	          
			@Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Person, String> param) {
                return param.getValue().getValue().BasePair;
            }
        });
		
		JFXTreeTableColumn<Person,String> AltPair = new JFXTreeTableColumn<Person,String>("Alt Pair");
		AltPair.setPrefWidth(100);
	
		AltPair.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
	          
			@Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Person, String> param) {
                return param.getValue().getValue().AltPair;
            }
        });
		
		JFXTreeTableColumn<Person,String> Exchange = new JFXTreeTableColumn<Person,String>("Exchange");
		Exchange.setPrefWidth(100);
	
		Exchange.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
	          
			@Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Person, String> param) {
                return param.getValue().getValue().Exchanges;
            }
        });
		
		JFXTreeTableColumn<Person,String> StartTime = new JFXTreeTableColumn<Person,String>("Start Time");
		StartTime.setPrefWidth(100);
	
		StartTime.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
	          
			@Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Person, String> param) {
                return param.getValue().getValue().StartTime;
            }
        });
		
		JFXTreeTableColumn<Person,String> EndTime = new JFXTreeTableColumn<Person,String>("EndTime");
		EndTime.setPrefWidth(100);
	
		EndTime.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
	          
			@Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Person, String> param) {
                return param.getValue().getValue().EndTime;
            }
        });
		
		JFXTreeTableColumn<Person,String> Running = new JFXTreeTableColumn<Person,String>("Running");
		Running.setPrefWidth(100);
	
		Running.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
	          
			@Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Person, String> param) {
                return param.getValue().getValue().Running;
            }
        });
		
		ObservableList<Person> person = FXCollections.observableArrayList();
		person.add(new Person("Market Making","BTC","ETH", "Bittrex", "28.2(5:30)", "NA", "Running"));
		person.add(new Person("Arbitrage","BTC","DCR", "Poloniex & Bittrex", "28.2(5:30)", "NA", "Running"));
		person.add(new Person("QuickBuy","BTC","XRP", "Binance", "28.2(5:30)", "28.2(5:30)", "Stopped"));
		person.add(new Person("AverageTrading","BTC","OMG", "Bittrex", "28.2(5:30)", "NA", "Running"));
		System.out.println("ADDED ITEMS!!!");
		final TreeItem<Person> root = new RecursiveTreeItem<Person>(person, RecursiveTreeObject::getChildren);
		System.out.println(table.getColumns());
		table.getColumns().setAll(OrderType,BasePair,AltPair, Exchange, StartTime, EndTime, Running);
		table.setRoot(root);
		table.setShowRoot(false);
	}
	
    @FXML
    private void LogIn(ActionEvent event) throws IOException
    {
       /* FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoggedIn.fxml"));
        AnchorPane rootLayout = loader.load(); 
        Scene scene = new Scene(rootLayout);
        Main m = new Main();
        m.usetScene(scene);
        */
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoggedIn.fxml"));
        AnchorPane rootLayout = loader.load(); 
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
        //Exchanges ex = new Exchanges();
    }
    
    public void quickPrice(ActionEvent event) {
   	 System.out.println("running quickPrice");
   	 quickbuy.sendQuickPriceRequest(qBase.getText(),qAlt.getText(),qEx.getValue().toString(), Double.parseDouble(qVolume.getText()), Double.parseDouble(qBAA.getText()));
   	
 	}
    
    public void averageTrading(ActionEvent event) throws JSONException {
      	 System.out.println("running averageTrading");
     	JSONObject averageTrading = new JSONObject();
     	averageTrading.put("Basecoin", BPAvU.getText());
     	averageTrading.put("Altcoin", APAvU.getText());
     	//averageTrading.put("Exchanges", ExAv.getValue().toString());
     	averageTrading.put("Exchanges", "bittrex");
     	averageTrading.put("request", "averageTrading");
     	averageTrading.put("coinstotrade", MABAv.getText());
     	averageTrading.put("volumeperorder", VpOAV.getText());
     	averageTrading.put("licenceKey", SocketCommunication.licencekey);
     	averageTrading.put("millis", System.currentTimeMillis());
     	String atbidtext = ((RadioButton) toggleBAAv.getSelectedToggle()).getText();
     	averageTrading.put("atbid", ((RadioButton) toggleBAAv.getSelectedToggle()).getText());
     	System.out.println(((RadioButton) toggleBSAv.getSelectedToggle()).getText());
     	averageTrading.put("buy", ((RadioButton) toggleBSAv.getSelectedToggle()).getText());
     	averageTrading.put("loop", LPAvU.getText());
     	System.out.println("--------------");
     	System.out.println(averageTrading);
      	AverageTrading.runOrder(averageTrading);
      	
    	}
    
    public void setPrimaryStage(Stage stage) {
    	  this.primaryStage = stage;
    	}

    private static final class Person extends RecursiveTreeObject<Person>{
		StringProperty OrderType;
		StringProperty BasePair;
		StringProperty AltPair;
		StringProperty Exchanges;
		StringProperty StartTime;
		StringProperty EndTime;
		StringProperty Running;
		
		
		public Person(String OrderType,String BasePair,String AltPair, String Exchanges, String StartTime, String EndTime, String Running)
		{
			this.OrderType = new SimpleStringProperty(OrderType);
			this.BasePair  = new SimpleStringProperty(BasePair);
			this.AltPair = new SimpleStringProperty(AltPair);
			this.Exchanges = new SimpleStringProperty(Exchanges);
			this.StartTime = new SimpleStringProperty(StartTime);
			this.EndTime = new SimpleStringProperty(EndTime);
			this.Running = new SimpleStringProperty(Running);
			
		}
		
		
	}
}


