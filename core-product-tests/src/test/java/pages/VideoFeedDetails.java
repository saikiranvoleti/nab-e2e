package pages;

public class VideoFeedDetails {
    private String title;
    private String date;
    private String timeSpan;
    private String duration;

    public VideoFeedDetails(String title, String date, String timeSpan,String duration) {
        this.title = title;
        this.date = date;
        this.timeSpan = timeSpan;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getDuration() {
        return duration;
    }

    public String getTimeSpan() {
        return timeSpan;
    }


}


