package com.fcpdsample.driver;

import com.fcpdsample.model.business.manager.DAOManager;
import com.fcpdsample.model.domain.Car;
import com.fcpdsample.model.domain.Customer;
import com.fcpdsample.model.domain.Itinerary;
import com.fcpdsample.model.domain.RentalComposite;
import org.apache.log4j.Logger;

/*-
 * Please note that the FleetRental application can be run either by running
 * the ViewDriver class or this class. This class tests the Controller and Model
 * components.  
 * 
 * Its common to have test drivers such as this class to test applications.
 *  
 * A real world implementation, would of course have a View (either Swing or Web based).
 * 
	* 
	 ///////////////////////////////////////////////////////////
  // Need to pass property file that is loaded by PropertyManager
		// 1. From IDE, set the VM Options under Project Properties
		//     -Dprop_location=<path>\config\application.properties
		// 2. Build.xml: reads off from the target to run the app with the sys property set as :
		//   <sysproperty key="prop_location" value="${config.dir}application.properties"/>
	* 
 * @author Mike.Prasad
 *
 */
public class TestDriver {

  /*
					* Category set in config/log4j.properties as
					* log4j.category.com.classexercise=DEBUG, A1
   */
  static Logger log = Logger.getLogger("com.fleetrentalconnpooldaosample");

  public static void main(String[] args) {
    Log4JInit.initializeLog4J();

    // In the real world implementation, customer would identify
    // an Itinerary.
    RentalComposite rentalComposite = new RentalComposite();

    // lets create a sample itinerary.
    // Itinerary constructor needs following fields : 
    // (fleetRentalPickUp, fleetRentalDropOff,pickUpMonth,pickUpDay,pickUpYear,pickUpTime,dropOffMonth,dropOffDay, dropOffYear,dropOffTime)		
    rentalComposite.setItinerary(new Itinerary("San Francisco Airport", "San Francisco Airport", "06", "18", "2006", "01:10", "06", "28", "2006", "12:00"));

    log.info("\n----------------");
    log.info("\n-->Checking Car Availablity for itinerary: \n\n" + rentalComposite.getItinerary());

    // now that we have an itinerary, lets call into the Model via the controller,
    // to see if have any cars available for this itinerary
    DAOManager daoManager = DAOManager.getInstance();
    boolean status = daoManager.performAction("ProcessItinerary", rentalComposite);

    if (status) //if true then request processed successfully
    {
      // Lets check if cars are available if so we can reserve them.
      if (rentalComposite != null) {
        if (rentalComposite.getAvailableRentals() != null) {
          if (rentalComposite.getAvailableRentals().isAvailable()) {
            // Cool, we have a car to rent, lets get Customer info and the Car customer
            // wants to rent.
            log.info("\n-->Cars available for above itinerary: \n\n" + rentalComposite.getAvailableRentals());

            // User enters personal info
            // Customer contructor takes in lastname, firstname, email address, day time phone and evening phone
            rentalComposite.setCustomer(new Customer("Simpson", "Homer", "homer@duff.com", "303-786-1111", "303-786-1111"));

            // User select the car he/she wants to rent
            // Car constructor takes in rate, manufacturer, model, miles included
            rentalComposite.setRentedCar(new Car(25.50f, "Ford", "Focus", "Unlimited"));

            log.info("\n-->Calling reserve rental car service with this details: \n\n" + rentalComposite);

            // Ideally the type of the service that needs to be executed
            // is mapped in a properties file. Hardcoded here to
            // illustrate the example.
            daoManager.performAction("ReserveRental", rentalComposite);
          } else {
            // Hopefully this doesn't happen in the real world! :)
            log.info("No car available! Suggest hitchhiking!");
          } //end if
        } else {
          // AvailableRentals is NULL - this due to SQL Exception issue
          log.error("We are facing an issue, please try back later!");
        }//end if								
      } //end if							
    } else {
      log.error("We are facing an issue, please try back later!");
    }

  } //end main

} //end class TestDriver


/*-
Output:

Successfully configured log4j
10:51:43.439 [mainhread] com.fcpdsample.driver.TestDriver.main(TestDriver.java:50) -
----------------
10:51:43.441 [mainhread] com.fcpdsample.driver.TestDriver.main(TestDriver.java:51) -
-->Checking Car Availablity for itinerary: 

fleetRentalPickUp Id:null
fleetRentalPickUp City:San Francisco Airport
fleetRentalDropOff Id:null
fleetRentalDropOff City:San Francisco Airport
pickUpMonth :06
pickUpDay :18
pickUpYear :2006
pickUpTime :01:10
dropOffMonth :06
dropOffDay :28
dropOffYear :2006
dropOffTime :12:00
qtyRentalDays :10
10:51:43.470 [mainhread] com.mchange.v2.log.MLog.<clinit>(MLog.java:92) -MLog clients using log4j logging.
10:51:43.676 [mainhread] com.mchange.v2.c3p0.C3P0Registry.banner(C3P0Registry.java:216) -Initializing c3p0-0.9.2.1 [built 20-March-2013 11:16:28 +0000; debug? true; trace: 10]
10:51:43.697 [mainhread] com.mchange.v2.c3p0.management.DynamicPooledDataSourceManagerMBean.reinitialize(DynamicPooledDataSourceManagerMBean.java:258) -MBean: com.mchange.v2.c3p0:type=PooledDataSource,identityToken=2s77yk9w12czx9o42yx1e|2328c243,name=2s77yk9w12czx9o42yx1e|2328c243 registered.
10:51:43.715 [mainhread] com.mchange.v2.c3p0.management.DynamicPooledDataSourceManagerMBean.reinitialize(DynamicPooledDataSourceManagerMBean.java:253) -MBean: com.mchange.v2.c3p0:type=PooledDataSource,identityToken=2s77yk9w12czx9o42yx1e|2328c243,name=2s77yk9w12czx9o42yx1e|2328c243 unregistered, in order to be reregistered after update.
10:51:43.715 [mainhread] com.mchange.v2.c3p0.management.DynamicPooledDataSourceManagerMBean.reinitialize(DynamicPooledDataSourceManagerMBean.java:258) -MBean: com.mchange.v2.c3p0:type=PooledDataSource,identityToken=2s77yk9w12czx9o42yx1e|2328c243,name=2s77yk9w12czx9o42yx1e|2328c243 registered.
10:51:43.716 [mainhread] com.fcpdsample.model.dao.jdbc.manager.JDBCConnectionPoolManager.createPool(JDBCConnectionPoolManager.java:34) --Creating Connection Pool
10:51:43.725 [mainhread] com.mchange.v2.c3p0.management.DynamicPooledDataSourceManagerMBean.reinitialize(DynamicPooledDataSourceManagerMBean.java:258) -MBean: com.mchange.v2.c3p0:type=PooledDataSource,identityToken=2s77yk9w12czx9o42yx1e|4c3e4790,name=2s77yk9w12czx9o42yx1e|4c3e4790 registered.
10:51:43.735 [mainhread] com.mchange.v2.c3p0.management.DynamicPooledDataSourceManagerMBean.reinitialize(DynamicPooledDataSourceManagerMBean.java:253) -MBean: com.mchange.v2.c3p0:type=PooledDataSource,identityToken=2s77yk9w12czx9o42yx1e|4c3e4790,name=2s77yk9w12czx9o42yx1e|4c3e4790 unregistered, in order to be reregistered after update.
10:51:43.735 [mainhread] com.mchange.v2.c3p0.management.DynamicPooledDataSourceManagerMBean.reinitialize(DynamicPooledDataSourceManagerMBean.java:258) -MBean: com.mchange.v2.c3p0:type=PooledDataSource,identityToken=2s77yk9w12czx9o42yx1e|4c3e4790,name=2s77yk9w12czx9o42yx1e|4c3e4790 registered.
10:51:43.736 [mainhread] com.fcpdsample.model.dao.jdbc.manager.JDBCConnectionPoolManager.createPool(JDBCConnectionPoolManager.java:108) --Connection Pool creation completed
10:51:43.746 [mainhread] com.fcpdsample.model.dao.jdbc.FleetRentalJDBCDaoImpl.getAvailableRentals(FleetRentalJDBCDaoImpl.java:40) --------------------------------
10:51:43.746 [mainhread] com.fcpdsample.model.dao.jdbc.FleetRentalJDBCDaoImpl.getAvailableRentals(FleetRentalJDBCDaoImpl.java:41) -Using JDBC Implementation
10:51:43.746 [mainhread] com.fcpdsample.model.dao.jdbc.FleetRentalJDBCDaoImpl.getAvailableRentals(FleetRentalJDBCDaoImpl.java:42) --------------------------------
10:51:43.746 [mainhread] com.fcpdsample.model.dao.jdbc.FleetRentalJDBCDaoImpl.getAvailableRentals(FleetRentalJDBCDaoImpl.java:44) -Inside Get Available Rentals
10:51:43.771 [mainhread] com.mchange.v2.c3p0.impl.AbstractPoolBackedDataSource.getPoolManager(AbstractPoolBackedDataSource.java:522) -Initializing c3p0 pool... com.mchange.v2.c3p0.ComboPooledDataSource [ acquireIncrement -> 3, acquireRetryAttempts -> 30, acquireRetryDelay -> 1000, autoCommitOnClose -> false, automaticTestTable -> null, breakAfterAcquireFailure -> false, checkoutTimeout -> 0, connectionCustomizerClassName -> null, connectionTesterClassName -> com.mchange.v2.c3p0.impl.DefaultConnectionTester, dataSourceName -> 2s77yk9w12czx9o42yx1e|4c3e4790, debugUnreturnedConnectionStackTraces -> false, description -> null, driverClass -> com.mysql.cj.jdbc.Driver, factoryClassLocation -> null, forceIgnoreUnresolvedTransactions -> false, identityToken -> 2s77yk9w12czx9o42yx1e|4c3e4790, idleConnectionTestPeriod -> 0, initialPoolSize -> 3, jdbcUrl -> jdbc:mysql://localhost:3306/regis, maxAdministrativeTaskTime -> 0, maxConnectionAge -> 0, maxIdleTime -> 0, maxIdleTimeExcessConnections -> 0, maxPoolSize -> 5, maxStatements -> 0, maxStatementsPerConnection -> 0, minPoolSize -> 10, numHelperThreads -> 3, preferredTestQuery -> null, properties -> {user=******, password=******}, propertyCycle -> 0, statementCacheNumDeferredCloseThreads -> 0, testConnectionOnCheckin -> false, testConnectionOnCheckout -> false, unreturnedConnectionTimeout -> 0, userOverrides -> {}, usesTraditionalReflectiveProxies -> false ]
10:51:43.784 [mainhread] com.mchange.v2.resourcepool.BasicResourcePool.incrementPendingAcquires(BasicResourcePool.java:450) -incremented pending_acquires: 1
10:51:43.785 [mainhread] com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.<init>(BasicResourcePool.java:1788) -Starting acquisition series. Incremented pending_acquires [1],  attempts_remaining: 30
10:51:43.785 [mainhread] com.mchange.v2.async.ThreadPoolAsynchronousRunner.postRunnable(ThreadPoolAsynchronousRunner.java:236) -com.mchange.v2.async.ThreadPoolAsynchronousRunner@c39f790: Adding task to queue -- com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@71e7a66b
10:51:43.785 [mainhread] com.mchange.v2.resourcepool.BasicResourcePool.incrementPendingAcquires(BasicResourcePool.java:450) -incremented pending_acquires: 2
10:51:43.786 [mainhread] com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.<init>(BasicResourcePool.java:1788) -Starting acquisition series. Incremented pending_acquires [2],  attempts_remaining: 30
10:51:43.786 [mainhread] com.mchange.v2.async.ThreadPoolAsynchronousRunner.postRunnable(ThreadPoolAsynchronousRunner.java:236) -com.mchange.v2.async.ThreadPoolAsynchronousRunner@c39f790: Adding task to queue -- com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@50cbc42f
10:51:43.786 [mainhread] com.mchange.v2.resourcepool.BasicResourcePool.incrementPendingAcquires(BasicResourcePool.java:450) -incremented pending_acquires: 3
10:51:43.786 [mainhread] com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.<init>(BasicResourcePool.java:1788) -Starting acquisition series. Incremented pending_acquires [3],  attempts_remaining: 30
10:51:43.787 [mainhread] com.mchange.v2.async.ThreadPoolAsynchronousRunner.postRunnable(ThreadPoolAsynchronousRunner.java:236) -com.mchange.v2.async.ThreadPoolAsynchronousRunner@c39f790: Adding task to queue -- com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@f5f2bb7
10:51:43.787 [mainhread] com.mchange.v2.resourcepool.BasicResourcePool.incrementPendingAcquires(BasicResourcePool.java:450) -incremented pending_acquires: 4
10:51:43.787 [mainhread] com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.<init>(BasicResourcePool.java:1788) -Starting acquisition series. Incremented pending_acquires [4],  attempts_remaining: 30
10:51:43.788 [mainhread] com.mchange.v2.async.ThreadPoolAsynchronousRunner.postRunnable(ThreadPoolAsynchronousRunner.java:236) -com.mchange.v2.async.ThreadPoolAsynchronousRunner@c39f790: Adding task to queue -- com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@483bf400
10:51:43.788 [mainhread] com.mchange.v2.resourcepool.BasicResourcePool.incrementPendingAcquires(BasicResourcePool.java:450) -incremented pending_acquires: 5
10:51:43.788 [mainhread] com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.<init>(BasicResourcePool.java:1788) -Starting acquisition series. Incremented pending_acquires [5],  attempts_remaining: 30
10:51:43.788 [mainhread] com.mchange.v2.async.ThreadPoolAsynchronousRunner.postRunnable(ThreadPoolAsynchronousRunner.java:236) -com.mchange.v2.async.ThreadPoolAsynchronousRunner@c39f790: Adding task to queue -- com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@25618e91
10:51:43.789 [mainhread] com.mchange.v2.resourcepool.BasicResourcePool.incrementPendingAcquires(BasicResourcePool.java:450) -incremented pending_acquires: 6
10:51:43.789 [mainhread] com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.<init>(BasicResourcePool.java:1788) -Starting acquisition series. Incremented pending_acquires [6],  attempts_remaining: 30
10:51:43.789 [mainhread] com.mchange.v2.async.ThreadPoolAsynchronousRunner.postRunnable(ThreadPoolAsynchronousRunner.java:236) -com.mchange.v2.async.ThreadPoolAsynchronousRunner@c39f790: Adding task to queue -- com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@5474c6c
10:51:43.789 [mainhread] com.mchange.v2.resourcepool.BasicResourcePool.incrementPendingAcquires(BasicResourcePool.java:450) -incremented pending_acquires: 7
10:51:43.789 [mainhread] com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.<init>(BasicResourcePool.java:1788) -Starting acquisition series. Incremented pending_acquires [7],  attempts_remaining: 30
10:51:43.790 [mainhread] com.mchange.v2.async.ThreadPoolAsynchronousRunner.postRunnable(ThreadPoolAsynchronousRunner.java:236) -com.mchange.v2.async.ThreadPoolAsynchronousRunner@c39f790: Adding task to queue -- com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@66048bfd
10:51:43.790 [mainhread] com.mchange.v2.resourcepool.BasicResourcePool.incrementPendingAcquires(BasicResourcePool.java:450) -incremented pending_acquires: 8
10:51:43.790 [mainhread] com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.<init>(BasicResourcePool.java:1788) -Starting acquisition series. Incremented pending_acquires [8],  attempts_remaining: 30
10:51:43.790 [mainhread] com.mchange.v2.async.ThreadPoolAsynchronousRunner.postRunnable(ThreadPoolAsynchronousRunner.java:236) -com.mchange.v2.async.ThreadPoolAsynchronousRunner@c39f790: Adding task to queue -- com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@233c0b17
10:51:43.790 [mainhread] com.mchange.v2.resourcepool.BasicResourcePool.incrementPendingAcquires(BasicResourcePool.java:450) -incremented pending_acquires: 9
10:51:43.791 [mainhread] com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.<init>(BasicResourcePool.java:1788) -Starting acquisition series. Incremented pending_acquires [9],  attempts_remaining: 30
10:51:43.791 [mainhread] com.mchange.v2.async.ThreadPoolAsynchronousRunner.postRunnable(ThreadPoolAsynchronousRunner.java:236) -com.mchange.v2.async.ThreadPoolAsynchronousRunner@c39f790: Adding task to queue -- com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@7006c658
10:51:43.791 [mainhread] com.mchange.v2.resourcepool.BasicResourcePool.incrementPendingAcquires(BasicResourcePool.java:450) -incremented pending_acquires: 10
10:51:43.791 [mainhread] com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.<init>(BasicResourcePool.java:1788) -Starting acquisition series. Incremented pending_acquires [10],  attempts_remaining: 30
10:51:43.792 [mainhread] com.mchange.v2.async.ThreadPoolAsynchronousRunner.postRunnable(ThreadPoolAsynchronousRunner.java:236) -com.mchange.v2.async.ThreadPoolAsynchronousRunner@c39f790: Adding task to queue -- com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask@3aa9e816
10:51:43.792 [mainhread] com.mchange.v2.resourcepool.BasicResourcePool.<init>(BasicResourcePool.java:335) -com.mchange.v2.resourcepool.BasicResourcePool@3834d63f config: [start -> 3; min -> 10; max -> 5; inc -> 3; num_acq_attempts -> 30; acq_attempt_delay -> 1000; check_idle_resources_delay -> 0; mox_resource_age -> 0; max_idle_time -> 0; excess_max_idle_time -> 0; destroy_unreturned_resc_time -> 0; expiration_enforcement_delay -> 0; break_on_acquisition_failure -> false; debug_store_checkout_exceptions -> false]
10:51:43.792 [mainhread] com.mchange.v2.c3p0.impl.C3P0PooledConnectionPoolManager.getPool(C3P0PooledConnectionPoolManager.java:335) -Created new pool for auth, username (masked): 'ro******'.
10:51:43.792 [mainhread] com.mchange.v2.resourcepool.BasicResourcePool.prelimCheckoutResource(BasicResourcePool.java:587) -acquire test -- pool size: 0; target_pool_size: 10; desired target? 1
10:51:43.793 [mainhread] com.mchange.v2.resourcepool.BasicResourcePool.awaitAvailable(BasicResourcePool.java:1390) -awaitAvailable(): [unknown]
10:51:43.793 [mainhread] com.mchange.v2.resourcepool.BasicResourcePool.trace(BasicResourcePool.java:1747) -trace com.mchange.v2.resourcepool.BasicResourcePool@3834d63f [managed: 0, unused: 0, excluded: 0]
Wed Jul 11 10:51:43 MDT 2018 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
Wed Jul 11 10:51:43 MDT 2018 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
Wed Jul 11 10:51:43 MDT 2018 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
10:51:44.089 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#0hread] com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager.acquireResource(C3P0PooledConnectionPool.java:283) -com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager@236013ac.acquireResource() returning. 
10:51:44.089 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#1hread] com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager.acquireResource(C3P0PooledConnectionPool.java:283) -com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager@236013ac.acquireResource() returning. 
10:51:44.089 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#2hread] com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager.acquireResource(C3P0PooledConnectionPool.java:283) -com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager@236013ac.acquireResource() returning. 
10:51:44.090 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#0hread] com.mchange.v2.resourcepool.BasicResourcePool.trace(BasicResourcePool.java:1747) -trace com.mchange.v2.resourcepool.BasicResourcePool@3834d63f [managed: 1, unused: 1, excluded: 0]
Wed Jul 11 10:51:44 MDT 2018 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
10:51:44.090 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#0hread] com.mchange.v2.resourcepool.BasicResourcePool._decrementPendingAcquires(BasicResourcePool.java:471) -decremented pending_acquires: 9
Wed Jul 11 10:51:44 MDT 2018 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
10:51:44.090 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#0hread] com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.run(BasicResourcePool.java:1825) -Acquisition series terminated successfully. Decremented pending_acquires [9],  attempts_remaining: 30
10:51:44.090 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#2hread] com.mchange.v2.resourcepool.BasicResourcePool.trace(BasicResourcePool.java:1747) -trace com.mchange.v2.resourcepool.BasicResourcePool@3834d63f [managed: 2, unused: 2, excluded: 0] (e.g. com.mchange.v2.c3p0.impl.NewPooledConnection@1e85f691)
Wed Jul 11 10:51:44 MDT 2018 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
10:51:44.091 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#2hread] com.mchange.v2.resourcepool.BasicResourcePool._decrementPendingAcquires(BasicResourcePool.java:471) -decremented pending_acquires: 8
Wed Jul 11 10:51:44 MDT 2018 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
10:51:44.091 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#2hread] com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.run(BasicResourcePool.java:1825) -Acquisition series terminated successfully. Decremented pending_acquires [8],  attempts_remaining: 30
10:51:44.091 [mainhread] com.mchange.v2.resourcepool.BasicResourcePool.trace(BasicResourcePool.java:1747) -trace com.mchange.v2.resourcepool.BasicResourcePool@3834d63f [managed: 2, unused: 1, excluded: 0] (e.g. com.mchange.v2.c3p0.impl.NewPooledConnection@1e85f691)
Wed Jul 11 10:51:44 MDT 2018 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
10:51:44.092 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#1hread] com.mchange.v2.resourcepool.BasicResourcePool.trace(BasicResourcePool.java:1747) -trace com.mchange.v2.resourcepool.BasicResourcePool@3834d63f [managed: 3, unused: 2, excluded: 0] (e.g. com.mchange.v2.c3p0.impl.NewPooledConnection@1e85f691)
10:51:44.092 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#1hread] com.mchange.v2.resourcepool.BasicResourcePool._decrementPendingAcquires(BasicResourcePool.java:471) -decremented pending_acquires: 7
10:51:44.092 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#1hread] com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.run(BasicResourcePool.java:1825) -Acquisition series terminated successfully. Decremented pending_acquires [7],  attempts_remaining: 30
Wed Jul 11 10:51:44 MDT 2018 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
10:51:44.098 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#2hread] com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager.acquireResource(C3P0PooledConnectionPool.java:283) -com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager@236013ac.acquireResource() returning. 
10:51:44.098 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#2hread] com.mchange.v2.resourcepool.BasicResourcePool.trace(BasicResourcePool.java:1747) -trace com.mchange.v2.resourcepool.BasicResourcePool@3834d63f [managed: 4, unused: 3, excluded: 0] (e.g. com.mchange.v2.c3p0.impl.NewPooledConnection@1e85f691)
10:51:44.098 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#2hread] com.mchange.v2.resourcepool.BasicResourcePool._decrementPendingAcquires(BasicResourcePool.java:471) -decremented pending_acquires: 6
10:51:44.098 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#2hread] com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.run(BasicResourcePool.java:1825) -Acquisition series terminated successfully. Decremented pending_acquires [6],  attempts_remaining: 30
10:51:44.104 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#0hread] com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager.acquireResource(C3P0PooledConnectionPool.java:283) -com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager@236013ac.acquireResource() returning. 
Wed Jul 11 10:51:44 MDT 2018 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
10:51:44.104 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#0hread] com.mchange.v2.resourcepool.BasicResourcePool.trace(BasicResourcePool.java:1747) -trace com.mchange.v2.resourcepool.BasicResourcePool@3834d63f [managed: 5, unused: 4, excluded: 0] (e.g. com.mchange.v2.c3p0.impl.NewPooledConnection@1e85f691)
10:51:44.104 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#0hread] com.mchange.v2.resourcepool.BasicResourcePool._decrementPendingAcquires(BasicResourcePool.java:471) -decremented pending_acquires: 5
10:51:44.105 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#0hread] com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.run(BasicResourcePool.java:1825) -Acquisition series terminated successfully. Decremented pending_acquires [5],  attempts_remaining: 30
10:51:44.107 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#2hread] com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager.acquireResource(C3P0PooledConnectionPool.java:283) -com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager@236013ac.acquireResource() returning. 
10:51:44.108 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#2hread] com.mchange.v2.resourcepool.BasicResourcePool.trace(BasicResourcePool.java:1747) -trace com.mchange.v2.resourcepool.BasicResourcePool@3834d63f [managed: 6, unused: 5, excluded: 0] (e.g. com.mchange.v2.c3p0.impl.NewPooledConnection@1e85f691)
10:51:44.108 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#2hread] com.mchange.v2.resourcepool.BasicResourcePool._decrementPendingAcquires(BasicResourcePool.java:471) -decremented pending_acquires: 4
10:51:44.108 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#2hread] com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.run(BasicResourcePool.java:1825) -Acquisition series terminated successfully. Decremented pending_acquires [4],  attempts_remaining: 30
10:51:44.108 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#1hread] com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager.acquireResource(C3P0PooledConnectionPool.java:283) -com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager@236013ac.acquireResource() returning. 
10:51:44.108 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#1hread] com.mchange.v2.resourcepool.BasicResourcePool.trace(BasicResourcePool.java:1747) -trace com.mchange.v2.resourcepool.BasicResourcePool@3834d63f [managed: 7, unused: 6, excluded: 0] (e.g. com.mchange.v2.c3p0.impl.NewPooledConnection@1e85f691)
10:51:44.109 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#1hread] com.mchange.v2.resourcepool.BasicResourcePool._decrementPendingAcquires(BasicResourcePool.java:471) -decremented pending_acquires: 3
10:51:44.109 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#1hread] com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.run(BasicResourcePool.java:1825) -Acquisition series terminated successfully. Decremented pending_acquires [3],  attempts_remaining: 30
10:51:44.110 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#0hread] com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager.acquireResource(C3P0PooledConnectionPool.java:283) -com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager@236013ac.acquireResource() returning. 
10:51:44.110 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#0hread] com.mchange.v2.resourcepool.BasicResourcePool.trace(BasicResourcePool.java:1747) -trace com.mchange.v2.resourcepool.BasicResourcePool@3834d63f [managed: 8, unused: 7, excluded: 0] (e.g. com.mchange.v2.c3p0.impl.NewPooledConnection@1e85f691)
10:51:44.111 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#0hread] com.mchange.v2.resourcepool.BasicResourcePool._decrementPendingAcquires(BasicResourcePool.java:471) -decremented pending_acquires: 2
10:51:44.111 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#0hread] com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.run(BasicResourcePool.java:1825) -Acquisition series terminated successfully. Decremented pending_acquires [2],  attempts_remaining: 30
10:51:44.115 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#2hread] com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager.acquireResource(C3P0PooledConnectionPool.java:283) -com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager@236013ac.acquireResource() returning. 
10:51:44.123 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#1hread] com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager.acquireResource(C3P0PooledConnectionPool.java:283) -com.mchange.v2.c3p0.impl.C3P0PooledConnectionPool$1PooledConnectionResourcePoolManager@236013ac.acquireResource() returning. 
10:51:44.126 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#2hread] com.mchange.v2.resourcepool.BasicResourcePool.trace(BasicResourcePool.java:1747) -trace com.mchange.v2.resourcepool.BasicResourcePool@3834d63f [managed: 9, unused: 8, excluded: 0] (e.g. com.mchange.v2.c3p0.impl.NewPooledConnection@1e85f691)
10:51:44.126 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#2hread] com.mchange.v2.resourcepool.BasicResourcePool._decrementPendingAcquires(BasicResourcePool.java:471) -decremented pending_acquires: 1
10:51:44.126 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#2hread] com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.run(BasicResourcePool.java:1825) -Acquisition series terminated successfully. Decremented pending_acquires [1],  attempts_remaining: 30
10:51:44.126 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#1hread] com.mchange.v2.resourcepool.BasicResourcePool.trace(BasicResourcePool.java:1747) -trace com.mchange.v2.resourcepool.BasicResourcePool@3834d63f [managed: 10, unused: 9, excluded: 0] (e.g. com.mchange.v2.c3p0.impl.NewPooledConnection@1e85f691)
10:51:44.127 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#1hread] com.mchange.v2.resourcepool.BasicResourcePool._decrementPendingAcquires(BasicResourcePool.java:471) -decremented pending_acquires: 0
10:51:44.127 [C3P0PooledConnectionPoolManager[identityToken->2s77yk9w12czx9o42yx1e|4c3e4790]-HelperThread-#1hread] com.mchange.v2.resourcepool.BasicResourcePool$ScatteredAcquireTask.run(BasicResourcePool.java:1825) -Acquisition series terminated successfully. Decremented pending_acquires [0],  attempts_remaining: 30
10:51:44.136 [mainhread] com.fcpdsample.model.dao.jdbc.FleetRentalJDBCDaoImpl.buildAvailableRentals(FleetRentalJDBCDaoImpl.java:124) -Inside buildAvailableRentals
10:51:44.139 [mainhread] com.mchange.v2.c3p0.impl.NewProxyStatement.close(NewProxyStatement.java:858) -com.mchange.v2.c3p0.impl.NewProxyStatement@7a79be86 closed orphaned ResultSet: com.mchange.v2.c3p0.impl.NewProxyResultSet@6a5fc7f7
10:51:44.140 [mainhread] com.mchange.v2.async.ThreadPoolAsynchronousRunner.postRunnable(ThreadPoolAsynchronousRunner.java:236) -com.mchange.v2.async.ThreadPoolAsynchronousRunner@c39f790: Adding task to queue -- com.mchange.v2.resourcepool.BasicResourcePool$1RefurbishCheckinResourceTask@3f3afe78
10:51:44.141 [mainhread] com.mchange.v2.resourcepool.BasicResourcePool.trace(BasicResourcePool.java:1747) -trace com.mchange.v2.resourcepool.BasicResourcePool@3834d63f [managed: 10, unused: 9, excluded: 0] (e.g. com.mchange.v2.c3p0.impl.NewPooledConnection@1e85f691)
10:51:44.141 [mainhread] com.fcpdsample.driver.TestDriver.main(TestDriver.java:66) -
-->Cars available for above itinerary: 

Rental is available
State Tax: 6.89
Available Rentals List: 	

Car[		
carId = null		
manufacturer = Hyundai		
milesIncluded = Unlimited		
model = Accent		
rate = 23.5		
rented = N]
	

Car[		
carId = null		
manufacturer = Toyota		
milesIncluded = Unlimited		
model = Camry		
rate = 23.5		
rented = N]

10:51:44.143 [mainhread] com.fcpdsample.driver.TestDriver.main(TestDriver.java:76) -
-->Calling reserve rental car service with this details: 


Customer Info :

lastname :Simpson
firstname :Homer
email address :homer@duff.com
day time phone :303-786-1111
evening Phone :303-786-1111

Available Rentals :
Rental is available
State Tax: 6.89
Available Rentals List: 	

Car[		
carId = null		
manufacturer = Hyundai		
milesIncluded = Unlimited		
model = Accent		
rate = 23.5		
rented = N]
	

Car[		
carId = null		
manufacturer = Toyota		
milesIncluded = Unlimited		
model = Camry		
rate = 23.5		
rented = N]


Itinerary :
fleetRentalPickUp Id:null
fleetRentalPickUp City:San Francisco Airport
fleetRentalDropOff Id:null
fleetRentalDropOff City:San Francisco Airport
pickUpMonth :06
pickUpDay :18
pickUpYear :2006
pickUpTime :01:10
dropOffMonth :06
dropOffDay :28
dropOffYear :2006
dropOffTime :12:00
qtyRentalDays :10

Rented Car :
	

Car[		
carId = null		
manufacturer = Ford		
milesIncluded = Unlimited		
model = Focus		
rate = 25.5		
rented = null]

10:51:44.147 [mainhread] com.fcpdsample.model.dao.jdbc.FleetRentalJDBCDaoImpl.reserveRentalCar(FleetRentalJDBCDaoImpl.java:68) -
 Reservation Implementation not complete
*/