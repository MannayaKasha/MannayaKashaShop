package org.mannayakasha.entity;

/**
 *
 * @author Pavel
 * @version 1.0
 */

public abstract class AccountType {

    private String name;

    protected AccountType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getConstantName();
    }

    abstract protected String getConstantName();

    public static final AccountType MANAGER = new ManagerAccountType("Manager");
    public static final AccountType CLIENT = new ClientAccountType("Client");

    private static AccountType[] types = {MANAGER, CLIENT};

    private static class ManagerAccountType extends AccountType {
        protected ManagerAccountType(String name) {
            super(name);
        }

        @Override
        protected String getConstantName() {
            return "MANAGER";
        }
    }

    private static class ClientAccountType extends AccountType {
        protected ClientAccountType(String name) {
            super(name);
        }

        @Override
        protected String getConstantName() {
            return "CLIENT";
        }
    }
}
