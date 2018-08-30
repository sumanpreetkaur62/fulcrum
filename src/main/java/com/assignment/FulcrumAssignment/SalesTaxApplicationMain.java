package com.assignment.FulcrumAssignment;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class SalesTaxApplicationMain {
    public static void main(String[] args) throws IOException {
        System.out.println("Select The Basket");
        System.out.println("1. Basket 1 ----");

        System.out.println(
                "    1 book at 12.49\r\n" + "    1 music CD at 14.99\r\n" + "    1 chocolate bar at 0.85\r\n" + "");

        System.out.println("2. Basket 2 ----");

        System.out.println(
                "    1 imported box of chocolates at 10.00\r\n" + "    1 imported bottle of perfume at 47.50\r\n" + "");

        System.out.println("3. Basket 3 ----");

        System.out.println("    1 imported bottle of perfume at 27.99\r\n" + "    1 bottle of perfume at 18.99\r\n"
                + "    1 packet of headache pills at 9.75\r\n" + "    1 box of imported chocolates at 11.25\r\n" + "");
        System.out.println("4. to customize your basket");
        Billing b = new Billing();
        Scanner sc = new Scanner(System.in);

        int basketNumber = b.getBasketNumber(sc);

        Map<String, Double> basket = b.intialiseBasketItem(basketNumber);
        if(basket != null) {
            Map<String, Double> price=b.generateInvoice(basket);
            b.displayInvoice(price);
        }else{
            System.out.println("Please select from the above choices only!");
        }

    }
}

