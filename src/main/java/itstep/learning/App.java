package itstep.learning;

import com.google.inject.Guice;
import com.google.inject.Injector;
import itstep.learning.db.DatabaseConnectionService;
import itstep.learning.db.DatabaseService;
import itstep.learning.db.DbDemo;
import itstep.learning.db.LaunchJournal;
import itstep.learning.ioc.IocDemo;
import itstep.learning.ioc.ServicesModule;

public class App 
{
    public static void main( String[] args )
    {
        Guice.createInjector( new ServicesModule() )
                .getInstance( IocDemo.class )
                .run();

        Injector injector = Guice.createInjector(new ServicesModule());
        LaunchJournal journal = injector.getInstance(LaunchJournal.class);

        journal.printJournal();

        journal.logLaunch();
    }
}
