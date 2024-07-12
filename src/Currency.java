import java.util.Scanner;

public class Currency {

    public void CurrencyHandler() {

        Scanner sc = new Scanner(System.in);

        String date;
        String valuteCode;
        double amount;

        System.out.print("Enter the date in DD.MM.YYYY format: ");
        date = sc.nextLine();
        System.out.print("Enter the valuteCode in USD/EUR format: ");
        valuteCode = sc.nextLine().toUpperCase();
        System.out.print("Enter the amount: ");
        amount = sc.nextDouble();

        XMLReader xr = new XMLReader();
        xr.XMLHandler(date, valuteCode, amount);

    }

}