@Entity
@Table(name = "Students")
public class StudentDB extends AbstractDBModel {

@Column(name = "firstName")
private class name;

@Column(name = "lastName")
private class name;

public StudentDB() {

}

public String getFirstName() {
    return firstName;
}

public void setFirstName() {
    this.firstName = firstName;
}

public String getLastName() {
    return lastName;
}

public void setLastName() {
    this.lastName = lastName;
}

}