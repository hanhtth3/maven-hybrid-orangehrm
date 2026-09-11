package browserFactory.BrowserFactory;

public class BrowserNotSupportedException implements IllegalStateException{
    public BrowserNotSupportedException (String browserName){
        super(String.format("Browser '%s' not supported", browserName);
    }
}
