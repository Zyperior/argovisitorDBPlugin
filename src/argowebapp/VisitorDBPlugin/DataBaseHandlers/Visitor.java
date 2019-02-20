package argowebapp.VisitorDBPlugin.DataBaseHandlers;


public class Visitor {

    int visitorNr;
    String name;
    String comment;

    public Visitor(int visitorNr, String name, String comment) {
        this.visitorNr = visitorNr;
        this.name = name;
        this.comment = comment;
    }

    public int getVisitorNr() {
        return visitorNr;
    }

    public void setVisitorNr(int visitorNr) {
        this.visitorNr = visitorNr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
