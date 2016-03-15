package com.ostmodern.videodemo.data;

/**
 * Created by philip.arnold on 15/03/2016.
 */
public class Video {
    private int id;
    private String uid;
    private String title;
    private String body;
    private String quote;
    private String quoter;
    private String summary;
    private VideoImage[] images;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public String getQuote() {
        return quote;
    }
    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getQuoter() {
        return quoter;
    }
    public void setQuoter(String quoter) {
        this.quoter = quoter;
    }

    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public VideoImage[] getImages() {
        return images;
    }
    public void setImages(VideoImage[] images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Video{")
                .append("id=").append(id)
                .append(", uid='").append(uid).append("'")
                .append(", title='").append(title).append("'")
                .append(", body='").append(body).append("'")
                .append(", quote='").append(quote).append("'")
                .append(", quoter='").append(quoter).append("'")
                .append(", summary='").append(summary).append("'")
                .append('}').toString();
    }
}
