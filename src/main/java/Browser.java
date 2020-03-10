import java.util.List;

public class Browser {
    private Page currentPage;

    public Browser(Page currentPage) {
        this.currentPage = currentPage;
    }

    public static class Momento {
        private Page currentPage;

        Momento(Page currentPage) {
            this.currentPage = currentPage;
        }

        Page getCurrentPage() {
            return currentPage;
        }
    }

    public Momento save(){
        return new Momento(currentPage);
    }

    public void restore(Momento momento){
        currentPage = momento.getCurrentPage();
    }

    public Page getCurrentPage() {
        return currentPage;
    }
}
