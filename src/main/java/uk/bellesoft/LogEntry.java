package uk.bellesoft;

class LogEntry {
    private String country;
    private long responseTime;

    public LogEntry(String nextLine) {
        String fields[] = nextLine.split(",",-1);
        responseTime = Integer.parseInt(fields[2]);
        country = fields[1];
    }

    public boolean fromCountry(String country) {
        return country.equals(this.country);
    }

    public static LogEntry fromString(String nextLine) {
        if (nextLine == null) return null;
        return new LogEntry(nextLine);
    }

    public boolean responseTimeGreaterThan(long limit) {
        return this.responseTime > limit;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public boolean responseTimeGreaterThan(double limit) {
        return this.responseTime > limit;
    }
}
