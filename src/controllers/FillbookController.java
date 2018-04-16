package controllers;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.lang.Exception;
import java.text.SimpleDateFormat;
import java.lang.Long;
import org.apache.commons.lang3.math.NumberUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;

import application.Exchanges;
import application.FillOrderBook;
import application.FxDialogs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
public class FillbookController {
    
//Fill Order Book
    @FXML private TextField FOBase;
    @FXML private TextField FOAlt;
    @FXML private TextField FOStartP;
    @FXML  private TextField FOEndP;
    @FXML private TextField FOBalanceUsed;
    @FXML private TextField FONumberOrders;
    @FXML private JFXRadioButton SellFO;
    @FXML private ToggleGroup FoBuySell;
    @FXML private JFXRadioButton BuyFO;
    @FXML private JFXButton RunFOBook;
    @FXML  private JFXComboBox<String> FOEx;
    
	@FXML
    public void initialize(){
        List<String> list = new ArrayList<String>(Exchanges.list);
        FOEx.getItems().addAll(list);
    }
	
	@FXML
    public void fillOrderBook(ActionEvent event) {
    	String base = FOBase.getText();
    	String alt = FOAlt.getText();
    	String startprice = FOStartP.getText();
    	String endprice = FOEndP.getText();
    	String balanceused = FOBalanceUsed.getText();
    	String nooforders = FONumberOrders.getText();
    	String BuySell = ((RadioButton) FoBuySell.getSelectedToggle()).getText();
    	
    	boolean noerror = true;
		StringBuilder stringBuilder = new StringBuilder();
     	boolean noexchange = false;
    	String exchange = "";
		 	
    	try {
    		exchange = FOEx.getValue().toString();
    	} catch (NullPointerException e) {
    		noerror=false;
    		noexchange=true;
    		stringBuilder.append("Please enter a exchange\n");
    	}
    	
    	if (!Exchanges.list.contains(exchange) && noexchange!=true) {
    		noerror=false;
    		stringBuilder.append(exchange + " is not a valid exchange.\n");
    	}
    	
    	if(!NumberUtils.isCreatable(startprice)) {
    		noerror=false;
    		stringBuilder.append(startprice + " is not a valid number(startprice).\n");
    	}
    	
    	if(!NumberUtils.isCreatable(endprice)) {
    		noerror=false;
    		stringBuilder.append(endprice + " is not a valid number(endprice).\n");
    	}
    	
    	if(!NumberUtils.isCreatable(balanceused)) {
    		noerror=false;
    		stringBuilder.append(balanceused + " is not a valid number(balanceused).\n");
    	}
    	
    	if(!NumberUtils.isCreatable(nooforders)) {
    		noerror=false;
    		stringBuilder.append(nooforders + " is not a valid number(nooforders).\n");
    	}
    	
		if (noerror==true) {
	    	try {
	    		String confirm = ("Base: " + base + "\nAlt: " + alt + "\nStartprice: " + startprice + "\nEndprice: " + endprice + "\nBalance Used: " + balanceused + " " + base + "\nNumber Of Orders: " + nooforders + "\nOrder Type: " + BuySell + "\nExchange: " + exchange);
	    		String run = FxDialogs.showConfirm("Run Order?",confirm, "Run", "Cancel");
	    		System.out.println(run);
				if (run.equals("Run")) {
					FillOrderBook.fillOrderBook(base, alt, startprice,endprice, balanceused, nooforders, BuySell, exchange);
				} else {
					System.out.println("Not running order");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String finalString = stringBuilder.toString();
    		FxDialogs.showError(null, finalString);
		}
    }
}
