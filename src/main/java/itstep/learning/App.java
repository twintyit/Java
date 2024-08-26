package itstep.learning;

import itstep.learning.oop.Random.*;
import itstep.learning.oop.Shop.Shop;

public class App 
{
    public static void main( String[] args )
    {
//        Shop shop = new Shop();
//        shop.printProducts();

        RandomStringService randomStringService = new RandomStringService(
            new FileNameGenerator(),
            new SaltGenerator(),
            new OTPGenerator(),
            new PasswordGenerator()
    );

        System.out.println("Generated File Name: " + randomStringService.generateFileName());
        System.out.println("Generated Salt: " + randomStringService.generateSalt());
        System.out.println("Generated OTP: " + randomStringService.generateOTP());
        System.out.println("Generated Password: " + randomStringService.generatePassword());
    }
}
