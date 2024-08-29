package itstep.learning.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import itstep.learning.db.DatabaseConnectionService;
import itstep.learning.db.DatabaseService;
import itstep.learning.services.hash.Random.*;

import java.sql.Connection;

public class ServicesModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(RandomStringGenerator.class)
                .annotatedWith(Names.named("fileName"))
                .to(FileNameGenerator.class);

//        bind(RandomStringGenerator.class)
//                .annotatedWith(Names.named("salt"))
//                .to(SaltGenerator.class);
//
//        bind(RandomStringGenerator.class)
//                .annotatedWith(Names.named("otp"))
//                .to(OtpGenerator.class);
//
//        bind(RandomStringGenerator.class)
//                .annotatedWith(Names.named("password"))
//                .to(PasswordGenerator.class);
//
//        // Инициализация RandomStringService с внедренными генераторами
//        bind(RandomStringService.class)
//                .annotatedWith(Names.named("fileNameService"))
//                .toInstance(new RandomStringService(new FileNameGenerator(), new SaltGenerator(), new OtpGenerator(), new PasswordGenerator()));
//
//        bind(RandomStringService.class)
//                .annotatedWith(Names.named("saltService"))
//                .toInstance(new RandomStringService(new FileNameGenerator(), new SaltGenerator(), new OtpGenerator(), new PasswordGenerator()));
//
//        bind(RandomStringService.class)
//                .annotatedWith(Names.named("otpService"))
//                .toInstance(new RandomStringService(new FileNameGenerator(), new SaltGenerator(), new OtpGenerator(), new PasswordGenerator()));
//
//        bind(RandomStringService.class)
//                .annotatedWith(Names.named("passwordService"))
//                .toInstance(new RandomStringService(new FileNameGenerator(), new SaltGenerator(), new OtpGenerator(), new PasswordGenerator()));

    }

    @Provides
    @Singleton
    DatabaseService provideDatabaseService() {
        return new DatabaseConnectionService();
    }

    @Provides
    @Singleton
    Connection provideConnection(DatabaseService databaseService) {
        return databaseService.getConnection();
    }

}
