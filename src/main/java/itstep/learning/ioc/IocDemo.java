package itstep.learning.ioc;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import itstep.learning.services.hash.Random.RandomStringService;

public class IocDemo {
    private final RandomStringService randomFileNameService;
    private final RandomStringService randomPasswordService;
    private final RandomStringService randomOtpService;
    private final RandomStringService randomSaltService;

    @Inject
    public IocDemo(
          // @Named("fileNameService")
           RandomStringService randomFileNameService,
           // @Named("passwordService")
            RandomStringService randomPasswordService,
           // @Named("otpService")
           RandomStringService randomOtpService,
           // @Named("saltService")
           RandomStringService randomSaltService
            ) {
        this.randomFileNameService = randomFileNameService;
        this.randomPasswordService = randomPasswordService;
        this.randomSaltService = randomSaltService;
        this.randomOtpService = randomOtpService;

    }

    public void run(){
        System.out.println("File Name: " + randomFileNameService.generateFileName());
        System.out.println("Salt: " + randomPasswordService.generateSalt());
        System.out.println("OTP: " + randomSaltService.generateOTP());
        System.out.println("Password: " + randomOtpService.generatePassword());
    }
}
