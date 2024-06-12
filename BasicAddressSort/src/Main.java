import data.address;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception {

        //address addr1 = new address("Flat 1A", "Sample House", "1 Sample Street", "Sample Town", "Sample County", "AB12 3CD", "Sample Country");
        //address a = new address("British Embassy Tirana", "Rruga Skenderbeg 12", "Tirana", "", "", "", "Albania");
        //addr1.printDetails();
        //a.printDetails();

        List<String> lines = Collections.emptyList();
        lines = Files.readAllLines(Paths.get("C:\\Users\\Lucy\\IdeaProjects\\BasicAddressSort\\out\\production\\BasicAddressSort\\Address.txt"), StandardCharsets.UTF_8);

        int recordCount = 0;
        long lineCount = lines.stream().count();

        //records are separated by empty lines
        for (int currentLine = 0; currentLine < lineCount; currentLine++) {
            String checkLine = lines.get(currentLine);
            if (checkLine.isEmpty()) {
                recordCount++;
            }
        }

        System.out.println("recordCount [" + recordCount + "]");

        address[] addressData;
        addressData = new address[recordCount];
        //addressData[0] = new address("Flat 1A", "Sample House", "1 Sample Street", "Sample Town", "Sample County", "AB12 3CD", "Sample Country");
        //addressData[1] = new address("British Embassy Tirana", "Rruga Skenderbeg 12", "Tirana", "", "", "", "Albania");

        int currentField = 0;
        int currentRecord = 0;
        String storeField1 = ""; //addrLn1
        String storeField2 = ""; //addrLn2
        String storeField3 = ""; //addrLn3
        String storeField4 = ""; //addrLn4
        String storeField5 = ""; //addrLn5
        String storeField6 = ""; //pstCd
        String storeField7 = ""; //country

        for (int i = 0; i < lines.size(); i++) {

            String check = lines.get(i);

            if (check.isEmpty()) {
                addressData[currentRecord] = new address(storeField1, storeField2, storeField3, storeField4, storeField5, storeField6, storeField7);

                currentField = 0;
                currentRecord++;
                storeField1 = "";
                storeField2 = "";
                storeField3 = "";
                storeField4 = "";
                storeField5 = "";
                storeField6 = "";
                storeField7 = "";
            } else {
                switch (currentField) {
                    case 0:
                        storeField1 = check;
                        break;

                    case 1:
                        storeField2 = check;
                        break;

                    case 2:
                        storeField3 = check;
                        break;

                    case 3:
                        storeField4 = check;
                        break;

                    case 4:
                        storeField5 = check;
                        break;

                    case 5:
                        storeField6 = check;
                        break;

                    case 6:
                        storeField7 = check;
                        break;
                } //end of switch
                currentField++;
            }
        }

        print_address(addressData);

        //TEST Sort data by addrLn2 to ensure the data is NOT supplied in a desired order
        Arrays.sort(addressData, (address1, address2) -> address1.get_addrLn2().compareTo(address2.get_addrLn2()));
        //Sort by isUK true, false
        Arrays.sort(addressData, (address1, address2) -> Boolean.compare(address2.get_isUK(), address1.get_isUK()));

        print_address(addressData);

    } //End of public static void main

    static void print_address(address[] addressData) {
        for (int currentAddress = 0; currentAddress < Arrays.stream(addressData).count(); currentAddress++) {
            System.out.println("Record [" + currentAddress + "] " +
                    "isUK [" +    addressData[currentAddress].get_isUK() + "] " +
                    "address [" + addressData[currentAddress].get_addrLn1() + "|" +
                                  addressData[currentAddress].get_addrLn2() + "|" +
                                  addressData[currentAddress].get_addrLn3() + "|" +
                                  addressData[currentAddress].get_addrLn4() + "|" +
                                  addressData[currentAddress].get_addrLn5() + "|" +
                                  addressData[currentAddress].get_pstCd() + "|" +
                                  addressData[currentAddress].get_country() + "]");
        }
    }

} //End of public class Main