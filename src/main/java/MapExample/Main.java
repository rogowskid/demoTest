package MapExample;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.FileNotFoundException;
import java.util.*;

public class Main extends ControllerApi {

    public static void main(String[] args) throws JsonProcessingException, FileNotFoundException {

        ControllerApi controllerApi = new ControllerApi();
        Scanner scanner = new Scanner(System.in);

        double currentMoney = 0;
        System.out.println("Enter your money");
        currentMoney = scanner.nextDouble();
        System.out.println("Choose the currency you want to convert money");
        String curent = scanner.next().toUpperCase(Locale.ROOT);

        controllerApi.setValueFromApi();
        System.out.println(controllerApi.convertedMoney(currentMoney, curent));

        controllerApi.downloadValueApi();



    }
}
