package Main;

import javafx.geometry.Insets;

public interface PhysicsInterface {
    
    final double gAcceleration = 9.8, oneHalf = 0.5, z = 0.00;
    int zero = 0, one = 1, two = 2, five = 5, ten = 10, oneHundred = 100, oneTwenty = 120, midScreen = 640;
    final double constantK = 9*(Math.pow(10, 9));
    final double yAcceleration = -9.8, piTimesTwo =  Math.PI*two, speedOfSound = 343.5;
    final double px10 = 10;
    final double receiverLength=70;
    double ttDur =50000;
    double ftDur =45000;
    double VerticalChargeHeight =222;
    double VerticalArrowHeight =150;
    double half = 0.5;
    double iniD =1000;
    double iniLpos1 =600;
    double iniLpos2 = 100;
    double iniRpos1=650;
    double iniRpos2 =1150;
    double spaceLength=500;
    String underConstruction = "In Construction", constructionMessage = "Not Available", 
            pressOKButton = "This section is under construction.";
        Insets basicInset = new Insets(15, 20, 15, 20);
}
