package objects;

public class CreateUserData {
    private String userType;
    private String userId;
    private String name;
    private String email;
    private String orgUserId;
    private String orgType;
    private String csioId;
    private String carrier;
    private String brokerage;
    private String file;
    private String modules;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrgUserId() {
        return orgUserId;
    }

    public void setOrgUserId(String orgUserId) {
        this.orgUserId = orgUserId;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getCsioId() {
        return csioId;
    }

    public void setCsioId(String csioId) {
        this.csioId = csioId;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(String brokerage) {
        this.brokerage = brokerage;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getModules() {
        return modules;
    }

    public void setModules(String modules) {
        this.modules = modules;
    }

    public CreateUserData(String userType, String userId, String name, String email, String orgUserId, String orgType, String csioId, String carrier, String brokerage, String file, String modules) {
        this.userType = userType;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.orgUserId = orgUserId;
        this.orgType = orgType;
        this.csioId = csioId;
        this.carrier = carrier;
        this.brokerage = brokerage;
        this.file = file;
        this.modules = modules;
    }

}
