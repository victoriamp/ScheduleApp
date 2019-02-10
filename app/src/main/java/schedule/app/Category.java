package schedule.app;

import java.util.ArrayList;

public class Category {
    private int time;
    private int priority;
    private String name;
    private ArrayList events = new ArrayList();
    private ArrayList splitEvents = new ArrayList();

    public Category(int time, int priority, String name) {
        this.time = time;
        this.priority = priority;
        this.name = name;
    }

    public void split() {
        splitEvents = new ArrayList();

        for (int i = events.size() - 1; i > -1; i--) {
            Event e = (Event) events.get(i);
            int n = 1, t;

            if (e.getSplit()) {
                if (e.getTime() % time > time / 2)
                    n = (int) Math.ceil(e.getTime() / (double) time);
                else
                    n = e.getTime() / time;

                t = e.getTime() / n;
            } else {
                t = e.getTime();
            }

            while (n > 0) {
                if (splitEvents.size() == 0) {
                    splitEvents.add(new Event(e.getName(), e.getCategory(), t, e.getSplit()));
                    break;
                }

                for (int j = splitEvents.size() - 1; j > -1; j--) {
                    if (((Event) splitEvents.get(j)).getTime() <= t) {
                        splitEvents.add(j, new Event(e.getName(), e.getCategory(), t, e.getSplit()));
                        break;
                    }
                }

                n--;
            }
        }
    }
}
