package edu.uoc.uocvirtualclass.model;

public class DataSourceFactory {

    public enum DataSourceType {
        FIREBASE
    }

    public static DataSource getDataSource(DataSourceType type) {
        if (type == null) return null;

        switch (type) {
            case FIREBASE:
                return DataSourceFirebase.getInstance();
            default:
                throw new IllegalArgumentException("Unknown data source type: " + type);
        }
    }
}
