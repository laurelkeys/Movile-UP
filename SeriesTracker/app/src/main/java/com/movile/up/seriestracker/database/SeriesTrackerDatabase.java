package com.movile.up.seriestracker.database;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = SeriesTrackerDatabase.NAME, version = SeriesTrackerDatabase.VERSION)
public class SeriesTrackerDatabase {

    public static final String NAME = "series_tracker_dbflow";
    public static final int VERSION = 1;

}