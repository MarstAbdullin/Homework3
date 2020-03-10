import java.util.Stack;

public class History {
    Browser browser;

    public History(Browser browser) {
        this.browser = browser;
        this.back = new Stack<Browser.Momento>();
        this.forward = new Stack<Browser.Momento>();
    }

    Stack<Browser.Momento> back;
    Stack<Browser.Momento> forward;

    public void back() {
        back.push(browser.save());
        browser.restore(forward.pop());
    }

    public void forward() {
        if (!back.empty()) {
            forward.push(browser.save());
            browser.restore(back.pop());
        }
    }

    public void goTo(Page page) {
        back = new Stack<Browser.Momento>();
        forward.push(browser.save());
        browser.restore(new Browser.Momento(page));
    }

    public void linkTo(Page page) {
        Integer index = browser.getCurrentPage().getLinks().indexOf(page);
        if (index != -1){
            back = new Stack<Browser.Momento>();
            forward.push(browser.save());
            browser.restore(new Browser.Momento(page));
        }
    }
}
