package config;

public enum User {

    DECS_USER("decs automated regression user", "Password1!", "DECS Regression (decsregression@test.com)"),
    UKVI_USER("ukvi automated regression user", "Password1!", "UKVI Regression (ukviregression@test.com)"),
    DCU_USER("dcu automated regression user", "Password1!", "DCU Regression (dcuregression@test.com)"),
    WCS_USER("wcs automated regression user", "Password1!", "WCS Regression (wcsregression@test.com)"),
    MPAM_SUPERUSER("mpam super user", "Password1!", "MPAM Super User (mpamsuperuser@test.com)"),
    CASEY("casey.prosser@ten10.com", "Password1!", "Casey Prosser (casey.prosser@ten10.com)"),
    CAMERON("cameron.page@ten10.com", "Password1!", "Cameron Page (cameron.page@ten10.com)"),
    FAKE("FakeUser", "FAKE1!", "");

    private final String username;

    private final String password;

    private final String allocationText;

    private User currentUser;

    User(String username, String password, String allocationText) {
        this.username = username;
        this.password = password;
        this.allocationText = allocationText;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAllocationText() {
        return allocationText;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }
}