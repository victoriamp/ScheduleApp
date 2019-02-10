package schedule.app;

import java.util.Date;
import java.util.ArrayList;

public class Schedule {
    private Date day;
    private ArrayList<Category> cats;
    private ArrayList<Event> sorted;

    public Schedule(Date day){
        this.day = day;
        this.cats = new ArrayList<>();
        this.sorted = new ArrayList<>();
    }

    public void sort(ArrayList<Event> permanent) {
        sorted = permanent;
        int s = 0;
        boolean finished = false;

        for (Category c : cats) {
            c.split();
        }

        while (!finished) {
            int index = 0, length = 0;
            String first = "", last = "";

            for (int i = s; i < sorted.size() - 1; i++) {
                if (i + 1 == sorted.size()) {
                    length = 1440 - sorted.get(i).getStop();
                    index = i + 1;
                    first = sorted.get(i).getCategory();
                    last = "hello this is empty"; //dummy category
                    break;
                }

                if (sorted.get(i + 1).getStart() - sorted.get(i).getStop() > 5) {
                    length = sorted.get(i + 1).getStart() - sorted.get(i).getStop();
                    index = i + 1;
                    first = sorted.get(i).getCategory();
                    last = sorted.get(i + 1).getCategory();
                    break;
                }
            }

            boolean added = false;
            int prior = 4, cat = -1, event = -1, time = -1;

            for (int i = cats.size() - 1; i >= 0; i--) {
                Category c = cats.get(i);
                ArrayList<Event> events = c.getSplitEvents();

                if (!(c.getName()).equals(first) && events.size() != 0) {
                    for (int j = events.size() - 1; j > -1; j--) {
                        Event e = events.get(j);
                        if (c.getPriority() > prior) { break; }

                        if (e.getTime() > time) {
                            if ((c.getName()).equals(last)) {
                                if (e.getTime() < length - 15) {
                                    time = e.getTime();
                                    prior = c.getPriority();
                                    cat = i;
                                    event = j;
                                }
                            } else if (e.getTime() < length) {
                                time = e.getTime();
                                prior = c.getPriority();
                                cat = i;
                                event = j;
                            }
                        }
                    }
                }
            }

            if (prior != 4) {
                added = true;
                Category c = cats.get(cat);
                Event e = c.getSplitEvents().get(event);
                int start = sorted.get(index - 1).getStop() + 1;
                sorted.add(index, new Event(e.getName(), c.getName(), e.getTime(), start, start + time, e.getSplit()));
                c.getSplitEvents().remove(event);
            }

            if (!added) {
                s = index;
                if (index == sorted.size())
                    finished = true;
            }
        }
    }
}
