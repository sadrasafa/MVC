package selab.mvc.models.entities;

import selab.mvc.models.Model;

import java.util.HashMap;
import java.util.regex.Pattern;

public class Student implements Model {
    private String name;
    private String studentNo;
    private HashMap<String, String> coursePoints = new HashMap<>();

    @Override
    public String getPrimaryKey() {
        return this.studentNo;
    }

    public void setName(String value) { this.name = value; }
    public String getName() { return this.name; }

    public void setStudentNo(String value) {
        if (!validateStudentNo(value))
            throw new IllegalArgumentException("The format is not correct");

        this.studentNo = value;
    }
    public String getStudentNo() { return this.studentNo; }

    public float getAverage() {
        // TODO: Calculate and return the average of the points
        float sum = 0.0f;
        if (coursePoints.values().size() == 0)
            return 0;
        for (String points: coursePoints.values()) {
            sum += (Float.parseFloat(points));
        }
        return sum/coursePoints.values().size();
    }

    public String getCourses() {
        // TODO: Return a comma separated list of course names
        String names = "";
        if (coursePoints.keySet().size() == 0)
            return "-";
        for (String courseName: coursePoints.keySet()) {
            names += (courseName+",");
        }
        return names.substring(0,names.length()-1);
    }

    /**
     *
     * @param studentNo Student number to be checeked
     * @return true, if the format of the student number is correct
     */
    private boolean validateStudentNo(String studentNo) {
        Pattern pattern = Pattern.compile("^[8-9]\\d{7}$");
        return pattern.matcher(studentNo).find();
    }

    public void addCourse(Course course, String points) {
        coursePoints.put(course.getTitle(), points);
    }
    public void removeCourse(Course course) {
        coursePoints.remove(course.getTitle());
    }
}
