import java.util.*;
//https://stepik.org/lesson/90696/step/7?thread=solutions&unit=66924
class Main{
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        Student[] students = new Student[n];
        int[] sumOfScore = new int[3];
        TreeMap<Integer, School> schools = new TreeMap<>();
        for (int i=0; i<n; i++) {
            students[i] = new Student(sc.next(), sc.next(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
            sumOfScore[0] += students[i].getScoreOfMath();
            sumOfScore[1] += students[i].getScoreOfRus();
            sumOfScore[2] += students[i].getScoreOfIS();
            School schoolBuf = schools.getOrDefault(students[i].getNumOfSchool(), new School(students[i].getNumOfSchool()));
            schoolBuf.setSumScoreOfMath(schoolBuf.getSumScoreOfMath()+students[i].getScoreOfMath());
            schoolBuf.setSumScoreOfRus(schoolBuf.getSumScoreOfRus()+students[i].getScoreOfRus());
            schoolBuf.setSumScoreOfIS(schoolBuf.getSumScoreOfIS()+students[i].getScoreOfIS());
            schoolBuf.setSumScore(schoolBuf.getSumScore()+students[i].getScoreOfMath()+students[i].getScoreOfRus()+students[i].getScoreOfIS());
            schoolBuf.getStudentsOfSchool().add(students[i]);
            schools.put(students[i].getNumOfSchool(), schoolBuf);
        }
        sc.close();
        System.out.printf("Отчет по городу: математика - %.1f, русский язык - %.1f, инфрматика - %.1f, общий средний балл - %.1f\n",
                sumOfScore[0]*1.0/students.length,
                sumOfScore[1]*1.0/students.length,
                sumOfScore[2]*1.0/students.length,
                Arrays.stream(sumOfScore).sum()*1.0/students.length/3);

        School[] schoolsSort = schools.values().toArray(new School[0]);
        Arrays.sort(schoolsSort, Comparator.comparing((School o) -> o.getSumScore()/o.getStudentsOfSchool().size()).reversed()
                .thenComparing(o -> o.getSumScoreOfMath()/o.getStudentsOfSchool().size())
                .thenComparing(o -> o.getSumScoreOfRus()/o.getStudentsOfSchool().size())
                .thenComparing(o -> o.getSumScoreOfIS()/o.getStudentsOfSchool().size())
                .thenComparing((o1, o2)-> o2.getNumOfSchool()- o1.getNumOfSchool()));
        System.out.println("Отчет по школам:");
        for (School school : schoolsSort) {
            System.out.printf("Школа № %d: математика - %.1f, русский язык - %.1f, инфрматика - %.1f, общий средний балл - %.1f\n",
                    school.getNumOfSchool(),
                    school.getSumScoreOfMath() * 1.0 / school.getStudentsOfSchool().size(),
                    school.getSumScoreOfRus() * 1.0 / school.getStudentsOfSchool().size(),
                    school.getSumScoreOfIS() * 1.0 / school.getStudentsOfSchool().size(),
                    school.getSumScore() * 1.0 / 3 / school.getStudentsOfSchool().size());
        }

        Arrays.sort(students, Comparator.comparing(Student::getScoreOfMath).reversed());
        Student[] studentsBuf = Arrays.stream(students).filter(o -> o.getScoreOfMath()==students[0].getScoreOfMath()).toArray(Student[]::new);
        Arrays.sort(studentsBuf, Comparator.comparing(Student::getFirstName).thenComparing(Student::getSecondName));
        for (Student i :studentsBuf) {
            System.out.printf("Лучший результат по математике - %s %s - %d\n", i.getFirstName(), i.getSecondName(), i.getScoreOfMath());
        }

        Arrays.sort(students, Comparator.comparing(Student::getScoreOfRus).reversed());
        studentsBuf = Arrays.stream(students).filter(o -> o.getScoreOfRus()==students[0].getScoreOfRus()).toArray(Student[]::new);
        Arrays.sort(studentsBuf, Comparator.comparing(Student::getFirstName).thenComparing(Student::getSecondName));
        for (Student i :studentsBuf) {
            System.out.printf("Лучший результат по русскому языку - %s %s - %d\n", i.getFirstName(), i.getSecondName(), i.getScoreOfRus());
        }

        Arrays.sort(students, Comparator.comparing(Student::getScoreOfIS).reversed());
        studentsBuf = Arrays.stream(students).filter(o -> o.getScoreOfIS()==students[0].getScoreOfIS()).toArray(Student[]::new);
        Arrays.sort(studentsBuf, Comparator.comparing(Student::getFirstName).thenComparing(Student::getSecondName));
        for (Student i : studentsBuf) {
            System.out.printf("Лучший результат по информатике - %s %s - %d\n", i.getFirstName(), i.getSecondName(), i.getScoreOfIS());
        }
    }
}

class School {
    private final int numOfSchool;
    private int sumScoreOfMath;
    private int sumScoreOfRus;
    private int sumScoreOfIS;
    private int sumScore;
    private final ArrayList<Student> studentsOfSchool = new ArrayList<>();

    public ArrayList<Student> getStudentsOfSchool() {
        return studentsOfSchool;
    }

    public int getNumOfSchool() {
        return numOfSchool;
    }

    public int getSumScore() {
        return sumScore;
    }

    public void setSumScore(int sumScore) {
        this.sumScore = sumScore;
    }

    public School(int numOfSchool) {
        this.numOfSchool = numOfSchool;
    }

    public int getSumScoreOfMath() {
        return sumScoreOfMath;
    }

    public void setSumScoreOfMath(int sumScoreOfMath) {
        this.sumScoreOfMath = sumScoreOfMath;
    }

    public int getSumScoreOfRus() {
        return sumScoreOfRus;
    }

    public void setSumScoreOfRus(int sumScoreOfRus) {
        this.sumScoreOfRus = sumScoreOfRus;
    }

    public int getSumScoreOfIS() {
        return sumScoreOfIS;
    }

    public void setSumScoreOfIS(int sumScoreOfIS) {
        this.sumScoreOfIS = sumScoreOfIS;
    }
}


class Student {
    private final String firstName;
    private final String secondName;
    private final int numOfSchool;
    private final int scoreOfMath;
    private final int scoreOfRus;
    private final int scoreOfIS;

    public Student(String firstName, String secondName, int numOfSchool, int scoreOfMath, int scoreOfRus, int scoreOfIS) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.numOfSchool = numOfSchool;
        this.scoreOfMath = scoreOfMath;
        this.scoreOfRus = scoreOfRus;
        this.scoreOfIS = scoreOfIS;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getScoreOfMath() {
        return scoreOfMath;
    }

    public int getScoreOfRus() {
        return scoreOfRus;
    }

    public int getScoreOfIS() {
        return scoreOfIS;
    }

    public int getNumOfSchool() {
        return numOfSchool;
    }
}

