package RSSFeed;

public class ReadTest {
    public static void main(String[] args) {
        RSSFeedParser parserORF = new RSSFeedParser("https://rss.orf.at/news.xml");
        Feed feedOrf = parserORF.readFeed();
        for (int i=0; i<= 5;i++) {
           	FeedMessage message = feedOrf.getMessages().get(i);
           	System.out.println("ORF: " + message.getTitle());
        }
        RSSFeedParser parserStand = new RSSFeedParser("http://derStandard.at/?page=rss&ressort=Seite1");
     	Feed feedStand = parserStand.readFeed();
        for (int i=1; i<= 6;i++) {
        	FeedMessage message = feedStand.getMessages().get(i);
        	System.out.println("DerStandard: " + message.getTitle());
        }
        RSSFeedParser parserGol = new RSSFeedParser("https://rss.golem.de/rss.php?feed=RSS2.0    ");
     	Feed feedGol = parserGol.readFeed();
        for (int i=1; i<= 6;i++) {
        	FeedMessage message = feedGol.getMessages().get(i);
        	System.out.println("Golem: " + message.getTitle());
        }
               
       
    }
}