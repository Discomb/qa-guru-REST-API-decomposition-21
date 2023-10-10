package guru.qa.config;

@org.aeonbits.owner.Config.Sources({
        "classpath:${env}.properties"
})
public interface Config extends org.aeonbits.owner.Config{
    @Key("browser.name")
    String getBrowserName();

    @Key("browser.version")
    String getBrowserVersion();

    @Key("browser.size")
    String getBrowserSize();

    @Key("browser.baseUrl")
    String getBaseUrl();

    @Key("remote.url")
    @DefaultValue("")
    String getRemoteUrl();

    @Key("remote.auth")
    @DefaultValue("")
    String getRemoteAuth();
}
