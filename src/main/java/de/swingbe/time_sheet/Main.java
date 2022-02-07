package de.swingbe.time_sheet;

import de.swingbe.time_sheet.controller.ProjectListCreator;
import de.swingbe.time_sheet.controller.TimeSheetParser;
import de.swingbe.time_sheet.model.TimeSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static de.swingbe.time_sheet.controller.UserInputController.control;
import static de.swingbe.time_sheet.controller.UserInputController.timeSheetFileName;

public class Main {

    public final static Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        if (!control(args)) {
            LOG.error("user input error");
            return;
        }
        LOG.debug("parse time sheet");
        TimeSheet timeSheet = TimeSheetParser.parse(timeSheetFileName);
        LOG.debug("timeSheet size: " + timeSheet.size());

        ProjectListCreator.create(timeSheet);
    }
}
