package monitoring;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import lombok.extern.slf4j.Slf4j;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

@Slf4j
public class JmxContextListener implements ServletContextListener {

    private final String hitCounterName = "monitoring:type=HitCounter";
    private final String statMissingName = "monitoring:type=StatMissing";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

            ObjectName hitCounterObjectName = new ObjectName(hitCounterName);
            HitCounter hitCounter = HitCounter.getInstance();
            mBeanServer.registerMBean(hitCounter, hitCounterObjectName);

            ObjectName statMissingObjectName = new ObjectName(statMissingName);
            StatMissing statMissing = StatMissing.getInstance();
            mBeanServer.registerMBean(statMissing, statMissingObjectName);

            MXBeanNotificationListener hitCounterListener = new MXBeanNotificationListener("multiplicity");
            mBeanServer.addNotificationListener(hitCounterObjectName, hitCounterListener, hitCounterListener.getNotificationFilter(), null);
        } catch (Exception exception) {
            log.error("Error registering MBean: " + exception);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

            ObjectName hitCounterObjectName = new ObjectName(hitCounterName);
            mBeanServer.unregisterMBean(hitCounterObjectName);

            ObjectName statMissingObjectName = new ObjectName(statMissingName);
            mBeanServer.unregisterMBean(statMissingObjectName);

        } catch (Exception exception) {
            log.error("Error registering MBean: " + exception);
        }
    }
}
