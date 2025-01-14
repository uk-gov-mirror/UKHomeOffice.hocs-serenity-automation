package config;

public enum Environment {

    LOCAL("http://localhost"),
    QA("https://qa.internal.cs-notprod.homeoffice.gov.uk/"),
    QAX("https://qax.internal.cs-notprod.homeoffice.gov.uk/"),
    DEV("https://dev.internal.cs-notprod.homeoffice.gov.uk/"),
    DEMO("https://demo.cs-notprod.homeoffice.gov.uk/"),
    MANAGEMENTUIQA("https://qa-management.internal.cs-notprod.homeoffice.gov.uk"),
    MANAGEMENTUIQAX("https://qax-management.internal.cs-notprod.homeoffice.gov.uk/"),
    MANAGEMENTUIDEV("http://dev-management.internal.cs-notprod.homeoffice.gov.uk/"),
    MANAGEMENTUIDEMO("http://demo-management.cs-notprod.homeoffice.gov.uk/");

    private final String hostname;

    Environment(String hostname) {
        this.hostname = hostname;
    }

    public String getEnvironmentURL() {
        return hostname;
    }

}
