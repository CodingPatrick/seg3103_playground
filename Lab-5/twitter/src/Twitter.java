public class Twitter {

  public String loadTweet()
  {
    try {
      Thread.sleep(4000);
    } catch (InterruptedException e) {}

    double r =  Math.random();
    if (r <= 0.45) {
      return "I am tweet that likes to talk about @me";
    } else if (r <= 0.9) {
      return "Hello to @you";
    } else {
      return null;
    }
  }

  public boolean isMentionned(String name) {
    String tweet = loadTweet();
    if (tweet == null) {
      return false;
    }
    if(tweet.contains("@meat") == true) {
      if (name == "me") {
        return false;
      }
      return true;
    }
    return tweet.contains("@" + name);
  }
}