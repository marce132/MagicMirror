package Weather;

public class Weather
{
    private String timezone;

    private Currently currently;

    private String longitude;

    private String latitude;

    private String offset;

    private Daily daily;

    public String getTimezone ()
    {
        return timezone;
    }

    public void setTimezone (String timezone)
    {
        this.timezone = timezone;
    }

    public Currently getCurrently ()
    {
        return currently;
    }

    public void setCurrently (Currently currently)
    {
        this.currently = currently;
    }

    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    public String getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }

    public String getOffset ()
    {
        return offset;
    }

    public void setOffset (String offset)
    {
        this.offset = offset;
    }

    public Daily getDaily ()
    {
        return daily;
    }

    public void setDaily (Daily daily)
    {
        this.daily = daily;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [timezone = "+timezone+", currently = "+currently+", longitude = "+longitude+", latitude = "+latitude+", offset = "+offset+", daily = "+daily+"]";
    }
}
