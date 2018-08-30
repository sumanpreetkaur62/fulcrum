package com.assignment.FulcrumAssignment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Billing {
    Map<String, Double> basketItem;
    String[] exemptedGoods = { "book", "chocolate", "pills", "exempted" };
    int basketNumber;
    Scanner sc;
    /*
     * Handles the exception if user input invalid value. and ask user to enter
     * correct value.
     */
    public int getBasketNumber(Scanner sc) {

        this.sc = sc;
        try {
            basketNumber = sc.nextInt();

        } catch (Exception e) { //catch Exception for invalid input (especiall y if user inputs character instead of Integer
            System.out.println("Enter a valid Number (1,2,3,4):");

            getBasketNumber(new Scanner(System.in));

        }
        return basketNumber;

    }
    /*
     * initiating the basketItem hash map with items and price according to the user
     * input
     */
    public Map<String, Double> intialiseBasketItem(int basketNumber) {

        switch (basketNumber) {

            case 1: {
                /* Basket 1 */
                basketItem = new LinkedHashMap<String, Double>();
                basketItem.put("1 book", 12.49);
                basketItem.put("1 Music CD", 14.99);
                basketItem.put("1 chocolate bar", 0.85);
                return basketItem;

            }
            case 2: {
                /* Basket 2 */
                basketItem = new LinkedHashMap<String, Double>();
                basketItem.put("1 imported box of chocolates", 10.00);
                basketItem.put("1 imported bottle of perfume", 47.50);
                return basketItem;
            }
            case 3: {
                /* Basket 3 */
                basketItem = new LinkedHashMap<String, Double>();
                basketItem.put("1 imported bottle of perfume", 27.99);
                basketItem.put("1 bottle of perfume", 18.99);
                basketItem.put("1 packet of headache pills", 9.75);
                basketItem.put("1 box of imported chocolates", 11.25);
                return basketItem;
            }
            case 4: {
                /* Basket 4 */
                basketItem = new LinkedHashMap<String, Double>();
                basketItem = getUserDefinedItem();
                return basketItem;
            }

            default: {
                /* invalid input.. */
                System.out.println("Invalid Input!");
                return null;
            }
        }

    }

    public Map<String, Double> getUserDefinedItem() {
        basketItem = new LinkedHashMap<String, Double>();
        Double finalPrice = null;
        String item = "";
        int number = 0;
// no of item in the basket:
        try {

            System.out.println("Enter the number of unique items you want in your basket:");
            number = sc.nextInt();
        } catch (Exception e) {
            System.out.println("invalid Input");
        }
// get item details:
        for (int i = 1; i <= number; i++) {

            try {
// get Item detail:
                System.out.println("Enter name of your Item number  " + i + ":");
                String inputNameOfItem = new Scanner(System.in).nextLine(); //need new object since nextLine ends until scanner object has '\n' character
                item = item + inputNameOfItem;
                System.out.println("Please enter if the given item is a food item/ medical item or a book: y/n");
                String Temp = sc.next();

                if (Temp.equalsIgnoreCase("y")) {
                    item = item + " exempted";
                } else if (!Temp.equalsIgnoreCase("n") && !Temp.equalsIgnoreCase("y")) {
                    System.out.println("invalid input");
                    System.exit(0);
                }
                System.out.println("Is it imported y/n:");
                String imported = sc.next();

                if (imported.equalsIgnoreCase("y")) {
                    item = " imported  " + item;
                } else if (!imported.equalsIgnoreCase("y") && !imported.equalsIgnoreCase("n")) {
                    System.out.println("invalid input");
                    System.exit(0);
                }
                System.out.println("Please enter the quantity you want:");
                int noOfItem = 0;

                noOfItem = sc.nextInt();
                item = noOfItem + " " + item;

                System.out.println("Please enter the price:");
                Double priceOfoneItem = sc.nextDouble();
                finalPrice = priceOfoneItem * noOfItem;

                basketItem.put(item, finalPrice);
            } catch (Exception e) {
                System.out.println("invalid input");
            }

        }

        return basketItem;
    }

    /*
     * checks if given item is exempted from tax or not return true if exempted.
     */
    public boolean isExempted(String item) {
        for (String str : exemptedGoods) {
            if (item.contains(str)) {
                return true;
            }
        }
        return false;
    }

    /*
     * calculate the tax on given price take arguments as price and sales tax %
     * applicable on it
     */
    public Double calculateSalesTax(Double price, Double salesTaxpercentage) {

        Double salesTax = price * salesTaxpercentage / 100;
// round off to nearest 0.05
        salesTax = Math.ceil(salesTax * 20) / 20;
        return salesTax;
    }

    /*
     * calculate the total
     */
    public Double calculateTotal(Double variable1, Double variable2) {

        Double value = variable1 + variable2;
        BigDecimal temp = new BigDecimal(value);
        value = temp.setScale(2, RoundingMode.HALF_UP).doubleValue();
        return value;

    }

    /*
     * Calculate the price for all items and update it in HashMap Calculate total
     * amount of tax paid and total amount paid and update it in Hashmap Output for
     * Invoice
     */
    public Map<String, Double> generateInvoice(Map<String, Double> inputbasketItem) {
        Map<String, Double> basketItem = inputbasketItem;
        Map<String, Double> outputMap = new LinkedHashMap<String, Double>();
        Set<String> keySet = basketItem.keySet();
        Double totalsalestax = new Double(0);
        Double total = new Double(0);
        Double salestax = new Double(0);

        for (String key : keySet) {
            if (key.contains("imported")) {
                if (isExempted(key)) {

// calculate sales tax for Item
                    salestax = calculateSalesTax(basketItem.get(key), Category.ImportedDuty.getSalesTax());
// calculate total price for the item
                    Double finalValue = calculateTotal(basketItem.get(key), salestax);
                    totalsalestax = calculateTotal(totalsalestax, salestax);
                    total = calculateTotal(total, finalValue);
// update the hash map with final value
                    if (key.contains("exempted")) {
                        String key1 = key.replaceAll("exempted", "");
                        outputMap.put(key1, finalValue);
                    } else {
                        outputMap.put(key, finalValue);
                    }
                } else {
                    salestax = calculateSalesTax(basketItem.get(key),
                            Category.ImportedDuty.getSalesTax() + Category.SaleTax.getSalesTax());

                    Double finalValue = calculateTotal(basketItem.get(key), salestax);

                    outputMap.put(key, finalValue);

                    totalsalestax = calculateTotal(totalsalestax, salestax);

                    total = calculateTotal(total, outputMap.get(key));

                }

            } else if (isExempted(key)) {
                double price = basketItem.get(key);
                if (key.contains("exempted")) {
                    String key1 = key.replaceAll("exempted", "");
                    outputMap.put(key1, price);
                } else {
                    outputMap.put(key, price);
                }

                total = calculateTotal(total, basketItem.get(key));

            } else {
                salestax = calculateSalesTax(basketItem.get(key), Category.SaleTax.getSalesTax());

                Double finalValue = calculateTotal(basketItem.get(key), salestax);

                outputMap.put(key, finalValue);

                totalsalestax = calculateTotal(totalsalestax, salestax);

                total = calculateTotal(total, outputMap.get(key));

            }

        }
// add entry for total sales tax paid and total amount paid in hash map
        outputMap.put("SalesTax", totalsalestax);
        outputMap.put("Total", total);
// return updated basketItem
        return outputMap;

    }

    // display the invoice to console
    public void displayInvoice(Map<String, Double> outputMap) {
        Set<String> keySet = outputMap.keySet();
        for (String key : keySet) {
            System.out.println(key + ": " +String.format("%.2f",outputMap.get(key)));

        }
    }
}
