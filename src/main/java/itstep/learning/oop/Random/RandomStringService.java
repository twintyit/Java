package itstep.learning.oop.Random;

public class RandomStringService {

    private final RandomStringGenerator fileNameGenerator;
    private final RandomStringGenerator saltGenerator;
    private final RandomStringGenerator otpGenerator;
    private final RandomStringGenerator passwordGenerator;

    public RandomStringService(RandomStringGenerator fileNameGenerator,
                               RandomStringGenerator saltGenerator,
                               RandomStringGenerator otpGenerator,
                               RandomStringGenerator passwordGenerator) {
        this.fileNameGenerator = fileNameGenerator;
        this.saltGenerator = saltGenerator;
        this.otpGenerator = otpGenerator;
        this.passwordGenerator = passwordGenerator;
    }

    public String generateFileName() {
        return fileNameGenerator.generate();
    }

    public String generateSalt() {
        return saltGenerator.generate();
    }

    public String generateOTP() {
        return otpGenerator.generate();
    }

    public String generatePassword() {
        return passwordGenerator.generate();
    }
}
