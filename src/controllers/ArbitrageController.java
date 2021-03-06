package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;

import org.apache.commons.lang3.math.NumberUtils;
import org.controlsfx.control.CheckComboBox;
import org.json.JSONException;
import org.json.JSONObject;
import org.knowm.xchange.Exchange;

import com.jfoenix.controls.JFXButton;

import application.ArbitrageOrder;
import application.Exchanges;
import application.FxDialogs;
import application.Main;
import application.SocketCommunication;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ArbitrageController {
	
    @FXML CheckComboBox<String> ArbitrageExchange;
    @FXML private TextField bPAr;
    @FXML private TextField aPAr;
    @FXML private TextField mAAr;
    @FXML private JFXButton RunArbitrage;

    public static HashMap<JSONObject, ArbitrageOrder> ArbitrageOrderMap = new HashMap<JSONObject, ArbitrageOrder>();
    
	@FXML
    public void initialize(){
		 // create the data to show in the CheckComboBox 
		 final ObservableList<String> strings = FXCollections.observableArrayList();
		 for (int i = 0; i < Exchanges.list.size(); i++) {
		     strings.add(Exchanges.list.get(i));
		 }
		 System.out.println("Strings: " + strings);
		 ArbitrageExchange.getItems().addAll(strings);
		 ArbitrageExchange.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
		     public void onChanged(ListChangeListener.Change<? extends String> c) {
		         System.out.println(ArbitrageExchange.getCheckModel().getCheckedItems());
		     }
		 });
		 }
	
	@FXML
    public void arbitrageEvent(ActionEvent event) throws JSONException {
		boolean noerror=true;
		StringBuilder stringBuilder = new StringBuilder();
		System.out.println("running arbitrageEvent");
		ObservableList<String> exchanges = ArbitrageExchange.getCheckModel().getCheckedItems();
		List<Exchange> exchange = new ArrayList<Exchange>();
		for (String string : exchanges){
			exchange.add(Exchanges.exchangemap.get(string));
		}
		String base = bPAr.getText().toUpperCase();
		String alt = aPAr.getText().toUpperCase();
		String MinArbitrage = mAAr.getText();
    	if(!NumberUtils.isCreatable(MinArbitrage)) {
    		noerror=false;
    		stringBuilder.append(MinArbitrage + " is not a valid number(MinArbitrage).\n");
    	}
    	System.out.println(exchanges.size());
    	if (exchanges.size()<2) {
    		noerror=false;
    		stringBuilder.append("Must have atleast 2 exchanges selected for arbitrage\n");
    	}
    	if (noerror==true) {
			JSONObject arbitrageJSON = new JSONObject();
			arbitrageJSON.put("base", base);
			arbitrageJSON.put("alt", alt);
			arbitrageJSON.put("MinArbitrage",MinArbitrage);
			String[] exchangestotal = new String[exchanges.size()];
			int x=0;
			for (String string : exchanges){
				exchangestotal[x]=string;
				x++;
			}
			arbitrageJSON.put("Exchanges",exchangestotal);
			arbitrageJSON.put("request","arbitrageOrder");
			arbitrageJSON.put("licenceKey", SocketCommunication.licencekey);
			arbitrageJSON.put("millisstart", System.currentTimeMillis());
			arbitrageJSON.put("endtime","N/A");
	    	Random rand = new Random(); 
	    	int value = rand.nextInt(1000000000); 
	    	arbitrageJSON.put("orderid", value);
	    	arbitrageJSON.put("running","True");
	    	arbitrageJSON.put("cancel","False");
			System.out.println(arbitrageJSON);
	    	ArbitrageOrder arbit = new ArbitrageOrder(base,alt,MinArbitrage,exchange,arbitrageJSON,exchangestotal);
	    	ArbitrageOrderMap.put(arbitrageJSON, arbit);
	    	Thread t = new Thread(arbit);
	    	t.start();
	    	Main.logger.log(Level.INFO, "Started running arbitrage order");
		} else {
			String finalString = stringBuilder.toString();
			FxDialogs.showError(null, finalString);
		}
	}
	
	public static void cancelArbitrageOrder(String orderid) {
		System.out.println("Cancel market order");
		for (Map.Entry<JSONObject, ArbitrageOrder> entry : ArbitrageOrderMap.entrySet()) {
		    JSONObject key = entry.getKey();
			try {
				if (key.getInt("orderid") == java.lang.Integer.parseInt(orderid)) {
					ArbitrageOrder value = entry.getValue();
					value.stopOrder();
				}
			} catch (java.lang.Exception e) {
				e.printStackTrace();
			}

	}
	}
}
