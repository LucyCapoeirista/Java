package data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class address {

    //data members
    String addrLn1;
    String addrLn2;
    String addrLn3;
    String addrLn4;
    String addrLn5;
    String pstCd;
    String country;
    Boolean isUK;

    //constructors
    public address(String addrLn1, String addrLn2, String addrLn3, String addrLn4, String addrLn5, String pstCd, String country) {
        this.addrLn1 = addrLn1;
        this.addrLn2 = addrLn2;
        this.addrLn3 = addrLn3;
        this.addrLn4 = addrLn4;
        this.addrLn5 = addrLn5;
        this.pstCd = pstCd;
        this.country = country;
        this.isUK = check_isUK();
        //printDetails();
    } //end of address

    //methods
    public void printDetails() {
        System.out.println("addrLn1 [" + addrLn1 + "]");
        System.out.println("addrLn2 [" + addrLn2 + "]");
        System.out.println("addrLn3 [" + addrLn3 + "]");
        System.out.println("addrLn4 [" + addrLn4 + "]");
        System.out.println("addrLn5 [" + addrLn5 + "]");
        System.out.println("pstCd [" + pstCd + "]");
        System.out.println("country [" + country + "]");
        System.out.println("isUK [" + isUK + "]");
    }

    public String get_addrLn1() {
        return addrLn1;
    }

    public String get_addrLn2() {
        return addrLn2;
    }

    public String get_addrLn3() {
        return addrLn3;
    }

    public String get_addrLn4() {
        return addrLn4;
    }

    public String get_addrLn5() {
        return addrLn5;
    }

    public String get_pstCd() {
        return pstCd;
    }

    public String get_country() {
        return country;
    }

    public Boolean get_isUK() {
        return isUK;
    }

    boolean check_isUK() {

        boolean returnValue;

        //based on https://en.wikipedia.org/wiki/Postcodes_in_the_United_Kingdom
        //^[A-Z]{1,2}[0-9][A-Z0-9]? ?[0-9][A-Z]{2}$
        //1st character MUST be a letter
        //2nd character CAN be a letter
        //Following character MUST be a number
        //Following character CAN be a 2nd number
        //Following character CAN be a letter
        Pattern postcodeUK = Pattern.compile(".*[A-Z]{1,2}[0-9]{1,2}[A-Z]{0,1}.*", Pattern.CASE_INSENSITIVE);
        Matcher workingPstCd = postcodeUK.matcher(addrLn2);
        boolean matchFound = workingPstCd.find();
        if (matchFound) {
            returnValue = true;
        } else {
            returnValue = false;
        }
        return returnValue;
    }

} //End of class address
