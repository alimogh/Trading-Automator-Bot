package controllers;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.lang3.math.NumberUtils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import application.Exchanges;
import application.FxDialogs;
import application.Main;
import application.QuickBuy;
import application.TrailingStop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
public class QuickbuyController {
	//QuickBuy
    @FXML private TextField qBase;
    @FXML private TextField qAlt;
    @FXML private TextField qVolume;
    @FXML private TextField qBAA;
    @FXML private JFXButton RunQBuy;
    @FXML private JFXComboBox<String> qEx;		   
    
	@FXML
    public void initialize(){
        List<String> list = new ArrayList<String>(Exchanges.list);
        qEx.getItems().addAll(list);
    }

	@FXML
    public void quickPrice(ActionEvent event) {
      	String base = qBase.getText().toUpperCase();
      	String alt = qAlt.getText().toUpperCase();
      	String volume =qVolume.getText();
      	String percent = qBAA.getText();
     	boolean noerror = true;
     	boolean noexchange = false;
    	String exchange = "";
    	StringBuilder stringBuilder = new StringBuilder();
    	
    	try {
    		exchange = qEx.getValue().toString();
    	} catch (NullPointerException e) {
    		noerror=false;
    		noexchange=true;
    		stringBuilder.append("Please enter a exchange\n");
    	}
    	
    	if (!Exchanges.list.contains(exchange) && noexchange!=true) {
    		noerror=false;
    		stringBuilder.append(exchange + " is not a valid exchange.\n");
    	}
    	
    	if(!NumberUtils.isCreatable(volume)) {
    		noerror=false;
    		stringBuilder.append(volume + " is not a valid number(volume).\n");
    	}
    	
    	if(!NumberUtils.isCreatable(percent)) {
    		noerror=false;
    		stringBuilder.append(percent + " is not a valid number(percent).\n");
    	}
    	if (noerror==true) {
    		QuickBuy QuickBuyClass = new QuickBuy(base,alt,exchange, Double.parseDouble(volume), Double.parseDouble(percent));
	    	Thread t = new Thread(QuickBuyClass);
	    	t.start();
        	Main.logger.log(Level.INFO, "Sending quick buy price request");
    	} else {
			String finalString = stringBuilder.toString();
			FxDialogs.showError(null, finalString);
    	}	
	}    
}
