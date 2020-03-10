import java.util.List;

public class CommandDispatcher {

    private History history;
    private Browser browser;
    private List<Page> pages;

    public CommandDispatcher(History history, List<Page> pages, Browser browser) {
        this.history = history;
        this.browser = browser;
        this.pages = pages;
    }

    public void dispatch(String command){
        if (command.substring(0, 1).equals("goto")){
            if (findPage(command.substring(1, 2))!= null)
            history.goTo(findPage(command.substring(1, 2)));
        }
        if (command.substring(0, 1).equals("link")){
            if (findPage(command.substring(1, 2))!= null)
                history.linkTo(findPage(command.substring(1, 2)));
        }
        if (command.substring(0, 1).equals("back")){
            if (findPage(command.substring(1, 2))!= null)
                history.back();
        }
        if (command.substring(0, 1).equals("forward")){
            if (findPage(command.substring(1, 2))!= null)
                history.forward();
        }
    }

    private Page findPage(String pageName){
        for (int i = 0; i < pages.size(); i++) {
            if(pageName.equals(pages.get(i).getPageName())){
                return pages.get(i);
            }
        }
        return null;
    }
}
