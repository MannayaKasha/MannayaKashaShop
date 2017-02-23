package org.mannayakasha.service;

/**
 *
 * @author Pavel
 * @version 1.0
 */

public interface ServiceFactory {

    Service getService(String serviceName);
}
