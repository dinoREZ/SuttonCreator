public class Student extends AbstractModel {

    private String firstName;
    private String lastName;

    @InjectLink(
    style = InjectLink.Style.ABSOLUTE,
    value = "students/${instance.id}/courses",
    rel = "getCoursesOfStudents",
    title = "courses",
    condition = "true"
    )
    private Link courses;

    @InjectLink(
    style = InjectLink.Style.ABSOLUTE,
    value = "/courses/${instance.primaryId}/students/${instance.id}",
    rel = "self",
    title = "self",
    condition = "${instance.primaryId != 0}"
    )
    private Link selfLinkOnSecond;

    public Student() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonConverter(JsonServerLinkConverter.class)
    public Link getCourses() {
        return courses;
    }

    public void setCourses(Link courses) {
        this.courses = courses;
    }

    @JsonConverter(JsonServerLinkConverter.class)
    public Link getSelfLinkOnSecond() {
        return selfLinkOnSecond;
    }

    public void setSelfLinkOnSecond(Link selfLinkOnSecond) {
        this.selfLinkOnSecond = selfLinkOnSecond;
    }
}