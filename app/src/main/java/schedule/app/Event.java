package schedule.app;

public class Event {
    private int time;
    private int start;
    private int stop;
    private String name;
    private String category;
    private boolean split;
    private boolean permanent;

    public Event(String name, String category, int time, boolean split) {
        this.time = time;
        this.start = 0;
        this.stop = 0;
        this.name = name;
        this.category = category;
        this.split = split;
        permanent = false;
    }

    public Event(String name, String category, int time, int start, int stop, boolean split) {
        this.time = time;
        this.start = start;
        this.stop = stop;
        this.name = name;
        this.category = category;
        this.split = split;
        permanent = false;
    }

    public Event(String name, String category, int start, int stop, boolean permanent) {
        this.time = stop - start;
        this.start = start;
        this.stop = stop;
        this.name = name;
        this.category = category;
        this.split = false;
        this.permanent = permanent;
    }

    public int getTime() { return time; }
    public int getStart() { return start; }
    public int getStop() { return stop; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public boolean getSplit() { return split; }

    public void setStart(int start) { this.start = start; }
    public void setStop(int stop) { this.stop = stop; }
}
