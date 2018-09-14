package services;

public class BrowseService {

    private static final BrowseService instance = new BrowseService();

    private BrowseService() {}

    public static BrowseService getInstance() {
        return instance;
    }

}
