package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase or Deposit Money";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};
	private static final String SECOND_MENU_OPTION_FEED_MONEY = "Deposit Money";
	private static final String SECOND_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String SECOND_MENU_OPTION_FINISH_TRANSACTION = "Back To Main Menu";
	private static final String[] SECOND_MENU_OPTIONS = {SECOND_MENU_OPTION_FEED_MONEY, SECOND_MENU_OPTION_SELECT_PRODUCT, SECOND_MENU_OPTION_FINISH_TRANSACTION};

	private Menu menu;
	private VendingMachine machine;

	public static void main(String[] args) throws IOException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachine machine = new VendingMachine();
		VendingMachineCLI cli = new VendingMachineCLI(menu, machine);
		cli.run();
	}

	public VendingMachineCLI(Menu menu, VendingMachine machine) {
		this.menu = menu;
		this.machine = machine;
	}

	public void run() throws IOException {

		menu.displayMainMenuBanner();
		while (true) {
			String firstChoice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (firstChoice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				machine.printListOfItems();

				} else if (firstChoice.equals(MAIN_MENU_OPTION_EXIT)) {
					System.out.println("--------------------------------------------------");
					System.out.println("\nThank you for shopping with us, please come again");
					break;

				} else if (firstChoice.equals(MAIN_MENU_OPTION_PURCHASE)) {
					while (true) {
						System.out.println("-------------------------------------------");
						System.out.println("Current balance: " + machine.getUserBalance());
						String purchaseChoice = (String) menu.getChoiceFromOptions(SECOND_MENU_OPTIONS);
						if (purchaseChoice.equals(SECOND_MENU_OPTION_FEED_MONEY)) {
							machine.depositMoney();
						} else if (purchaseChoice.equals(SECOND_MENU_OPTION_FINISH_TRANSACTION)) {
							break;
						} else if (purchaseChoice.equals(SECOND_MENU_OPTION_SELECT_PRODUCT)) {
							machine.purchaseItems();
						} else {
							System.out.println("Selection invalid, please choose again:");
						}
					}
				}
			}
		}
	}



