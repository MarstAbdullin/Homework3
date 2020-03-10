import java.util.ArrayList;
import java.util.List;

public class Page {
    private String pageName;
    private List<Page> links;

    public Page(String pageName) {
        this.pageName = pageName;
        links = new ArrayList<Page>();
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public List<Page> getLinks() {
        return links;
    }

    public void setLinks(List<Page> links) {
        this.links = links;
    }
}
