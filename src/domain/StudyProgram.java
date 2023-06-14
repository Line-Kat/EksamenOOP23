package domain;

public class StudyProgram {
    //fields
    private int idStudyProgram;
    private String nameProgram;
    private String programResponsible;

    //constructor
    public StudyProgram(int idStudyProgram, String nameProgram, String programResponsible) {
        this.idStudyProgram = idStudyProgram;
        this.nameProgram = nameProgram;
        this.programResponsible = programResponsible;
    }

    //getters and setters
    public int getIdStudyProgram() {
        return idStudyProgram;
    }

    public void setIdStudyProgram(int idStudyProgram) {
        this.idStudyProgram = idStudyProgram;
    }

    public String getNameProgram() {
        return nameProgram;
    }

    public void setNameProgram(String nameProgram) {
        this.nameProgram = nameProgram;
    }

    public String getProgramResponsible() {
        return programResponsible;
    }

    public void setProgramResponsible(String programResponsible) {
        this.programResponsible = programResponsible;
    }
}
