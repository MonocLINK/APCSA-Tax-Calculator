import java.util.Scanner;
import java.text.DecimalFormat;

public class Main {

    public static void main(String[] args) {

        // TAX VALUES, ARRAYS, AND VARS

        // WI 2020 single head of household values from https://www.efile.com/wisconsin-tax-brackets-rates-and-forms/
        // length: 4
        final int[] WI_TAX_BRACKETS = {0, 11970, 23930, 263480};
        final double[] WI_TAX_FLATS = {0, 423.74, 979.88, 15,999.67};
        final double[] WI_TAX_PERCENTS = {0.0354, 0.0465, 0.0627, 0.0765};
        
        // FEDERAL 2020 values from https://www.nerdwallet.com/article/taxes/federal-income-tax-brackets
        // length: 7
        final int[] FED_TAX_BRACKETS = {0, 9875, 40125, 855260, 163300, 207350, 518400};
        final double[] FED_TAX_FLATS = {0, 987.5, 4617.5, 14605.5, 33271.5, 47367.5, 156235};
        final double[] FED_TAX_PERCENTS = {0.1, 0.12, 0.22, 0.24, 0.32, 0.35, 0.37};

        //MEDICARE values from marzahl's video
        // length: 2
        final double[] MEDICARE_BRACKETS = {0, 200000};
        final double[] MEDICARE_FLATS = {0, 2900};
        final double[] MEDICARE_PERCENTS = {0.0145, 0.0235};

        // SS vales from marzahl's video
        // length: 2
        final double[] SOCIAL_SECURITY_PERCENTS = {0.062, 0};
        final double[] SOCIAL_SECURITY_FLATS = {0, 8537.4};
        final double[] SOCIAL_SECURITY_BRACKET = {0, 137700};

        // END TAX VALUES, ARRAYS, AND VARS

        // ETC VARIABLES

        Scanner inp = new Scanner(System.in);
        double userIncome;
        double finalUserIncome;

        int wiIndex = 0;
        int fedIndex = 0;
        int ssIndex = 0;
        int medicareIndex = 0;

        double wiTaxValue = 0;
        double fedTaxValue = 0;
        double ssTaxValue = 0;
        double medicareTaxValue = 0;

        double finalTaxValue = 0;

        // END ETC VARIABLES

        /*******************************************************/

        // CALCULATE TAX

        // user input for var userIncome
        println("Enter your tax income: ");
        userIncome = inp.nextInt();
        inp.close();

        // get bracket of WI tax
        for(int i = 0; i < WI_TAX_BRACKETS.length; i++){ // cycle through tax brackets
            if(userIncome > WI_TAX_BRACKETS[i]){
                wiIndex = i;
            }
        }

        // get bracket of federal tax
        for(int i = 0; i < FED_TAX_BRACKETS.length; i++){
            if(userIncome > FED_TAX_BRACKETS[i]){
                fedIndex = i;
            }
        }

        // get bracket of medicare tax
        for(int i = 0; i < MEDICARE_BRACKETS.length; i++){
            if(userIncome > MEDICARE_BRACKETS[i]){
                medicareIndex = i;
            }
        }

        // get bracket of social security tax
        for(int i = 0; i < SOCIAL_SECURITY_BRACKET.length; i++){
            if(userIncome > SOCIAL_SECURITY_BRACKET[i]){
                ssIndex = i;
            }
        }

        // tax value calculation
        wiTaxValue = ((userIncome - WI_TAX_BRACKETS[wiIndex]) * WI_TAX_PERCENTS[wiIndex]) + WI_TAX_FLATS[wiIndex];
        fedTaxValue = ((userIncome - FED_TAX_BRACKETS[fedIndex]) * FED_TAX_PERCENTS[fedIndex]) + FED_TAX_FLATS[fedIndex];
        medicareTaxValue = ((userIncome - MEDICARE_BRACKETS[medicareIndex]) * MEDICARE_PERCENTS[medicareIndex]) + MEDICARE_FLATS[medicareIndex];
        ssTaxValue = ((userIncome - SOCIAL_SECURITY_BRACKET[ssIndex]) * SOCIAL_SECURITY_PERCENTS[ssIndex]) + SOCIAL_SECURITY_FLATS[ssIndex];

        finalTaxValue = wiTaxValue + fedTaxValue + medicareTaxValue + ssTaxValue;   // total off all tax values

        finalUserIncome = userIncome - finalTaxValue;   // take home pay

        // END TAX CALCULATION

        /*******************************************************/

        // FINAL OUTPUT

        outputTaxInfo(userIncome, wiTaxValue, fedTaxValue, ssTaxValue, medicareTaxValue, finalTaxValue, finalUserIncome);

        // END FINAL OUTPUT
    }

    private static void outputTaxInfo(double userIncome, double wiTaxValue, double fedTaxValue, double ssTaxValue, double medicareTaxValue, double finalTaxValue, double finalUserIncome) {
        DecimalFormat formatter = new DecimalFormat("###,###.00");  // formatting
        println("\n\n\n");

        println("You made $" + formatter.format(userIncome) + " in one year.");   // user income

        // individual and total tax
        println("\nWisconsin took a tax of $" + formatter.format(wiTaxValue));
        println("The Federal Government took a tax of $" + formatter.format(fedTaxValue));
        println("Social Security took a tax of $" + formatter.format(ssTaxValue));
        println("Medicare took a tax of $" + formatter.format(medicareTaxValue));

        println("\nIn total, your paycheck was taxed $" + formatter.format(finalTaxValue));
    
        println("You are left with $" + formatter.format(finalUserIncome) + "\n");
    }

    // makes output easier
    private static void println(Object o) {
        System.out.println(o);
    }
}