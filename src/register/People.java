package register;

import java.util.Date;

public class People {

    private String name;
    private String email;
    private String gender;
    private Date birthday;

    public People() {

    }

    public People(String name, String email, String gender, Date birthday) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthday;
    }

    public void setBirthDate(Date birthDate) {
        this.birthday = birthDate;
    }


}
