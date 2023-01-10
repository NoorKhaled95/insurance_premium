import java.text.DecimalFormat;
import java.util.Calendar;

public class App {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) throws Exception {
        System.out.println("-------Insurance Premium-------");
        try {
            System.out.println(df.format(calculateInsurancePremium(40, isNew(2020), true, true)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean isNew(int year) throws Exception {
        Calendar currentDate = Calendar.getInstance();
        int currentYear = currentDate.get(Calendar.YEAR);
        if (year >= 1900 && year <= currentYear)
            return year > currentYear - 4;
        throw new Exception("The year is invalid");
    }

    public static double calculateInsurancePremium(int horsepower, boolean isNew, boolean isSportsCar,
            boolean isParkedInGarage) throws Exception {
        double basePremium;
        double additionalCost = 0;

        if (horsepower > 0 && horsepower <= 10000) {
            if (horsepower < 50) {
                basePremium = 400;
            } else if (horsepower <= 100) {
                basePremium = 600;
            } else {
                basePremium = 600 + (3.80 * (horsepower - 100));
            }
        } else {
            throw new Exception("Horsepower is invalid");
        }
        if (isSportsCar) {

            if (isNew) {
                additionalCost += basePremium * 0.08;
            } else {
                additionalCost += basePremium * 0.04;
            }
        }

        if (!isParkedInGarage) {
            additionalCost += basePremium * 0.08;
        } else {
            additionalCost -= basePremium * 0.02;
        }

        return basePremium + additionalCost;
    }

}
