package de.swingbe.time_sheet.model;

import java.util.*;

public class TimeSheet {
    private final List<TimeSheetEntry> timeSheet;

    public TimeSheet() {
        this.timeSheet = new ArrayList<>();
    }

    public int size() {
        return timeSheet.size();
    }

    public void clear() {
        timeSheet.clear();
    }

    public void add(TimeSheetEntry timeSheetEntry) {
        timeSheet.add(timeSheetEntry);
    }

    public TimeSheetEntry get(int index) {
        return timeSheet.get(index);
    }

    public List<TimeSheetEntry> getTimeSheet() {
        return timeSheet;
    }
}
