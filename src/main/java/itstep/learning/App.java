package itstep.learning;

import com.google.inject.Guice;
import com.google.inject.Injector;
import itstep.learning.async.AsyncDemo;
import itstep.learning.async.PandigitalGenerator;
import itstep.learning.db.LaunchJournal;
import itstep.learning.fs.ConfigWriter;
import itstep.learning.ioc.IocDemo;
import itstep.learning.ioc.ServicesModule;

public class App 
{
    public static void main( String[] args )
    {
//        String projectDir = System.getProperty("user.dir"); // Абсолютный путь до корня проекта
//        String resourcePath = projectDir + "/src/main/resources/db.ini";
//
//        ConfigWriter cw = new ConfigWriter();
//        cw.createConfigFile(resourcePath);

//        AsyncDemo asyncDemo = new AsyncDemo();
//        asyncDemo.run();

        PandigitalGenerator pg = new PandigitalGenerator();
        pg.run();

//        Guice.createInjector( new ServicesModule() )
//                .getInstance( IocDemo.class )
//                .run();
//
//        Injector injector = Guice.createInjector(new ServicesModule());
//        LaunchJournal journal = injector.getInstance(LaunchJournal.class);
//
//        journal.printJournal();
//
//        journal.logLaunch();
    }
}
