package application;

import org.json.JSONException;

import controllers.ArbitrageController;
import controllers.AveragetradingController;
import controllers.DashboardController;
import controllers.LiveController;
import controllers.MarketController;
import controllers.PendingController;
import controllers.TrailingController;

public class RemoveOrder {
	public static void removeOrder(DashboardController.Person person) throws JSONException {
		String ordertype = person.getOrderType();
		System.out.println("REMOVING");
		if (ordertype.equals("averageTrading")) {
			AveragetradingController.cancelAverageOrder(person.getOrderID());
		} else if(ordertype.equals("trailingStop")) {
			TrailingController.removeOrder(person.getOrderID());
		} else if(ordertype.equals("pendingOrder")) {
			PendingController.cancelPendingOrder(person.getOrderID());
		} else if(ordertype.equals("marketMaking")) {
			MarketController.cancelMarketOrder(person.getOrderID());
		} else if(ordertype.equals("arbitrage")) {
			ArbitrageController.cancelArbitrageOrder(person.getOrderID());
		} else if(ordertype.equals("livetrading")) {
			LiveController.cancelLiveOrder(person.getOrderID());
			//LiveController.show
		}
	}
}
